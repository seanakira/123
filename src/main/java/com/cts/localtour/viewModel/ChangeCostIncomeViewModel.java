package com.cts.localtour.viewModel;

import java.util.ArrayList;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;

public class ChangeCostIncomeViewModel {
	private ArrayList<ChangeCostTable> costTables;
	private ArrayList<ChangeIncomeTable> incomeTables;
	private ArrayList<ChangeCostViewModel> costs;
	private ArrayList<ChangeIncomeViewModel> incomes;
	public ArrayList<ChangeCostTable> getCostTables() {
		return costTables;
	}
	public void setCostTables(ArrayList<ChangeCostTable> costTables) {
		this.costTables = costTables;
	}
	public ArrayList<ChangeIncomeTable> getIncomeTables() {
		return incomeTables;
	}
	public void setIncomeTables(ArrayList<ChangeIncomeTable> incomeTables) {
		this.incomeTables = incomeTables;
	}
	public ArrayList<ChangeCostViewModel> getCosts() {
		return costs;
	}
	public void setCosts(ArrayList<ChangeCostViewModel> costs) {
		this.costs = costs;
	}
	public ArrayList<ChangeIncomeViewModel> getIncomes() {
		return incomes;
	}
	public void setIncomes(ArrayList<ChangeIncomeViewModel> incomes) {
		this.incomes = incomes;
	}
}
