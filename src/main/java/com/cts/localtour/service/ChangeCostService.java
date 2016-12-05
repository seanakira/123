package com.cts.localtour.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.pojo.BillInfo;
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
			costSumInfo.append("<a class='orange'>").append(supplierName).append(",").append("单价 ").append(costTable.getCost()).append(" X 数量 ").append(costTable.getCount()).append(" X 天数 ").append(costTable.getDays()).append(" = ").append(cost.multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())).toString()).append("<a>").append(",");
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
	public BillInfo getBillInfo(int supplierId){
		BillInfo billInfo = new BillInfo();
		BigDecimal billSum = new BigDecimal(0);
		BigDecimal applicationSum = new BigDecimal(0);
		BigDecimal willRemittanceSum = new BigDecimal(0);
		BigDecimal remittancedSum = new BigDecimal(0);
		/*默认账期三个月*/
		int accountPeriod = 3;
		Date to = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		calendar.add(Calendar.MONTH, -2);
		Date from = calendar.getTime();
		SupplierTable supplierTable = (SupplierTable)this.getById("SupplierTable", supplierId);
		if((supplierTable.getAccountPeriod()!=null&&supplierTable.getAccountPeriod()!=0)&&supplierTable.getAccountDate()!=null){
			Calendar accountDate = Calendar.getInstance();
			Calendar now = Calendar.getInstance();
			now.setTime(to);
			accountDate.setTime(supplierTable.getAccountDate());
			int year = now.get(Calendar.YEAR)-accountDate.get(Calendar.YEAR);
			if(year<0){
				from = to;
				to = supplierTable.getAccountDate();
			}else{
				int month = year*12+now.get(Calendar.MONTH)-accountDate.get(Calendar.MONTH);
				int period = month/accountPeriod;
				if(period==0){
					period = 2;
				}
				if(month%accountPeriod>0){
					period = period+1;
				}
				now.setTime(supplierTable.getAccountDate());
				now.add(Calendar.MONTH, period*accountPeriod-accountPeriod);
				from = now.getTime();
				accountDate.setTime(supplierTable.getAccountDate());
				accountDate.add(Calendar.MONTH, period*accountPeriod);
				to = accountDate.getTime();
				billInfo.setSettlementDate(new SimpleDateFormat("yyyy-MM-dd").format(to));
			}
		}
		ArrayList<ChangeCostTable> costTables = (ArrayList<ChangeCostTable>) this.getAllByString("ChangeCostTable", "supplierId=? and bill=true and status=3 and costDate between ? and ?", supplierId, from, to);
		for (ChangeCostTable costTable : costTables) {
			billSum = billSum.add(new BigDecimal(costTable.getCost()).multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())));
			if(costTable.getPayStatus()==1||costTable.getPayStatus()==2){
				applicationSum = applicationSum.add(new BigDecimal(costTable.getReimbursement()));
			}
			if(costTable.getPayStatus()==3){
				willRemittanceSum = willRemittanceSum.add(new BigDecimal(costTable.getReimbursement()));
			}
			if(costTable.isRemittanced()){
				remittancedSum = remittancedSum.add(new BigDecimal(costTable.getReimbursement()));
			}
		}
		billInfo.setBillSum(billSum);
		billInfo.setApplicationSum(applicationSum);
		billInfo.setWillRemittanceSum(willRemittanceSum);
		billInfo.setRemittancedSum(remittancedSum);
		return billInfo;
	}
}
