package com.cts.localtour.viewModel;

import com.cts.localtour.entity.LoanTable;

public class LoanViewModel {
	private LoanTable loanTable;
	private String lenderRealName;
	public LoanTable getLoanTable() {
		return loanTable;
	}
	public void setLoanTable(LoanTable loanTable) {
		this.loanTable = loanTable;
	}
	public String getLenderRealName() {
		return lenderRealName;
	}
	public void setLenderRealName(String lenderRealName) {
		this.lenderRealName = lenderRealName;
	}
	
}
