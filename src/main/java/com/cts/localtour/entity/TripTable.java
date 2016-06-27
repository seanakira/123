package com.cts.localtour.entity;
// Generated 2016-6-24 18:43:01 by Hibernate Tools 3.4.0.CR1

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
 * TripTable generated by hbm2java
 */
@Entity
@Table(name = "tripTable", catalog = "localtour")
public class TripTable implements java.io.Serializable {

	private Integer id;
	private String tourNo;
	private Date tripDate;
	private String trip;
	private String meal;
	private String stay;
	private String traffic;
	private String remark;

	public TripTable() {
	}

	public TripTable(String tourNo, Date tripDate, String trip, String meal, String stay, String traffic,
			String remark) {
		this.tourNo = tourNo;
		this.tripDate = tripDate;
		this.trip = trip;
		this.meal = meal;
		this.stay = stay;
		this.traffic = traffic;
		this.remark = remark;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "tripDate", nullable = false, length = 10)
	public Date getTripDate() {
		return this.tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	@Column(name = "trip", nullable = false, length = 65535)
	public String getTrip() {
		return this.trip;
	}

	public void setTrip(String trip) {
		this.trip = trip;
	}

	@Column(name = "meal", nullable = false, length = 16)
	public String getMeal() {
		return this.meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	@Column(name = "stay", nullable = false, length = 16)
	public String getStay() {
		return this.stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	@Column(name = "traffic", nullable = false, length = 4)
	public String getTraffic() {
		return this.traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	@Column(name = "remark", nullable = false, length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
