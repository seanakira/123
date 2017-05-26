package com.cts.localtour.entity;
// Generated 2017-5-25 17:25:22 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LoanInvoiceTable generated by hbm2java
 */
@Entity
@Table(name = "loanInvoiceTable", catalog = "localtour")
public class LoanInvoiceTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private int customerAgencyId;
	private String invoiceContent;
	private String invoiceNo;
	private BigDecimal invoiceAmount;
	private Date issueDate;
	private int issueUserId;
	private Integer applicationerId;
	private Integer managerId;
	private String remark;
	private int status;
	private Integer printCount;

	public LoanInvoiceTable() {
	}

	public LoanInvoiceTable(int tourId, int customerAgencyId, BigDecimal invoiceAmount, Date issueDate, int issueUserId,
			int status) {
		this.tourId = tourId;
		this.customerAgencyId = customerAgencyId;
		this.invoiceAmount = invoiceAmount;
		this.issueDate = issueDate;
		this.issueUserId = issueUserId;
		this.status = status;
	}

	public LoanInvoiceTable(int tourId, int customerAgencyId, String invoiceContent, String invoiceNo,
			BigDecimal invoiceAmount, Date issueDate, int issueUserId, Integer applicationerId, Integer managerId,
			String remark, int status, Integer printCount) {
		this.tourId = tourId;
		this.customerAgencyId = customerAgencyId;
		this.invoiceContent = invoiceContent;
		this.invoiceNo = invoiceNo;
		this.invoiceAmount = invoiceAmount;
		this.issueDate = issueDate;
		this.issueUserId = issueUserId;
		this.applicationerId = applicationerId;
		this.managerId = managerId;
		this.remark = remark;
		this.status = status;
		this.printCount = printCount;
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

	@Column(name = "customerAgencyId", nullable = false)
	public int getCustomerAgencyId() {
		return this.customerAgencyId;
	}

	public void setCustomerAgencyId(int customerAgencyId) {
		this.customerAgencyId = customerAgencyId;
	}

	@Column(name = "invoiceContent", length = 65535)
	public String getInvoiceContent() {
		return this.invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	@Column(name = "invoiceNo")
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "invoiceAmount", nullable = false, precision = 11)
	public BigDecimal getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
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

	@Column(name = "managerId")
	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
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

	@Column(name = "printCount")
	public Integer getPrintCount() {
		return this.printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

}
