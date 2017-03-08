package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.entity.SupplierBusinessTable;
import com.cts.localtour.entity.SupplierScopeTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.BaseService;
@Component
public class SupplierInfoViewModel {
	private SupplierTable supplierTable;
	private String regionName;
	private ArrayList<String> supplierScopeNames;
	private ArrayList<Integer> supplierScopeIds;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	public SupplierTable getSupplierTable() {
		return supplierTable;
	}
	public void setSupplierTable(SupplierTable supplierTable) {
		this.supplierTable = supplierTable;
	}
	public ArrayList<String> getSupplierScopeNames() {
		return supplierScopeNames;
	}
	public void setSupplierScopeNames(ArrayList<String> supplierScopeNames2) {
		this.supplierScopeNames = supplierScopeNames2;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public ArrayList<Integer> getSupplierScopeIds() {
		return supplierScopeIds;
	}
	public void setSupplierScopeIds(ArrayList<Integer> supplierScopeIds) {
		this.supplierScopeIds = supplierScopeIds;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<SupplierInfoViewModel> getAllSupplierInfoViewModel(ArrayList<SupplierTable> suppliers){
		ArrayList<SupplierInfoViewModel> SupplierInfoViewModels = new ArrayList<SupplierInfoViewModel>();
		for (int i = 0; i < suppliers.size(); i++) {
			SupplierInfoViewModel supplierViewModel = new SupplierInfoViewModel();
			supplierViewModel.setSupplierTable(suppliers.get(i));
			String regionName = ((ArrayList<RegionTable>) baseService.getByWhere("RegionTable", "id", suppliers.get(i).getRegionId()+"")).get(0).getRegionName();
			supplierViewModel.setRegionName(regionName);
			ArrayList<String> supplierScopeNames = new ArrayList<String>();
			ArrayList<Integer> supplierScopeIds = new ArrayList<Integer>();
			ArrayList<SupplierBusinessTable> SupplierBusinesses = ((ArrayList<SupplierBusinessTable>) baseService.getByWhere("SupplierBusinessTable", "supplierId", suppliers.get(i).getId()+"","id"));
			for (int j = 0; j < SupplierBusinesses.size(); j++) {
				String supplierScopeName = ((ArrayList<SupplierScopeTable>) baseService.getByWhere("SupplierScopeTable", "id",  SupplierBusinesses.get(j).getSupplierScopeId()+"")).get(0).getSupplierScopeName();
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
