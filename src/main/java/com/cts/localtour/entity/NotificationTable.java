package com.cts.localtour.entity;
// Generated 2016-8-31 15:05:02 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NotificationTable generated by hbm2java
 */
@Entity
@Table(name = "notificationTable", catalog = "localtour")
public class NotificationTable implements java.io.Serializable {

	private int notificationId;
	private Date time;
	private Integer deptId;
	private Integer userId;
	private String content;
	private Boolean all;
	private Boolean enable;

	public NotificationTable() {
	}

	public NotificationTable(int notificationId) {
		this.notificationId = notificationId;
	}

	public NotificationTable(int notificationId, Date time, Integer deptId, Integer userId, String content, Boolean all,
			Boolean enable) {
		this.notificationId = notificationId;
		this.time = time;
		this.deptId = deptId;
		this.userId = userId;
		this.content = content;
		this.all = all;
		this.enable = enable;
	}

	@Id

	@Column(name = "notificationId", unique = true, nullable = false)
	public int getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "deptId")
	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "content", length = 45)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "all")
	public Boolean getAll() {
		return this.all;
	}

	public void setAll(Boolean all) {
		this.all = all;
	}

	@Column(name = "enable")
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
