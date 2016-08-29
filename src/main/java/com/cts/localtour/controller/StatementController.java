package com.cts.localtour.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.localtour.service.PayService;
import com.cts.localtour.viewModel.SimplPayViewModel;

@Controller
public class StatementController {
	@Autowired
	private PayService payService;
	
	/*∏∂øÓπ‹¿Ì*/
	@RequestMapping("/payManage")
	public String getPayManageAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		ArrayList<SimplPayViewModel> pays = payService.getAll(key,page,maxResults);
		int counts = pays.size();
		int pageMax = counts/maxResults;
		if(counts%maxResults>0){
			pageMax++;
		}
		if(page>pageMax){
			page=pageMax;
		}
		if(page<1){
			page=1;
		}
		md.addAttribute("pays", pays);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
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