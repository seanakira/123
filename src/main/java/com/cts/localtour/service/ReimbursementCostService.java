package com.cts.localtour.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.pojo.BillInfo;
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
	
	@SuppressWarnings("unchecked")
	public BillInfo getBillInfo(int supplierId, int relativePeriod){
		BillInfo billInfo = new BillInfo();
		BigDecimal billSum = new BigDecimal(0);
		BigDecimal applicationSum = new BigDecimal(0);
		BigDecimal willRemittanceSum = new BigDecimal(0);
		BigDecimal remittancedSum = new BigDecimal(0);
		HashMap<String, Date> fromTo = supplierInfoService.getSettlementDateFromTo(supplierId, relativePeriod);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		ArrayList<ReimbursementCostTable> costTables = (ArrayList<ReimbursementCostTable>) this.getByHql("SELECT c FROM ReimbursementCostTable c, LocalTourTable l WHERE c.supplierId="+supplierId+"  and c.bill=true and c.tourId=l.id and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+") and c.costDate between '"+df.format(fromTo.get("from"))+"' and '"+df.format(fromTo.get("to"))+"'");
		for (ReimbursementCostTable costTable : costTables) {
			billSum = billSum.add(new BigDecimal(costTable.getCost()).multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())));
			if(costTable.getPayStatus()==1){
				applicationSum = applicationSum.add(new BigDecimal(costTable.getReimbursement()==null?0:costTable.getReimbursement()));
			}
			if(costTable.getPayStatus()==1&&!costTable.isRemittanced()){
				willRemittanceSum = willRemittanceSum.add(new BigDecimal(costTable.getReimbursement()));
			}
			if(costTable.isRemittanced()){
				remittancedSum = remittancedSum.add(new BigDecimal(costTable.getReimbursement()));
			}
		}
		billInfo.setEmpty(costTables.isEmpty());
		billInfo.setBillSum(billSum);
		billInfo.setApplicationSum(applicationSum);
		billInfo.setWillRemittanceSum(willRemittanceSum);
		billInfo.setRemittancedSum(remittancedSum);
		return billInfo;
	}
	
	@SuppressWarnings("unchecked")
	public BillInfo getBillInfo(int supplierId, int payStatus, int relativePeriod){
		BillInfo billInfo = new BillInfo();
		BigDecimal billSum = new BigDecimal(0);
		BigDecimal applicationSum = new BigDecimal(0);
		BigDecimal willRemittanceSum = new BigDecimal(0);
		BigDecimal remittancedSum = new BigDecimal(0);
		HashMap<String, Date> fromTo = supplierInfoService.getSettlementDateFromTo(supplierId, relativePeriod);
		ArrayList<ReimbursementCostTable> costTables = (ArrayList<ReimbursementCostTable>) this.getAllByString("ReimbursementCostTable", "supplierId=? and bill=true and payStatus=? and costDate between ? and ?", supplierId, payStatus, fromTo.get("from"), fromTo.get("to"));
		for (ReimbursementCostTable costTable : costTables) {
			billSum = billSum.add(new BigDecimal(costTable.getCost()).multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())));
			if(costTable.getPayStatus()==1){
				applicationSum = applicationSum.add(new BigDecimal(costTable.getReimbursement()==null?0:costTable.getReimbursement()));
			}
			if(costTable.getPayStatus()==1&&!costTable.isRemittanced()){
				willRemittanceSum = willRemittanceSum.add(new BigDecimal(costTable.getReimbursement()));
			}
			if(costTable.isRemittanced()){
				remittancedSum = remittancedSum.add(new BigDecimal(costTable.getReimbursement()));
			}
		}
		billInfo.setEmpty(costTables.isEmpty());
		billInfo.setBillSum(billSum);
		billInfo.setApplicationSum(applicationSum);
		billInfo.setWillRemittanceSum(willRemittanceSum);
		billInfo.setRemittancedSum(remittancedSum);
		return billInfo;
	}
}
