package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.CostTable;
import com.cts.localtour.pojo.CostInfo;

@SuppressWarnings("rawtypes")
@Service
public class CostService extends BaseService{
	@Autowired
	private SupplierInfoService supplierInfoService;
	@SuppressWarnings("unchecked")
	public CostInfo getCostInfo(int tourId){
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) this.getAllByString("CostTable", "tourId=?", tourId);
		BigDecimal costSum = new BigDecimal(0.00);
		BigDecimal canCostSum = new BigDecimal(0.00);
		BigDecimal willCostSum = new BigDecimal(0.00);
		BigDecimal realCostSum = new BigDecimal(0.00);
		BigDecimal reimbursementSum = new BigDecimal(0.00);
		StringBuffer costSumInfo = new StringBuffer();
		StringBuffer willCostSumInfo = new StringBuffer();
		StringBuffer realCostSumInfo = new StringBuffer();
		StringBuffer reimbursementSumInfo = new StringBuffer();
		CostInfo costInfo = new CostInfo();
		for (CostTable costTable : costTables) {
			BigDecimal cost = new BigDecimal(costTable.getCost());
			String supplierName = supplierInfoService.getSupplierName(costTable.getSupplierId());
			costSum =  costSum.add(cost.multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())));
			costSumInfo.append(supplierName).append(",").append("单价").append(costTable.getCost()).append(" X 数量").append(costTable.getCount()).append(" X 天数").append(costTable.getDays()).append(" = ").append(cost.multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())).setScale(2, BigDecimal.ROUND_HALF_UP).toString()).append(",");
			canCostSum = canCostSum.add(new BigDecimal(costTable.getRealCost()));
			if(costTable.getPayStatus()==3){
				willCostSum = willCostSum.add(new BigDecimal(costTable.getRealCost()));
				willCostSumInfo.append(supplierName).append(" ").append(costTable.getRealCost()).append(",");
			}
			if(costTable.isRemittanced()){
				realCostSum = realCostSum.add(new BigDecimal(costTable.getRealCost()));
				realCostSumInfo.append(supplierName).append(" ").append(costTable.getRealCost()).append(",");
			}
			if(costTable.getReimbursement()!=null){
				reimbursementSum = reimbursementSum.add(new BigDecimal(costTable.getReimbursement()));
				reimbursementSumInfo.append(supplierName).append(" ").append(costTable.getReimbursement()).append(",");
			}
		}
		costInfo.setCostSum(costSum);
		costInfo.setCostSumInfo(costSumInfo.toString());
		costInfo.setCanCostSum(canCostSum);
		costInfo.setWillCostSum(willCostSum);
		costInfo.setWillCostSumInfo(willCostSumInfo.toString());
		costInfo.setRealCostSum(realCostSum);
		costInfo.setRealCostSumInfo(realCostSumInfo.toString());
		costInfo.setReimbursementSum(reimbursementSum);
		costInfo.setReimbursementSumInfo(reimbursementSumInfo.toString());
		return costInfo;
	}
	
	@SuppressWarnings("unchecked")
	public BigDecimal getBillSum(int supplierId){
		BigDecimal billSum = new BigDecimal(0);
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) this.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false and payStatus=0", supplierId);
		for (CostTable costTable : costTables) {
			billSum = billSum.add(new BigDecimal(costTable.getCost()).multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())));
		}
		return billSum;
	}
}
