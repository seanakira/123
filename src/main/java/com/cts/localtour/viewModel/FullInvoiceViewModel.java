package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.service.CustomerAgencyService;

@Component
public class FullInvoiceViewModel {
	private ArrayList<InvoiceViewModel> invoices;
	private String customerAgencyName;
	@Autowired
	private InvoiceViewModel invoiceViewModel;
	@Autowired
	private CustomerAgencyService customerAgencyService;
	public ArrayList<InvoiceViewModel> getInvoices() {
		return invoices;
	}
	public void setInvoices(ArrayList<InvoiceViewModel> invoices) {
		this.invoices = invoices;
	}
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	
	public FullInvoiceViewModel getFullInvoiceViewModel(int tourId){
		FullInvoiceViewModel full = new FullInvoiceViewModel();
		full.setInvoices(invoiceViewModel.getAllInvoiceViewModel(tourId));
		full.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(tourId));
		return full;
	}
}
