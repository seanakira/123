package com.cts.localtour.entity;
// Generated 2016-8-12 11:12:23 by Hibernate Tools 3.4.0.CR1

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
 * GuideTimeTable generated by hbm2java
 */
@Entity
@Table(name = "guideTimeTable", catalog = "localtour")
public class GuideTimeTable implements java.io.Serializable {

	private Integer id;
	private Integer tourId;
	private int guideId;
	private Date startTime;
	private Date endTime;
	private String remark;

	public GuideTimeTable() {
	}

	public GuideTimeTable(int guideId, Date startTime, Date endTime) {
		this.guideId = guideId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public GuideTimeTable(Integer tourId, int guideId, Date startTime, Date endTime, String remark) {
		this.tourId = tourId;
		this.guideId = guideId;
		this.startTime = startTime;
		this.endTime = endTime;
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

	@Column(name = "tourID")
	public Integer getTourId() {
		return this.tourId;
	}

	public void setTourId(Integer tourId) {
		this.tourId = tourId;
	}

	@Column(name = "guideID", nullable = false)
	public int getGuideId() {
		return this.guideId;
	}

	public void setGuideId(int guideId) {
		this.guideId = guideId;
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

}
