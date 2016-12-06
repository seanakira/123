package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.UserTableDAO;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.PermissionTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.util.WeiXinUtil;
import com.cts.localtour.viewModel.UserViewModel;

import net.sf.json.JSONObject;

@SuppressWarnings("rawtypes")
@Service
public class UserService extends BaseService{

	@Autowired
	UserTableDAO userTableDAO;

	@SuppressWarnings("unchecked")
	public ArrayList<UserViewModel> getAll(String key, int pageNo, int pageSize) {
		ArrayList<UserViewModel> userViewModels = new ArrayList<UserViewModel>();
		if(key.equals("")){
			ArrayList<UserTable> users = this.getAllByTableName("UserTable", pageNo, pageSize);
			for (int i = 0; i < users.size(); i++) {
				UserViewModel userViewModel = new UserViewModel();
				users.get(i).setPwd("");
				ArrayList<DeptTable> dept = (ArrayList<DeptTable>) this.getByWhere("DeptTable", "id", users.get(i).getDeptId()+"");
				userViewModel.setDeptName(dept.get(0).getDeptName());
				userViewModel.setUserTable(users.get(i));
				userViewModels.add(userViewModel);
			}
		}else{
			String where = "u.userName like :userName or u.realName like :realName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("userName", "%"+key+"%");
			param.put("realName", "%"+key+"%");
			
			ArrayList<UserTable> users = this.getAllByParam("UserTable u", where, param, pageNo, pageSize);
			for (int i = 0; i < users.size(); i++) {
				UserViewModel userViewModel = new UserViewModel();
				users.get(i).setPwd("");
				ArrayList<DeptTable> dept = (ArrayList<DeptTable>) this.getByWhere("DeptTable", "id", users.get(i).getDeptId()+"");
				userViewModel.setDeptName(dept.get(0).getDeptName());
				userViewModel.setUserTable(users.get(i));
				userViewModels.add(userViewModel);
			}
		}
		return userViewModels;
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("UserTable");
		}else{
			String where = "userName like :userName or realName like :realName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("userName", "%"+key+"%");
			param.put("realName", "%"+key+"%");
			return this.getCountsByParam("UserTable", where, param);
		}
	}

	public void del(int id) {
		this.changeValue("UserTable", "enable", "false", id);
	}

	public void recover(int id) {
		this.changeValue("UserTable", "enable", "true", id);
	}

	@SuppressWarnings("unchecked")
	public void update(UserTable user) {
		try {
			UserTable userTable = (UserTable) userTableDAO.getById(user.getClass(), user.getId());
			userTable.setDeptId(user.getDeptId());
			userTable.setPhone(user.getPhone());
			userTable.setPosition(user.getPosition());
			userTable.setQq(user.getQq());
			userTable.setRealName(user.getRealName());
			userTable.setUserName(user.getUserName());
			userTableDAO.update(userTable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void reset(int id) {
		UserTable user = (UserTable) userTableDAO.getById(UserTable.class, id);
		user.setPwd("123456");
		try {
			userTableDAO.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserTable> getUserStructureTree(int deptId){
		String hql = "from UserTable u where u.deptId = "+ deptId;
		ArrayList<UserTable> users = (ArrayList<UserTable>)userTableDAO.findHql(hql);
		return users;
	}
	
	@SuppressWarnings("unchecked")
	public UserViewModel getUserView(int userId){
		UserViewModel user = new UserViewModel();
		UserTable userTemp = (UserTable) userTableDAO.getById(UserTable.class, userId);
		user.setUserTable(userTemp);
		int deptId = userTemp.getDeptId();
		ArrayList<DeptTable> deptTemp = (ArrayList<DeptTable>) this.getByWhere("DeptTable", "id", deptId+"");
		user.setDeptName(deptTemp.get(0).getDeptName());
		return user;
	}

	public void addToWeiXin(UserTable user) {
		if(user.getPhone().equals("")||user.getPhone()==null){
			user.setPhone("0000000000000");
		}
		JSONObject error = WeiXinUtil.addUser(user.getUserName(), user.getRealName(), "1", user.getPhone());
		if(!error.getString("errmsg").equals("created")){
			System.out.println(error.toString());
		}
	}

	public void updateToWeiXin(UserTable user) {
		JSONObject error = WeiXinUtil.updateUser(user.getUserName(),user.getRealName(), user.getPosition(), user.getPhone(), user.getEmail(), user.getWeiXinId());
		if(!error.getString("errmsg").equals("updated")){
			System.out.println(error.toString());
		}
	}
	
	public void changWeiXinEnable(String userName, String enable){
		JSONObject error = WeiXinUtil.changEnable(userName, enable);
		if(!error.getString("errmsg").equals("updated")){
			System.out.println(error.toString());
		}
	}
	
	public String getUserName(Integer id){
		if(id==null||id==0){
			return "";
		}else{
			UserTable user = (UserTable)this.getById("UserTable", id);
			return user.getUserName();
		}
	}
	public String getUserRealName(Integer id){
		if(id==null||id==0){
			return "";
		}else{
			UserTable user = (UserTable)this.getById("UserTable", id);
			return user.getRealName();
		}
	}

	public HashSet<String> getRolesByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public HashSet<String> getPermissionsByUserName(String username) {
		HashSet<String> permissions = new HashSet<String>();
		UserTable user = this.getByUserName(username);
		ArrayList<PermissionTable> permissionTables = (ArrayList<PermissionTable>)this.getByHql("SELECT p FROM UserRoleTable u, RolePermissionTable r, PermissionTable p WHERE r.roleId = u.roleId and r.permissionId=p.id and p.enable=true and u.userId= "+user.getId());
		for (PermissionTable permissionTable : permissionTables) {
			permissions.add(permissionTable.getPermissionName());
		}
		return permissions;
	}

	public UserTable getByUserName(String username) {
		return (UserTable) this.getAllByString("UserTable", "userName=?", username).get(0);
	}
}
