package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;
@Component
public class ChangeCostViewModel {
	private ChangeCostTable costTable;
	private String contentName;
	private String supplierName;
	private String borrowUserName;
	private String applicationerRealName;
	private String status;
	private String payStatus;
	private String payApplicationerRealName;
	@Autowired
	private UserService userService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	public ChangeCostTable getCostTable() {
		return costTable;
	}
	public void setCostTable(ChangeCostTable changeCostTable) {
		this.costTable = changeCostTable;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplicationerRealName() {
		return applicationerRealName;
	}
	public void setApplicationerRealName(String applicationerRealName) {
		this.applicationerRealName = applicationerRealName;
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
	public ArrayList<ChangeCostViewModel> getAllChangeCostViewModel(int tourId){
		ArrayList<ChangeCostTable> changeCostTables = (ArrayList<ChangeCostTable>) baseService.getAllByString("ChangeCostTable", "tourId=?", tourId);
		ArrayList<ChangeCostViewModel> costs = new ArrayList<ChangeCostViewModel>();
		for (ChangeCostTable changeCostTable : changeCostTables) {
			ChangeCostViewModel changeCost = new ChangeCostViewModel();
			changeCost.setCostTable(changeCostTable);
			changeCost.setBorrowUserName(userService.getUserRealName(changeCostTable.getBorrowUserId()));
			changeCost.setContentName(((SupplierContentTable)baseService.getById("SupplierContentTable", changeCostTable.getContentId())).getContentName());
			changeCost.setPayApplicationerRealName(userService.getUserName(changeCostTable.getPayApplicationerId()));
			changeCost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", changeCostTable.getSupplierId())).getSupplierName());
			if(changeCostTable.getPayStatus()==0){
				changeCost.setPayStatus("可付");
			}else if(changeCostTable.getPayStatus()==1){
				changeCost.setPayStatus("待审");
			}else if(changeCostTable.getPayStatus()==2){
				changeCost.setPayStatus("待批");
			}else if(changeCostTable.getPayStatus()==3){
				changeCost.setPayStatus("已批");
			}
			costs.add(changeCost);
		}
		return costs;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<ChangeCostViewModel> getAllChangeCostViewModel(int tourId, int status) {
		ArrayList<ChangeCostViewModel> costs = new ArrayList<ChangeCostViewModel>();
		ArrayList<ChangeCostTable> changeCostTables;
		/*这里需要权限判断  如果是总经理 status>2*/
		if(status==-1){
			changeCostTables = (ArrayList<ChangeCostTable>)baseService.getAllByString("ChangeCostTable", "tourId=? and status>1", tourId);
		}else{
			changeCostTables = (ArrayList<ChangeCostTable>)baseService.getAllByString("ChangeCostTable", "tourId=? and status=?", tourId, status);
		}
		for (ChangeCostTable changeCostTable : changeCostTables) {
			ChangeCostViewModel cost = new ChangeCostViewModel();
			cost.setCostTable(changeCostTable);
			cost.setApplicationerRealName(userService.getUserRealName(changeCostTable.getApplicationerId()));
			cost.setBorrowUserName(userService.getUserRealName(changeCostTable.getBorrowUserId()));
			cost.setContentName(changeCostTable.getContentId()==null||changeCostTable.getContentId()==0?"":((SupplierContentTable)baseService.getById("SupplierContentTable", changeCostTable.getContentId())).getContentName());
			cost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", changeCostTable.getSupplierId())).getSupplierName());
			if(changeCostTable.isRemittanced()){
				cost.setStatus("已电汇");
			}else{
				cost.setStatus(changeCostTable.getStatus()==0?"新建":changeCostTable.getStatus()==1?"待审核":changeCostTable.getStatus()==2?"待批准":changeCostTable.getStatus()==3?"已批准":"");
			}
			cost.setPayStatus(changeCostTable.getPayStatus()==0?"可付":changeCostTable.getPayStatus()==1?"待审":changeCostTable.getPayStatus()==2?"待批":changeCostTable.getPayStatus()==3?"已批":"");
			costs.add(cost);
		}
		return costs;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ChangeCostViewModel> getAllBillViewModel(int supplierId){
		ArrayList<ChangeCostTable> costTables = (ArrayList<ChangeCostTable>) baseService.getAllByString("ChangeCostTable", "supplierId=? and bill=true and remittanced=false and payStatus=0 and status=3", supplierId);
		ArrayList<ChangeCostViewModel> costs = new ArrayList<ChangeCostViewModel>();
		for (ChangeCostTable costTable : costTables) {
			ChangeCostViewModel cost = new ChangeCostViewModel();
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
