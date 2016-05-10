package com.cts.localtour.viewModel;

import com.cts.localtour.entity.ContentTable;

public class ContentViewModel {
	private String supplierScopeName;
	private ContentTable contentTable;
	public String getSupplierScopeName() {
		return supplierScopeName;
	}
	public void setSupplierScopeName(String supplierScopeName) {
		this.supplierScopeName = supplierScopeName;
	}
	public ContentTable getContentTable() {
		return contentTable;
	}
	public void setContentTable(ContentTable contentTable) {
		this.contentTable = contentTable;
	}
	
}
