package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LoanInvoiceTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;

@Component
public class LoanInvoiceViewModel {
	private LoanInvoiceTable invoiceTable;
	private String issueUserRealName;
	private String applicationerRealName;
	private String status;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private UserService userService;
	public LoanInvoiceTable getInvoiceTable() {
		return invoiceTable;
	}
	public void setInvoiceTable(LoanInvoiceTable invoiceTable) {
		this.invoiceTable = invoiceTable;
	}
	public String getIssueUserRealName() {
		return issueUserRealName;
	}
	public void setIssueUserRealName(String issueUserRealName) {
		this.issueUserRealName = issueUserRealName;
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
	public ArrayList<LoanInvoiceViewModel> getAllLoanInvoiceViewModel(int tourId){
		ArrayList<LoanInvoiceViewModel> loanInvoiceViewModels = new ArrayList<LoanInvoiceViewModel>();
		ArrayList<LoanInvoiceTable> invoiceTables = (ArrayList<LoanInvoiceTable>) baseService.getAllByString("LoanInvoiceTable", "tourId=?", tourId);
		for (LoanInvoiceTable loanInvoiceTable : invoiceTables) {
			LoanInvoiceViewModel invoiceViewModel = new LoanInvoiceViewModel();
			invoiceViewModel.setInvoiceTable(loanInvoiceTable);
			invoiceViewModel.setApplicationerRealName(userService.getUserRealName(loanInvoiceTable.getApplicationerId()));
			invoiceViewModel.setIssueUserRealName(userService.getUserRealName(loanInvoiceTable.getIssueUserId()));
			if(loanInvoiceTable.getStatus()==0){
				invoiceViewModel.setStatus("ÐÂ½¨");
			}else if(loanInvoiceTable.getStatus()==1){
				invoiceViewModel.setStatus("´ýÉóºË");
			}else if(loanInvoiceTable.getStatus()==2){
				invoiceViewModel.setStatus("ÒÑÉóºË");
			}
			loanInvoiceViewModels.add(invoiceViewModel);
		}
		return loanInvoiceViewModels;
	}
}
