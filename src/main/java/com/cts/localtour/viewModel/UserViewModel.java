package com.cts.localtour.viewModel;

import com.cts.localtour.entity.UserTable;

public class UserViewModel {
	private UserTable userTable;
	private String deptName;
	
	public UserTable getUserTable() {
		return userTable;
	}
	public void setUserTable(UserTable userTable) {
		this.userTable = userTable;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
