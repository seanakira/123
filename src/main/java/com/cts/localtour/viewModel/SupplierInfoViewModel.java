package com.cts.localtour.viewModel;

import java.util.ArrayList;

import com.cts.localtour.entity.SupplierTable;

public class SupplierInfoViewModel {
	private SupplierTable supplierTable;
	private String regionName;
	private ArrayList<String> supplierScopeNames;
	private ArrayList<Integer> supplierScopeIds;
	
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
	
	
}
