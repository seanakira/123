package com.cts.localtour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {
	@RequestMapping("/error/noPermissions")
	public String noPermissions(@RequestParam(defaultValue="") String errorMessage, Model md){
		md.addAttribute("errorMessage", errorMessage);
		return "/error/noPermissions";
	}
}
