package com.cts.localtour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.UserService;



@Controller
public class IndexController {
	@Autowired
	private UserService userService;
	@RequestMapping("/")
	public String index(){
		return "loginManage/login";
	}
	
	@RequestMapping("/profile")
	public String profile(){
		return "/userSettings/profile";
	}
	
	@RequestMapping("/settings")
	public String settings(){
		return "/userSettings/settings";
	}
	
	@RequestMapping("/profile/updateInfo")
	public void updateInfo(@RequestBody UserTable user){
		userService.updateByString("UserTable", "phone=?,qq=?,email=?", "id=?", user.getPhone(), user.getQq(), user.getEmail(), user.getId());
	}
	@RequestMapping("/profile/updatePw")
	public @ResponseBody int updatePw(@RequestParam int id, @RequestParam String old, @RequestParam String new1, @RequestParam String new2){
		UserTable user = (UserTable) userService.getById("UserTable", id);
		if(user.getPwd().equals(old)&&new1.equals(new2)){
			userService.updateByString("UserTable", "pwd=?", "id=?", new1, id);
			return 1;
		}else{
			return -1;
		}
	}
}

