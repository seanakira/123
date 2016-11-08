package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;
@Component
public class ChangeCostIncomeViewModel {
	private ArrayList<ChangeCostTable> costTables;
	private ArrayList<ChangeIncomeTable> incomeTables;
	private ArrayList<ChangeCostViewModel> costs;
	private ArrayList<ChangeIncomeViewModel> incomes;
	@Autowired
	private ChangeCostViewModel changeCostViewModel;
	@Autowired
	private ChangeIncomeViewModel changeIncomeViewModel;
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
	public ChangeCostIncomeViewModel getAllChangeCostIncomeViewModel(int tourId){
		ChangeCostIncomeViewModel changeCostIncomeViewModel = new ChangeCostIncomeViewModel();
		changeCostIncomeViewModel.setCosts(changeCostViewModel.getAllChangeCostViewModel(tourId));
		changeCostIncomeViewModel.setIncomes(changeIncomeViewModel.getAllChangeIncomeViewModel(tourId));
		return changeCostIncomeViewModel;
	}
	public ChangeCostIncomeViewModel getAllChangeCostIncomeViewModel(int tourId, int status){
		ChangeCostIncomeViewModel changeCostIncomeViewModel = new ChangeCostIncomeViewModel();
		changeCostIncomeViewModel.setCosts(changeCostViewModel.getAllChangeCostViewModel(tourId,status));
		changeCostIncomeViewModel.setIncomes(changeIncomeViewModel.getAllChangeIncomeViewModel(tourId,status));
		return changeCostIncomeViewModel;
	}
}
