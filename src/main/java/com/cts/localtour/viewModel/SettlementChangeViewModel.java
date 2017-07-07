package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.ReimbursementIncomeTable;
@Component
public class SettlementChangeViewModel {
	private ArrayList<ReimbursementCostTable> costTables;
	private ArrayList<ReimbursementIncomeTable> incomeTables;
	private ArrayList<ReimbursementCostViewModel> costs;
	private ArrayList<ReimbursementIncomeViewModel> incomes;
	@Autowired
	private ReimbursementCostViewModel reimbursementCostViewModel;
	@Autowired
	private ReimbursementIncomeViewModel reimbursementIncomeViewModel;

	public ArrayList<ReimbursementCostTable> getCostTables() {
		return costTables;
	}

	public void setCostTables(ArrayList<ReimbursementCostTable> costTables) {
		this.costTables = costTables;
	}

	public ArrayList<ReimbursementIncomeTable> getIncomeTables() {
		return incomeTables;
	}

	public void setIncomeTables(ArrayList<ReimbursementIncomeTable> incomeTables) {
		this.incomeTables = incomeTables;
	}

	public ArrayList<ReimbursementCostViewModel> getCosts() {
		return costs;
	}

	public void setCosts(ArrayList<ReimbursementCostViewModel> costs) {
		this.costs = costs;
	}

	public ArrayList<ReimbursementIncomeViewModel> getIncomes() {
		return incomes;
	}

	public void setIncomes(ArrayList<ReimbursementIncomeViewModel> incomes) {
		this.incomes = incomes;
	}

	public SettlementChangeViewModel getAllSettlementChangeViewModel(int tourId) {
		SettlementChangeViewModel settlementChangeViewModel = new SettlementChangeViewModel();
		settlementChangeViewModel.setCosts(reimbursementCostViewModel.getAllReimbursementCostViewModel(tourId, 1, "财务结算调整"));
		settlementChangeViewModel.setIncomes(reimbursementIncomeViewModel.getAllIncomeViewModel(tourId, "财务结算调整"));
		return settlementChangeViewModel;
	}

}
