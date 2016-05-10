package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.viewModel.CustomerAgencyViewModel;

@SuppressWarnings("rawtypes")
@Service
public class CustomerAgencyService extends BaseService{
	@SuppressWarnings("unchecked")
	public ArrayList<CustomerAgencyViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<CustomerAgencyTable> contents = this.getAllByTableName("CustomerAgencyTable", page, maxResults);
			ArrayList<CustomerAgencyViewModel> CustomerAgencyViewModels = new ArrayList<CustomerAgencyViewModel>();
			for (int i = 0; i < contents.size(); i++) {
				CustomerAgencyViewModel contentViewModel = new CustomerAgencyViewModel();
				contentViewModel.setCustomerAgencyTable(contents.get(i));
				String supplierScopeName = ((ArrayList<RegionTable>) this.getByWhere("RegionTable", "id", contents.get(i).getRegionId()+"")).get(0).getRegionName();
				contentViewModel.setRegionName(supplierScopeName);
				CustomerAgencyViewModels.add(contentViewModel);
			}
			return CustomerAgencyViewModels;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("contentName", "%"+key+"%");
			ArrayList<CustomerAgencyTable> contents = this.getAllByParam("CustomerAgencyTable", "contentName like :contentName", param, page, maxResults);
			ArrayList<CustomerAgencyViewModel> CustomerAgencyViewModels = new ArrayList<CustomerAgencyViewModel>();
			for (int i = 0; i < contents.size(); i++) {
				CustomerAgencyViewModel contentViewModel = new CustomerAgencyViewModel();
				contentViewModel.setCustomerAgencyTable(contents.get(i));
				String supplierScopeName = ((ArrayList<RegionTable>) this.getByWhere("RegionTable", "id", contents.get(i).getRegionId()+"")).get(0).getRegionName();
				contentViewModel.setRegionName(supplierScopeName);
				CustomerAgencyViewModels.add(contentViewModel);
			}
			return CustomerAgencyViewModels;
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("CustomerAgencyTable");
		}else{
			String where = "contentName like :contentName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("contentName", "%"+key+"%");
			return this.getCountsByParam("CustomerAgencyTable", where, param);
		}
	}

	public void del(int id) {
		this.changeEnable("CustomerAgencyTable", false, id);
	}
	
	public void recover(int id){
		this.changeEnable("CustomerAgencyTable", true, id);
	}

	public void update(CustomerAgencyTable content) {
		this.updateByParam("CustomerAgencyTable", "contentName=?,supplierScopeId=?", "id="+content.getId(), content.getCustomerAgencyName(),content.getRegionId());
	}
}
