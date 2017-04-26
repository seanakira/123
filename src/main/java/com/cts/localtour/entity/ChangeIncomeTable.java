package com.cts.localtour.entity;
// Generated 2017-4-25 14:07:33 by Hibernate Tools 3.4.0.CR1

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
 * ChangeIncomeTable generated by hbm2java
 */
@Entity
@Table(name = "changeIncomeTable", catalog = "localtour")
public class ChangeIncomeTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private Date incomeDate;
	private Integer applicationerId;
	private int customerAgencyId;
	private float income;
	private float realIncome;
	private Integer handlerId;
	private String remark;
	private int status;

	public ChangeIncomeTable() {
	}

	public ChangeIncomeTable(int tourId, int customerAgencyId, float income, float realIncome, int status) {
		this.tourId = tourId;
		this.customerAgencyId = customerAgencyId;
		this.income = income;
		this.realIncome = realIncome;
		this.status = status;
	}

	public ChangeIncomeTable(int tourId, Date incomeDate, Integer applicationerId, int customerAgencyId, float income,
			float realIncome, Integer handlerId, String remark, int status) {
		this.tourId = tourId;
		this.incomeDate = incomeDate;
		this.applicationerId = applicationerId;
		this.customerAgencyId = customerAgencyId;
		this.income = income;
		this.realIncome = realIncome;
		this.handlerId = handlerId;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "incomeDate", length = 10)
	public Date getIncomeDate() {
		return this.incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	@Column(name = "applicationerId")
	public Integer getApplicationerId() {
		return this.applicationerId;
	}

	public void setApplicationerId(Integer applicationerId) {
		this.applicationerId = applicationerId;
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

	@Column(name = "realIncome", nullable = false, precision = 12, scale = 0)
	public float getRealIncome() {
		return this.realIncome;
	}

	public void setRealIncome(float realIncome) {
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

	@Column(name = "status", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
