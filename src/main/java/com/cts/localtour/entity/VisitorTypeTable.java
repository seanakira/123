package com.cts.localtour.entity;
// Generated 2017-4-24 10:58:35 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VisitorTypeTable generated by hbm2java
 */
@Entity
@Table(name = "visitorTypeTable", catalog = "localtour")
public class VisitorTypeTable implements java.io.Serializable {

	private Integer id;
	private String visitorTypeName;
	private boolean enable;

	public VisitorTypeTable() {
	}

	public VisitorTypeTable(String visitorTypeName, boolean enable) {
		this.visitorTypeName = visitorTypeName;
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

	@Column(name = "visitorTypeName", nullable = false, length = 8)
	public String getVisitorTypeName() {
		return this.visitorTypeName;
	}

	public void setVisitorTypeName(String visitorTypeName) {
		this.visitorTypeName = visitorTypeName;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
