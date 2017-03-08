package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.pojo.IncomeInfo;

@SuppressWarnings("rawtypes")
@Service
public class ChangeIncomeService extends BaseService{
	@Autowired
	private CustomerAgencyService customerAgencyService;
	@SuppressWarnings("unchecked")
	public IncomeInfo getIncomeInfo(int tourId){
		IncomeInfo incomeInfo = new IncomeInfo();
		BigDecimal incomeSum = new BigDecimal(0);
		BigDecimal realIncomeSum = new BigDecimal(0);
		StringBuffer realIncomeSumInfo = new StringBuffer();
		StringBuffer incomeSumInfo = new StringBuffer();
		ArrayList<ChangeIncomeTable> changeIncomeTables = (ArrayList<ChangeIncomeTable>) this.getAllByString("ChangeIncomeTable", "tourId=? and status=3", tourId);
		for (ChangeIncomeTable changeIncomeTable : changeIncomeTables) {
			String customerAgencyName = customerAgencyService.getCustomerAgencyName(changeIncomeTable.getTourId());
			incomeSum = incomeSum.add(new BigDecimal(changeIncomeTable.getIncome()));
			incomeSumInfo.append(customerAgencyName).append(" ").append(changeIncomeTable.getIncome()).append(",");
			realIncomeSum = realIncomeSum.add(new BigDecimal(changeIncomeTable.getRealIncome()));
			realIncomeSumInfo.append(customerAgencyName).append(" ").append(changeIncomeTable.getRealIncome()).append(",");
		}
		incomeInfo.setRealIncomeSum(realIncomeSum);
		incomeInfo.setIncomeSum(incomeSum);
		incomeInfo.setIncomeSumInfo(incomeSumInfo.toString());
		incomeInfo.setRealIncomeSumInfo(realIncomeSumInfo.toString());
		return incomeInfo;
	}
}
