package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LoanInvoiceTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.CustomerAgencyService;
import com.cts.localtour.service.UserService;

@Component
public class LoanInvoiceViewModel {
	private LoanInvoiceTable loanInvoiceTable;
	private String issueUserRealName;
	private String applicationerRealName;
	private String status;
	private String customerAgencyName;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerAgencyService customerAgencyService;
	public LoanInvoiceTable getLoanInvoiceTable() {
		return loanInvoiceTable;
	}
	public void setLoanInvoiceTable(LoanInvoiceTable loanInvoiceTable) {
		this.loanInvoiceTable = loanInvoiceTable;
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
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<LoanInvoiceViewModel> getAllLoanInvoiceViewModel(int tourId){
		ArrayList<LoanInvoiceViewModel> loanInvoiceViewModels = new ArrayList<LoanInvoiceViewModel>();
		ArrayList<LoanInvoiceTable> invoiceTables = (ArrayList<LoanInvoiceTable>) baseService.getAllByString("LoanInvoiceTable", "tourId=?", tourId);
		for (LoanInvoiceTable loanInvoiceTable : invoiceTables) {
			LoanInvoiceViewModel invoiceViewModel = new LoanInvoiceViewModel();
			invoiceViewModel.setLoanInvoiceTable(loanInvoiceTable);
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
	@SuppressWarnings("unchecked")
	public ArrayList<LoanInvoiceViewModel> getAllLoanInvoiceViewModel(int tourId, int status) {
		ArrayList<LoanInvoiceTable> invoiceTables;
		if(status==-1){
			invoiceTables = (ArrayList<LoanInvoiceTable>) baseService.getAllByString("LoanInvoiceTable", "tourId=? and status>1", tourId);
		}else{
			invoiceTables = (ArrayList<LoanInvoiceTable>) baseService.getAllByString("LoanInvoiceTable", "tourId=? and status=?", tourId, status);
		}
		ArrayList<LoanInvoiceViewModel> loanInvoiceViewModels = new ArrayList<LoanInvoiceViewModel>();
		for (LoanInvoiceTable loanInvoiceTable : invoiceTables) {
			LoanInvoiceViewModel invoiceViewModel = new LoanInvoiceViewModel();
			invoiceViewModel.setLoanInvoiceTable(loanInvoiceTable);
			invoiceViewModel.setApplicationerRealName(userService.getUserRealName(loanInvoiceTable.getApplicationerId()));
			invoiceViewModel.setIssueUserRealName(userService.getUserRealName(loanInvoiceTable.getIssueUserId()));
			invoiceViewModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(tourId));
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
