package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.SupplierBusinessTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierScopeTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.BaseService;

@Component
public class SupplierGainsViewModel {
	private String headerName;
	private BigDecimal willCostSum;
	private BigDecimal realCostSum;
	private int tourCount;
	private int useCount;
	private String type;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public BigDecimal getWillCostSum() {
		return willCostSum;
	}

	public void setWillCostSum(BigDecimal willCostSum) {
		this.willCostSum = willCostSum;
	}

	public BigDecimal getRealCostSum() {
		return realCostSum;
	}

	public void setRealCostSum(BigDecimal realCostSum) {
		this.realCostSum = realCostSum;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SupplierGainsViewModel> getSupplierGainsViewModelAll(String supplierIds, Date start, Date end){
		/*供应商内容初始化*/
		HashMap<Integer, ArrayList<SupplierContentTable>> supplierContentByType = new HashMap<Integer, ArrayList<SupplierContentTable>>();
		for (int i = 1; i <= 8; i++) {
			supplierContentByType.put(i, new ArrayList<SupplierContentTable>());
		}
		ArrayList<SupplierContentTable> supplierContentTables =  (ArrayList<SupplierContentTable>) baseService.getAllByString("SupplierContentTable", "", null);
		for (SupplierContentTable supplierContentTable : supplierContentTables) {
			supplierContentByType.get(supplierContentTable.getSupplierScopeId()).add(supplierContentTable);
		}
		for (int i = 1; i <= 8; i++) {
			SupplierContentTable cache = new SupplierContentTable();
			cache.setContentName("没填写");
			supplierContentByType.get(i).add(cache);
		}
		/*供应范围初始化*/
		ArrayList<SupplierScopeTable> scopes = (ArrayList<SupplierScopeTable>) baseService.getAllByString("SupplierScopeTable", "", null);
		
		/*查询所有供应商*/
		ArrayList<SupplierGainsViewModel> supplierGainsViewModels = new ArrayList<SupplierGainsViewModel>();
		ArrayList<SupplierTable> supplierTables;
		if("".equals(supplierIds)){
			supplierTables = (ArrayList<SupplierTable>) baseService.getAllByString("SupplierTable", "", null);
		}else{
			supplierTables = (ArrayList<SupplierTable>) baseService.getAllByString("SupplierTable", "id in("+supplierIds+")", null);
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		/*根据供应商和团队开始时间查询所有成本*/
		for (SupplierTable supplierTable : supplierTables) {
			ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getByHql("SELECT c FROM CostTable as c, LocalTourTable as l WHERE c.tourId=l.id and c.payStatus=3 and c.supplierId="+supplierTable.getId()+" and l.startTime BETWEEN '"+sd.format(start)+"' and '"+sd.format(end)+"'");
			ArrayList<ChangeCostTable> changeCostTables = (ArrayList<ChangeCostTable>) baseService.getByHql("SELECT c FROM ChangeCostTable as c, LocalTourTable as l WHERE c.tourId=l.id and c.payStatus=3 and c.supplierId="+supplierTable.getId()+" and l.startTime BETWEEN '"+sd.format(start)+"' and '"+sd.format(end)+"'");
			ArrayList<ReimbursementCostTable> reimbursementCostTables = (ArrayList<ReimbursementCostTable>) baseService.getByHql("SELECT c FROM ReimbursementCostTable as c, LocalTourTable as l WHERE c.tourId=l.id and c.payStatus=1 and c.supplierId="+supplierTable.getId()+" and l.startTime BETWEEN '"+sd.format(start)+"' and '"+sd.format(end)+"'");
			/*如果有成本*/
			if(!costTables.isEmpty()||!changeCostTables.isEmpty()||!reimbursementCostTables.isEmpty()){
				SupplierGainsViewModel supplier = new SupplierGainsViewModel();
				supplier.setHeaderName(supplierTable.getSupplierName());
				supplier.setType("supplier");
				supplierGainsViewModels.add(supplier);
				ArrayList<Integer> supplierTourIds = new ArrayList<Integer>();
				/*根据供应商查找供应范围*/
				ArrayList<SupplierBusinessTable> supplierBusinessTables = (ArrayList<SupplierBusinessTable>) baseService.getAllByString("SupplierBusinessTable", "supplierId=?", supplierTable.getId());
				for (SupplierBusinessTable supplierBusinessTable : supplierBusinessTables) {
					SupplierGainsViewModel scope = new SupplierGainsViewModel();
					scope.setHeaderName(scopes.get(supplierBusinessTable.getSupplierScopeId()-1).getSupplierScopeName());
					scope.setType("scope");
					supplierGainsViewModels.add(scope);
					ArrayList<Integer> scopesTourIds = new ArrayList<Integer>();
					/*根据范围查找内容*/
					ArrayList<SupplierContentTable> contents = supplierContentByType.get(supplierBusinessTable.getSupplierScopeId());
				
					for (SupplierContentTable supplierContentTable : contents) {
						SupplierGainsViewModel content = new SupplierGainsViewModel();
						content.setHeaderName(supplierContentTable.getContentName());
						ArrayList<Integer> contentTourIds = new ArrayList<Integer>();
						for (CostTable cost : costTables) {
							if(cost.getContentId()==supplierContentTable.getId()){
								content.setWillCostSum(content.getWillCostSum().add(cost.getRealCost()));
								if(cost.isRemittanced()){
									content.setRealCostSum(content.getRealCostSum().add(cost.getRealCost()));
								}
								content.setUseCount(cost.getCount()*cost.getDays());
								if(!contentTourIds.contains(cost.getTourId())){
									contentTourIds.add(cost.getTourId());
								}
								if(!scopesTourIds.contains(cost.getTourId())){
									scopesTourIds.add(cost.getTourId());
								}
								if(!supplierTourIds.contains(cost.getTourId())){
									supplierTourIds.add(cost.getTourId());
								}
							}
						}
						for (ChangeCostTable changeCost : changeCostTables) {
							if(changeCost.getContentId()==supplierContentTable.getId()){
								content.setWillCostSum(content.getWillCostSum().add(changeCost.getRealCost()));
								if(changeCost.isRemittanced()){
									content.setRealCostSum(content.getRealCostSum().add(changeCost.getRealCost()));
								}
								content.setUseCount(changeCost.getCount()*changeCost.getDays());
								if(!contentTourIds.contains(changeCost.getTourId())){
									contentTourIds.add(changeCost.getTourId());
								}
								if(!scopesTourIds.contains(changeCost.getTourId())){
									scopesTourIds.add(changeCost.getTourId());
								}
								if(!supplierTourIds.contains(changeCost.getTourId())){
									supplierTourIds.add(changeCost.getTourId());
								}
							}
						}
						for (ReimbursementCostTable reimbursementCost : reimbursementCostTables) {
							if(reimbursementCost.getContentId()==supplierContentTable.getId()){
								content.setWillCostSum(content.getWillCostSum().add(reimbursementCost.getReimbursement()));
								if(reimbursementCost.isRemittanced()){
									content.setRealCostSum(content.getRealCostSum().add(reimbursementCost.getReimbursement()));
								}
								content.setUseCount(reimbursementCost.getCount()*reimbursementCost.getDays());
								if(!contentTourIds.contains(reimbursementCost.getTourId())){
									contentTourIds.add(reimbursementCost.getTourId());
								}
								if(!scopesTourIds.contains(reimbursementCost.getTourId())){
									scopesTourIds.add(reimbursementCost.getTourId());
								}
								if(!supplierTourIds.contains(reimbursementCost.getTourId())){
									supplierTourIds.add(reimbursementCost.getTourId());
								}
							}
						}
						content.setType("content");
						content.setTourCount(contentTourIds.size());
						supplierGainsViewModels.add(content);
						
						scope.setWillCostSum(scope.getWillCostSum().add(content.getWillCostSum()));
						scope.setRealCostSum(scope.getRealCostSum().add(content.getRealCostSum()));
						scope.setUseCount(scope.getUseCount()+content.getUseCount());
					}
					
					scope.setTourCount(scopesTourIds.size());

					supplier.setWillCostSum(supplier.getWillCostSum().add(scope.getWillCostSum()));
					supplier.setRealCostSum(supplier.getRealCostSum().add(scope.getRealCostSum()));
					supplier.setUseCount(supplier.getUseCount()+scope.getUseCount());
				}
				supplier.setTourCount(supplierTourIds.size());
			}
		}
		return supplierGainsViewModels;
	}
}
