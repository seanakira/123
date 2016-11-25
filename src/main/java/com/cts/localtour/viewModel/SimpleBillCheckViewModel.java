package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.CostService;

@Component
public class SimpleBillCheckViewModel {
	private SupplierTable supplierTable;
	private float billSum;
	@Autowired
	private CostService costService;
	@Autowired
	private ChangeCostService changeCostService;
	public SupplierTable getSupplierTable() {
		return supplierTable;
	}
	public void setSupplierTable(SupplierTable supplierTable) {
		this.supplierTable = supplierTable;
	}
	public float getBillSum() {
		return billSum;
	}
	public void setBillSum(float billSum) {
		this.billSum = billSum;
	}
	public ArrayList<SimpleBillCheckViewModel> getAllSimpleBillCheckViewModel(ArrayList<SupplierTable> supplierTables){
		ArrayList<SimpleBillCheckViewModel> billCheckViewModels = new ArrayList<SimpleBillCheckViewModel>();
		for (SupplierTable supplierTable : supplierTables) {
			SimpleBillCheckViewModel billCheckViewModel = new SimpleBillCheckViewModel();
			billCheckViewModel.setSupplierTable(supplierTable);
			billCheckViewModel.setBillSum(costService.getBillSum(supplierTable.getId()).add(changeCostService.getBillSum(supplierTable.getId())).floatValue());
			billCheckViewModels.add(billCheckViewModel);
		}
		return billCheckViewModels;
	}
}
