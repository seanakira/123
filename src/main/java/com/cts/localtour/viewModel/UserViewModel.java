package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.RoleTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;
@Component
public class UserViewModel {
	private UserTable userTable;
	private String deptName;
	private String roleNames;
	private ArrayList<RoleTable> roleTables;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
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
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public ArrayList<RoleTable> getRoleTables() {
		return roleTables;
	}
	public void setRoleTables(ArrayList<RoleTable> roleTables) {
		this.roleTables = roleTables;
	}
	@SuppressWarnings("unchecked")
	public UserViewModel getViewModelById(int id){
		UserTable userTable = (UserTable) baseService.getById("UserTable", id);
		UserViewModel user = new UserViewModel();
		user.setUserTable(userTable);
		user.setDeptName(((DeptTable)baseService.getById("DeptTable", userTable.getDeptId())).getDeptName());
		ArrayList<RoleTable> roleTables = (ArrayList<RoleTable>) baseService.getByHql("SELECT r FROM UserRoleTable u, RoleTable r WHERE u.roleId=r.id AND u.userId="+id);
		user.setRoleTables(roleTables);
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserViewModel> getAllViewModel(ArrayList<UserTable> users){
		ArrayList<UserViewModel> userViewModels = new ArrayList<UserViewModel>();
		for (UserTable userTable : users) {
			UserViewModel userViewModel = new UserViewModel();
			ArrayList<DeptTable> dept = (ArrayList<DeptTable>) baseService.getByWhere("DeptTable", "id", userTable.getDeptId()+"");
			userViewModel.setDeptName(dept.get(0).getDeptName());
			userViewModel.setUserTable(userTable);
			ArrayList<RoleTable> roleTables = (ArrayList<RoleTable>) baseService.getByHql("SELECT r FROM UserRoleTable u, RoleTable r WHERE u.roleId=r.id AND u.userId="+userTable.getId());
			userViewModel.setRoleNames("");
			for (RoleTable roleTable : roleTables) {
				userViewModel.setRoleNames(userViewModel.getRoleNames()+roleTable.getRemark()+"&nbsp;&nbsp;");
			}
			userViewModels.add(userViewModel);
		}
		return userViewModels;
	}
}
