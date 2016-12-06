package com.cts.localtour.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("user")
public class LoginController {
	
	@RequestMapping("/admin")
	public String admin(){
		return "loginManage/login";
	}
	
	@RequestMapping("/admin/login")
	public String login(@RequestParam String userName, @RequestParam String pwd, Model md){
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(userName,pwd);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			if(subject.isAuthenticated()){
				return "/operatingStatus/operatingStatus";
			}else{
				md.addAttribute("msg","用户名或密码错误");
				return "/loginManage/login";
			}
		} catch (Exception e) {
			md.addAttribute("msg","用户名或密码错误");
			return "/loginManage/login";
		}
	}
	
	@RequestMapping("/admin/logout")
	public String logout(){
		SecurityUtils.getSubject().logout();
		return "/loginManage/login";
	}
}
