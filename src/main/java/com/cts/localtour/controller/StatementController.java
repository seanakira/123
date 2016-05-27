package com.cts.localtour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatementController {
	@RequestMapping("/loanManage")
	public String getLoanAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/loanManage";
	}
	
	
	
	@RequestMapping("/lendManage")
	public String getLendAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/lendManage";
	}
	
	
	@RequestMapping("/actualLendManage")
	public String getActualLendAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/actualLendManage";
	}
}