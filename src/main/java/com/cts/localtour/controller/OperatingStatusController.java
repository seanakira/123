package com.cts.localtour.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.SysUsageTable;
import com.cts.localtour.service.SysUsageService;

@Controller
public class OperatingStatusController {
	@Autowired
	private SysUsageService sysUsageService;
	@RequestMapping("/operatingStatus")
	public String operatingStatus(){
		return "/operatingStatus/operatingStatus";
	}
	
	@RequestMapping("/operatingStatus/getAllSysUsage")
	public @ResponseBody ArrayList<SysUsageTable> getAllSysUsage(){
		return sysUsageService.getAllSysUsage();
	}
}

