package com.cts.localtour.entity;
// Generated 2017-6-29 10:52:58 by Hibernate Tools 3.4.0.CR1

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
 * ReimbursementIncomeTable generated by hbm2java
 */
@Entity
@Table(name = "reimbursementIncomeTable", catalog = "localtour")
public class ReimbursementIncomeTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private int customerAgencyId;
	private BigDecimal income;
	private String remark;
	private Date incomeDate;

	public ReimbursementIncomeTable() {
	}

	public ReimbursementIncomeTable(int tourId, int customerAgencyId, BigDecimal income) {
		this.tourId = tourId;
		this.customerAgencyId = customerAgencyId;
		this.income = income;
	}

	public ReimbursementIncomeTable(int tourId, int customerAgencyId, BigDecimal income, String remark,
			Date incomeDate) {
		this.tourId = tourId;
		this.customerAgencyId = customerAgencyId;
		this.income = income;
		this.remark = remark;
		this.incomeDate = incomeDate;
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

	@Column(name = "income", nullable = false, precision = 11)
	public BigDecimal getIncome() {
		return this.income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "incomeDate", length = 10)
	public Date getIncomeDate() {
		return this.incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

}
