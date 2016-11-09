package com.cts.localtour.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.service.MobileService;

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
	
	@RequestMapping("/mobile/cancalCost")
	public void cancalCost(HttpServletRequest request, HttpSession session, @RequestParam int id){
		mobileService.cancalChangeCost(request, session, id);
	}
	
	@RequestMapping("/mobile/changeIcomeOk")
	public void changeIcomeOkOk(HttpServletRequest request, HttpSession session, @RequestParam int id){
		mobileService.updateChangeIcome(request, session, id);
	}
	
	@RequestMapping("/mobile/cancalIncome")
	public void cancalIncome(HttpServletRequest request, HttpSession session, @RequestParam int id){
		mobileService.cancalChangeIncome(request, session, id);
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
		mobileService.loanApplicationOk(request, session, id);
	}
	
	@RequestMapping("/mobile/loanApplicationCancel")
	public void loanApplicationCancel(HttpServletRequest request, HttpSession session, @RequestParam int id){
		mobileService.loanApplicationCancel(request, session, id);
	}
	
	/*付款申请*/
	@RequestMapping("/mobile/payApplication")
	public String payApplication(@RequestParam int tourId, @RequestParam int status, Model md){
		md.addAttribute("full", mobileService.getAllPayApplication(tourId, status));
		md.addAttribute("tour",(LocalTourTable)mobileService.getById("LocalTourTable", tourId));
		return "/mobile/payApplication";
	}
	
	@RequestMapping("/mobile/payApplicationOk")
	public void payApplicationOk(HttpServletRequest request, HttpSession session, @RequestParam int id, @RequestParam boolean change){
		mobileService.payApplicationOk(request, session, id, change);
	}
	
	@RequestMapping("/mobile/payApplicationCancel")
	public void payApplicationCancel(HttpServletRequest request, HttpSession session, @RequestParam int id, @RequestParam boolean change){
		mobileService.payApplicationCancel(request, session, id, change);
	}

	/*预借发票*/
	@RequestMapping("/mobile/loanInvoiceApplication")
	public String loanInvoiceApplication(@RequestParam int tourId, @RequestParam int status, Model md){
		md.addAttribute("loanInvoices", mobileService.getAllLoanInvoiceApplication(tourId, status));
		md.addAttribute("tour",(LocalTourTable)mobileService.getById("LocalTourTable", tourId));
		return "/mobile/loanInvoiceApplication";
	}
	
	@RequestMapping("/mobile/loanInvoiceApplicationOk")
	public void loanInvoiceApplicationOk(@RequestParam int id){
		mobileService.loanInvoiceApplicationOk(id);
	}
	
	@RequestMapping("/mobile/loanInvoiceApplicationCancel")
	public void loanInvoiceApplicationCancel(@RequestParam int id){
		mobileService.loanInvoiceApplicationCancel(id);
	}
}
