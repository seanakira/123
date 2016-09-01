package com.cts.localtour.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.service.PayService;
import com.cts.localtour.viewModel.CostViewModel;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.SimplPayViewModel;

@Controller
public class StatementController {
	@Autowired
	private PayService payService;
	
	/*付款管理*/
	@RequestMapping("/payManage")
	public String getPayAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = payService.getCounts(key);
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
		ArrayList<SimplPayViewModel> pays = payService.getAll(key,page,maxResults);
		md.addAttribute("pays", pays);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/statementManage/payManage";
	}

	@RequestMapping("/payManage/find")
	public @ResponseBody FullPayViewModel findPay(@RequestParam int tourId){
		return payService.findPay(tourId);
	}
	
	@RequestMapping("/payManage/update")
	public @ResponseBody int updatePay(@RequestBody FullPayViewModel full){
		System.out.println(full);
		return payService.updatePay(full);
	}
	
	
	/*收款管理*/
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