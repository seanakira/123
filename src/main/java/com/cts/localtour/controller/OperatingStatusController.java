package com.cts.localtour.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OperatingStatusController {

	@RequestMapping("/operatingStatus")
	public String operatingStatus(){
		return "/operatingStatus/operatingStatus";
	}
}

