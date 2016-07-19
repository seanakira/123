package com.cts.localtour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatementController {
	@RequestMapping("/lendManage")
	public String getLendAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/lendManage";
	}
	
	@RequestMapping("/needCollectManage")
	public String getNeedCollectAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/needCollectManage";
	}
	
	
	@RequestMapping("/needPayManage")
	public String getNeedPayAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/needPayManage";
	}
	
	
	@RequestMapping("/actualCollectManage")
	public String getActualCollectAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/actualCollectManage";
	}
	
	
	@RequestMapping("/actualPayManage")
	public String getActualPayAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/actualPayManage";
	}
	
	@RequestMapping("/tourBalanceManage")
	public String getTourBalanceAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/statementManage/tourBalanceManage";
	}
}