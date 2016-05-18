package com.cts.localtour.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.service.CustomerAgencyService;
import com.cts.localtour.service.RegionService;
import com.cts.localtour.viewModel.CustomerAgencyViewModel;
@Controller
public class CustomerAgencyController {
	@Autowired
	private CustomerAgencyService customerAgencyService;
	@Autowired
	private RegionService regionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/customerAgencyManage")
	public String getCustomerAgencyAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = customerAgencyService.getCounts(key);
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
		ArrayList<CustomerAgencyViewModel> customerAgencys = customerAgencyService.getAll(key,page,maxResults);
		ArrayList<RegionTable>regions = (ArrayList<RegionTable>) regionService.getAllByString("RegionTable", "enable=?", true);
		md.addAttribute("customerAgencys", customerAgencys);
		md.addAttribute("regions", regions);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/customerManage/customerAgencyManage";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/customerAgencyManage/save")
	public @ResponseBody boolean save(@RequestBody CustomerAgencyTable customerAgency){
		customerAgency.setEnable(true);
		customerAgencyService.add(customerAgency);
		return true;
	}
	
	@RequestMapping("/customerAgencyManage/del")
	public @ResponseBody boolean delCustomerAgency(@RequestParam int id){
		customerAgencyService.del(id);
		return true;
	}
	
	@RequestMapping("/customerAgencyManage/recover")
	public @ResponseBody boolean recoverCustomerAgency(@RequestParam int id){
		customerAgencyService.recover(id);
		return true;
	}
	
	@RequestMapping("/customerAgencyManage/update")
	public @ResponseBody boolean updata(@RequestBody CustomerAgencyTable customerAgency){
		customerAgencyService.update(customerAgency);
		return true;
	}
}
