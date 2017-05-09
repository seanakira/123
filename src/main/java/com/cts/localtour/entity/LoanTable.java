package com.cts.localtour.entity;
// Generated 2017-5-9 11:28:52 by Hibernate Tools 3.4.0.CR1

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
 * LoanTable generated by hbm2java
 */
@Entity
@Table(name = "loanTable", catalog = "localtour")
public class LoanTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private BigDecimal loanAmount;
	private Date loanDate;
	private Integer applicationerId;
	private int lenderId;
	private Integer managerId;
	private Integer bossId;
	private boolean lended;
	private String remark;
	private Integer printCount;
	private int status;

	public LoanTable() {
	}

	public LoanTable(int tourId, BigDecimal loanAmount, Date loanDate, int lenderId, boolean lended, int status) {
		this.tourId = tourId;
		this.loanAmount = loanAmount;
		this.loanDate = loanDate;
		this.lenderId = lenderId;
		this.lended = lended;
		this.status = status;
	}

	public LoanTable(int tourId, BigDecimal loanAmount, Date loanDate, Integer applicationerId, int lenderId,
			Integer managerId, Integer bossId, boolean lended, String remark, Integer printCount, int status) {
		this.tourId = tourId;
		this.loanAmount = loanAmount;
		this.loanDate = loanDate;
		this.applicationerId = applicationerId;
		this.lenderId = lenderId;
		this.managerId = managerId;
		this.bossId = bossId;
		this.lended = lended;
		this.remark = remark;
		this.printCount = printCount;
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

	@Column(name = "loanAmount", nullable = false, precision = 11)
	public BigDecimal getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "loanDate", nullable = false, length = 10)
	public Date getLoanDate() {
		return this.loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	@Column(name = "applicationerId")
	public Integer getApplicationerId() {
		return this.applicationerId;
	}

	public void setApplicationerId(Integer applicationerId) {
		this.applicationerId = applicationerId;
	}

	@Column(name = "lenderId", nullable = false)
	public int getLenderId() {
		return this.lenderId;
	}

	public void setLenderId(int lenderId) {
		this.lenderId = lenderId;
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

	@Column(name = "lended", nullable = false)
	public boolean isLended() {
		return this.lended;
	}

	public void setLended(boolean lended) {
		this.lended = lended;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "printCount")
	public Integer getPrintCount() {
		return this.printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	@Column(name = "status", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
