package com.cts.localtour.controller;

import java.util.ArrayList;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.StatisticsService;
import com.cts.localtour.viewModel.DeptGainsViewModel;

@Controller
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;
	@RequestMapping("/deptGains")
	public String deptGains(Model md){
		return "statistics/deptGains";
	}
	
	@RequestMapping("/deptGains/get")
	public @ResponseBody ArrayList<DeptGainsViewModel> getDeptGains (@RequestParam Date start, @RequestParam Date end){
		return statisticsService.getDeptGains(((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds().split(", "), start, end);
	}
	
	@RequestMapping("/supplierGains")
	public String supplierGains(Model md){
		return "statistics/supplierGains";
	}
	
	@RequestMapping("/supplierGains/get")
	public @ResponseBody ArrayList<DeptGainsViewModel> getSupplierGains (@RequestParam Date start, @RequestParam Date end){
		return statisticsService.getSupplierGains(((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds().split(", "), start, end);
	}
}
