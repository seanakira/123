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

/**
 * CostTable generated by hbm2java
 */
@Entity
@Table(name = "costTable", catalog = "localtour")
public class CostTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private Integer borrowUserId;
	private Date costDate;
	private int supplierScopeId;
	private Integer contentId;
	private int supplierId;
	private float cost;
	private int count;
	private float realCost;
	private int days;
	private boolean remittanced;
	private boolean lend;
	private boolean bill;
	private String remark;
	private int payStatus;
	private Integer payApplicationerId;
	private Integer managerId;
	private Integer bossId;
	private Integer printCount;
	private Float reimbursement;

	public CostTable() {
	}

	public CostTable(int tourId, int supplierScopeId, int supplierId, float cost, int count, float realCost, int days,
			boolean remittanced, boolean lend, boolean bill, int payStatus) {
		this.tourId = tourId;
		this.supplierScopeId = supplierScopeId;
		this.supplierId = supplierId;
		this.cost = cost;
		this.count = count;
		this.realCost = realCost;
		this.days = days;
		this.remittanced = remittanced;
		this.lend = lend;
		this.bill = bill;
		this.payStatus = payStatus;
	}

	public CostTable(int tourId, Integer borrowUserId, Date costDate, int supplierScopeId, Integer contentId,
			int supplierId, float cost, int count, float realCost, int days, boolean remittanced, boolean lend,
			boolean bill, String remark, int payStatus, Integer payApplicationerId, Integer managerId, Integer bossId,
			Integer printCount, Float reimbursement) {
		this.tourId = tourId;
		this.borrowUserId = borrowUserId;
		this.costDate = costDate;
		this.supplierScopeId = supplierScopeId;
		this.contentId = contentId;
		this.supplierId = supplierId;
		this.cost = cost;
		this.count = count;
		this.realCost = realCost;
		this.days = days;
		this.remittanced = remittanced;
		this.lend = lend;
		this.bill = bill;
		this.remark = remark;
		this.payStatus = payStatus;
		this.payApplicationerId = payApplicationerId;
		this.managerId = managerId;
		this.bossId = bossId;
		this.printCount = printCount;
		this.reimbursement = reimbursement;
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

	@Column(name = "BorrowUserID")
	public Integer getBorrowUserId() {
		return this.borrowUserId;
	}

	public void setBorrowUserId(Integer borrowUserId) {
		this.borrowUserId = borrowUserId;
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

	@Column(name = "cost", nullable = false, precision = 12, scale = 0)
	public float getCost() {
		return this.cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	@Column(name = "count", nullable = false)
	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Column(name = "realCost", nullable = false, precision = 12, scale = 0)
	public float getRealCost() {
		return this.realCost;
	}

	public void setRealCost(float realCost) {
		this.realCost = realCost;
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

	@Column(name = "lend", nullable = false)
	public boolean isLend() {
		return this.lend;
	}

	public void setLend(boolean lend) {
		this.lend = lend;
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

	@Column(name = "payStatus", nullable = false)
	public int getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	@Column(name = "payApplicationerId")
	public Integer getPayApplicationerId() {
		return this.payApplicationerId;
	}

	public void setPayApplicationerId(Integer payApplicationerId) {
		this.payApplicationerId = payApplicationerId;
	}

	@Column(name = "managerId")
	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	@Column(name = "bossId")
	public Integer getBossId() {
		return this.bossId;
	}

	public void setBossId(Integer bossId) {
		this.bossId = bossId;
	}

	@Column(name = "printCount")
	public Integer getPrintCount() {
		return this.printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	@Column(name = "reimbursement", precision = 12, scale = 0)
	public Float getReimbursement() {
		return this.reimbursement;
	}

	public void setReimbursement(Float reimbursement) {
		this.reimbursement = reimbursement;
	}

}
