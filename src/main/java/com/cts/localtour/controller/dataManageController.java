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
import com.cts.localtour.service.BusinessTypeService;

@Controller
public class dataManageController {
	@Autowired
	private BusinessTypeService businessTypeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/businessTypeManage")
	public String getBusinessTypeAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		ArrayList<BusinessTypeTable> businessTypes = businessTypeService.getAll(key,page,maxResults);
		md.addAttribute("businessTypes", businessTypes);
		return "/dataManage/businessTypeManage";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/businessTypeManage/save")
	public @ResponseBody boolean save(@RequestBody BusinessTypeTable businessTypeTable){
		businessTypeTable.setEnable(true);
		businessTypeService.add(businessTypeTable);
		return true;
	}
}
