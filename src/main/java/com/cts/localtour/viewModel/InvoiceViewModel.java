package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.CustomerAgencyService;
import com.cts.localtour.service.UserService;

@Component
public class InvoiceViewModel {
	private InvoiceTable invoiceTable;
	private String issueUserRealName;
	private String customerAgencyName;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerAgencyService customerAgencyService;
	public InvoiceTable getInvoiceTable() {
		return invoiceTable;
	}
	public void setInvoiceTable(InvoiceTable invoiceTable) {
		this.invoiceTable = invoiceTable;
	}
	public String getIssueUserRealName() {
		return issueUserRealName;
	}
	public void setIssueUserRealName(String issueUserRealName) {
		this.issueUserRealName = issueUserRealName;
	}
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<InvoiceViewModel> getAllInvoiceViewModel(int tourId){
		ArrayList<InvoiceViewModel> invoiceViewModels = new ArrayList<InvoiceViewModel>();
		ArrayList<InvoiceTable> invoiceTables = (ArrayList<InvoiceTable>)baseService.getAllByString("InvoiceTable", "tourId=?", tourId);
		for (InvoiceTable invoiceTable : invoiceTables) {
			InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
			invoiceViewModel.setInvoiceTable(invoiceTable);
			invoiceViewModel.setIssueUserRealName(userService.getUserRealName(invoiceTable.getIssueUserId()));
			invoiceViewModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(tourId));
			invoiceViewModels.add(invoiceViewModel);
		}
		return invoiceViewModels;
	}
}
