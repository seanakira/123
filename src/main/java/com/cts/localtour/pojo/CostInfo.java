package com.cts.localtour.pojo;

import java.math.BigDecimal;

public class CostInfo {
	private BigDecimal costSum;
	private BigDecimal canCostSum;
	private BigDecimal willCostSum;
	private BigDecimal realCostSum;
	private BigDecimal reimbursementSum;
	private String costSumInfo;
	private String willCostSumInfo;
	private String realCostSumInfo;
	private String reimbursementSumInfo;
	public BigDecimal getCostSum() {
		return costSum;
	}
	public void setCostSum(BigDecimal costSum) {
		this.costSum = costSum;
	}
	public BigDecimal getCanCostSum() {
		return canCostSum;
	}
	public void setCanCostSum(BigDecimal canCostSum) {
		this.canCostSum = canCostSum;
	}
	public BigDecimal getWillCostSum() {
		return willCostSum;
	}
	public void setWillCostSum(BigDecimal willCostSum) {
		this.willCostSum = willCostSum;
	}
	public BigDecimal getRealCostSum() {
		return realCostSum;
	}
	public void setRealCostSum(BigDecimal realCostSum) {
		this.realCostSum = realCostSum;
	}
	public BigDecimal getReimbursementSum() {
		return reimbursementSum;
	}
	public void setReimbursementSum(BigDecimal reimbursementSum) {
		this.reimbursementSum = reimbursementSum;
	}
	public String getCostSumInfo() {
		return costSumInfo;
	}
	public void setCostSumInfo(String costSumInfo) {
		this.costSumInfo = costSumInfo;
	}
	public String getWillCostSumInfo() {
		return willCostSumInfo;
	}
	public void setWillCostSumInfo(String willCostSumInfo) {
		this.willCostSumInfo = willCostSumInfo;
	}
	public String getRealCostSumInfo() {
		return realCostSumInfo;
	}
	public void setRealCostSumInfo(String realCostSumInfo) {
		this.realCostSumInfo = realCostSumInfo;
	}
	public String getReimbursementSumInfo() {
		return reimbursementSumInfo;
	}
	public void setReimbursementSumInfo(String reimbursementSumInfo) {
		this.reimbursementSumInfo = reimbursementSumInfo;
	}
}
