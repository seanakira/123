package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.pojo.IncomeInfo;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.RefundService;
import com.cts.localtour.service.ReimbursementIncomeService;

@Component
public class PrintVoucherViewModel {
	private String deptName;
	private ArrayList<LoanViewModel> loans;
	private String customerAgencyName;
	private BigDecimal incomeTotal;
	private ArrayList<PrintPayVoucherViewModel> pays;
	private ArrayList<LoanInvoiceViewModel> loanInvoices;
	private ArrayList<RefundViewModel> refunds;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private LoanViewModel loanViewModel;
	@Autowired
	private PrintPayVoucherViewModel printPayVoucherViewModel;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@Autowired
	private ReimbursementIncomeService reimbursementIncomeService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private LoanInvoiceViewModel loanInvoiceViewModel;
	@Autowired
	private RefundViewModel refundViewModel;
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public ArrayList<LoanViewModel> getLoans() {
		return loans;
	}
	public void setLoans(ArrayList<LoanViewModel> loans) {
		this.loans = loans;
	}
	public ArrayList<PrintPayVoucherViewModel> getPays() {
		return pays;
	}
	public void setPays(ArrayList<PrintPayVoucherViewModel> pays) {
		this.pays = pays;
	}
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	public BigDecimal getIncomeTotal() {
		return incomeTotal;
	}
	public void setIncomeTotal(BigDecimal incomeTotal) {
		this.incomeTotal = incomeTotal;
	}
	public ArrayList<LoanInvoiceViewModel> getLoanInvoices() {
		return loanInvoices;
	}
	public void setLoanInvoices(ArrayList<LoanInvoiceViewModel> loanInvoices) {
		this.loanInvoices = loanInvoices;
	}
	public ArrayList<RefundViewModel> getRefunds() {
		return refunds;
	}
	public void setRefunds(ArrayList<RefundViewModel> refunds) {
		this.refunds = refunds;
	}
	public PrintVoucherViewModel getPrintViewModel(int tourId, String type) {
		PrintVoucherViewModel payVoucherViewModel = new PrintVoucherViewModel();
		payVoucherViewModel.setDeptName(((DeptTable)baseService.getById("DeptTable", ((UserTable)SecurityUtils.getSubject().getPrincipal()).getDeptId())).getDeptName());
		if("lend".equals(type)){
			payVoucherViewModel.setLoans(loanViewModel.getPrintViewModel(tourId));
		}else if("pay".equals(type)){
			payVoucherViewModel.setPays(printPayVoucherViewModel.getPrintViewModel(tourId));
		}else if("income".equals(type)){
			LocalTourTable localTour = (LocalTourTable)baseService.getById("LocalTourTable", tourId);
			payVoucherViewModel.setCustomerAgencyName(((CustomerAgencyTable)baseService.getById("CustomerAgencyTable", localTour.getCustomerAgencyId())).getCustomerAgencyName());
			IncomeInfo incomeInfo = incomeService.getIncomeInfo(tourId);
			IncomeInfo changeIncomeInfo = changeIncomeService.getIncomeInfo(tourId);
			IncomeInfo reimbursementIncomeInfo = reimbursementIncomeService.getIncomeInfo(tourId);
			payVoucherViewModel.setIncomeTotal(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).add(reimbursementIncomeInfo.getIncomeSum()).subtract(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).subtract(refundService.getIncomeInfo(tourId).getRealIncomeSum())));
		}else if("loanInvoice".equals(type)){
			payVoucherViewModel.setLoanInvoices(loanInvoiceViewModel.getAllLoanInvoiceViewModel(tourId, 2));
		}else if("refund".equals(type)){
			payVoucherViewModel.setRefunds(refundViewModel.getPrintViewModel(tourId));
		}
		return payVoucherViewModel;
	}
	

}
