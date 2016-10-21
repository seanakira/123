package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.service.BaseService;
@Component
public class IncomeViewModel {
	private IncomeTable incomeTable;
	private String customerAgencyName;
	private float invoiceAmount;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
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
	@SuppressWarnings("unchecked")
	public ArrayList<IncomeViewModel> getAllIncomeViewModel(int tourId){
		ArrayList<IncomeTable> incomeTables = (ArrayList<IncomeTable>) baseService.getAllByString("IncomeTable", "tourId=?", tourId);
		ArrayList<IncomeViewModel> incomes = new ArrayList<IncomeViewModel>();
		for (int i = 0; i < incomeTables.size(); i++) {
			IncomeViewModel income = new IncomeViewModel();
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
