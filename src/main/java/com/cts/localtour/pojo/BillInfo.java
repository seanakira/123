package com.cts.localtour.pojo;

import java.math.BigDecimal;

public class BillInfo {
	private String settlementDate;
	private BigDecimal billSum;
	private BigDecimal applicationSum;
	private BigDecimal willRemittanceSum;
	private BigDecimal remittancedSum;
	private boolean empty;
	public BigDecimal getBillSum() {
		return billSum;
	}
	public void setBillSum(BigDecimal billSum) {
		this.billSum = billSum;
	}
	public BigDecimal getApplicationSum() {
		return applicationSum;
	}
	public void setApplicationSum(BigDecimal applicationSum) {
		this.applicationSum = applicationSum;
	}
	public BigDecimal getWillRemittanceSum() {
		return willRemittanceSum;
	}
	public void setWillRemittanceSum(BigDecimal willRemittanceSum) {
		this.willRemittanceSum = willRemittanceSum;
	}
	public BigDecimal getRemittancedSum() {
		return remittancedSum;
	}
	public void setRemittancedSum(BigDecimal remittancedSum) {
		this.remittancedSum = remittancedSum;
	}
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public boolean isEmpty() {
		return empty;
	}
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
}
