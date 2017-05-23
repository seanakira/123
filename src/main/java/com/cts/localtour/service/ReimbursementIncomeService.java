package com.cts.localtour.service;

import java.math.BigDecimal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ReimbursementIncomeTable;
import com.cts.localtour.pojo.IncomeInfo;

@SuppressWarnings("rawtypes")
@Service
public class ReimbursementIncomeService extends BaseService{
	@Autowired
	private CustomerAgencyService customerAgencyService;
	@SuppressWarnings("unchecked")
	public IncomeInfo getIncomeInfo(int tourId){
		IncomeInfo incomeInfo = new IncomeInfo();
		BigDecimal incomeSum = new BigDecimal(0);
		BigDecimal realIncomeSum = new BigDecimal(0);
		StringBuffer realIncomeSumInfo = new StringBuffer();
		StringBuffer incomeSumInfo = new StringBuffer();
		ArrayList<ReimbursementIncomeTable> incomeTables = (ArrayList<ReimbursementIncomeTable>) this.getAllByString("ReimbursementIncomeTable", "tourId=?", tourId);
		String customerAgencyName = "";
		if(!incomeTables.isEmpty()){
			customerAgencyName = customerAgencyService.getCustomerAgencyName(tourId);
		}
		for (ReimbursementIncomeTable incomeTable : incomeTables) {
			incomeSum = incomeSum.add(incomeTable.getIncome());
			incomeSumInfo.append(customerAgencyName).append(" ").append(incomeTable.getIncome()).append(",");
		}
		incomeInfo.setRealIncomeSum(realIncomeSum);
		incomeInfo.setIncomeSum(incomeSum);
		incomeInfo.setIncomeSumInfo(incomeSumInfo.toString());
		incomeInfo.setRealIncomeSumInfo(realIncomeSumInfo.toString());
		return incomeInfo;
	}
}