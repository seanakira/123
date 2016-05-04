package com.cts.localtour.entity;
// Generated 2016-5-4 17:53:20 by Hibernate Tools 3.4.0.CR1

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
	private String tourNo;
	private Date costDate;
	private int contentId;
	private int supplierId;
	private float cost;
	private float price;
	private int count;
	private boolean canBorrow;
	private float lendAmount;
	private int days;
	private int supplierScopeId;
	private String remark;

	public CostTable() {
	}

	public CostTable(String tourNo, Date costDate, int contentId, int supplierId, float cost, float price, int count,
			boolean canBorrow, float lendAmount, int days, int supplierScopeId, String remark) {
		this.tourNo = tourNo;
		this.costDate = costDate;
		this.contentId = contentId;
		this.supplierId = supplierId;
		this.cost = cost;
		this.price = price;
		this.count = count;
		this.canBorrow = canBorrow;
		this.lendAmount = lendAmount;
		this.days = days;
		this.supplierScopeId = supplierScopeId;
		this.remark = remark;
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

	@Column(name = "tourNO", nullable = false, length = 16)
	public String getTourNo() {
		return this.tourNo;
	}

	public void setTourNo(String tourNo) {
		this.tourNo = tourNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "costDate", nullable = false, length = 10)
	public Date getCostDate() {
		return this.costDate;
	}

	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}

	@Column(name = "contentID", nullable = false)
	public int getContentId() {
		return this.contentId;
	}

	public void setContentId(int contentId) {
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

	@Column(name = "price", nullable = false, precision = 12, scale = 0)
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Column(name = "count", nullable = false)
	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Column(name = "canBorrow", nullable = false)
	public boolean isCanBorrow() {
		return this.canBorrow;
	}

	public void setCanBorrow(boolean canBorrow) {
		this.canBorrow = canBorrow;
	}

	@Column(name = "lendAmount", nullable = false, precision = 12, scale = 0)
	public float getLendAmount() {
		return this.lendAmount;
	}

	public void setLendAmount(float lendAmount) {
		this.lendAmount = lendAmount;
	}

	@Column(name = "days", nullable = false)
	public int getDays() {
		return this.days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	@Column(name = "supplierScopeID", nullable = false)
	public int getSupplierScopeId() {
		return this.supplierScopeId;
	}

	public void setSupplierScopeId(int supplierScopeId) {
		this.supplierScopeId = supplierScopeId;
	}

	@Column(name = "remark", nullable = false, length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
