package com.cts.localtour.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.entity.SupplierScopeTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.RegionService;
import com.cts.localtour.service.LocalTourService;
import com.cts.localtour.service.SupplierScopeService;
import com.cts.localtour.viewModel.SupplierInfoViewModel;

@Controller
public class TourController {
	@Autowired
	private LocalTourService localTourService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private SupplierScopeService supplierScopeService;
	
	@SuppressWarnings("unchecked")
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
		ArrayList<SupplierInfoViewModel> suppliers = localTourService.getAll(key,page,maxResults);
		ArrayList<RegionTable> regions = (ArrayList<RegionTable>) regionService.getAllByString("RegionTable", "enable=?", true);
		ArrayList<SupplierScopeTable> supplierScopes = (ArrayList<SupplierScopeTable>) supplierScopeService.getAllByString("SupplierScopeTable", "enable=?", true);
		md.addAttribute("suppliers", suppliers);
		md.addAttribute("regions", regions);
		md.addAttribute("supplierScopes", supplierScopes);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/tourManage/localTourManage";
		
	}
	
	@RequestMapping("/localTourManage/save")
	public @ResponseBody int save(@RequestBody SupplierTable supplier){
		supplier.setEnable(true);
		return localTourService.add(supplier);
	}
	
/*	@RequestMapping("/supplierBusiness/save")
	public void saveSupplierBusiness(@RequestParam int supplierId, @RequestParam String supplierScopeIds){
		localTourService.addSupplierBusiness(supplierId,supplierScopeIds);
	}*/
	
	@RequestMapping("/localTourManage/del")
	public @ResponseBody boolean delSupplier(@RequestParam int id){
		localTourService.del(id);
		return true;
	}
	
	@RequestMapping("/localTourManage/recover")
	public @ResponseBody boolean recoverSupplier(@RequestParam int id){
		localTourService.recover(id);
		return true;
	}
	
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
