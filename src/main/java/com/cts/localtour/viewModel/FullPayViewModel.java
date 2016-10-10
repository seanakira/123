package com.cts.localtour.viewModel;

import java.util.ArrayList;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;

public class FullPayViewModel {
	private ArrayList<CostViewModel> costs;
	private ArrayList<LoanViewModel> loans;
	private ArrayList<CostTable> costTables;
	private ArrayList<LoanTable> loanTables;
	private ArrayList<ChangeCostViewModel> changeCosts;
	private ArrayList<ChangeIncomeViewModel> changeIncomes;
	private ArrayList<ChangeCostTable> changeCostTables;
	private ArrayList<ChangeIncomeTable> changeIncomeTables;
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
	public ArrayList<ChangeCostViewModel> getChangeCosts() {
		return changeCosts;
	}
	public void setChangeCosts(ArrayList<ChangeCostViewModel> changeCosts) {
		this.changeCosts = changeCosts;
	}
	public ArrayList<ChangeIncomeViewModel> getChangeIncomes() {
		return changeIncomes;
	}
	public void setChangeIncomes(ArrayList<ChangeIncomeViewModel> changeIncomes) {
		this.changeIncomes = changeIncomes;
	}
	public ArrayList<ChangeCostTable> getChangeCostTables() {
		return changeCostTables;
	}
	public void setChangeCostTables(ArrayList<ChangeCostTable> changeCostTables) {
		this.changeCostTables = changeCostTables;
	}
	public ArrayList<ChangeIncomeTable> getChangeIncomeTables() {
		return changeIncomeTables;
	}
	public void setChangeIncomeTables(ArrayList<ChangeIncomeTable> changeIncomeTables) {
		this.changeIncomeTables = changeIncomeTables;
	}
}
