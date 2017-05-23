package com.cts.localtour.entity;
// Generated 2017-5-22 15:24:40 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TripTable generated by hbm2java
 */
@Entity
@Table(name = "tripTable", catalog = "localtour")
public class TripTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private int number;
	private String trip;
	private String meal;
	private String stay;
	private String traffic;
	private String remark;

	public TripTable() {
	}

	public TripTable(int tourId, int number) {
		this.tourId = tourId;
		this.number = number;
	}

	public TripTable(int tourId, int number, String trip, String meal, String stay, String traffic, String remark) {
		this.tourId = tourId;
		this.number = number;
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

	@Column(name = "tourID", nullable = false)
	public int getTourId() {
		return this.tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	@Column(name = "number", nullable = false)
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Column(name = "trip", length = 65535)
	public String getTrip() {
		return this.trip;
	}

	public void setTrip(String trip) {
		this.trip = trip;
	}

	@Column(name = "meal", length = 16)
	public String getMeal() {
		return this.meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	@Column(name = "stay", length = 16)
	public String getStay() {
		return this.stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	@Column(name = "traffic", length = 16)
	public String getTraffic() {
		return this.traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
