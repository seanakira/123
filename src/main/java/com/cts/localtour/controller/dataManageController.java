package com.cts.localtour.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.BusinessTypeTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.entity.TourTypeTable;
import com.cts.localtour.entity.VisitorTypeTable;
import com.cts.localtour.service.BusinessTypeService;
import com.cts.localtour.service.RegionService;
import com.cts.localtour.service.TourTypeService;
import com.cts.localtour.service.VisitorTypeService;

@Controller
public class dataManageController {
	@Autowired
	private BusinessTypeService businessTypeService;
	@Autowired
	private TourTypeService tourTypeService;
	@Autowired
	private VisitorTypeService visitorTypeService;
	@Autowired
	private RegionService regionService;
/*
 * 
 * 业务类型管理
 * 
 * */
	@RequestMapping("/businessTypeManage")
	public String getBusinessTypeAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = businessTypeService.getCounts(key);
		int pageMax = counts/maxResults;
		if(counts!=1&&counts%maxResults>0){
			pageMax++;
		}
		if(page>pageMax){
			page=pageMax;
		}
		if(page<1){
			page=1;
		}
		ArrayList<BusinessTypeTable> businessTypes = businessTypeService.getAll(key,page,maxResults);
		md.addAttribute("businessTypes", businessTypes);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		return "/dataManage/businessTypeManage";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/businessTypeManage/save")
	public @ResponseBody boolean save(@RequestBody BusinessTypeTable businessType){
		businessType.setEnable(true);
		businessTypeService.add(businessType);
		return true;
	}
	
	@RequestMapping("/businessTypeManage/del")
	public @ResponseBody boolean delBusinessType(@RequestParam int id){
		businessTypeService.del(id);
		return true;
	}
	
	@RequestMapping("/businessTypeManage/recover")
	public @ResponseBody boolean recoverBusinessType(@RequestParam int id){
		businessTypeService.recover(id);
		return true;
	}
	
	@RequestMapping("/businessTypeManage/update")
	public @ResponseBody boolean updata(@RequestBody BusinessTypeTable businessType){
		businessTypeService.update(businessType);
		return true;
	}
/*
 * 
 * 团队类型管理
 * 
 * */
	@RequestMapping("/tourTypeManage")
	public String gettourTypeAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = tourTypeService.getCounts(key);
		int pageMax = counts/maxResults;
		if(counts!=1&&counts%maxResults>0){
			pageMax++;
		}
		if(page>pageMax){
			page=pageMax;
		}
		if(page<1){
			page=1;
		}
		ArrayList<TourTypeTable> tourTypes = tourTypeService.getAll(key,page,maxResults);
		md.addAttribute("tourTypes", tourTypes);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		return "/dataManage/tourTypeManage";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tourTypeManage/save")
	public @ResponseBody boolean save(@RequestBody TourTypeTable tourType){
		tourType.setEnable(true);
		tourTypeService.add(tourType);
		return true;
	}
	
	@RequestMapping("/tourTypeManage/del")
	public @ResponseBody boolean deltourType(@RequestParam int id){
		tourTypeService.del(id);
		return true;
	}
	
	@RequestMapping("/tourTypeManage/recover")
	public @ResponseBody boolean recovertourType(@RequestParam int id){
		tourTypeService.recover(id);
		return true;
	}
	
	@RequestMapping("/tourTypeManage/update")
	public @ResponseBody boolean updata(@RequestBody TourTypeTable tourType){
		tourTypeService.update(tourType);
		return true;
	}
/*
 * 
 * 游客类型管理
 * 
 * */
		@RequestMapping("/visitorTypeManage")
		public String getvisitorTypeAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
			int counts = visitorTypeService.getCounts(key);
			int pageMax = counts/maxResults;
			if(counts!=1&&counts%maxResults>0){
				pageMax++;
			}
			if(page>pageMax){
				page=pageMax;
			}
			if(page<1){
				page=1;
			}
			ArrayList<VisitorTypeTable> visitorTypes = visitorTypeService.getAll(key,page,maxResults);
			md.addAttribute("visitorTypes", visitorTypes);
			md.addAttribute("counts", counts);
			md.addAttribute("pageMax", pageMax);
			md.addAttribute("pageNo", page);
			return "/dataManage/visitorTypeManage";
			
		}
		
		@SuppressWarnings("unchecked")
		@RequestMapping("/visitorTypeManage/save")
		public @ResponseBody boolean save(@RequestBody VisitorTypeTable visitorType){
			visitorType.setEnable(true);
			visitorTypeService.add(visitorType);
			return true;
		}
		
		@RequestMapping("/visitorTypeManage/del")
		public @ResponseBody boolean delvisitorType(@RequestParam int id){
			visitorTypeService.del(id);
			return true;
		}
		
		@RequestMapping("/visitorTypeManage/recover")
		public @ResponseBody boolean recovervisitorType(@RequestParam int id){
			visitorTypeService.recover(id);
			return true;
		}
		
		@RequestMapping("/visitorTypeManage/update")
		public @ResponseBody boolean updata(@RequestBody VisitorTypeTable visitorType){
			visitorTypeService.update(visitorType);
			return true;
		}
/*
 * 
 * 地区管理
 * 
 * */
		@RequestMapping("/regionManage")
		public String getregionAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
			int counts = regionService.getCounts(key);
			int pageMax = counts/maxResults;
			if(counts!=1&&counts%maxResults>0){
				pageMax++;
			}
			if(page>pageMax){
				page=pageMax;
			}
			if(page<1){
				page=1;
			}
			ArrayList<RegionTable> regions = regionService.getAll(key,page,maxResults);
			md.addAttribute("regions", regions);
			md.addAttribute("counts", counts);
			md.addAttribute("pageMax", pageMax);
			md.addAttribute("pageNo", page);
			return "/dataManage/regionManage";
			
		}
		
		@SuppressWarnings("unchecked")
		@RequestMapping("/regionManage/save")
		public @ResponseBody boolean save(@RequestBody RegionTable region){
			region.setEnable(true);
			regionService.add(region);
			return true;
		}
		
		@RequestMapping("/regionManage/del")
		public @ResponseBody boolean delregion(@RequestParam int id){
			regionService.del(id);
			return true;
		}
		
		@RequestMapping("/regionManage/recover")
		public @ResponseBody boolean recoverregion(@RequestParam int id){
			regionService.recover(id);
			return true;
		}
		
		@RequestMapping("/regionManage/update")
		public @ResponseBody boolean updata(@RequestBody RegionTable region){
			regionService.update(region);
			return true;
		}		
}
