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

import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.GuideTable;
import com.cts.localtour.entity.GuideTimeTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.GuideService;
import com.cts.localtour.service.GuideTimeService;
import com.cts.localtour.service.UserService;
import com.cts.localtour.viewModel.GuideTimeViewModel;
import com.cts.localtour.viewModel.GuideViewModel;

@Controller
public class GuideController {
	@Autowired
	private GuideTimeService guideTimeService;
	@Autowired
	private GuideService guideService;
	@Autowired
	private UserService userService;
/*
 * 
 * 导游排团管理
 * 
 * */
	@RequestMapping("/guideTimeManage")
	public String getAllGuideTime(Model md ){
		return "guideManage/guideTimeManage";
	}
	
	@RequestMapping("/guideTimeManage/initialize")
	public @ResponseBody ArrayList<GuideTimeViewModel> initialize(@RequestParam Date from,@RequestParam Date to){
		ArrayList<GuideTimeViewModel> guideTimes = guideTimeService.getAll(from,to);
		return guideTimes;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/guideTimeManage/save")
	public @ResponseBody boolean saveGuideTime(@RequestBody GuideTimeTable guideTime){
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
	
	@RequestMapping("/guideTimeManage/get")
	public @ResponseBody GuideTimeTable getById(@RequestParam int id){
		return (GuideTimeTable) guideTimeService.getById("GuideTimeTable", id);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/guideTimeManage/update")
	public @ResponseBody boolean updateGuideTime(@RequestBody GuideTimeTable guideTime){
		if(guideTime.getStartTime()==null&&guideTime.getEndTime()==null&&guideTime.getTourId()==null){
			guideTimeService.delById("GuideTimeTable", guideTime.getId());
			return true;
		}else if(guideTime.getStartTime()==null||guideTime.getEndTime()==null){
			return false;
		}else if(guideTime.getStartTime().getTime()>guideTime.getEndTime().getTime()){
			return false;
		}else if(!guideTimeService.checkTime(guideTime.getId(), guideTime.getGuideId(), guideTime.getStartTime(), guideTime.getEndTime())){
			return false;
		}else{
			guideTimeService.update(guideTime);
			return true;
		}
	}
	@RequestMapping("/guideTimeManage/checkTime")
	public @ResponseBody ArrayList<GuideViewModel> checkTime(@RequestParam Date startTime, @RequestParam Date endTime){
		if(startTime==null&&endTime==null){
			return null;
		}else if(startTime.getTime()>endTime.getTime()){
			return null;
		}else{
			return guideTimeService.checkTime(startTime, endTime);
		}
	}

/*
 * 
 * 导游管理
 * 
 * */
	@SuppressWarnings("unchecked")
	@RequestMapping("/guideManage")
	public String getAllGuide(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = guideService.getCounts(key);
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
		ArrayList<GuideViewModel> guides = guideService.getAll(key,page,maxResults);
		ArrayList<UserTable> users = (ArrayList<UserTable>) userService.getAllByString("UserTable", "enable=?", true);
		md.addAttribute("guides", guides);
		md.addAttribute("users", users);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/guideManage/guideManage";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/guideManage/save")
	public @ResponseBody boolean saveGuide(@RequestBody GuideTable guide){
		if(guide.getUserId()==0){
			return false;
		}else if(guideService.exist(guide.getUserId())){
			return false;
		}else{
			guideService.add(guide);
			return true;
		}
	}
	
	@RequestMapping("/guideManage/del")
	public @ResponseBody boolean delGuide(@RequestParam int id){
		guideService.del(id);
		return true;
	}
	
	@RequestMapping("/guideManage/recover")
	public @ResponseBody boolean recoverGuide(@RequestParam int id){
		guideService.recover(id);
		return true;
	}
	
	@RequestMapping("/guideManage/update")
	public @ResponseBody boolean updataGuide(@RequestBody GuideTable guide){
		if(guide.getUserId()==0){
			return false;
		}else if(guideService.exist(guide.getUserId())){
			return false;
		}else{
			guideService.update(guide);
			return true;
		}
	}	
}
