package com.cts.localtour.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.entity.SupplierScopeTable;
import com.cts.localtour.service.SupplierInfoService;
import com.cts.localtour.service.SupplierScopeService;
import com.cts.localtour.service.RegionService;
import com.cts.localtour.viewModel.SupplierInfoViewModel;

@Controller
public class SupplierController {
	@Autowired
	private SupplierInfoService supplierInfoService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private SupplierScopeService supplierScopeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/supplierInfoManage")
	public String getSupplierAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = supplierInfoService.getCounts(key);
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
		ArrayList<SupplierInfoViewModel> suppliers = supplierInfoService.getAll(key,page,maxResults);
		ArrayList<RegionTable> regions = (ArrayList<RegionTable>) regionService.getAllByString("RegionTable", "enable=?", true);
		ArrayList<SupplierScopeTable> supplierScopes = (ArrayList<SupplierScopeTable>) supplierScopeService.getAllByString("SupplierScopeTable", "enable=?", true);
		md.addAttribute("suppliers", suppliers);
		md.addAttribute("regions", regions);
		md.addAttribute("supplierScopes", supplierScopes);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/supplierManage/supplierInfoManage";
		
	}
	
	@RequestMapping("/supplierInfoManage/save")
	public @ResponseBody int save(@RequestBody SupplierTable supplier){
		supplier.setEnable(true);
		return supplierInfoService.add(supplier);
	}
	
	@RequestMapping("/supplierBusiness/save")
	public void saveSupplierBusiness(@RequestParam int supplierId, @RequestParam String supplierScopeIds){
		supplierInfoService.addSupplierBusiness(supplierId,supplierScopeIds);
	}
	
	@RequestMapping("/supplierInfoManage/del")
	public @ResponseBody boolean delSupplier(@RequestParam int id){
		supplierInfoService.del(id);
		return true;
	}
	
	@RequestMapping("/supplierInfoManage/recover")
	public @ResponseBody boolean recoverSupplier(@RequestParam int id){
		supplierInfoService.recover(id);
		return true;
	}
	
	@RequestMapping("/supplierInfoManage/update")
	public @ResponseBody boolean updata(@RequestBody SupplierTable supplier){
		supplierInfoService.update(supplier);
		return true;
	}
	
	@RequestMapping("/supplierBusiness/update")
	public void updateSupplierBusiness(@RequestParam int supplierId, @RequestParam String supplierScopeIds){
		supplierInfoService.deleteSupplierBusiness(supplierId);
		supplierInfoService.addSupplierBusiness(supplierId,supplierScopeIds);
	}
}
