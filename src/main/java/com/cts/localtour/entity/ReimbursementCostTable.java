package com.cts.localtour.entity;
// Generated 2017-7-3 13:59:12 by Hibernate Tools 3.4.0.CR1

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
 * ReimbursementCostTable generated by hbm2java
 */
@Entity
@Table(name = "reimbursementCostTable", catalog = "localtour")
public class ReimbursementCostTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private Date costDate;
	private int supplierScopeId;
	private Integer contentId;
	private int supplierId;
	private BigDecimal cost;
	private int count;
	private int days;
	private boolean remittanced;
	private Date remittanceDate;
	private boolean bill;
	private String remark;
	private BigDecimal reimbursement;
	private int payStatus;

	public ReimbursementCostTable() {
	}

	public ReimbursementCostTable(int tourId, int supplierScopeId, int supplierId, BigDecimal cost, int count, int days,
			boolean remittanced, boolean bill, int payStatus) {
		this.tourId = tourId;
		this.supplierScopeId = supplierScopeId;
		this.supplierId = supplierId;
		this.cost = cost;
		this.count = count;
		this.days = days;
		this.remittanced = remittanced;
		this.bill = bill;
		this.payStatus = payStatus;
	}

	public ReimbursementCostTable(int tourId, Date costDate, int supplierScopeId, Integer contentId, int supplierId,
			BigDecimal cost, int count, int days, boolean remittanced, Date remittanceDate, boolean bill, String remark,
			BigDecimal reimbursement, int payStatus) {
		this.tourId = tourId;
		this.costDate = costDate;
		this.supplierScopeId = supplierScopeId;
		this.contentId = contentId;
		this.supplierId = supplierId;
		this.cost = cost;
		this.count = count;
		this.days = days;
		this.remittanced = remittanced;
		this.remittanceDate = remittanceDate;
		this.bill = bill;
		this.remark = remark;
		this.reimbursement = reimbursement;
		this.payStatus = payStatus;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "tourID", nullable = false)
	public int getTourId() {
		return this.tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "costDate", length = 10)
	public Date getCostDate() {
		return this.costDate;
	}

	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}

	@Column(name = "supplierScopeID", nullable = false)
	public int getSupplierScopeId() {
		return this.supplierScopeId;
	}

	public void setSupplierScopeId(int supplierScopeId) {
		this.supplierScopeId = supplierScopeId;
	}

	@Column(name = "contentID")
	public Integer getContentId() {
		return this.contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	@Column(name = "supplierID", nullable = false)
	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "cost", nullable = false, precision = 11)
	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Column(name = "count", nullable = false)
	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Column(name = "days", nullable = false)
	public int getDays() {
		return this.days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	@Column(name = "remittanced", nullable = false)
	public boolean isRemittanced() {
		return this.remittanced;
	}

	public void setRemittanced(boolean remittanced) {
		this.remittanced = remittanced;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "remittanceDate", length = 10)
	public Date getRemittanceDate() {
		return this.remittanceDate;
	}

	public void setRemittanceDate(Date remittanceDate) {
		this.remittanceDate = remittanceDate;
	}

	@Column(name = "bill", nullable = false)
	public boolean isBill() {
		return this.bill;
	}

	public void setBill(boolean bill) {
		this.bill = bill;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "reimbursement", precision = 11)
	public BigDecimal getReimbursement() {
		return this.reimbursement;
	}

	public void setReimbursement(BigDecimal reimbursement) {
		this.reimbursement = reimbursement;
	}

	@Column(name = "payStatus", nullable = false)
	public int getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

}
