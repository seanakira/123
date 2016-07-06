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
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.entity.SupplierScopeTable;
import com.cts.localtour.entity.TourTypeTable;
import com.cts.localtour.entity.VisitorTypeTable;
import com.cts.localtour.service.BusinessTypeService;
import com.cts.localtour.service.ContentService;
import com.cts.localtour.service.RegionService;
import com.cts.localtour.service.SupplierScopeService;
import com.cts.localtour.service.TourTypeService;
import com.cts.localtour.service.VisitorTypeService;
import com.cts.localtour.viewModel.ContentViewModel;

@Controller
public class DataManageController {
	@Autowired
	private BusinessTypeService businessTypeService;
	@Autowired
	private TourTypeService tourTypeService;
	@Autowired
	private VisitorTypeService visitorTypeService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private SupplierScopeService supplierScopeService;
	@Autowired
	private ContentService contentService;
	
	
/*
 * 
 * 业务类型管理
 * 
 * */
	@RequestMapping("/businessTypeManage")
	public String getBusinessTypeAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = businessTypeService.getCounts(key);
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
		ArrayList<BusinessTypeTable> businessTypes = businessTypeService.getAll(key,page,maxResults);
		md.addAttribute("businessTypes", businessTypes);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
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
		if(counts%maxResults>0){
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
		md.addAttribute("key", key);
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
			if(counts%maxResults>0){
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
			md.addAttribute("key", key);
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
			if(counts%maxResults>0){
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
			md.addAttribute("key", key);
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
/*
 * 
 * 供应范围管理管理
 * 
 * */
		@RequestMapping("/supplierScopeManage")
		public String getsupplierScopeAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
			int counts = supplierScopeService.getCounts(key);
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
			ArrayList<SupplierScopeTable> supplierScopes = supplierScopeService.getAll(key,page,maxResults);
			md.addAttribute("supplierScopes", supplierScopes);
			md.addAttribute("counts", counts);
			md.addAttribute("pageMax", pageMax);
			md.addAttribute("pageNo", page);
			md.addAttribute("key", key);
			return "/dataManage/supplierScopeManage";
			
		}
		
		@SuppressWarnings("unchecked")
		@RequestMapping("/supplierScopeManage/save")
		public @ResponseBody boolean save(@RequestBody SupplierScopeTable supplierScope){
			supplierScope.setEnable(true);
			supplierScopeService.add(supplierScope);
			return true;
		}
		
		@RequestMapping("/supplierScopeManage/del")
		public @ResponseBody boolean delsupplierScope(@RequestParam int id){
			supplierScopeService.del(id);
			return true;
		}
		
		@RequestMapping("/supplierScopeManage/recover")
		public @ResponseBody boolean recoversupplierScope(@RequestParam int id){
			supplierScopeService.recover(id);
			return true;
		}
		
		@RequestMapping("/supplierScopeManage/update")
		public @ResponseBody boolean updata(@RequestBody SupplierScopeTable supplierScope){
			supplierScopeService.update(supplierScope);
			return true;
		}
/*
 * 
 * 供应内容管理管理
 * 
 * */
		@SuppressWarnings("unchecked")
		@RequestMapping("/contentManage")
		public String getcontentAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
			int counts = contentService.getCounts(key);
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
			ArrayList<ContentViewModel> contents = contentService.getAll(key,page,maxResults);
			ArrayList<SupplierScopeTable> supplierScopes = supplierScopeService.getAllByTableName("SupplierScopeTable", 1, 999);
			md.addAttribute("contents", contents);
			md.addAttribute("supplierScopes", supplierScopes);
			md.addAttribute("counts", counts);
			md.addAttribute("pageMax", pageMax);
			md.addAttribute("pageNo", page);
			md.addAttribute("key", key);
			return "/dataManage/contentManage";
			
		}
		
		@SuppressWarnings("unchecked")
		@RequestMapping("/contentManage/save")
		public @ResponseBody boolean save(@RequestBody SupplierContentTable content){
			content.setEnable(true);
			contentService.add(content);
			return true;
		}
		
		@RequestMapping("/contentManage/del")
		public @ResponseBody boolean delcontent(@RequestParam int id){
			contentService.del(id);
			return true;
		}
		
		@RequestMapping("/contentManage/recover")
		public @ResponseBody boolean recovercontent(@RequestParam int id){
			contentService.recover(id);
			return true;
		}
		
		@SuppressWarnings("unchecked")
		@RequestMapping("/contentManage/update")
		public @ResponseBody boolean updata(@RequestBody SupplierContentTable content){
			contentService.update(content);
			return true;
		}
}
