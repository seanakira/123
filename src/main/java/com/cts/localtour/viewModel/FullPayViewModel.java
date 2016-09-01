package com.cts.localtour.viewModel;

import java.util.ArrayList;

import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;

public class FullPayViewModel {
	private ArrayList<CostViewModel> costs;
	private ArrayList<LoanViewModel> loans;
	private ArrayList<CostTable> costTables;
	private ArrayList<LoanTable> loanTables;
	public ArrayList<CostViewModel> getCosts() {
		return costs;
	}
	public void setCosts(ArrayList<CostViewModel> costs) {
		this.costs = costs;
	}
	public ArrayList<LoanViewModel> getLoans() {
		return loans;
	}
	public void setLoans(ArrayList<LoanViewModel> loans) {
		this.loans = loans;
	}
	public ArrayList<CostTable> getCostTables() {
		return costTables;
	}
	public void setCostTables(ArrayList<CostTable> costTables) {
		this.costTables = costTables;
	}
	public ArrayList<LoanTable> getLoanTables() {
		return loanTables;
	}
	public void setLoanTables(ArrayList<LoanTable> loanTables) {
		this.loanTables = loanTables;
	}
}
