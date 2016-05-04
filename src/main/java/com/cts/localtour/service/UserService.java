package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.UserTableDAO;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.UserViewModel;

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
				ArrayList<DeptTable> dept = (ArrayList<DeptTable>) this.getByOtherId("DeptTable", "id", users.get(i).getDeptId()+"");
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
				ArrayList<DeptTable> dept = (ArrayList<DeptTable>) this.getByOtherId("DeptTable", "id", users.get(i).getDeptId()+"");
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

	@SuppressWarnings("unchecked")
	public void del(Class<UserTable> c, int id) {
		UserTable user = (UserTable) userTableDAO.getById(c, id);
		user.setEnable(false);
		try {
			userTableDAO.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void recover(Class<UserTable> c, int id) {
		UserTable user = (UserTable) userTableDAO.getById(c, id);
		user.setEnable(true);
		try {
			userTableDAO.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	

}
