package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.IncomeService;

@Component
public class PrintVoucherViewModel {
	private String deptName;
	private ArrayList<LoanViewModel> loans;
	private String customerAgencyName;
	private LocalTourTable localTourTable;
	private float incomeTotal;
	private ArrayList<PrintPayVoucherViewModel> pays;
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
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
	}
	public float getIncomeTotal() {
		return incomeTotal;
	}
	public void setIncomeTotal(float incomeTotal) {
		this.incomeTotal = incomeTotal;
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
			payVoucherViewModel.setLocalTourTable(localTour);
			payVoucherViewModel.setCustomerAgencyName(((CustomerAgencyTable)baseService.getById("CustomerAgencyTable", localTour.getCustomerAgencyId())).getCustomerAgencyName());
			payVoucherViewModel.setIncomeTotal(incomeService.getIncomeInfo(tourId).getIncomeSum().add(changeIncomeService.getIncomeInfo(tourId).getIncomeSum()).floatValue());
		}
		return payVoucherViewModel;
	}
	

}
