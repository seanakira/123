package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CustomerAgencyService;
import com.cts.localtour.service.DeptService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.InvoiceService;
import com.cts.localtour.service.LoanInvoiceService;
import com.cts.localtour.service.ReimbursementIncomeService;
import com.cts.localtour.service.UserService;
@Component
public class InvoiceStatisticViewModel {
	private String tourNo;
	private String tourName;
	private String customerAgencyName;
	private BigDecimal willIncomeSum;
	private BigDecimal allInvoiceSum;
	private String deptName;
	private String userRealName;
	@Autowired
	private DeptService deptService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private CustomerAgencyService customerAgencyService;
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
	private ReimbursementIncomeService reimbursementIncomeService;
	public String getTourNo() {
		return tourNo;
	}
	public void setTourNo(String tourNo) {
		this.tourNo = tourNo;
	}
	public String getTourName() {
		return tourName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	public BigDecimal getWillIncomeSum() {
		return willIncomeSum;
	}
	public void setWillIncomeSum(BigDecimal willIncomeSum) {
		this.willIncomeSum = willIncomeSum;
	}
	public BigDecimal getAllInvoiceSum() {
		return allInvoiceSum;
	}
	public void setAllInvoiceSum(BigDecimal allInvoiceSum) {
		this.allInvoiceSum = allInvoiceSum;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<InvoiceStatisticViewModel> getInvoiceStatisticViewModelAll(Date start, Date end, String deptIds, String tourNo) {
		ArrayList<InvoiceStatisticViewModel> invoiceStatisticViewModels = new ArrayList<InvoiceStatisticViewModel>();
		deptIds = deptService.getDownerDpetIds(deptIds);
		ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) baseService.getAllByString("LocalTourTable", "startTime between ? and ? and deptId in ("+deptIds+")"+("".equals(tourNo)?"":" and tourNo like '%"+tourNo+"%'"), start, end);
		for (LocalTourTable localTourTable : localTourTables) {
			InvoiceStatisticViewModel invoiceStatisticViewModel = new InvoiceStatisticViewModel();
			invoiceStatisticViewModel.setTourNo(localTourTable.getTourNo());
			invoiceStatisticViewModel.setTourName(localTourTable.getTourName());
			invoiceStatisticViewModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(localTourTable.getId()));
			invoiceStatisticViewModel.setDeptName(deptService.getDeptName(localTourTable.getDeptId()));
			invoiceStatisticViewModel.setUserRealName(userService.getUserRealName(localTourTable.getUserId()));
			invoiceStatisticViewModel.setAllInvoiceSum(invoiceService.getInvoiceSum(localTourTable.getId()).add(loanInvoiceService.getLoanInvoiceSum(localTourTable.getId())));
			invoiceStatisticViewModel.setWillIncomeSum(incomeService.getIncomeInfo(localTourTable.getId()).getIncomeSum().add(changeIncomeService.getIncomeInfo(localTourTable.getId()).getIncomeSum()).add(reimbursementIncomeService.getIncomeInfo(localTourTable.getId()).getIncomeSum()));
			invoiceStatisticViewModels.add(invoiceStatisticViewModel);
		}
		return invoiceStatisticViewModels;
	}
	
}
