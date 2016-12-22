//package com.cts.localtour.aop;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class WeixinAutoLogin {
//	@Pointcut("execution(* com.cts.localtour.controller.MobileController.*Application(..))")
//	public void pointcut(){}
//	
//	@Around("pointcut()")
//	public Object autoLogin(ProceedingJoinPoint pjp){
//		HttpServletRequest request = (HttpServletRequest) pjp.getArgs()[0];
//		if(request.getHeader("user-agent").indexOf("MicroMessenger")>=0){
//			System.out.println(true);
//		}else{
//			System.out.println(false);
//		}
//		return "/error/noPermissions";
//	}
//}
