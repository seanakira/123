package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.ReimbursementCostTable;


@Component
public class FullBillViewModel {
	private ArrayList<BillCostViewModel> costs;
	private ArrayList<BillChangeCostViewModel> changeCosts;
	private ArrayList<BillReimbursementCostViewModel> reimbursementCosts;
	private ArrayList<CostTable> costTables;
	private ArrayList<ChangeCostTable> changeCostTables;
	private ArrayList<ReimbursementCostTable> reimbursementCostTables;
	@Autowired
	private BillCostViewModel costViewModel;
	@Autowired
	private BillChangeCostViewModel changeCostViewModel;
	@Autowired
	private BillReimbursementCostViewModel reimbursementCostViewModel;
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

	public ArrayList<BillReimbursementCostViewModel> getReimbursementCosts() {
		return reimbursementCosts;
	}

	public void setReimbursementCosts(ArrayList<BillReimbursementCostViewModel> reimbursementCosts) {
		this.reimbursementCosts = reimbursementCosts;
	}

	public ArrayList<ReimbursementCostTable> getReimbursementCostTables() {
		return reimbursementCostTables;
	}

	public void setReimbursementCostTables(ArrayList<ReimbursementCostTable> reimbursementCostTables) {
		this.reimbursementCostTables = reimbursementCostTables;
	}

	public FullBillViewModel getFullBillCheckViewModel(int supplierId, int payStatus, int relativePeriod){
		FullBillViewModel billViewModel = new FullBillViewModel();
		billViewModel.setCosts(costViewModel.getAllBillViewModel(supplierId, payStatus, relativePeriod));
		billViewModel.setChangeCosts(changeCostViewModel.getAllBillViewModel(supplierId, payStatus, relativePeriod));
		billViewModel.setReimbursementCosts(reimbursementCostViewModel.getAllBillViewModel(supplierId, payStatus, relativePeriod));
		return billViewModel;
	}

	public FullBillViewModel getFullBillCheckViewModel(int supplierId, int relativePeriod) {
		FullBillViewModel billViewModel = new FullBillViewModel();
		billViewModel.setCosts(costViewModel.getAllBillViewModel(supplierId, relativePeriod));
		billViewModel.setChangeCosts(changeCostViewModel.getAllBillViewModel(supplierId, relativePeriod));
		billViewModel.setReimbursementCosts(reimbursementCostViewModel.getAllBillViewModel(supplierId, relativePeriod));
		return billViewModel;
	}
}
