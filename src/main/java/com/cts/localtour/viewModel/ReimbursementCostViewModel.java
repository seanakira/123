package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ContentService;
import com.cts.localtour.service.SupplierInfoService;
@Component
public class ReimbursementCostViewModel {
	private ReimbursementCostTable costTable;
	private String contentName;
	private String supplierName;
	private String payStatus;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private SupplierInfoService supplierInfoService;
	@Autowired
	private ContentService contentService;
	public ReimbursementCostTable getCostTable() {
		return costTable;
	}
	public void setCostTable(ReimbursementCostTable costTable) {
		this.costTable = costTable;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<ReimbursementCostViewModel> getAllReimbursementCostViewModel(int tourId, int payStatus){
		ArrayList<ReimbursementCostViewModel> reimbursementCostViewModels = new ArrayList<ReimbursementCostViewModel>();
		ArrayList<ReimbursementCostTable> reimbursementCostTables = (ArrayList<ReimbursementCostTable>) baseService.getAllByString("ReimbursementCostTable", "tourId=? and payStatus=?", tourId, payStatus);
		for (ReimbursementCostTable reimbursementCostTable : reimbursementCostTables) {
			ReimbursementCostViewModel reimbursementCostViewModel = new ReimbursementCostViewModel();
			reimbursementCostViewModel.setCostTable(reimbursementCostTable);
			reimbursementCostViewModel.setSupplierName(supplierInfoService.getSupplierName(reimbursementCostTable.getSupplierId()));
			reimbursementCostViewModel.setContentName(contentService.getContentName(reimbursementCostTable.getContentId()));
			reimbursementCostViewModel.setPayStatus(reimbursementCostTable.getPayStatus()==0?"新增":"已批准");
			reimbursementCostViewModels.add(reimbursementCostViewModel);
		}
		return reimbursementCostViewModels;
	}
}
