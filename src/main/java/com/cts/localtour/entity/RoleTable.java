package com.cts.localtour.entity;
// Generated 2016-5-19 14:40:25 by Hibernate Tools 3.4.0.CR1

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

	private Integer roleId;
	private String roleName;
	private String roleComment;
	private String roleCreator;
	private Boolean enable;

	public RoleTable() {
	}

	public RoleTable(String roleName) {
		this.roleName = roleName;
	}

	public RoleTable(String roleName, String roleComment, String roleCreator, Boolean enable) {
		this.roleName = roleName;
		this.roleComment = roleComment;
		this.roleCreator = roleCreator;
		this.enable = enable;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "roleId", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "roleName", nullable = false, length = 10)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roleComment", length = 45)
	public String getRoleComment() {
		return this.roleComment;
	}

	public void setRoleComment(String roleComment) {
		this.roleComment = roleComment;
	}

	@Column(name = "roleCreator", length = 45)
	public String getRoleCreator() {
		return this.roleCreator;
	}

	public void setRoleCreator(String roleCreator) {
		this.roleCreator = roleCreator;
	}

	@Column(name = "enable")
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}