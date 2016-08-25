package com.cts.localtour.entity;
// Generated 2016-8-25 17:12:52 by Hibernate Tools 3.4.0.CR1

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
 * DepartTable generated by hbm2java
 */
@Entity
@Table(name = "departTable", catalog = "localtour")
public class DepartTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private int destId;
	private String departTraffic;
	private Date departTime;
	private String departTrafficNo;
	private int departRegionId;

	public DepartTable() {
	}

	public DepartTable(int tourId, int destId, String departTraffic, Date departTime, String departTrafficNo,
			int departRegionId) {
		this.tourId = tourId;
		this.destId = destId;
		this.departTraffic = departTraffic;
		this.departTime = departTime;
		this.departTrafficNo = departTrafficNo;
		this.departRegionId = departRegionId;
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

	@Column(name = "tourID", nullable = false)
	public int getTourId() {
		return this.tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	@Column(name = "destID", nullable = false)
	public int getDestId() {
		return this.destId;
	}

	public void setDestId(int destId) {
		this.destId = destId;
	}

	@Column(name = "departTraffic", nullable = false, length = 4)
	public String getDepartTraffic() {
		return this.departTraffic;
	}

	public void setDepartTraffic(String departTraffic) {
		this.departTraffic = departTraffic;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "departTime", nullable = false, length = 10)
	public Date getDepartTime() {
		return this.departTime;
	}

	public void setDepartTime(Date departTime) {
		this.departTime = departTime;
	}

	@Column(name = "departTrafficNO", nullable = false, length = 8)
	public String getDepartTrafficNo() {
		return this.departTrafficNo;
	}

	public void setDepartTrafficNo(String departTrafficNo) {
		this.departTrafficNo = departTrafficNo;
	}

	@Column(name = "departRegionID", nullable = false)
	public int getDepartRegionId() {
		return this.departRegionId;
	}

	public void setDepartRegionId(int departRegionId) {
		this.departRegionId = departRegionId;
	}

}
