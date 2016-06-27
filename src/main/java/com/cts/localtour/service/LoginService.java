package com.cts.localtour.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.UserTable;

@Service
@SuppressWarnings("rawtypes")
public class LoginService extends BaseService{

	@SuppressWarnings("unchecked")
	public UserTable check(String userName, String pwd) {
		ArrayList<UserTable> list = (ArrayList<UserTable>) this.getAllByStringOrderByLimit("UserTable", "userName=? and pwd=?", "id", 1, userName,pwd);
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}

}
