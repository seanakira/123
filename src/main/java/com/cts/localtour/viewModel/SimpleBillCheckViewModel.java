package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.pojo.BillInfo;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.CostService;

@Component
public class SimpleBillCheckViewModel {
	private SupplierTable supplierTable;
	private String settlementDate;
	private float billSum;
	private float applicationSum;
	private float willRemittanceSum;
	private float remittancedSum;
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
	public float getApplicationSum() {
		return applicationSum;
	}
	public void setApplicationSum(float applicationSum) {
		this.applicationSum = applicationSum;
	}
	public float getWillRemittanceSum() {
		return willRemittanceSum;
	}
	public void setWillRemittanceSum(float willRemittanceSum) {
		this.willRemittanceSum = willRemittanceSum;
	}
	public float getRemittancedSum() {
		return remittancedSum;
	}
	public void setRemittancedSum(float remittancedSum) {
		this.remittancedSum = remittancedSum;
	}
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public ArrayList<SimpleBillCheckViewModel> getAllSimpleBillCheckViewModel(ArrayList<SupplierTable> supplierTables){
		ArrayList<SimpleBillCheckViewModel> billCheckViewModels = new ArrayList<SimpleBillCheckViewModel>();
		for (SupplierTable supplierTable : supplierTables) {
			SimpleBillCheckViewModel billCheckViewModel = new SimpleBillCheckViewModel();
			
			BillInfo billinfo = costService.getBillInfo(supplierTable.getId());
			BillInfo changeBillInfo = changeCostService.getBillInfo(supplierTable.getId());
			
			billCheckViewModel.setSupplierTable(supplierTable);
			billCheckViewModel.setSettlementDate(billinfo.getSettlementDate());
			billCheckViewModel.setBillSum(billinfo.getBillSum().add(changeBillInfo.getBillSum()).floatValue());
			billCheckViewModel.setApplicationSum(billinfo.getApplicationSum().add(changeBillInfo.getApplicationSum()).floatValue());
			billCheckViewModel.setWillRemittanceSum(billinfo.getWillRemittanceSum().add(changeBillInfo.getWillRemittanceSum()).floatValue());
			billCheckViewModel.setRemittancedSum(billinfo.getRemittancedSum().add(changeBillInfo.getRemittancedSum()).floatValue());
			billCheckViewModels.add(billCheckViewModel);
		}
		return billCheckViewModels;
	}
}
