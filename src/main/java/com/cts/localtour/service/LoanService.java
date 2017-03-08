package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.pojo.LoanInfo;

@SuppressWarnings("rawtypes")
@Service
public class LoanService extends BaseService{
	@Autowired
	private UserService userService;
	@SuppressWarnings("unchecked")
	public LoanInfo getLoanInfo(int tourId){
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=?", tourId);
		BigDecimal loanSum = new BigDecimal(0.00);
		BigDecimal willLoanSum = new BigDecimal(0.00);
		BigDecimal realLoanSum = new BigDecimal(0.00);
		StringBuffer willLoanSumInfo = new StringBuffer();
		StringBuffer realLoanSumInfo = new StringBuffer();
		for (LoanTable loanTable : loanTables) {
			loanSum = loanSum.add(new BigDecimal(loanTable.getLoanAmount()));
			if(loanTable.getStatus()==4){
				willLoanSum = willLoanSum.add(new BigDecimal(loanTable.getLoanAmount()));
				willLoanSumInfo.append(userService.getUserRealName(loanTable.getApplicationerId())).append(" ½è¿î ").append(loanTable.getLoanAmount()).append(",");
			}
			if(loanTable.isLended()){
				realLoanSum = realLoanSum.add(new BigDecimal(loanTable.getLoanAmount()));
				realLoanSumInfo.append(userService.getUserRealName(loanTable.getApplicationerId())).append(" ½è¿î ").append(loanTable.getLoanAmount()).append(",");
			}
		}
		LoanInfo loanInfo = new LoanInfo();
		loanInfo.setLoanSum(loanSum);
		loanInfo.setWillLoanSum(willLoanSum);
		loanInfo.setRealLoanSum(realLoanSum);
		loanInfo.setWillLoanSumInfo(willLoanSumInfo.toString());
		loanInfo.setRealLoanSumInfo(realLoanSumInfo.toString());
		return loanInfo;
	}
}
