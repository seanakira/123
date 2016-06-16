package com.cts.localtour.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.service.GuideTimeService;
import com.cts.localtour.viewModel.GuideTimeViewModel;

@Controller
public class GuideController {
	@Autowired
	private GuideTimeService guideTimeService;
	
	@RequestMapping("/guideTimeManage")
	public String getAll(Model md ){
		return "guideManage/guideTimeManage";
	}
	
	@RequestMapping("/guideTimeManage/initialize")
	public @ResponseBody ArrayList<GuideTimeViewModel> initialize(@RequestParam Date from,@RequestParam Date to){
		ArrayList<GuideTimeViewModel> guideTimes = guideTimeService.getAll(from,to);
		return guideTimes;
	}
}
