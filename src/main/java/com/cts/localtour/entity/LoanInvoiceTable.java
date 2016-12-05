package com.cts.localtour.entity;
// Generated 2016-11-29 15:16:41 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * LoanInvoiceTable generated by hbm2java
 */
@Entity
@Table(name = "loanInvoiceTable", catalog = "localtour", uniqueConstraints = @UniqueConstraint(columnNames = "invoiceNo"))
public class LoanInvoiceTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private String invoiceName;
	private String invoiceContent;
	private Integer invoiceNo;
	private float invoiceAmount;
	private Date issueDate;
	private int issueUserId;
	private Integer applicationerId;
	private String remark;
	private int status;

	public LoanInvoiceTable() {
	}

	public LoanInvoiceTable(int tourId, float invoiceAmount, Date issueDate, int issueUserId, int status) {
		this.tourId = tourId;
		this.invoiceAmount = invoiceAmount;
		this.issueDate = issueDate;
		this.issueUserId = issueUserId;
		this.status = status;
	}

	public LoanInvoiceTable(int tourId, String invoiceName, String invoiceContent, Integer invoiceNo,
			float invoiceAmount, Date issueDate, int issueUserId, Integer applicationerId, String remark, int status) {
		this.tourId = tourId;
		this.invoiceName = invoiceName;
		this.invoiceContent = invoiceContent;
		this.invoiceNo = invoiceNo;
		this.invoiceAmount = invoiceAmount;
		this.issueDate = issueDate;
		this.issueUserId = issueUserId;
		this.applicationerId = applicationerId;
		this.remark = remark;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "tourId", nullable = false)
	public int getTourId() {
		return this.tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	@Column(name = "invoiceName", length = 40)
	public String getInvoiceName() {
		return this.invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	@Column(name = "invoiceContent", length = 65535)
	public String getInvoiceContent() {
		return this.invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	@Column(name = "invoiceNo", unique = true)
	public Integer getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "invoiceAmount", nullable = false, precision = 12, scale = 0)
	public float getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "issueDate", nullable = false, length = 10)
	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@Column(name = "issueUserId", nullable = false)
	public int getIssueUserId() {
		return this.issueUserId;
	}

	public void setIssueUserId(int issueUserId) {
		this.issueUserId = issueUserId;
	}

	@Column(name = "applicationerId")
	public Integer getApplicationerId() {
		return this.applicationerId;
	}

	public void setApplicationerId(Integer applicationerId) {
		this.applicationerId = applicationerId;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "status", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
