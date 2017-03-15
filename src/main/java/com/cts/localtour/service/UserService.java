package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.UserTableDAO;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.PermissionTable;
import com.cts.localtour.entity.RoleTable;
import com.cts.localtour.entity.UserRoleTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.util.WeiXinUtil;
import com.cts.localtour.viewModel.UserViewModel;

import net.sf.json.JSONObject;

@SuppressWarnings("rawtypes")
@Service
public class UserService extends BaseService{

	@Autowired
	private UserTableDAO userTableDAO;
	@Autowired
	private UserViewModel userViewModel;

	@SuppressWarnings("unchecked")
	public ArrayList<UserViewModel> getAll(String key, int pageNo, int pageSize) {
		if(key.equals("")){
			ArrayList<UserTable> users = this.getAllByTableName("UserTable", pageNo, pageSize);
			return userViewModel.getAllViewModel(users);
		}else{
			String where = "u.userName like :userName or u.realName like :realName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("userName", "%"+key+"%");
			param.put("realName", "%"+key+"%");
			
			ArrayList<UserTable> users = this.getAllByParam("UserTable u", where, param, pageNo, pageSize);
			return userViewModel.getAllViewModel(users);
		}
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
	public UserTable update(UserTable user) {
		try {
			UserTable userTable = (UserTable) userTableDAO.getById(user.getClass(), user.getId());
			userTable.setPhone(user.getPhone());
			userTable.setPosition(user.getPosition());
			userTable.setQq(user.getQq());
			userTable.setDataDeptIds(user.getDataDeptIds());
			userTableDAO.update(userTable);
			return userTable;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	
	@SuppressWarnings("unchecked")
	public int addUser(UserViewModel userViewModel){
		int id = 0;
		if(userViewModel.getUserTable().getUserName()!=null&&!"".equals(userViewModel.getUserTable().getUserName())&&userViewModel.getUserTable().getDeptId()!=0){
			userViewModel.getUserTable().setPwd("123456");
			ArrayList<Integer> ids = new ArrayList<Integer>();
			ids.add(userViewModel.getUserTable().getDeptId());
			ArrayList<Integer> idsCache = (ArrayList<Integer>) ids.clone();
			while (true) {
				String in = idsCache.toString().substring(1, idsCache.toString().length()-1);
				ArrayList<DeptTable> deptTables = (ArrayList<DeptTable>) this.getByHql("FROM DeptTable WHERE upperDeptID in ("+in+")");
				if (deptTables.size()==0) {
					break;
				}else{
					idsCache.clear();
					for (DeptTable deptTable : deptTables) {
						idsCache.add(deptTable.getId());
					}
					ids.addAll(idsCache);
				}
			}
			userViewModel.getUserTable().setDataDeptIds(ids.toString().substring(1, ids.toString().length()-1));
			userViewModel.getUserTable().setEnable(true);
			userViewModel.getUserTable().setWeiXinMessageSwitch(true);
			try {
				id = ((UserTable)this.add(userViewModel.getUserTable())).getId();
				for (RoleTable roleTable : userViewModel.getRoleTables()) {
					UserRoleTable userRoleTable = new UserRoleTable();
					userRoleTable.setRoleId(roleTable.getId());
					userRoleTable.setUserId(id);
					this.add(userRoleTable);
				}
			} catch (Exception e) {
				return -2;
			}
			this.addToWeiXin(userViewModel.getUserTable());
		}else{
			return -1;
		}
		return id;
	}

	public void addToWeiXin(UserTable user) {
		if(user.getPhone().equals("")||user.getPhone()==null){
			user.setPhone("0000000000000");
		}
		/*添加微信通讯录*/
		/*JSONObject error = WeiXinUtil.addUser(user.getUserName(), user.getRealName(), "1", user.getPhone());
		if(!error.getString("errmsg").equals("created")){
			System.out.println(error.toString());
		}*/
	}

	/*public void updateToWeiXin(UserTable user) {
		JSONObject error = WeiXinUtil.updateUser(user.getUserName()+"@ctssd.com",user.getRealName(), user.getPosition(), user.getPhone(), user.getEmail(), user.getWeiXinId());
		if(!error.getString("errmsg").equals("updated")){
			System.out.println(error.toString());
		}
	}*/
	public void updateToWeiXin(UserTable user) {
		JSONObject error = WeiXinUtil.updateUser(user.getUserName()+"@ctssd.com",user.getRealName(), user.getPosition(), user.getPhone(), user.getEmail());
		if(!error.getString("errmsg").equals("updated")){
			/*System.out.println(error.toString());*/
		}
	}
	public void changWeiXinEnable(String userName, String enable){
		JSONObject error = WeiXinUtil.changEnable(userName+"@ctssd.com", enable);
		if(!error.getString("errmsg").equals("updated")){
			/*System.out.println(error.toString());*/
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

	@SuppressWarnings("unchecked")
	public HashSet<String> getRolesByUserName(String username) {
		HashSet<String> roles = new HashSet<String>();
		UserTable user = this.getByUserName(username);
		ArrayList<RoleTable> roleTables = (ArrayList<RoleTable>) this.getByHql("SELECT r FROM UserRoleTable u, RoleTable r WHERE u.roleId=r.id AND u.userId="+user.getId());
		for (RoleTable roleTable : roleTables) {
			roles.add(roleTable.getRemark());
		}
		return roles;
	}

	@SuppressWarnings("unchecked")
	public HashSet<String> getPermissionsByUserName(String username) {
		HashSet<String> permissions = new HashSet<String>();
		UserTable user = this.getByUserName(username);
		ArrayList<PermissionTable> permissionTables = (ArrayList<PermissionTable>)this.getByHql("SELECT p FROM UserRoleTable u, RolePermissionTable r, PermissionTable p WHERE r.roleId = u.roleId and r.permissionId=p.id and p.enable=true and u.userId= "+user.getId());
		for (PermissionTable permissionTable : permissionTables) {
			permissions.add(permissionTable.getName());
		}
		return permissions;
	}

	@SuppressWarnings("unchecked")
	public UserTable getByUserName(String username) {
		ArrayList<UserTable> users = (ArrayList<UserTable>) this.getAllByString("UserTable", "userName=?", username);
		if(users.isEmpty()){
			return null;
		}else{
			return users.get(0);
		}
	}

	public UserViewModel find(int userId) {
		return userViewModel.getViewModelById(userId);
	}
}
