package com.cts.localtour.entity;
// Generated 2017-4-25 14:07:33 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ReimbursementIncomeTable generated by hbm2java
 */
@Entity
@Table(name = "reimbursementIncomeTable", catalog = "localtour")
public class ReimbursementIncomeTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private int customerAgencyId;
	private float income;
	private String remark;

	public ReimbursementIncomeTable() {
	}

	public ReimbursementIncomeTable(int tourId, int customerAgencyId, float income) {
		this.tourId = tourId;
		this.customerAgencyId = customerAgencyId;
		this.income = income;
	}

	public ReimbursementIncomeTable(int tourId, int customerAgencyId, float income, String remark) {
		this.tourId = tourId;
		this.customerAgencyId = customerAgencyId;
		this.income = income;
		this.remark = remark;
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

	@Column(name = "income", nullable = false, precision = 12, scale = 0)
	public float getIncome() {
		return this.income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}