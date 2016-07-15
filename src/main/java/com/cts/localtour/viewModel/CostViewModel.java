package com.cts.localtour.viewModel;

import com.cts.localtour.entity.CostTable;

public class CostViewModel {
	private CostTable costTable;
	private String contentName;
	private String supplierName;
	private String borrowUserName;
	public CostTable getCostTable() {
		return costTable;
	}
	public void setCostTable(CostTable costTable) {
		this.costTable = costTable;
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
