package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;

@Component
public class InvoiceViewModel {
	private InvoiceTable invoiceTable;
	private String issueUserRealName;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private UserService userService;
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
	@SuppressWarnings("unchecked")
	public ArrayList<InvoiceViewModel> getAllInvoiceViewModel(int tourId){
		ArrayList<InvoiceViewModel> invoiceViewModels = new ArrayList<InvoiceViewModel>();
		ArrayList<InvoiceTable> invoiceTables = (ArrayList<InvoiceTable>)baseService.getAllByString("InvoiceTable", "tourId=?", tourId);
		for (InvoiceTable invoiceTable : invoiceTables) {
			InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
			invoiceViewModel.setInvoiceTable(invoiceTable);
			invoiceViewModel.setIssueUserRealName(userService.getUserRealName(invoiceTable.getIssueUserId()));
			invoiceViewModels.add(invoiceViewModel);
		}
		return invoiceViewModels;
	}
}
