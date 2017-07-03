package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.viewModel.CustomerAgencyViewModel;

@SuppressWarnings("rawtypes")
@Service
public class CustomerAgencyService extends BaseService{
	@SuppressWarnings("unchecked")
	public ArrayList<CustomerAgencyViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<CustomerAgencyTable> customerAgencys = this.getAllByTableName("CustomerAgencyTable", page, maxResults);
			ArrayList<CustomerAgencyViewModel> CustomerAgencyViewModels = new ArrayList<CustomerAgencyViewModel>();
			for (int i = 0; i < customerAgencys.size(); i++) {
				CustomerAgencyViewModel customerAgencyViewModel = new CustomerAgencyViewModel();
				customerAgencyViewModel.setCustomerAgencyTable(customerAgencys.get(i));
				RegionTable region = ((RegionTable) this.getById("RegionTable", customerAgencys.get(i).getRegionId()));
				customerAgencyViewModel.setRegionName(region==null?"":region.getRegionName());
				CustomerAgencyViewModels.add(customerAgencyViewModel);
			}
			return CustomerAgencyViewModels;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("customerAgencyName", "%"+key+"%");
			ArrayList<CustomerAgencyTable> customerAgencys = this.getAllByParam("CustomerAgencyTable", "customerAgencyName like :customerAgencyName", param, page, maxResults);
			ArrayList<CustomerAgencyViewModel> CustomerAgencyViewModels = new ArrayList<CustomerAgencyViewModel>();
			for (int i = 0; i < customerAgencys.size(); i++) {
				CustomerAgencyViewModel customerAgencyViewModel = new CustomerAgencyViewModel();
				customerAgencyViewModel.setCustomerAgencyTable(customerAgencys.get(i));
				String regionName = ((ArrayList<RegionTable>) this.getByWhere("RegionTable", "id", customerAgencys.get(i).getRegionId()+"")).get(0).getRegionName();
				customerAgencyViewModel.setRegionName(regionName);
				CustomerAgencyViewModels.add(customerAgencyViewModel);
			}
			return CustomerAgencyViewModels;
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("CustomerAgencyTable");
		}else{
			String where = "customerAgencyName like :customerAgencyName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("customerAgencyName", "%"+key+"%");
			return this.getCountsByParam("CustomerAgencyTable", where, param);
		}
	}

	public void del(int id) {
		this.changeValue("CustomerAgencyTable", "enable", "false", id);
	}
	
	public void recover(int id){
		this.changeValue("CustomerAgencyTable", "enable", "true", id);
	}

	public void update(CustomerAgencyTable customerAgency) {
		this.updateByString("CustomerAgencyTable", "customerAgencyName=?,regionId=?,phone=?,invoiceInfo=?", "id="+customerAgency.getId(), customerAgency.getCustomerAgencyName(),customerAgency.getRegionId(),customerAgency.getPhone(),customerAgency.getInvoiceInfo());
	}
	
	public String getCustomerAgencyName(int tourId){
		return ((CustomerAgencyTable)this.getById("CustomerAgencyTable", ((LocalTourTable)this.getById("LocalTourTable", tourId)).getCustomerAgencyId())).getCustomerAgencyName();
	}
}
