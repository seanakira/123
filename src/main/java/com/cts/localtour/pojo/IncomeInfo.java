package com.cts.localtour.pojo;

import java.math.BigDecimal;

public class IncomeInfo {
	private BigDecimal IncomeSum;
	private BigDecimal realIncomeSum;
	private String incomeSumInfo;
	private String realIncomeSumInfo;
	public BigDecimal getIncomeSum() {
		return IncomeSum;
	}
	public void setIncomeSum(BigDecimal incomeSum) {
		IncomeSum = incomeSum;
	}
	public BigDecimal getRealIncomeSum() {
		return realIncomeSum;
	}
	public void setRealIncomeSum(BigDecimal realIncomeSum) {
		this.realIncomeSum = realIncomeSum;
	}
	public String getIncomeSumInfo() {
		return incomeSumInfo;
	}
	public void setIncomeSumInfo(String incomeSumInfo) {
		this.incomeSumInfo = incomeSumInfo;
	}
	public String getRealIncomeSumInfo() {
		return realIncomeSumInfo;
	}
	public void setRealIncomeSumInfo(String realIncomeSumInfo) {
		this.realIncomeSumInfo = realIncomeSumInfo;
	}
}
