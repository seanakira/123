package com.cts.localtour.viewModel;

import com.cts.localtour.entity.ChangeIncomeTable;

public class ChangeIncomeViewModel {
	private ChangeIncomeTable incomeTable;
	private String customerAgencyName;
	private float invoiceAmount;
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
}
