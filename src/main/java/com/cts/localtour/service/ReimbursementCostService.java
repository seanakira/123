package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.pojo.CostInfo;

@SuppressWarnings("rawtypes")
@Service
public class ReimbursementCostService extends BaseService{
	@Autowired
	private SupplierInfoService supplierInfoService;
	@SuppressWarnings("unchecked")
	public CostInfo getReimbursementCostInfo(int tourId){
		ArrayList<ReimbursementCostTable> costTables = (ArrayList<ReimbursementCostTable>) this.getAllByString("ReimbursementCostTable", "tourId=?", tourId);
		BigDecimal willCostSum = new BigDecimal(0.00);
		BigDecimal realCostSum = new BigDecimal(0.00);
		BigDecimal reimbursementSum = new BigDecimal(0.00);
		StringBuffer reimbursementSumInfo = new StringBuffer();
		StringBuffer willCosSumInfo = new StringBuffer();
		StringBuffer realCosSumInfo = new StringBuffer();
		CostInfo costInfo = new CostInfo();
		for (ReimbursementCostTable reimbursementCostTable : costTables) {
			String supplierName = supplierInfoService.getSupplierName(reimbursementCostTable.getSupplierId());
			if(reimbursementCostTable.getPayStatus()==1){
				willCostSum = willCostSum.add(new BigDecimal(reimbursementCostTable.getReimbursement()));
				willCosSumInfo.append("<a class='red'>").append(supplierName).append(" ").append(reimbursementCostTable.getReimbursement()).append("</a>").append(",");
			}
			if(reimbursementCostTable.isRemittanced()){
				realCostSum = realCostSum.add(new BigDecimal(reimbursementCostTable.getReimbursement()));
				realCosSumInfo.append("<a class='red'>").append(supplierName).append(" ").append(reimbursementCostTable.getReimbursement()).append("</a>").append(",");
			}
			if(reimbursementCostTable.getReimbursement()!=null){
				reimbursementSum = reimbursementSum.add(new BigDecimal(reimbursementCostTable.getReimbursement()));
				reimbursementSumInfo.append("<a class='red'>").append(supplierName).append(" ").append(reimbursementCostTable.getReimbursement()).append("</a>").append(",");
			}
		}
		costInfo.setWillCostSum(willCostSum);
		costInfo.setWillCostSumInfo(willCosSumInfo.toString());
		costInfo.setRealCostSum(realCostSum);
		costInfo.setRealCostSumInfo(realCosSumInfo.toString());
		costInfo.setReimbursementSum(reimbursementSum);
		costInfo.setReimbursementSumInfo(reimbursementSumInfo.toString());
		return costInfo;
	}
}
