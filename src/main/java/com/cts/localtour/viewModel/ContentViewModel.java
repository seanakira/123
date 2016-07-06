package com.cts.localtour.viewModel;

import com.cts.localtour.entity.SupplierContentTable;

public class ContentViewModel {
	private String supplierScopeName;
	private SupplierContentTable contentTable;
	public String getSupplierScopeName() {
		return supplierScopeName;
	}
	public void setSupplierScopeName(String supplierScopeName) {
		this.supplierScopeName = supplierScopeName;
	}
	public SupplierContentTable getContentTable() {
		return contentTable;
	}
	public void setContentTable(SupplierContentTable contentTable) {
		this.contentTable = contentTable;
	}
	
}
