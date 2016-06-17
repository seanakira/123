package com.cts.localtour.controller;


import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.GuideTimeTable;
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/guideTimeManage/save")
	public @ResponseBody boolean guideTimeSave(@RequestBody GuideTimeTable guideTime){
		if(guideTime.getStartTime()==null||guideTime.getEndTime()==null){
			return false;
		}else if(guideTime.getStartTime().getTime()>guideTime.getEndTime().getTime()){
			return false;
		}else if(!guideTimeService.checkTime(guideTime.getGuideId(), guideTime.getStartTime(), guideTime.getEndTime())){
			return false;
		}else{
			guideTimeService.add(guideTime);
			return true;
		}
	}
}
