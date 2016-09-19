package com.cts.localtour.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class WeixinAutoLogin {
	@Pointcut("execution(* com.cts.localtour.controller..*(..))")
	public void pc(){}
	
	@Before("pc()")
	public void test(){
		System.out.println("≤‚ ‘");
	}
	
	@Pointcut("execution(* com.cts.localtour.service..*(..))")
	public void sv(){}
}
