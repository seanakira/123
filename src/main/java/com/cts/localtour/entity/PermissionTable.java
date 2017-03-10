package com.cts.localtour.entity;
// Generated 2017-3-10 15:09:16 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * PermissionTable generated by hbm2java
 */
@Entity
@Table(name = "permissionTable", catalog = "localtour", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class PermissionTable implements java.io.Serializable {

	private Integer id;
	private String name;
	private String remark;
	private boolean enable;

	public PermissionTable() {
	}

	public PermissionTable(String name, String remark, boolean enable) {
		this.name = name;
		this.remark = remark;
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

	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "remark", nullable = false, length = 32)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
