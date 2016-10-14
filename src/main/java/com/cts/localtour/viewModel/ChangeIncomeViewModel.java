package com.cts.localtour.viewModel;

import com.cts.localtour.entity.ChangeIncomeTable;

public class ChangeIncomeViewModel {
	private ChangeIncomeTable incomeTable;
	private String customerAgencyName;
	private float invoiceAmount;
	private String applicationerRealName;
	private String status;
	public ChangeIncomeTable getIncomeTable() {
		return incomeTable;
	}
	public void setIncomeTable(ChangeIncomeTable incomeTable) {
		this.incomeTable = incomeTable;
	}
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	public float getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getApplicationerRealName() {
		return applicationerRealName;
	}
	public void setApplicationerRealName(String applicationerRealName) {
		this.applicationerRealName = applicationerRealName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
