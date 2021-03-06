package com.cts.localtour.entity;
// Generated 2017-3-27 13:53:58 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DeptTable generated by hbm2java
 */
@Entity
@Table(name = "deptTable", catalog = "localtour")
public class DeptTable implements java.io.Serializable {

	private Integer id;
	private String deptName;
	private Integer upperDeptId;
	private String managerIds;
	private Integer deptLevel;
	private boolean enable;

	public DeptTable() {
	}

	public DeptTable(String deptName, boolean enable) {
		this.deptName = deptName;
		this.enable = enable;
	}

	public DeptTable(String deptName, Integer upperDeptId, String managerIds, Integer deptLevel, boolean enable) {
		this.deptName = deptName;
		this.upperDeptId = upperDeptId;
		this.managerIds = managerIds;
		this.deptLevel = deptLevel;
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

	@Column(name = "deptName", nullable = false, length = 40)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "upperDeptID")
	public Integer getUpperDeptId() {
		return this.upperDeptId;
	}

	public void setUpperDeptId(Integer upperDeptId) {
		this.upperDeptId = upperDeptId;
	}

	@Column(name = "managerIds", length = 20)
	public String getManagerIds() {
		return this.managerIds;
	}

	public void setManagerIds(String managerIds) {
		this.managerIds = managerIds;
	}

	@Column(name = "deptLevel")
	public Integer getDeptLevel() {
		return this.deptLevel;
	}

	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
