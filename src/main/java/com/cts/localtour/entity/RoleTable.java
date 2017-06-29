package com.cts.localtour.entity;
// Generated 2017-6-29 10:52:58 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RoleTable generated by hbm2java
 */
@Entity
@Table(name = "roleTable", catalog = "localtour")
public class RoleTable implements java.io.Serializable {

	private Integer id;
	private String remark;

	public RoleTable() {
	}

	public RoleTable(String remark) {
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

	@Column(name = "remark", nullable = false, length = 16)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
