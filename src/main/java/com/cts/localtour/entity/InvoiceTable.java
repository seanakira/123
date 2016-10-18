package com.cts.localtour.entity;
// Generated 2016-10-18 12:14:38 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * InvoiceTable generated by hbm2java
 */
@Entity
@Table(name = "invoiceTable", catalog = "localtour")
public class InvoiceTable implements java.io.Serializable {

	private int id;
	private Integer incomeId;
	private Integer changeIncomeId;
	private String invoiceName;
	private String invoiceContent;
	private int invoiceNo;
	private int requestUserId;
	private float invoiceAmount;
	private Date openDate;
	private int openUserId;
	private int lend;
	private int isLend;

	public InvoiceTable() {
	}

	public InvoiceTable(int id, String invoiceName, String invoiceContent, int invoiceNo, int requestUserId,
			float invoiceAmount, Date openDate, int openUserId, int lend, int isLend) {
		this.id = id;
		this.invoiceName = invoiceName;
		this.invoiceContent = invoiceContent;
		this.invoiceNo = invoiceNo;
		this.requestUserId = requestUserId;
		this.invoiceAmount = invoiceAmount;
		this.openDate = openDate;
		this.openUserId = openUserId;
		this.lend = lend;
		this.isLend = isLend;
	}

	public InvoiceTable(int id, Integer incomeId, Integer changeIncomeId, String invoiceName, String invoiceContent,
			int invoiceNo, int requestUserId, float invoiceAmount, Date openDate, int openUserId, int lend,
			int isLend) {
		this.id = id;
		this.incomeId = incomeId;
		this.changeIncomeId = changeIncomeId;
		this.invoiceName = invoiceName;
		this.invoiceContent = invoiceContent;
		this.invoiceNo = invoiceNo;
		this.requestUserId = requestUserId;
		this.invoiceAmount = invoiceAmount;
		this.openDate = openDate;
		this.openUserId = openUserId;
		this.lend = lend;
		this.isLend = isLend;
	}

	@Id

	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "incomeId")
	public Integer getIncomeId() {
		return this.incomeId;
	}

	public void setIncomeId(Integer incomeId) {
		this.incomeId = incomeId;
	}

	@Column(name = "changeIncomeId")
	public Integer getChangeIncomeId() {
		return this.changeIncomeId;
	}

	public void setChangeIncomeId(Integer changeIncomeId) {
		this.changeIncomeId = changeIncomeId;
	}

	@Column(name = "invoiceName", nullable = false, length = 40)
	public String getInvoiceName() {
		return this.invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	@Column(name = "invoiceContent", nullable = false, length = 10)
	public String getInvoiceContent() {
		return this.invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	@Column(name = "invoiceNo", nullable = false)
	public int getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "requestUserId", nullable = false)
	public int getRequestUserId() {
		return this.requestUserId;
	}

	public void setRequestUserId(int requestUserId) {
		this.requestUserId = requestUserId;
	}

	@Column(name = "invoiceAmount", nullable = false, precision = 12, scale = 0)
	public float getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "openDate", nullable = false, length = 10)
	public Date getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	@Column(name = "openUserId", nullable = false)
	public int getOpenUserId() {
		return this.openUserId;
	}

	public void setOpenUserId(int openUserId) {
		this.openUserId = openUserId;
	}

	@Column(name = "lend", nullable = false)
	public int getLend() {
		return this.lend;
	}

	public void setLend(int lend) {
		this.lend = lend;
	}

	@Column(name = "isLend", nullable = false)
	public int getIsLend() {
		return this.isLend;
	}

	public void setIsLend(int isLend) {
		this.isLend = isLend;
	}

}
