package com.cts.localtour.entity;
// Generated 2016-10-21 12:34:27 by Hibernate Tools 3.4.0.CR1

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
 * IncomeTable generated by hbm2java
 */
@Entity
@Table(name = "incomeTable", catalog = "localtour")
public class IncomeTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private Date incomeDate;
	private int customerAgencyId;
	private float income;
	private Float realIncome;
	private boolean incomed;
	private String remark;

	public IncomeTable() {
	}

	public IncomeTable(int tourId, int customerAgencyId, float income, boolean incomed) {
		this.tourId = tourId;
		this.customerAgencyId = customerAgencyId;
		this.income = income;
		this.incomed = incomed;
	}

	public IncomeTable(int tourId, Date incomeDate, int customerAgencyId, float income, Float realIncome,
			boolean incomed, String remark) {
		this.tourId = tourId;
		this.incomeDate = incomeDate;
		this.customerAgencyId = customerAgencyId;
		this.income = income;
		this.realIncome = realIncome;
		this.incomed = incomed;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "incomeDate", length = 10)
	public Date getIncomeDate() {
		return this.incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
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

	@Column(name = "realIncome", precision = 12, scale = 0)
	public Float getRealIncome() {
		return this.realIncome;
	}

	public void setRealIncome(Float realIncome) {
		this.realIncome = realIncome;
	}

	@Column(name = "incomed", nullable = false)
	public boolean isIncomed() {
		return this.incomed;
	}

	public void setIncomed(boolean incomed) {
		this.incomed = incomed;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
