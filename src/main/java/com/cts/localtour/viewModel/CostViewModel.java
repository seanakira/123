package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;
@Component
public class CostViewModel {
	private CostTable costTable;
	private String contentName;
	private String supplierName;
	private String borrowUserName;
	private String payStatus;
	private String payApplicationerRealName;
	@Autowired
	private UserService userService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	public CostTable getCostTable() {
		return costTable;
	}
	public void setCostTable(CostTable costTable) {
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
	public String getBorrowUserName() {
		return borrowUserName;
	}
	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayApplicationerRealName() {
		return payApplicationerRealName;
	}
	public void setPayApplicationerRealName(String payApplicationerRealName) {
		this.payApplicationerRealName = payApplicationerRealName;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<CostViewModel> getAllCostViewModel(int tourId){
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "tourId=?", tourId);
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (CostTable costTable : costTables) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTable);
			cost.setBorrowUserName(userService.getUserRealName(costTable.getBorrowUserId()));
			cost.setContentName(costTable.getContentId()==0?"":((SupplierContentTable)userService.getById("SupplierContentTable", costTable.getContentId())).getContentName());
			cost.setPayApplicationerRealName(userService.getUserName(costTable.getPayApplicationerId()));
			cost.setSupplierName(((SupplierTable)userService.getById("SupplierTable", costTable.getContentId())).getSupplierName());
			if(costTable.getPayStatus()==0){
				cost.setPayStatus("可付");
			}else if(costTable.getPayStatus()==1){
				cost.setPayStatus("待审");
			}else if(costTable.getPayStatus()==2){
				cost.setPayStatus("待批");
			}else if(costTable.getPayStatus()==3){
				cost.setPayStatus("已批");
			}
			costs.add(cost);
		}
		return costs;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CostViewModel> getAllBillViewModel(int supplierId){
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false and payStatus=0", supplierId);
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (CostTable costTable : costTables) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTable);
			cost.setContentName(costTable.getContentId()==0?"":((SupplierContentTable)userService.getById("SupplierContentTable", costTable.getContentId())).getContentName());
			if(costTable.getPayStatus()==0){
				cost.setPayStatus("可付");
			}else if(costTable.getPayStatus()==1){
				cost.setPayStatus("待审");
			}else if(costTable.getPayStatus()==2){
				cost.setPayStatus("待批");
			}else if(costTable.getPayStatus()==3){
				cost.setPayStatus("已批");
			}
			costs.add(cost);
		}
		return costs;
	}
}
