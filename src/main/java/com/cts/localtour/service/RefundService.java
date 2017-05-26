package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.RefundTable;
import com.cts.localtour.pojo.IncomeInfo;

@SuppressWarnings("rawtypes")
@Service
public class RefundService extends BaseService{
	@Autowired
	private CustomerAgencyService customerAgencyService;
	@SuppressWarnings("unchecked")
	public IncomeInfo getIncomeInfo(int tourId){
		IncomeInfo incomeInfo = new IncomeInfo();
		BigDecimal incomeSum = new BigDecimal(0);
		BigDecimal realIncomeSum = new BigDecimal(0);
		StringBuffer realIncomeSumInfo = new StringBuffer();
		StringBuffer incomeSumInfo = new StringBuffer();
		ArrayList<RefundTable> refundTables = (ArrayList<RefundTable>) this.getAllByString("RefundTable", "tourId=?", tourId);
		String customerAgencyName = "";
		if(!refundTables.isEmpty()){
			customerAgencyName = customerAgencyService.getCustomerAgencyName(tourId);
		}
		for (RefundTable refundTable : refundTables) {
			realIncomeSum = realIncomeSum.add(refundTable.getRefundAmount());
			realIncomeSumInfo.append(customerAgencyName).append(" ").append(refundTable.getRefundAmount()).append(",");
		}
		incomeInfo.setRealIncomeSum(realIncomeSum);
		incomeInfo.setIncomeSum(incomeSum);
		incomeInfo.setIncomeSumInfo(incomeSumInfo.toString());
		incomeInfo.setRealIncomeSumInfo(realIncomeSumInfo.toString());
		return incomeInfo;
	}
}
