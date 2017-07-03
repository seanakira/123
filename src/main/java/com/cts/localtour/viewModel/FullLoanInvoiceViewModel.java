package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.service.CustomerAgencyService;

@Component
public class FullLoanInvoiceViewModel {
	private String customerAgencyName;
	private String invoiceInfo;
	private ArrayList<LoanInvoiceViewModel> invoices;
	@Autowired
	private CustomerAgencyService customerAgencyService;
	@Autowired
	private LoanInvoiceViewModel loanInvoiceViewModel;
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	public ArrayList<LoanInvoiceViewModel> getInvoices() {
		return invoices;
	}
	public void setInvoices(ArrayList<LoanInvoiceViewModel> invoices) {
		this.invoices = invoices;
	}
	public String getInvoiceInfo() {
		return invoiceInfo;
	}
	public void setInvoiceInfo(String invoiceInfo) {
		this.invoiceInfo = invoiceInfo;
	}
	public FullLoanInvoiceViewModel getFullLoanInvoiceViewModel(int tourId){
		FullLoanInvoiceViewModel full = new FullLoanInvoiceViewModel();
		CustomerAgencyTable customerAgencyTable = (CustomerAgencyTable)customerAgencyService.getById("CustomerAgencyTable", ((LocalTourTable)customerAgencyService.getById("LocalTourTable", tourId)).getCustomerAgencyId());
		full.setCustomerAgencyName(customerAgencyTable.getCustomerAgencyName());
		full.setInvoiceInfo(customerAgencyTable.getInvoiceInfo()==null?"":customerAgencyTable.getInvoiceInfo().replaceAll("<br>", "\n"));
		full.setInvoices(loanInvoiceViewModel.getAllLoanInvoiceViewModel(tourId));
		return full;
	}
}
