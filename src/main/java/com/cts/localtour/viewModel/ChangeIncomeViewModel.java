package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.service.BaseService;
@Component
public class ChangeIncomeViewModel {
	private ChangeIncomeTable incomeTable;
	private String customerAgencyName;
	private float invoiceAmount;
	private String applicationerRealName;
	private String status;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
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
	@SuppressWarnings("unchecked")
	public ArrayList<ChangeIncomeViewModel> getAllChangeIncomeViewModel(int tourId){
		ArrayList<ChangeIncomeTable> incomeTables = (ArrayList<ChangeIncomeTable>) baseService.getAllByString("ChangeIncomeTable", "tourId=? and status<3", tourId);
		ArrayList<ChangeIncomeViewModel> incomes = new ArrayList<ChangeIncomeViewModel>();
		for (int i = 0; i < incomeTables.size(); i++) {
			ChangeIncomeViewModel income = new ChangeIncomeViewModel();
			income.setIncomeTable(incomeTables.get(i));
			income.setCustomerAgencyName(((CustomerAgencyTable)baseService.getById("CustomerAgencyTable", incomeTables.get(i).getCustomerAgencyId())).getCustomerAgencyName());
			BigDecimal invoiceAmount = new BigDecimal(0);
			ArrayList<InvoiceTable> invoiceTables =  (ArrayList<InvoiceTable>) baseService.getAllByString("InvoiceTable", "incomeId=?", incomeTables.get(i).getId());
			for (int j = 0; j < invoiceTables.size(); j++) {
				invoiceAmount = invoiceAmount.add(new BigDecimal(invoiceTables.get(i).getInvoiceAmount()));
			}
			income.setInvoiceAmount(invoiceAmount.floatValue());
			incomes.add(income);
		}
		return incomes;
	}
}
