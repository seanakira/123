package com.cts.localtour.viewModel;

import com.cts.localtour.entity.IncomeTable;

public class IncomeViewModel {
	private IncomeTable incomeTable;
	private String customerAgencyName;
	private float invoiceAmount;
	public IncomeTable getIncomeTable() {
		return incomeTable;
	}
	public void setIncomeTable(IncomeTable incomeTable) {
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
}
