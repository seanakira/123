package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CustomerAgencyService;
import com.cts.localtour.service.DeptService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.InvoiceService;
import com.cts.localtour.service.LoanInvoiceService;
import com.cts.localtour.service.RefundService;
import com.cts.localtour.service.ReimbursementIncomeService;
import com.cts.localtour.service.UserService;

@Component
public class SimpleRevenueViewModel {
	private LocalTourTable localTourTable;
	private BigDecimal willIncome;
	private BigDecimal realIncome;
	private BigDecimal invoice;
	private String customerAgencyName;
	private String status;
	private String deptName;
	private String userRealName;
	@Autowired
	private UserService userService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private LoanInvoiceService loanInvoiceService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private CustomerAgencyService customerAgencyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private ReimbursementIncomeService reimbursementIncomeService;
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public BigDecimal getWillIncome() {
		return willIncome;
	}
	public void setWillIncome(BigDecimal willIncome) {
		this.willIncome = willIncome;
	}
	public BigDecimal getRealIncome() {
		return realIncome;
	}
	public void setRealIncome(BigDecimal realIncome) {
		this.realIncome = realIncome;
	}
	public BigDecimal getInvoice() {
		return invoice;
	}
	public void setInvoice(BigDecimal invoice) {
		this.invoice = invoice;
	}
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public ArrayList<SimpleRevenueViewModel> getAllSimpleRevenueViewModel(ArrayList<LocalTourTable> localTours){
		ArrayList<SimpleRevenueViewModel> simpleRevenueViewModels = new ArrayList<SimpleRevenueViewModel>();
		for (LocalTourTable localTour : localTours) {
			SimpleRevenueViewModel simpleRevenueViewModel = new SimpleRevenueViewModel();
			simpleRevenueViewModel.setLocalTourTable(localTour);
			simpleRevenueViewModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(localTour.getId()));
			simpleRevenueViewModel.setDeptName(deptService.getDeptName(localTour.getDeptId()));
			simpleRevenueViewModel.setUserRealName(userService.getUserRealName(localTour.getUserId()));
			simpleRevenueViewModel.setRealIncome(incomeService.getIncomeInfo(localTour.getId()).getRealIncomeSum().add(changeIncomeService.getIncomeInfo(localTour.getId()).getRealIncomeSum().subtract(refundService.getIncomeInfo(localTour.getId()).getRealIncomeSum())));
			simpleRevenueViewModel.setWillIncome(incomeService.getIncomeInfo(localTour.getId()).getIncomeSum().add(changeIncomeService.getIncomeInfo(localTour.getId()).getIncomeSum()).add(reimbursementIncomeService.getIncomeInfo(localTour.getId()).getIncomeSum()));
			simpleRevenueViewModel.setInvoice((invoiceService.getInvoiceSum(localTour.getId()).add(loanInvoiceService.getLoanInvoiceSum(localTour.getId()))));
			if(localTour.getStatus()==0){
				simpleRevenueViewModel.setStatus("�½�");
			}else if(localTour.getStatus()==1){
				simpleRevenueViewModel.setStatus("���ύ");
			}else if(localTour.getStatus()==2){
				simpleRevenueViewModel.setStatus("�ѱ���");
			}else if(localTour.getStatus()==3){
				simpleRevenueViewModel.setStatus("�ɽ��");
			}else if(localTour.getStatus()==4){
				simpleRevenueViewModel.setStatus("������");
			}else if(localTour.getStatus()==5){
				simpleRevenueViewModel.setStatus("�ѽ���");
			}else if(localTour.getStatus()==6){
				simpleRevenueViewModel.setStatus("������");
			}else if(localTour.getStatus()==7){
				simpleRevenueViewModel.setStatus("�ѱ���");
			}else if(localTour.getStatus()==8){
				simpleRevenueViewModel.setStatus("������");
			}else if(localTour.getStatus()==9){
				simpleRevenueViewModel.setStatus("������");
			}else if(localTour.getStatus()==10){
				simpleRevenueViewModel.setStatus("�ѽ���");
			}
			simpleRevenueViewModels.add(simpleRevenueViewModel);
		}
		return simpleRevenueViewModels;
	} 
}
