package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;
@Component
public class BillCostViewModel {
	private CostTable costTable;
	private String contentName;
	private String payStatus;
	private String payApplicationerRealName;
	private LocalTourTable localTourTable;
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
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BillCostViewModel> getAllBillViewModel(int supplierId){
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false", supplierId);
		ArrayList<BillCostViewModel> costs = new ArrayList<BillCostViewModel>();
		for (CostTable costTable : costTables) {
			BillCostViewModel cost = new BillCostViewModel();
			cost.setCostTable(costTable);
			cost.setContentName(costTable.getContentId()==0?"":((SupplierContentTable)userService.getById("SupplierContentTable", costTable.getContentId())).getContentName());
			cost.setLocalTourTable((LocalTourTable) baseService.getById("LocalTourTable", costTable.getTourId()));
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
	public ArrayList<BillCostViewModel> getAllBillViewModel(int supplierId, int payStatus){
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false and payStatus=?", supplierId, payStatus);
		ArrayList<BillCostViewModel> costs = new ArrayList<BillCostViewModel>();
		for (CostTable costTable : costTables) {
			BillCostViewModel cost = new BillCostViewModel();
			cost.setCostTable(costTable);
			cost.setContentName(costTable.getContentId()==0?"":((SupplierContentTable)userService.getById("SupplierContentTable", costTable.getContentId())).getContentName());
			cost.setLocalTourTable((LocalTourTable) baseService.getById("LocalTourTable", costTable.getTourId()));
			if(costTable.isRemittanced()){
				cost.setPayStatus("已汇");
			}else{
				if(costTable.getPayStatus()==0){
					cost.setPayStatus("可付");
				}else if(costTable.getPayStatus()==1){
					cost.setPayStatus("待审");
				}else if(costTable.getPayStatus()==2){
					cost.setPayStatus("待批");
				}else if(costTable.getPayStatus()==3){
					cost.setPayStatus("已批");
				}
			}
			costs.add(cost);
		}
		return costs;
	}
}
