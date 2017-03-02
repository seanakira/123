package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;

@Component
public class PayVoucherViewModel {
	private String deptName;
	private ArrayList<LoanViewModel> loans;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private LoanViewModel loanViewModel;
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
	public PayVoucherViewModel getPrintViewModel(int tourId) {
		PayVoucherViewModel payVoucherViewModel = new PayVoucherViewModel();
		payVoucherViewModel.setDeptName(((DeptTable)baseService.getById("DeptTable", ((UserTable)SecurityUtils.getSubject().getPrincipal()).getDeptId())).getDeptName());
		payVoucherViewModel.setLoans(loanViewModel.getPrintViewModel(tourId));
		return payVoucherViewModel;
	}

}
