package com.cts.localtour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IndexController {

	@RequestMapping("/profile")
	public String profile(){
		return "/userSettings/profile";
	}
	
	@RequestMapping("/settings")
	public String settings(){
		return "/userSettings/settings";
	}
}

