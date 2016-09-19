package com.cts.localtour.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.service.MobileService;

import net.sf.json.JSONObject;

@Controller
public class MobileController {
	@Autowired
	private MobileService mobileService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/mobile/changeCostApproval")
	public String changeCostApproval(@RequestParam(defaultValue="{tourId:76}")String parm,Model md){
		JSONObject json = JSONObject.fromObject(parm);
		md.addAttribute("changeCosts", (ArrayList<ChangeCostTable>)mobileService.getAllByString("ChangeCostTable", "tourId=?", json.getInt("tourId")));
		return "/mobile/changeCost";
	}
}
