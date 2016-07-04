package com.cts.localtour.entity;
// Generated 2016-6-30 17:22:00 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * LocalTourTable generated by hbm2java
 */
@Entity
@Table(name = "localTourTable", catalog = "localtour", uniqueConstraints = @UniqueConstraint(columnNames = "tourNO"))
public class LocalTourTable implements java.io.Serializable {

	private Integer id;
	private String tourNo;
	private String tourName;
	private String guideIds;
	private int userId;
	private int deptId;
	private int businessTypeId;
	private int tourTypeId;
	private int regionId;
	private int visitorTypeId;
	private int adultNo;
	private Integer childrenNo;
	private Integer qpGuideNo;
	private String organizor;
	private int customerAgencyId;
	private Date startTime;
	private Date endTime;
	private String remark;
	private int status;
	private boolean enable;

	public LocalTourTable() {
	}

	public LocalTourTable(String tourNo, String tourName, int userId, int deptId, int businessTypeId, int tourTypeId,
			int regionId, int visitorTypeId, int adultNo, String organizor, int customerAgencyId, Date startTime,
			Date endTime, int status, boolean enable) {
		this.tourNo = tourNo;
		this.tourName = tourName;
		this.userId = userId;
		this.deptId = deptId;
		this.businessTypeId = businessTypeId;
		this.tourTypeId = tourTypeId;
		this.regionId = regionId;
		this.visitorTypeId = visitorTypeId;
		this.adultNo = adultNo;
		this.organizor = organizor;
		this.customerAgencyId = customerAgencyId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.enable = enable;
	}

	public LocalTourTable(String tourNo, String tourName, String guideIds, int userId, int deptId, int businessTypeId,
			int tourTypeId, int regionId, int visitorTypeId, int adultNo, Integer childrenNo, Integer qpGuideNo,
			String organizor, int customerAgencyId, Date startTime, Date endTime, String remark, int status,
			boolean enable) {
		this.tourNo = tourNo;
		this.tourName = tourName;
		this.guideIds = guideIds;
		this.userId = userId;
		this.deptId = deptId;
		this.businessTypeId = businessTypeId;
		this.tourTypeId = tourTypeId;
		this.regionId = regionId;
		this.visitorTypeId = visitorTypeId;
		this.adultNo = adultNo;
		this.childrenNo = childrenNo;
		this.qpGuideNo = qpGuideNo;
		this.organizor = organizor;
		this.customerAgencyId = customerAgencyId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.remark = remark;
		this.status = status;
		this.enable = enable;
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

	@Column(name = "tourNO", unique = true, nullable = false, length = 16)
	public String getTourNo() {
		return this.tourNo;
	}

	public void setTourNo(String tourNo) {
		this.tourNo = tourNo;
	}

	@Column(name = "tourName", nullable = false, length = 30)
	public String getTourName() {
		return this.tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	@Column(name = "guideIDs", length = 50)
	public String getGuideIds() {
		return this.guideIds;
	}

	public void setGuideIds(String guideIds) {
		this.guideIds = guideIds;
	}

	@Column(name = "userID", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "deptID", nullable = false)
	public int getDeptId() {
		return this.deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Column(name = "businessTypeID", nullable = false)
	public int getBusinessTypeId() {
		return this.businessTypeId;
	}

	public void setBusinessTypeId(int businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	@Column(name = "tourTypeID", nullable = false)
	public int getTourTypeId() {
		return this.tourTypeId;
	}

	public void setTourTypeId(int tourTypeId) {
		this.tourTypeId = tourTypeId;
	}

	@Column(name = "regionID", nullable = false)
	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	@Column(name = "visitorTypeID", nullable = false)
	public int getVisitorTypeId() {
		return this.visitorTypeId;
	}

	public void setVisitorTypeId(int visitorTypeId) {
		this.visitorTypeId = visitorTypeId;
	}

	@Column(name = "adultNO", nullable = false)
	public int getAdultNo() {
		return this.adultNo;
	}

	public void setAdultNo(int adultNo) {
		this.adultNo = adultNo;
	}

	@Column(name = "childrenNO")
	public Integer getChildrenNo() {
		return this.childrenNo;
	}

	public void setChildrenNo(Integer childrenNo) {
		this.childrenNo = childrenNo;
	}

	@Column(name = "qpGuideNO")
	public Integer getQpGuideNo() {
		return this.qpGuideNo;
	}

	public void setQpGuideNo(Integer qpGuideNo) {
		this.qpGuideNo = qpGuideNo;
	}

	@Column(name = "organizor", nullable = false, length = 8)
	public String getOrganizor() {
		return this.organizor;
	}

	public void setOrganizor(String organizor) {
		this.organizor = organizor;
	}

	@Column(name = "customerAgencyID", nullable = false)
	public int getCustomerAgencyId() {
		return this.customerAgencyId;
	}

	public void setCustomerAgencyId(int customerAgencyId) {
		this.customerAgencyId = customerAgencyId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "startTime", nullable = false, length = 10)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "endTime", nullable = false, length = 10)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
