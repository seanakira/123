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
 * TourTable generated by hbm2java
 */
@Entity
@Table(name = "tourTable", catalog = "localtour")
public class TourTable implements java.io.Serializable {

	private Integer id;
	private String tourNo;
	private String tourName;
	private int userId;
	private int businessTypeId;
	private int tourTypeId;
	private int regionId;
	private int visitorTypeId;
	private int adultNo;
	private int childrenNo;
	private int qpGuideNo;
	private String organizor;
	private String customerAgency;
	private String departTraffic;
	private String arrTraffic;
	private int originId;
	private int destId;
	private Date arrTime;
	private Date departTime;
	private String arrTrafficNo;
	private String departTrafficNo;
	private String remark;
	private int status;
	private boolean enable;

	public TourTable() {
	}

	public TourTable(String tourNo, String tourName, int userId, int businessTypeId, int tourTypeId, int regionId,
			int visitorTypeId, int adultNo, int childrenNo, int qpGuideNo, String organizor, String customerAgency,
			String departTraffic, String arrTraffic, int originId, int destId, Date arrTime, Date departTime,
			String arrTrafficNo, String departTrafficNo, String remark, int status, boolean enable) {
		this.tourNo = tourNo;
		this.tourName = tourName;
		this.userId = userId;
		this.businessTypeId = businessTypeId;
		this.tourTypeId = tourTypeId;
		this.regionId = regionId;
		this.visitorTypeId = visitorTypeId;
		this.adultNo = adultNo;
		this.childrenNo = childrenNo;
		this.qpGuideNo = qpGuideNo;
		this.organizor = organizor;
		this.customerAgency = customerAgency;
		this.departTraffic = departTraffic;
		this.arrTraffic = arrTraffic;
		this.originId = originId;
		this.destId = destId;
		this.arrTime = arrTime;
		this.departTime = departTime;
		this.arrTrafficNo = arrTrafficNo;
		this.departTrafficNo = departTrafficNo;
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

	@Column(name = "tourNO", nullable = false, length = 16)
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

	@Column(name = "userID", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	@Column(name = "childrenNO", nullable = false)
	public int getChildrenNo() {
		return this.childrenNo;
	}

	public void setChildrenNo(int childrenNo) {
		this.childrenNo = childrenNo;
	}

	@Column(name = "qpGuideNO", nullable = false)
	public int getQpGuideNo() {
		return this.qpGuideNo;
	}

	public void setQpGuideNo(int qpGuideNo) {
		this.qpGuideNo = qpGuideNo;
	}

	@Column(name = "organizor", nullable = false, length = 8)
	public String getOrganizor() {
		return this.organizor;
	}

	public void setOrganizor(String organizor) {
		this.organizor = organizor;
	}

	@Column(name = "customerAgency", nullable = false, length = 20)
	public String getCustomerAgency() {
		return this.customerAgency;
	}

	public void setCustomerAgency(String customerAgency) {
		this.customerAgency = customerAgency;
	}

	@Column(name = "departTraffic", nullable = false, length = 4)
	public String getDepartTraffic() {
		return this.departTraffic;
	}

	public void setDepartTraffic(String departTraffic) {
		this.departTraffic = departTraffic;
	}

	@Column(name = "arrTraffic", nullable = false, length = 4)
	public String getArrTraffic() {
		return this.arrTraffic;
	}

	public void setArrTraffic(String arrTraffic) {
		this.arrTraffic = arrTraffic;
	}

	@Column(name = "originID", nullable = false)
	public int getOriginId() {
		return this.originId;
	}

	public void setOriginId(int originId) {
		this.originId = originId;
	}

	@Column(name = "destID", nullable = false)
	public int getDestId() {
		return this.destId;
	}

	public void setDestId(int destId) {
		this.destId = destId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "arrTime", nullable = false, length = 10)
	public Date getArrTime() {
		return this.arrTime;
	}

	public void setArrTime(Date arrTime) {
		this.arrTime = arrTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "departTime", nullable = false, length = 10)
	public Date getDepartTime() {
		return this.departTime;
	}

	public void setDepartTime(Date departTime) {
		this.departTime = departTime;
	}

	@Column(name = "arrTrafficNO", nullable = false, length = 8)
	public String getArrTrafficNo() {
		return this.arrTrafficNo;
	}

	public void setArrTrafficNo(String arrTrafficNo) {
		this.arrTrafficNo = arrTrafficNo;
	}

	@Column(name = "departTrafficNO", nullable = false, length = 8)
	public String getDepartTrafficNo() {
		return this.departTrafficNo;
	}

	public void setDepartTrafficNo(String departTrafficNo) {
		this.departTrafficNo = departTrafficNo;
	}

	@Column(name = "remark", nullable = false, length = 65535)
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
