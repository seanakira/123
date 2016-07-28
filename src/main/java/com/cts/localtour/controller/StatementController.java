package com.cts.localtour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatementController {
	@RequestMapping("/payManage")
	public String getPayManageAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/payManage";
	}
	
	@RequestMapping("/collectManage")
	public String getCollectManageAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/collectManage";
	}
	
	@RequestMapping("/tourVerifyManage")
	public String getTourVerifyAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/tourVerifyManage";
	}
	
	@RequestMapping("/tourBalanceManage")
	public String getTourBalanceAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/tourBalanceManage";
	}
}