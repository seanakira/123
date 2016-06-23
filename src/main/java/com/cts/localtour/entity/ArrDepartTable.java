package com.cts.localtour.entity;
// Generated 2016-6-21 13:43:44 by Hibernate Tools 3.4.0.CR1

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
 * ArrDepartTable generated by hbm2java
 */
@Entity
@Table(name = "arrDepartTable", catalog = "localtour")
public class ArrDepartTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private String departTraffic;
	private String arrTraffic;
	private int originId;
	private int destId;
	private Date arrTime;
	private Date departTime;
	private String arrTrafficNo;
	private String departTrafficNo;
	private int arrRegionId;
	private int departRegionId;

	public ArrDepartTable() {
	}

	public ArrDepartTable(int tourId, String departTraffic, String arrTraffic, int originId, int destId, Date arrTime,
			Date departTime, String arrTrafficNo, String departTrafficNo, int arrRegionId, int departRegionId) {
		this.tourId = tourId;
		this.departTraffic = departTraffic;
		this.arrTraffic = arrTraffic;
		this.originId = originId;
		this.destId = destId;
		this.arrTime = arrTime;
		this.departTime = departTime;
		this.arrTrafficNo = arrTrafficNo;
		this.departTrafficNo = departTrafficNo;
		this.arrRegionId = arrRegionId;
		this.departRegionId = departRegionId;
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

	@Column(name = "tourID", nullable = false)
	public int getTourId() {
		return this.tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
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

	@Column(name = "arrRegionID", nullable = false)
	public int getArrRegionId() {
		return this.arrRegionId;
	}

	public void setArrRegionId(int arrRegionId) {
		this.arrRegionId = arrRegionId;
	}

	@Column(name = "departRegionID", nullable = false)
	public int getDepartRegionId() {
		return this.departRegionId;
	}

	public void setDepartRegionId(int departRegionId) {
		this.departRegionId = departRegionId;
	}

}
