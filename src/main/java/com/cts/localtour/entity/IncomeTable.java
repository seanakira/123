package com.cts.localtour.entity;
// Generated 2017-5-27 11:37:14 by Hibernate Tools 3.4.0.CR1

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
 * IncomeTable generated by hbm2java
 */
@Entity
@Table(name = "incomeTable", catalog = "localtour")
public class IncomeTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private Date incomeDate;
	private int customerAgencyId;
	private BigDecimal income;
	private BigDecimal realIncome;
	private Integer handlerId;
	private String remark;

	public IncomeTable() {
	}

	public IncomeTable(int tourId, int customerAgencyId, BigDecimal income, BigDecimal realIncome) {
		this.tourId = tourId;
		this.customerAgencyId = customerAgencyId;
		this.income = income;
		this.realIncome = realIncome;
	}

	public IncomeTable(int tourId, Date incomeDate, int customerAgencyId, BigDecimal income, BigDecimal realIncome,
			Integer handlerId, String remark) {
		this.tourId = tourId;
		this.incomeDate = incomeDate;
		this.customerAgencyId = customerAgencyId;
		this.income = income;
		this.realIncome = realIncome;
		this.handlerId = handlerId;
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

	@Column(name = "income", nullable = false, precision = 11)
	public BigDecimal getIncome() {
		return this.income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	@Column(name = "realIncome", nullable = false, precision = 11)
	public BigDecimal getRealIncome() {
		return this.realIncome;
	}

	public void setRealIncome(BigDecimal realIncome) {
		this.realIncome = realIncome;
	}

	@Column(name = "handlerId")
	public Integer getHandlerId() {
		return this.handlerId;
	}

	public void setHandlerId(Integer handlerId) {
		this.handlerId = handlerId;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
