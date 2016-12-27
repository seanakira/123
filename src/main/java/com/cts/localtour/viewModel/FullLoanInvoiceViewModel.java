package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.service.CustomerAgencyService;

@Component
public class FullLoanInvoiceViewModel {
	private String customerAgencyName;
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
	
	public FullLoanInvoiceViewModel getFullLoanInvoiceViewModel(int tourId){
		FullLoanInvoiceViewModel full = new FullLoanInvoiceViewModel();
		full.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(tourId));
		full.setInvoices(loanInvoiceViewModel.getAllLoanInvoiceViewModel(tourId));
		return full;
	}
}
