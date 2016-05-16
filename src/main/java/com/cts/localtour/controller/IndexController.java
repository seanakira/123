package com.cts.localtour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.cts.localtour.entity.DeptTable;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(){
		return "index";
	}
	

}

