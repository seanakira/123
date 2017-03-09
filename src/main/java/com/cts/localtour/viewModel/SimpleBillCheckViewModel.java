package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.pojo.BillInfo;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.ReimbursementCostService;

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
	@Autowired
	private ReimbursementCostService reimbursementCostService;
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
	
	public ArrayList<SimpleBillCheckViewModel> getAllSimpleBillTodowModel(ArrayList<SupplierTable> supplierTables, int relativePeriod){
		ArrayList<SimpleBillCheckViewModel> billCheckViewModels = new ArrayList<SimpleBillCheckViewModel>();
		for (SupplierTable supplierTable : supplierTables) {
			BillInfo billinfo = costService.getBillTodoInfo(supplierTable.getId(), relativePeriod);
			BillInfo changeBillInfo = changeCostService.getBillTodoInfo(supplierTable.getId(), relativePeriod);
			BillInfo reimbursementBillInfo = reimbursementCostService.getBillInfo(supplierTable.getId(),relativePeriod);
			/*if(billinfo.isEmpty()&&changeBillInfo.isEmpty()){
				continue;
			}*/
			
			SimpleBillCheckViewModel billCheckViewModel = new SimpleBillCheckViewModel();
			billCheckViewModel.setSupplierTable(supplierTable);
			billCheckViewModel.setSettlementDate(billinfo.getSettlementDate());
			billCheckViewModel.setBillSum(billinfo.getBillSum().add(changeBillInfo.getBillSum()).add(reimbursementBillInfo.getBillSum()).floatValue());
			billCheckViewModel.setApplicationSum(billinfo.getApplicationSum().add(changeBillInfo.getApplicationSum()).add(reimbursementBillInfo.getApplicationSum()).floatValue());
			billCheckViewModel.setWillRemittanceSum(billinfo.getWillRemittanceSum().add(changeBillInfo.getWillRemittanceSum()).add(reimbursementBillInfo.getWillRemittanceSum()).floatValue());
			billCheckViewModel.setRemittancedSum(billinfo.getRemittancedSum().add(changeBillInfo.getRemittancedSum()).add(reimbursementBillInfo.getRemittancedSum()).floatValue());
			billCheckViewModels.add(billCheckViewModel);
		}
		return billCheckViewModels;
	}
	
	public ArrayList<SimpleBillCheckViewModel> getAllSimpleBillCheckViewModel(ArrayList<SupplierTable> supplierTables, int relativePeriod){
		ArrayList<SimpleBillCheckViewModel> billCheckViewModels = new ArrayList<SimpleBillCheckViewModel>();
		for (SupplierTable supplierTable : supplierTables) {
			BillInfo billinfo = costService.getBillInfo(supplierTable.getId(), relativePeriod);
			BillInfo changeBillInfo = changeCostService.getBillInfo(supplierTable.getId(), relativePeriod);
			BillInfo reimbursementBillInfo = reimbursementCostService.getBillInfo(supplierTable.getId(),relativePeriod);
			/*判断是否本账期内有挂账，如果没有，不进行装配*/
			/*if(billinfo.isEmpty()&&changeBillInfo.isEmpty()){
				continue;
			}*/
			
			SimpleBillCheckViewModel billCheckViewModel = new SimpleBillCheckViewModel();
			billCheckViewModel.setSupplierTable(supplierTable);
			billCheckViewModel.setSettlementDate(billinfo.getSettlementDate());
			billCheckViewModel.setBillSum(billinfo.getBillSum().add(changeBillInfo.getBillSum()).add(reimbursementBillInfo.getBillSum()).floatValue());
			billCheckViewModel.setApplicationSum(billinfo.getApplicationSum().add(changeBillInfo.getApplicationSum()).add(reimbursementBillInfo.getApplicationSum()).floatValue());
			billCheckViewModel.setWillRemittanceSum(billinfo.getWillRemittanceSum().add(changeBillInfo.getWillRemittanceSum()).add(reimbursementBillInfo.getWillRemittanceSum()).floatValue());
			billCheckViewModel.setRemittancedSum(billinfo.getRemittancedSum().add(changeBillInfo.getRemittancedSum()).add(reimbursementBillInfo.getRemittancedSum()).floatValue());
			billCheckViewModels.add(billCheckViewModel);
		}
		return billCheckViewModels;
	}
	
	public ArrayList<SimpleBillCheckViewModel> getAllSimpleBillCheckViewModel(ArrayList<SupplierTable> supplierTables, int payStatus, int relativePeriod){
		ArrayList<SimpleBillCheckViewModel> billCheckViewModels = new ArrayList<SimpleBillCheckViewModel>();
		for (SupplierTable supplierTable : supplierTables) {
			BillInfo billinfo = costService.getBillInfo(supplierTable.getId(),payStatus,relativePeriod);
			BillInfo changeBillInfo = changeCostService.getBillInfo(supplierTable.getId(),payStatus, relativePeriod);
			BillInfo reimbursementBillInfo = reimbursementCostService.getBillInfo(supplierTable.getId(),payStatus, relativePeriod);
			/*if(billinfo.isEmpty()&&changeBillInfo.isEmpty()){
				continue;
			}*/
			
			SimpleBillCheckViewModel billCheckViewModel = new SimpleBillCheckViewModel();
			billCheckViewModel.setSupplierTable(supplierTable);
			billCheckViewModel.setSettlementDate(billinfo.getSettlementDate());
			billCheckViewModel.setBillSum(billinfo.getBillSum().add(changeBillInfo.getBillSum()).add(reimbursementBillInfo.getBillSum()).floatValue());
			billCheckViewModel.setApplicationSum(billinfo.getApplicationSum().add(changeBillInfo.getApplicationSum()).add(reimbursementBillInfo.getApplicationSum()).floatValue());
			billCheckViewModel.setWillRemittanceSum(billinfo.getWillRemittanceSum().add(changeBillInfo.getWillRemittanceSum()).add(reimbursementBillInfo.getWillRemittanceSum()).floatValue());
			billCheckViewModel.setRemittancedSum(billinfo.getRemittancedSum().add(changeBillInfo.getRemittancedSum()).add(reimbursementBillInfo.getRemittancedSum()).floatValue());
			billCheckViewModels.add(billCheckViewModel);
		}
		return billCheckViewModels;
	}
}
