package com.cts.localtour.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.service.ReimbursementApplicationService;
import com.cts.localtour.viewModel.FullReimbursementApplicationViewModel;
import com.cts.localtour.viewModel.SimpleReimbursementApplicationViewModel;

@Controller
public class ToDoController {
	@Autowired
	ReimbursementApplicationService reimbursementApplicationService;
	@RequestMapping("/reimbursementApplication")
	public String getPayAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = reimbursementApplicationService.getCounts(key);
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
		ArrayList<SimpleReimbursementApplicationViewModel> reimbursementApplications = reimbursementApplicationService.getAll(key,page,maxResults);
		md.addAttribute("reimbursementApplications", reimbursementApplications);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/toDoManage/reimbursementApplication";
	}
	
	@RequestMapping("/reimbursementApplication/find")
	public @ResponseBody FullReimbursementApplicationViewModel findReimbursementApplication(@RequestParam int tourId){
		return reimbursementApplicationService.findReimbursementApplication(tourId);
	}
	
	@RequestMapping("/reimbursementApplication/ok")
	public String okReimbursementApplication(@RequestParam int tourId){
		reimbursementApplicationService.okReimbursementApplication(tourId);
		return "/mobile/applicationOk";
	}
	
	@RequestMapping("/reimbursementApplication/cancel")
	public void cancelReimbursementApplication(@RequestParam int tourId){
		reimbursementApplicationService.cancelReimbursementApplication(tourId);
	}
	
	@RequestMapping("/reimbursementApplication/checkStatus")
	public @ResponseBody int checkStatusReimbursementApplication(@RequestParam int tourId){
		return reimbursementApplicationService.checkStatusReimbursementApplication(tourId);
	}
}
