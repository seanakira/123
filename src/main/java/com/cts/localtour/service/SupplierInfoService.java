package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.DAO.SupplierInfoDAO;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.entity.SupplierBusinessTable;
import com.cts.localtour.entity.SupplierScopeTable;
import com.cts.localtour.viewModel.SupplierInfoViewModel;

@SuppressWarnings("rawtypes")
@Service
public class SupplierInfoService extends BaseService{
	@Autowired
	private SupplierInfoDAO supplierInfoDAO;
	@SuppressWarnings("unchecked")
	public ArrayList<SupplierInfoViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<SupplierTable> suppliers = this.getAllByTableName("SupplierTable", page, maxResults);
			ArrayList<SupplierInfoViewModel> SupplierInfoViewModels = new ArrayList<SupplierInfoViewModel>();
			for (int i = 0; i < suppliers.size(); i++) {
				SupplierInfoViewModel supplierViewModel = new SupplierInfoViewModel();
				supplierViewModel.setSupplierTable(suppliers.get(i));
				String regionName = ((ArrayList<RegionTable>) this.getByWhere("RegionTable", "id", suppliers.get(i).getRegionId()+"")).get(0).getRegionName();
				supplierViewModel.setRegionName(regionName);
				ArrayList<String> supplierScopeNames = new ArrayList<String>();
				ArrayList<Integer> supplierScopeIds = new ArrayList<Integer>();
				ArrayList<SupplierBusinessTable> SupplierBusinesses = ((ArrayList<SupplierBusinessTable>) this.getByWhere("SupplierBusinessTable", "supplierId", suppliers.get(i).getId()+"","id"));
				for (int j = 0; j < SupplierBusinesses.size(); j++) {
					String supplierScopeName = ((ArrayList<SupplierScopeTable>) this.getByWhere("SupplierScopeTable", "id",  SupplierBusinesses.get(j).getSupplierScopeId()+"")).get(0).getSupplierScopeName();
					supplierScopeNames.add(supplierScopeName);
					supplierScopeIds.add(SupplierBusinesses.get(j).getSupplierScopeId());
				}
				supplierViewModel.setSupplierScopeNames(supplierScopeNames);
				supplierViewModel.setSupplierScopeIds(supplierScopeIds);
				SupplierInfoViewModels.add(supplierViewModel);
			}
			return SupplierInfoViewModels;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("supplierName", "%"+key+"%");
			ArrayList<SupplierTable> suppliers = this.getAllByParam("SupplierTable", "supplierName like :supplierName", param, page, maxResults);
			ArrayList<SupplierInfoViewModel> SupplierInfoViewModels = new ArrayList<SupplierInfoViewModel>();
			for (int i = 0; i < suppliers.size(); i++) {
				SupplierInfoViewModel supplierViewModel = new SupplierInfoViewModel();
				supplierViewModel.setSupplierTable(suppliers.get(i));
				String regionName = ((ArrayList<RegionTable>) this.getByWhere("RegionTable", "id", suppliers.get(i).getRegionId()+"")).get(0).getRegionName();
				supplierViewModel.setRegionName(regionName);
				ArrayList<String> supplierScopeNames = new ArrayList<String>();
				ArrayList<Integer> supplierScopeIds = new ArrayList<Integer>();
				ArrayList<SupplierBusinessTable> SupplierBusinesses = ((ArrayList<SupplierBusinessTable>) this.getByWhere("SupplierBusinessTable", "supplierId", suppliers.get(i).getId()+"","id"));
				for (int j = 0; j < SupplierBusinesses.size(); j++) {
					String supplierScopeName = ((ArrayList<SupplierScopeTable>) this.getByWhere("SupplierScopeTable", "id", SupplierBusinesses.get(j).getSupplierScopeId()+"")).get(0).getSupplierScopeName();
					supplierScopeNames.add(supplierScopeName);
					supplierScopeIds.add(SupplierBusinesses.get(j).getSupplierScopeId());
				}
				supplierViewModel.setSupplierScopeNames(supplierScopeNames);
				supplierViewModel.setSupplierScopeIds(supplierScopeIds);
				SupplierInfoViewModels.add(supplierViewModel);
			}
			return SupplierInfoViewModels;
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("SupplierTable");
		}else{
			String where = "supplierName like :supplierName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("supplierName", "%"+key+"%");
			return this.getCountsByParam("SupplierTable", where, param);
		}
	}

	public void del(int id) {
		this.changeValue("SupplierTable", "enable", "false", id);
	}
	
	public void recover(int id){
		this.changeValue("SupplierTable", "enable", "true", id);
	}

	public void update(SupplierTable supplier) {
		this.updateByString("SupplierTable", "supplierName=?,regionId=?,phone=?,accountPeriod=?,accountDate=?", "id="+supplier.getId(), supplier.getSupplierName(),supplier.getRegionId(),supplier.getPhone(),supplier.getAccountPeriod(),supplier.getAccountDate());
	}
	
	@SuppressWarnings("unchecked")
	public int add(SupplierTable supplier){
		try {
			return ((SupplierTable)supplierInfoDAO.add(supplier)).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public void addSupplierBusiness(int supplierId, String supplierScopeIds){
		try {
			String ids[] = supplierScopeIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				SupplierBusinessTable supplierBusinessTable = new SupplierBusinessTable();
				supplierBusinessTable.setSupplierId(supplierId);
				supplierBusinessTable.setSupplierScopeId(Integer.parseInt(ids[i]));
				this.add(supplierBusinessTable);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteSupplierBusiness(int supplierId) {
		this.deleteByString("SupplierBusinessTable", "supplierId=?", supplierId);
	}
	
	public String getSupplierName(int supplierId){
		return ((SupplierTable)this.getById("SupplierTable", supplierId)).getSupplierName();
	}
	
	public HashMap<String, Date> getSettlementDateFromTo(int supplierId){
		Date to = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		calendar.add(Calendar.MONTH, -3);
		Date from = calendar.getTime();
		SupplierTable supplierTable = (SupplierTable)this.getById("SupplierTable", supplierId);
		/*默认账期三个月*/
		int accountPeriod = supplierTable.getAccountPeriod()==null?3:supplierTable.getAccountPeriod();
		if((supplierTable.getAccountPeriod()!=null&&supplierTable.getAccountPeriod()!=0)&&supplierTable.getAccountDate()!=null){
			Calendar accountDate = Calendar.getInstance();
			accountDate.setTime(supplierTable.getAccountDate());
			Calendar now = Calendar.getInstance();
			now.setTime(to);
			int year = now.get(Calendar.YEAR)-accountDate.get(Calendar.YEAR);
			if(year<0){
				from = new Date(0);
				to = supplierTable.getAccountDate();
			}else{
				int month = year*12+now.get(Calendar.MONTH)-accountDate.get(Calendar.MONTH);
				int period = month/accountPeriod;
				if(period==0){
					
				}else{
					period = period+1;
				}
				now.setTime(supplierTable.getAccountDate());
				now.add(Calendar.MONTH, period*accountPeriod-accountPeriod);
				from = now.getTime();
				accountDate.setTime(supplierTable.getAccountDate());
				accountDate.add(Calendar.MONTH, period*accountPeriod);
				to = accountDate.getTime();
			}
		}
		HashMap<String, Date> fromTo = new HashMap<String, Date>();
		fromTo.put("from", from);
		fromTo.put("to", to);
		return fromTo;
	}
}
