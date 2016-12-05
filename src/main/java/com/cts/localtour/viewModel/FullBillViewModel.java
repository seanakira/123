package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;


@Component
public class FullBillViewModel {
	private ArrayList<BillCostViewModel> costs;
	private ArrayList<BillChangeCostViewModel> changeCosts;
	private ArrayList<CostTable> costTables;
	private ArrayList<ChangeCostTable> changeCostTables;
	@Autowired
	private BillCostViewModel costViewModel;
	@Autowired
	private BillChangeCostViewModel changeCostViewModel;
	public ArrayList<BillCostViewModel> getCosts() {
		return costs;
	}

	public void setCosts(ArrayList<BillCostViewModel> costs) {
		this.costs = costs;
	}

	public ArrayList<BillChangeCostViewModel> getChangeCosts() {
		return changeCosts;
	}

	public void setChangeCosts(ArrayList<BillChangeCostViewModel> changeCosts) {
		this.changeCosts = changeCosts;
	}
	public ArrayList<CostTable> getCostTables() {
		return costTables;
	}

	public void setCostTables(ArrayList<CostTable> costTables) {
		this.costTables = costTables;
	}
	public ArrayList<ChangeCostTable> getChangeCostTables() {
		return changeCostTables;
	}

	public void setChangeCostTables(ArrayList<ChangeCostTable> changeCostTables) {
		this.changeCostTables = changeCostTables;
	}

	public FullBillViewModel getFullBillCheckViewModel(int supplierId, int payStatus){
		FullBillViewModel billViewModel = new FullBillViewModel();
		billViewModel.setCosts(costViewModel.getAllBillViewModel(supplierId, payStatus));
		billViewModel.setChangeCosts(changeCostViewModel.getAllBillViewModel(supplierId, payStatus));
		return billViewModel;
	}

	public FullBillViewModel getFullBillCheckViewModel(int supplierId) {
		FullBillViewModel billViewModel = new FullBillViewModel();
		billViewModel.setCosts(costViewModel.getAllBillViewModel(supplierId));
		billViewModel.setChangeCosts(changeCostViewModel.getAllBillViewModel(supplierId));
		return billViewModel;
	}
}
