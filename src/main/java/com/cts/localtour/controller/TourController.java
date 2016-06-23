package com.cts.localtour.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.BusinessTypeTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.entity.SupplierScopeTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.TourTypeTable;
import com.cts.localtour.entity.VisitorTypeTable;
import com.cts.localtour.service.RegionService;
import com.cts.localtour.service.LocalTourService;
import com.cts.localtour.service.SupplierScopeService;
import com.cts.localtour.viewModel.CreateInfoViewModel;
import com.cts.localtour.viewModel.SimpleLocalTourViewModel;
import com.cts.localtour.viewModel.SupplierInfoViewModel;

@Controller
public class TourController {
	@Autowired
	private LocalTourService localTourService;
	
	@RequestMapping("/localTourManage")
	public String getSupplierAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = localTourService.getCounts(key);
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
		ArrayList<SimpleLocalTourViewModel> localTours = localTourService.getAll(key,page,maxResults);
		md.addAttribute("localTours", localTours);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/tourManage/localTourManage";
	}
	
	@RequestMapping("/localTourManage/getCreateInfo")
	public @ResponseBody CreateInfoViewModel getCreateInfo(){
		return localTourService.getCreateInfo();
	}
	
	@RequestMapping("/localTourManage/save")
	public @ResponseBody int save(@RequestBody LocalTourTable localTour){
		localTour.setEnable(true);
		return localTourService.add(localTour);
	}
	
/*	@RequestMapping("/supplierBusiness/save")
	public void saveSupplierBusiness(@RequestParam int supplierId, @RequestParam String supplierScopeIds){
		localTourService.addSupplierBusiness(supplierId,supplierScopeIds);
	}*/
	
	@RequestMapping("/localTourManage/del")
	public @ResponseBody boolean delLocalTour(@RequestParam int id){
		System.out.println(id);
		localTourService.del(id);
		return true;
	}
	
	@RequestMapping("/localTourManage/recover")
	public @ResponseBody boolean recoverLocalTour(@RequestParam int id){
		localTourService.recover(id);
		return true;
	}
	
	@RequestMapping("/localTourManage/chanageStatus/{status}")
	public @ResponseBody boolean auditingLocalTour(@RequestParam int id ,@PathVariable int status){
		localTourService.changeStatus(id,status);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/localTourManage/update")
	public @ResponseBody boolean updata(@RequestBody SupplierTable supplier){
		localTourService.update(supplier);
		return true;
	}
	
/*	@RequestMapping("/supplierBusiness/update")
	public void updateSupplierBusiness(@RequestParam int supplierId, @RequestParam String supplierScopeIds){
		localTourService.deleteSupplierBusiness(supplierId);
		localTourService.addSupplierBusiness(supplierId,supplierScopeIds);
	}*/
}
