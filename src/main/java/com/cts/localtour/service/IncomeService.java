package com.cts.localtour.service;


import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.pojo.IncomeInfo;

@SuppressWarnings("rawtypes")
@Service
public class IncomeService extends BaseService{
	@Autowired
	private CustomerAgencyService customerAgencyService;
	@SuppressWarnings("unchecked")
	public IncomeInfo getIncomeInfo(int tourId){
		IncomeInfo incomeInfo = new IncomeInfo();
		BigDecimal incomeSum = new BigDecimal(0);
		BigDecimal realIncomeSum = new BigDecimal(0);
		StringBuffer realIncomeSumInfo = new StringBuffer();
		StringBuffer incomeSumInfo = new StringBuffer();
		ArrayList<IncomeTable> incomeTables = (ArrayList<IncomeTable>) this.getAllByString("IncomeTable", "tourId=?", tourId);
		for (IncomeTable incomeTable : incomeTables) {
			String customerAgencyName = customerAgencyService.getCustomerAgencyName(incomeTable.getTourId());
			incomeSum = incomeSum.add(incomeTable.getIncome());
			incomeSumInfo.append(customerAgencyName).append(" ").append(incomeTable.getIncome()).append(",");
			realIncomeSum = realIncomeSum.add(incomeTable.getRealIncome());
			realIncomeSumInfo.append(customerAgencyName).append(" ").append(incomeTable.getRealIncome()).append(",");
		}
		incomeInfo.setRealIncomeSum(realIncomeSum);
		incomeInfo.setIncomeSum(incomeSum);
		incomeInfo.setIncomeSumInfo(incomeSumInfo.toString());
		incomeInfo.setRealIncomeSumInfo(realIncomeSumInfo.toString());
		return incomeInfo;
	}
}
