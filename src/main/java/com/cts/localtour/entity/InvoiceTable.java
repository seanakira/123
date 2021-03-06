package com.cts.localtour.entity;
// Generated 2017-3-27 13:53:58 by Hibernate Tools 3.4.0.CR1

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
 * InvoiceTable generated by hbm2java
 */
@Entity
@Table(name = "invoiceTable", catalog = "localtour", uniqueConstraints = @UniqueConstraint(columnNames = "invoiceNo"))
public class InvoiceTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private int customerAgencyId;
	private String invoiceContent;
	private String invoiceNo;
	private float invoiceAmount;
	private Date issueDate;
	private int issueUserId;

	public InvoiceTable() {
	}

	public InvoiceTable(int tourId, int customerAgencyId, String invoiceContent, String invoiceNo, float invoiceAmount,
			Date issueDate, int issueUserId) {
		this.tourId = tourId;
		this.customerAgencyId = customerAgencyId;
		this.invoiceContent = invoiceContent;
		this.invoiceNo = invoiceNo;
		this.invoiceAmount = invoiceAmount;
		this.issueDate = issueDate;
		this.issueUserId = issueUserId;
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

	@Column(name = "invoiceContent", nullable = false, length = 65535)
	public String getInvoiceContent() {
		return this.invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	@Column(name = "invoiceNo", unique = true, nullable = false)
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
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

}
