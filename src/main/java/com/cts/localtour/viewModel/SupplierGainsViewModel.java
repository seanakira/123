package com.cts.localtour.viewModel;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.BaseService;

@Component
public class SupplierGainsViewModel {
	private String headerName;
	private float willCost;
	private float realCost;
	private int tourCount;
	private int useCount;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public float getWillCost() {
		return willCost;
	}
	public void setWillCost(float willCost) {
		this.willCost = willCost;
	}
	public float getRealCost() {
		return realCost;
	}
	public void setRealCost(float realCost) {
		this.realCost = realCost;
	}
	public int getTourCount() {
		return tourCount;
	}
	public void setTourCount(int tourCount) {
		this.tourCount = tourCount;
	}
	public int getUseCount() {
		return useCount;
	}
	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<SupplierGainsViewModel> getSupplierGainsViewModelAll(Date start, Date end){
		ArrayList<SupplierGainsViewModel> supplierGainsViewModels = new ArrayList<SupplierGainsViewModel>();
		ArrayList<SupplierTable> supplierTables = (ArrayList<SupplierTable>) baseService.getAllByString("SupplierTable", "", "");
		for (SupplierTable supplierTable : supplierTables) {
			ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getByHql("SELECT c FROM CostTable as c, LocalTourTable as l WHERE l.startTime between "+start+" and "+end+" and c.tourId=l.id and c.payStatus=3 and c.supplierScopeId="+supplierTable.getId());
			ArrayList<ChangeCostTable> changeCostTables = (ArrayList<ChangeCostTable>) baseService.getByHql("SELECT c FROM ChangeCostTable as c, LocalTourTable as l WHERE l.startTime between "+start+" and "+end+" and c.tourId=l.id and c.payStatus=3 and c.supplierScopeId="+supplierTable.getId());
			ArrayList<ReimbursementCostTable> reimbursementCostTables = (ArrayList<ReimbursementCostTable>) baseService.getByHql("SELECT c FROM ReimbursementCostTable as c, LocalTourTable as l WHERE l.startTime between "+start+" and "+end+" and c.tourId=l.id and c.payStatus=1 and c.supplierScopeId="+supplierTable.getId());
			if(!costTables.isEmpty()&&!changeCostTables.isEmpty()&&!reimbursementCostTables.isEmpty()){
				SupplierGainsViewModel supplierGainsViewModel = new SupplierGainsViewModel();
				supplierGainsViewModel.setHeaderName(headerName);
			}
		}
		return supplierGainsViewModels;
	}
}
