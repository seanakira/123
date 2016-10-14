package com.cts.localtour.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.MobileService;
import com.cts.localtour.viewModel.ChangeCostViewModel;
import com.cts.localtour.viewModel.LoanViewModel;

@Controller
public class MobileController {
	@Autowired
	private MobileService mobileService;
	/*成本收入更改*/
	@RequestMapping("/mobile/changeCostIncomeApproval")
	public String changeCostApproval(@RequestParam int tourId, @RequestParam int status, Model md){
		md.addAttribute("changes", mobileService.getAllChangCostIncome(tourId,status));
		md.addAttribute("tour",(LocalTourTable)mobileService.getById("LocalTourTable", tourId));
		return "/mobile/changeCostIncome";
	}
	
	@RequestMapping("/mobile/changeCostOk")
	public void changeCostOk(HttpServletRequest request, HttpSession session, @RequestParam int id){
		mobileService.updateChangeCost(request, session, id);
	}
	/*借款申请*/
	@RequestMapping("/mobile/loanApplication")
	public String loanApplication(@RequestParam int tourId, @RequestParam int status, Model md){
		md.addAttribute("loans", mobileService.getAllLoanApplication(tourId, status));
		md.addAttribute("tour",(LocalTourTable)mobileService.getById("LocalTourTable", tourId));
		return "/mobile/loanApplication";
	}
	
	@RequestMapping("/mobile/loanApplicationOk")
	public void loanApplicationOk(HttpServletRequest request, HttpSession session, @RequestParam int id){
		mobileService.updateLoanTable(request, session, id);
	}
}
