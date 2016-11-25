package com.cts.localtour.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.LoginService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("user")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/admin")
	public String admin(){
		return "loginManage/login";
	}
	
	@RequestMapping("/admin/login")
	public String login(@RequestParam String userName, @RequestParam String pwd, Model md, HttpServletRequest request, HttpServletResponse response){
		if(userName.equals("")||pwd.equals("")){
			md.addAttribute("msg","用户名或密码错误");
			return "/loginManage/login";
		}
		UserTable user = loginService.check(userName,pwd);
		if(user!=null){
			if(request.getHeader("user-agent").indexOf("MicroMessenger")>=0){
				md.addAttribute("user", user);
				Cookie cookie = new Cookie("userId", user.getUserName());
				cookie.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(cookie);
				String servletPath = request.getServletPath();
				return servletPath;
			}else{
				md.addAttribute("user", user);
				return "/operatingStatus/operatingStatus";
			}
		}else{
			md.addAttribute("msg","用户名或密码错误");
			return "/loginManage/login";
		}
	}
	
	@RequestMapping("/admin/logout")
	public String logout(SessionStatus s, HttpServletRequest request, HttpServletResponse response){
		s.setComplete();
		return "/loginManage/login";
	}
}
