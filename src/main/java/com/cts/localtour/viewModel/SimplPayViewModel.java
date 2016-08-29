package com.cts.localtour.viewModel;

import com.cts.localtour.entity.LocalTourTable;

public class SimplPayViewModel {
	private LocalTourTable localTourTable;
	private float loan;
	private float remittance;
	private float cost;
	private float realPay;
	private String realName;
	private float sumCache;
	private String status;
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
	}
	public float getLoan() {
		return loan;
	}
	public void setLoan(float loan) {
		this.loan = loan;
	}
	public float getRemittance() {
		return remittance;
	}
	public void setRemittance(float remittance) {
		this.remittance = remittance;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public float getSumCache() {
		return sumCache;
	}
	public void setSumCache(float sumCache) {
		this.sumCache = sumCache;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getRealPay() {
		return realPay;
	}
	public void setRealPay(float realPay) {
		this.realPay = realPay;
	}
	
}
