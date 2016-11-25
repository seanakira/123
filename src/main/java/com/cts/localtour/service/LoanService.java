package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.pojo.LoanInfo;

@SuppressWarnings("rawtypes")
@Service
public class LoanService extends BaseService{
	@SuppressWarnings("unchecked")
	public LoanInfo getLoanInfo(int tourId){
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=?", tourId);
		BigDecimal loanSum = new BigDecimal(0.00);
		BigDecimal willLoanSum = new BigDecimal(0.00);
		BigDecimal realLoanSum = new BigDecimal(0.00);
		for (LoanTable loanTable : loanTables) {
			loanSum = loanSum.add(new BigDecimal(loanTable.getLoanAmount()));
			if(loanTable.getStatus()==4){
				willLoanSum = willLoanSum.add(new BigDecimal(loanTable.getLoanAmount()));
			}
			if(loanTable.isLended()){
				realLoanSum = realLoanSum.add(new BigDecimal(loanTable.getLoanAmount()));
			}
		}
		LoanInfo loanInfo = new LoanInfo();
		loanInfo.setLoanSum(loanSum);
		loanInfo.setWillLoanSum(willLoanSum);
		loanInfo.setRealLoanSum(realLoanSum);
		return loanInfo;
	}
}
