package com.cts.localtour.pojo;

import java.math.BigDecimal;

public class LoanInfo {
	private BigDecimal loanSum;
	private BigDecimal willLoanSum;
	private BigDecimal realLoanSum;
	private String willLoanSumInfo;
	private String realLoanSumInfo;
	public BigDecimal getLoanSum() {
		return loanSum;
	}
	public void setLoanSum(BigDecimal loanSum) {
		this.loanSum = loanSum;
	}
	public BigDecimal getWillLoanSum() {
		return willLoanSum;
	}
	public void setWillLoanSum(BigDecimal willLoanSum) {
		this.willLoanSum = willLoanSum;
	}
	public BigDecimal getRealLoanSum() {
		return realLoanSum;
	}
	public void setRealLoanSum(BigDecimal realLoanSum) {
		this.realLoanSum = realLoanSum;
	}
	public String getWillLoanSumInfo() {
		return willLoanSumInfo;
	}
	public void setWillLoanSumInfo(String willLoanSumInfo) {
		this.willLoanSumInfo = willLoanSumInfo;
	}
	public String getRealLoanSumInfo() {
		return realLoanSumInfo;
	}
	public void setRealLoanSumInfo(String realLoanSumInfo) {
		this.realLoanSumInfo = realLoanSumInfo;
	}
}
