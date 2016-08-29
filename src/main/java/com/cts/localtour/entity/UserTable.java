package com.cts.localtour.entity;
// Generated 2016-8-26 13:59:42 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserTable generated by hbm2java
 */
@Entity
@Table(name = "userTable", catalog = "localtour")
public class UserTable implements java.io.Serializable {

	private Integer id;
	private String userName;
	private String realName;
	private String pwd;
	private int deptId;
	private String position;
	private String phone;
	private String qq;
	private boolean enable;

	public UserTable() {
	}

	public UserTable(String userName, String realName, String pwd, int deptId, boolean enable) {
		this.userName = userName;
		this.realName = realName;
		this.pwd = pwd;
		this.deptId = deptId;
		this.enable = enable;
	}

	public UserTable(String userName, String realName, String pwd, int deptId, String position, String phone, String qq,
			boolean enable) {
		this.userName = userName;
		this.realName = realName;
		this.pwd = pwd;
		this.deptId = deptId;
		this.position = position;
		this.phone = phone;
		this.qq = qq;
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

	@Column(name = "userName", nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "realName", nullable = false, length = 10)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "pwd", nullable = false, length = 10)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "deptID", nullable = false)
	public int getDeptId() {
		return this.deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Column(name = "position", length = 16)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "phone", length = 16)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "qq", length = 20)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
