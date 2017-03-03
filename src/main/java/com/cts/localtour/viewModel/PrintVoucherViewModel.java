package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;

@Component
public class PrintVoucherViewModel {
	private String deptName;
	private ArrayList<LoanViewModel> loans;
	private ArrayList<PrintPayVoucherViewModel> pays;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private LoanViewModel loanViewModel;
	@Autowired
	private PrintPayVoucherViewModel printPayVoucherViewModel;
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
	public PrintVoucherViewModel getPrintViewModel(int tourId, String type) {
		PrintVoucherViewModel payVoucherViewModel = new PrintVoucherViewModel();
		payVoucherViewModel.setDeptName(((DeptTable)baseService.getById("DeptTable", ((UserTable)SecurityUtils.getSubject().getPrincipal()).getDeptId())).getDeptName());
		if("lend".equals(type)){
			payVoucherViewModel.setLoans(loanViewModel.getPrintViewModel(tourId));
		}else if("pay".equals(type)){
			payVoucherViewModel.setPays(printPayVoucherViewModel.getPrintViewModel(tourId));
		}
		return payVoucherViewModel;
	}
	

}
