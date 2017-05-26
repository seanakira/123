package com.cts.localtour.entity;
// Generated 2017-5-25 17:25:22 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GuideTable generated by hbm2java
 */
@Entity
@Table(name = "guideTable", catalog = "localtour")
public class GuideTable implements java.io.Serializable {

	private Integer id;
	private int userId;
	private boolean enable;

	public GuideTable() {
	}

	public GuideTable(int userId, boolean enable) {
		this.userId = userId;
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

	@Column(name = "userID", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
