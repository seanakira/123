package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.service.CustomerAgencyService;

@Component
public class FullInvoiceViewModel {
	private ArrayList<InvoiceViewModel> invoices;
	private String customerAgencyName;
	private String invoiceInfo;
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
	public String getInvoiceInfo() {
		return invoiceInfo;
	}
	public void setInvoiceInfo(String invoiceInfo) {
		this.invoiceInfo = invoiceInfo;
	}
	public FullInvoiceViewModel getFullInvoiceViewModel(int tourId){
		FullInvoiceViewModel full = new FullInvoiceViewModel();
		full.setInvoices(invoiceViewModel.getAllInvoiceViewModel(tourId));
		CustomerAgencyTable customerAgencyTable = (CustomerAgencyTable)customerAgencyService.getById("CustomerAgencyTable", ((LocalTourTable)customerAgencyService.getById("LocalTourTable", tourId)).getCustomerAgencyId());
		full.setCustomerAgencyName(customerAgencyTable.getCustomerAgencyName());
		full.setInvoiceInfo(customerAgencyTable.getInvoiceInfo());
		return full;
	}
}
