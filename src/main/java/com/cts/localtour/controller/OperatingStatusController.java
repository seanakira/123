package com.cts.localtour.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.util.WeiXinUtil;

@Controller
public class OperatingStatusController {

	@RequestMapping("/operatingStatus")
	public String operatingStatus(){
		return "/operatingStatus/operatingStatus";
	}
}

