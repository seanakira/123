package com.cts.localtour.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.UserService;
import com.cts.localtour.util.WeiXinUtil;

import net.sf.json.JSONObject;

@Controller
@SessionAttributes("user")
public class WeiXinValidationController {
	@Autowired
	private UserService userService;
	@RequestMapping("/weixin")
	public void test(){
		
	}
//	@SuppressWarnings("unchecked")
//	@RequestMapping("/weixin")
//	public void redirect(@RequestParam(defaultValue="") String controller, @RequestParam(defaultValue="") String parm, HttpSession session,  HttpServletRequest request, HttpServletResponse response, @CookieValue("userId") String userId, @RequestParam(defaultValue="") String code, @RequestParam(defaultValue="") String state, Model md){
//		/*判断session是否为空*/
//		System.out.println(controller+"  "+parm+"  "+code+" "+state);
//		if(!"".equals(controller)&&!"".equals(parm)&&"".equals(code)&&"".equals(state)){
//			UserTable user = (UserTable) session.getAttribute("user");
//			if(user==null){
//				/*判断是否有cookie*/
//				if("".equals(userId)||userId==null){
//					StringBuffer url = request.getRequestURL();  
//					String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
//					String redirect = tempContextUrl+"weixin/autoLogin";
//					JSONObject json = new JSONObject();
//					json.element("controller", controller);
//					json.element("parm", parm);
//					WeiXinUtil.getCode(response, redirect, json.toString());
//					return;
//				}else{
//					ArrayList<UserTable> users = (ArrayList<UserTable>) userService.getAllByString("UserTable", "userName=?", userId);
//					if(users.size()==0){
//						try {
//							response.sendRedirect("/localtour/admin");
//							return;
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}else{
//						user = users.get(0);
//						session.setAttribute("user", user);
//						try {
//							response.sendRedirect(controller+"?parm="+parm);
//							return;
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//			}else{
//					try {
//						response.sendRedirect(controller+"?parm="+parm);
//						return;
//					} catch (UnsupportedEncodingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				
//			}
//		}else if("".equals(controller)&&"".equals(parm)&&!"".equals(code)&&!"".equals(state)){
//			userId = WeiXinUtil.getUserId(code);
//			ArrayList<UserTable> users = (ArrayList<UserTable>) userService.getAllByString("UserTable", "userName=?", userId);
//			if(users.size()==0){
//				try {
//					response.sendRedirect("/localtour/admin");
//					return;
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}else{
//				UserTable user = users.get(0);
//				md.addAttribute("user", user);
//				Cookie cookie = new Cookie("userId", userId);
//				cookie.setMaxAge(Integer.MAX_VALUE);
//				cookie.setPath("/");
//				response.addCookie(cookie);
//				JSONObject json = JSONObject.fromObject(state);
//				try {
//					response.sendRedirect(request.getContextPath()+"/"+json.getString("controller")+"?parm="+json.getString("parm"));
//					return;
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}else{
//			request.setAttribute("errorMsg", "不能打开此页");
//            try {
//				request.getRequestDispatcher("/error/noPermissions").forward(request, response);
//			} catch (ServletException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        	return;
//		}
//	}
}
