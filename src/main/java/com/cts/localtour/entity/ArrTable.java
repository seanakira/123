package com.cts.localtour.entity;
// Generated 2016-11-8 16:41:17 by Hibernate Tools 3.4.0.CR1

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
 * ArrTable generated by hbm2java
 */
@Entity
@Table(name = "arrTable", catalog = "localtour")
public class ArrTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private String arrTraffic;
	private int originId;
	private Date arrTime;
	private String arrTrafficNo;
	private int arrRegionId;

	public ArrTable() {
	}

	public ArrTable(int tourId, String arrTraffic, int originId, Date arrTime, String arrTrafficNo, int arrRegionId) {
		this.tourId = tourId;
		this.arrTraffic = arrTraffic;
		this.originId = originId;
		this.arrTime = arrTime;
		this.arrTrafficNo = arrTrafficNo;
		this.arrRegionId = arrRegionId;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "arrTime", nullable = false, length = 10)
	public Date getArrTime() {
		return this.arrTime;
	}

	public void setArrTime(Date arrTime) {
		this.arrTime = arrTime;
	}

	@Column(name = "arrTrafficNO", nullable = false, length = 8)
	public String getArrTrafficNo() {
		return this.arrTrafficNo;
	}

	public void setArrTrafficNo(String arrTrafficNo) {
		this.arrTrafficNo = arrTrafficNo;
	}

	@Column(name = "arrRegionID", nullable = false)
	public int getArrRegionId() {
		return this.arrRegionId;
	}

	public void setArrRegionId(int arrRegionId) {
		this.arrRegionId = arrRegionId;
	}

}
