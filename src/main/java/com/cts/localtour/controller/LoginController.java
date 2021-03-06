package com.cts.localtour.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.DeptService;
import com.cts.localtour.service.UserService;
import com.cts.localtour.util.WeiXinUtil;


/**
 * Handles requests for the application home page.
 */
@Controller
/*@SessionAttributes("user")*/
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@RequestMapping("/admin")
	public String admin(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue="") String userId){
		/*微信自动登录*/
		if(request.getHeader("user-agent").indexOf("MicroMessenger")>=0||request.getHeader("user-agent").indexOf("Mobile MQQBrowser")>=0){
			if("".equals(userId)){
				this.getUserId(request, response, null);
			}else{
				UsernamePasswordToken token = new UsernamePasswordToken(userId.split("@")[0],userService.getByUserName(userId.split("@")[0]).getPwd());
				Subject subject = SecurityUtils.getSubject();
				subject.login(token);
				if(subject.isAuthenticated()){
					try {
						response.sendRedirect(((SavedRequest)subject.getSession().getAttribute("shiroSavedRequest")).getRequestUrl());
					} catch (InvalidSessionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return "loginManage/login";
	}
	
	@RequestMapping("/admin/login")
	public String login(@RequestParam String userName, @RequestParam String pwd, Model md, HttpServletResponse response){
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(userName,pwd);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			if(subject.isAuthenticated()){
				subject.getSession().setAttribute("isMice", deptService.isMice());
				if(((UserTable)subject.getPrincipal()).getPwd().equals("123456")){
					md.addAttribute("msg","您的密码为123456，请修改初始密码");
					return "/userSettings/profile";
				}else{
					response.sendRedirect("../operatingStatus");
				}
			}else{
				md.addAttribute("msg","用户名或密码错误");
				return "/loginManage/login";
			}
		} catch (Exception e) {
			md.addAttribute("msg","用户名或密码错误");
		}
		return "/loginManage/login";
	}
	
	@RequestMapping("/admin/logout")
	public String logout(){
		SecurityUtils.getSubject().logout();
		return "/loginManage/login";
	}
	
	/*获取微信userId*/
	@RequestMapping("/admin/getUserId")
	public void getUserId(HttpServletRequest request, HttpServletResponse response, @RequestParam String code){
		if(code==null){
			StringBuffer url = request.getRequestURL();  
			String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
			String redirect = tempContextUrl+"admin/getUserId";
			WeiXinUtil.getCode(response, redirect, "");
		}else{
			String userId = WeiXinUtil.getUserId(code);
			try {
				response.sendRedirect("../admin?userId="+userId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
