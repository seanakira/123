package com.cts.localtour.entity;
// Generated 2017-7-7 14:48:25 by Hibernate Tools 3.4.0.CR1

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
 * RefundTable generated by hbm2java
 */
@Entity
@Table(name = "refundTable", catalog = "localtour")
public class RefundTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private Date refundDate;
	private String refundContent;
	private String refundWays;
	private BigDecimal refundAmount;
	private boolean refunded;
	private int customerAgencyId;
	private int applicationerId;
	private Integer managerId;
	private Integer bossId;
	private Integer handlerId;
	private String remark;
	private int status;
	private Integer printCount;

	public RefundTable() {
	}

	public RefundTable(int tourId, Date refundDate, BigDecimal refundAmount, boolean refunded, int customerAgencyId,
			int applicationerId, int status) {
		this.tourId = tourId;
		this.refundDate = refundDate;
		this.refundAmount = refundAmount;
		this.refunded = refunded;
		this.customerAgencyId = customerAgencyId;
		this.applicationerId = applicationerId;
		this.status = status;
	}

	public RefundTable(int tourId, Date refundDate, String refundContent, String refundWays, BigDecimal refundAmount,
			boolean refunded, int customerAgencyId, int applicationerId, Integer managerId, Integer bossId,
			Integer handlerId, String remark, int status, Integer printCount) {
		this.tourId = tourId;
		this.refundDate = refundDate;
		this.refundContent = refundContent;
		this.refundWays = refundWays;
		this.refundAmount = refundAmount;
		this.refunded = refunded;
		this.customerAgencyId = customerAgencyId;
		this.applicationerId = applicationerId;
		this.managerId = managerId;
		this.bossId = bossId;
		this.handlerId = handlerId;
		this.remark = remark;
		this.status = status;
		this.printCount = printCount;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
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
	@Column(name = "refundDate", nullable = false, length = 10)
	public Date getRefundDate() {
		return this.refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	@Column(name = "refundContent", length = 20)
	public String getRefundContent() {
		return this.refundContent;
	}

	public void setRefundContent(String refundContent) {
		this.refundContent = refundContent;
	}

	@Column(name = "refundWays", length = 20)
	public String getRefundWays() {
		return this.refundWays;
	}

	public void setRefundWays(String refundWays) {
		this.refundWays = refundWays;
	}

	@Column(name = "refundAmount", nullable = false, precision = 11)
	public BigDecimal getRefundAmount() {
		return this.refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	@Column(name = "refunded", nullable = false)
	public boolean isRefunded() {
		return this.refunded;
	}

	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}

	@Column(name = "customerAgencyId", nullable = false)
	public int getCustomerAgencyId() {
		return this.customerAgencyId;
	}

	public void setCustomerAgencyId(int customerAgencyId) {
		this.customerAgencyId = customerAgencyId;
	}

	@Column(name = "applicationerId", nullable = false)
	public int getApplicationerId() {
		return this.applicationerId;
	}

	public void setApplicationerId(int applicationerId) {
		this.applicationerId = applicationerId;
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

	@Column(name = "printCount")
	public Integer getPrintCount() {
		return this.printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

}
