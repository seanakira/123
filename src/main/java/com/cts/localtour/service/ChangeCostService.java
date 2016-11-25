package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.pojo.CostInfo;

@SuppressWarnings("rawtypes")
@Service
public class ChangeCostService extends BaseService{
	@Autowired
	private SupplierInfoService supplierInfoService;
	@SuppressWarnings("unchecked")
	public CostInfo getCostInfo(int tourId){
		ArrayList<ChangeCostTable> costTables = (ArrayList<ChangeCostTable>) this.getAllByString("ChangeCostTable", "tourId=? and status=3", tourId);
		BigDecimal costSum = new BigDecimal(0.00);
		BigDecimal canCostSum = new BigDecimal(0.00);
		BigDecimal willCostSum = new BigDecimal(0.00);
		BigDecimal realCostSum = new BigDecimal(0.00);
		BigDecimal reimbursementSum = new BigDecimal(0.00);
		StringBuffer costSumInfo = new StringBuffer();
		StringBuffer willCosSumInfo = new StringBuffer();
		StringBuffer realCosSumInfo = new StringBuffer();
		StringBuffer reimbursementSumInfo = new StringBuffer();
		CostInfo costInfo = new CostInfo();
		for (ChangeCostTable costTable : costTables) {
			BigDecimal cost = new BigDecimal(costTable.getCost());
			String supplierName = supplierInfoService.getSupplierName(costTable.getSupplierId());
			costSum =  costSum.add(cost.multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())));
			costSumInfo.append("<a class='orange'>").append(supplierName).append(",").append("���� ").append(costTable.getCost()).append(" X ���� ").append(costTable.getCount()).append(" X ���� ").append(costTable.getDays()).append(" = ").append(cost.multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())).toString()).append("<a>").append(",");
			canCostSum = canCostSum.add(new BigDecimal(costTable.getRealCost()));
			if(costTable.getPayStatus()==3){
				willCostSum = willCostSum.add(new BigDecimal(costTable.getRealCost()));
				willCosSumInfo.append(supplierName).append(" ").append(costTable.getRealCost()).append(",");
			}
			if(costTable.isRemittanced()){
				realCostSum = realCostSum.add(new BigDecimal(costTable.getRealCost()));
				realCosSumInfo.append(supplierName).append(" ").append(costTable.getRealCost()).append(",");
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
		costInfo.setWillCostSumInfo(willCosSumInfo.toString());
		costInfo.setRealCostSum(realCostSum);
		costInfo.setRealCostSumInfo(realCosSumInfo.toString());
		costInfo.setReimbursementSum(reimbursementSum);
		costInfo.setReimbursementSumInfo(reimbursementSumInfo.toString());
		return costInfo;
	}
	
	@SuppressWarnings("unchecked")
	public BigDecimal getBillSum(int supplierId){
		BigDecimal billSum = new BigDecimal(0);
		ArrayList<ChangeCostTable> costTables = (ArrayList<ChangeCostTable>) this.getAllByString("ChangeCostTable", "supplierId=? and bill=true and remittanced=false and payStatus=0 and status=3", supplierId);
		for (ChangeCostTable costTable : costTables) {
			billSum.add(new BigDecimal(costTable.getCost()).multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())));
		}
		return billSum;
	}
}
