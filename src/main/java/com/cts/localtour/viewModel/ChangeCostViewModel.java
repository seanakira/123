package com.cts.localtour.viewModel;

import com.cts.localtour.entity.ChangeCostTable;

public class ChangeCostViewModel {
	private ChangeCostTable costTable;
	private String contentName;
	private String supplierName;
	private String borrowUserName;
	public ChangeCostTable getCostTable() {
		return costTable;
	}
	public void setCostTable(ChangeCostTable changeCostTable) {
		this.costTable = changeCostTable;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getBorrowUserName() {
		return borrowUserName;
	}
	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}
	
}
