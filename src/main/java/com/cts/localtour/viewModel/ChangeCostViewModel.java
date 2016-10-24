package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
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
	public ArrayList<ChangeCostViewModel> getAllChangeCostViewModell(int tourId){
		ArrayList<ChangeCostTable> changeCostTables = (ArrayList<ChangeCostTable>) baseService.getAllByString("ChangeCostTable", "tourId=?", tourId);
		ArrayList<ChangeCostViewModel> costs = new ArrayList<ChangeCostViewModel>();
		for (ChangeCostTable changeCostTable : changeCostTables) {
			ChangeCostViewModel changeCost = new ChangeCostViewModel();
			changeCost.setCostTable(changeCostTable);
			changeCost.setBorrowUserName(userService.getUserName(changeCostTable.getBorrowUserId()));
			changeCost.setContentName(((SupplierContentTable)baseService.getById("SupplierContentTable", changeCostTable.getContentId())).getContentName());
			changeCost.setPayApplicationerRealName(userService.getUserName(changeCostTable.getPayApplicationerId()));
			changeCost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", changeCostTable.getContentId())).getSupplierName());
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
		ArrayList<ChangeCostTable> costTables;
		/*这里需要权限判断  如果是总经理 status>2*/
		if(status==-1){
			costTables = (ArrayList<ChangeCostTable>)baseService.getAllByString("ChangeCostTable", "tourId=? and status>1", tourId);
		}else{
			costTables = (ArrayList<ChangeCostTable>)baseService.getAllByString("ChangeCostTable", "tourId=? and status=?", tourId, status);
		}
		ArrayList<ChangeCostViewModel> costs = new ArrayList<ChangeCostViewModel>();
		for (int i = 0; i < costTables.size(); i++) {
			ChangeCostViewModel cost = new ChangeCostViewModel();
			cost.setCostTable(costTables.get(i));
			cost.setApplicationerRealName(costTables.get(i).getApplicationerId()==null||costTables.get(i).getApplicationerId()==0?"":((UserTable)baseService.getById("UserTable", costTables.get(i).getApplicationerId())).getRealName());
			cost.setContentName(costTables.get(i).getContentId()==null||costTables.get(i).getContentId()==0?"":((SupplierContentTable)baseService.getById("SupplierContentTable", costTables.get(i).getContentId())).getContentName());
			cost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", costTables.get(i).getSupplierId())).getSupplierName());
			if(costTables.get(i).isRemittanced()){
				cost.setStatus("已电汇");
			}else{
				cost.setStatus(costTables.get(i).getStatus()==0?"新建":costTables.get(i).getStatus()==1?"待审核":costTables.get(i).getStatus()==2?"待批准":costTables.get(i).getStatus()==3?"已批准":"");
			}
			costs.add(cost);
		}
		return costs;
	}
}
