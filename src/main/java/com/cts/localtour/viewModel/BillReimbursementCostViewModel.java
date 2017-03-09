package com.cts.localtour.viewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.SupplierInfoService;
import com.cts.localtour.service.UserService;
@Component
public class BillReimbursementCostViewModel {
	private ReimbursementCostTable costTable;
	private String contentName;
	private String payStatus;
	private String payApplicationerRealName;
	private LocalTourTable localTourTable;
	@Autowired
	private UserService userService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private SupplierInfoService supplierInfoService;
	
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
	public ArrayList<BillReimbursementCostViewModel> getAllBillViewModel(int supplierId, int relativePeriod){
		HashMap<String, Date> fromTo = supplierInfoService.getSettlementDateFromTo(supplierId, relativePeriod);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		ArrayList<ReimbursementCostTable> costTables = (ArrayList<ReimbursementCostTable>) baseService.getByHql("SELECT c FROM ReimbursementCostTable c, LocalTourTable l WHERE c.supplierId="+supplierId+"  and c.bill=true and c.remittanced=false and c.tourId=l.id and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+") and c.costDate between '"+df.format(fromTo.get("from"))+"' and '"+df.format(fromTo.get("to"))+"'");
		ArrayList<BillReimbursementCostViewModel> costs = new ArrayList<BillReimbursementCostViewModel>();
		for (ReimbursementCostTable costTable : costTables) {
			BillReimbursementCostViewModel cost = new BillReimbursementCostViewModel();
			cost.setCostTable(costTable);
			cost.setContentName(costTable.getContentId()==0?"":((SupplierContentTable)userService.getById("SupplierContentTable", costTable.getContentId())).getContentName());
			LocalTourTable tour = (LocalTourTable) baseService.getById("LocalTourTable", costTable.getTourId());
			cost.setLocalTourTable(tour);
			cost.setPayApplicationerRealName(userService.getUserRealName(tour.getUserId()));
			if(costTable.getPayStatus()==0){
				cost.setPayStatus("����");
			}else if(costTable.getPayStatus()==1){
				cost.setPayStatus("����");
			}
			costs.add(cost);
		}
		return costs;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BillReimbursementCostViewModel> getAllBillViewModel(int supplierId, int payStatus, int relativePeriod){
		HashMap<String, Date> fromTo = supplierInfoService.getSettlementDateFromTo(supplierId, relativePeriod);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		ArrayList<ReimbursementCostTable> costTables = (ArrayList<ReimbursementCostTable>) baseService.getByHql("SELECT c FROM ReimbursementCostTable c, LocalTourTable l WHERE c.supplierId="+supplierId+"  and c.bill=true and c.remittanced=false and c.tourId=l.id and c.payStatus=1 and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+") and c.costDate between '"+df.format(fromTo.get("from"))+"' and '"+df.format(fromTo.get("to"))+"'");
		/*ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "supplierId=? and bill=true and payStatus=? and  and costDate between ? and ?", supplierId, payStatus, fromTo.get("from"), fromTo.get("to"));*/
		ArrayList<BillReimbursementCostViewModel> costs = new ArrayList<BillReimbursementCostViewModel>();
		for (ReimbursementCostTable costTable : costTables) {
			BillReimbursementCostViewModel cost = new BillReimbursementCostViewModel();
			cost.setCostTable(costTable);
			cost.setContentName(costTable.getContentId()==0?"":((SupplierContentTable)userService.getById("SupplierContentTable", costTable.getContentId())).getContentName());
			LocalTourTable tour = (LocalTourTable) baseService.getById("LocalTourTable", costTable.getTourId());
			cost.setLocalTourTable(tour);
			cost.setPayApplicationerRealName(userService.getUserRealName(tour.getUserId()));
			if(costTable.isRemittanced()){
				cost.setPayStatus("�ѻ�");
			}else{
				if(costTable.getPayStatus()==0){
					cost.setPayStatus("����");
				}else if(costTable.getPayStatus()==1){
					cost.setPayStatus("����");
				}
			}
			costs.add(cost);
		}
		return costs;
	}
}
