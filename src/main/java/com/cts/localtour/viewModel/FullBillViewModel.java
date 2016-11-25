package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FullBillViewModel {
	private ArrayList<CostViewModel> costViewModels;
	private ArrayList<ChangeCostViewModel> changeCostViewModels;
	@Autowired
	private CostViewModel costViewModel;
	@Autowired
	private ChangeCostViewModel changeCostViewModel;
	public ArrayList<CostViewModel> getCostViewModels() {
		return costViewModels;
	}
	public void setCostViewModels(ArrayList<CostViewModel> costViewModels) {
		this.costViewModels = costViewModels;
	}
	public ArrayList<ChangeCostViewModel> getChangeCostViewModels() {
		return changeCostViewModels;
	}
	public void setChangeCostViewModels(ArrayList<ChangeCostViewModel> changeCostViewModels) {
		this.changeCostViewModels = changeCostViewModels;
	}
	public FullBillViewModel getFullBillCheckViewModel(int supplierId){
		FullBillViewModel billViewModel = new FullBillViewModel();
		billViewModel.setCostViewModels(costViewModel.getAllBillViewModel(supplierId));
		billViewModel.setChangeCostViewModels(changeCostViewModel.getAllBillViewModel(supplierId));
		return billViewModel;
	}
}
