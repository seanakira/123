package com.cts.localtour.viewModel;

import com.cts.localtour.entity.LoanTable;

public class LoanViewModel {
	private LoanTable loanTable;
	private String lenderRealName;
	private String applicationerRealName;
	private String status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplicationerRealName() {
		return applicationerRealName;
	}
	public void setApplicationerRealName(String applicationerRealName) {
		this.applicationerRealName = applicationerRealName;
	}
}
