package com.cts.localtour.viewModel;

import org.springframework.stereotype.Component;

import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.UserService;
@Component
public class UserViewModel {
	private UserTable userTable;
	private String deptName;
	private UserService userService;
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
	public UserViewModel getViewModelById(int id){
		UserTable userTable = (UserTable) userService.getById("UserTable", id);
		UserViewModel user = new UserViewModel();
		user.setUserTable(userTable);
		user.setDeptName(((DeptTable)userService.getById("DeptTable", userTable.getDeptId())).getDeptName());
		return user;
	}
}
