package com.cts.localtour.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.MobileService;

@Controller
public class MobileController {
	@Autowired
	private MobileService mobileService;
	/*成本收入更改*/
	@RequestMapping("/mobile/changeCostIncomeApplication")
	public String changeCostApproval(@RequestParam int id, @RequestParam int status, Model md){
		md.addAttribute("changes", mobileService.getAllChangCostIncome(id,status));
		md.addAttribute("tour",(LocalTourTable)mobileService.getById("LocalTourTable", id));
		return "/mobile/changeCostIncome";
	}
	
	@RequestMapping("/mobile/changeCostOk")
	public void changeCostOk(HttpServletRequest request, @RequestParam int id){
		mobileService.updateChangeCost(request, id);
	}
	
	@RequestMapping("/mobile/cancelCost")
	public void cancelCost(HttpServletRequest request, @RequestParam int id){
		mobileService.cancelChangeCost(request, id);
	}
	
	@RequestMapping("/mobile/changeIcomeOk")
	public void changeIcomeOkOk(HttpServletRequest request, @RequestParam int id){
		mobileService.updateChangeIcome(request, id);
	}
	
	@RequestMapping("/mobile/cancelIncome")
	public void cancelIncome(HttpServletRequest request, @RequestParam int id){
		mobileService.cancelChangeIncome(request, id);
	}
	
	
	/*借款申请*/
	@RequestMapping("/mobile/loanApplication")
	public String loanApplication(@RequestParam int id, @RequestParam int status, Model md){
		md.addAttribute("loans", mobileService.getAllLoanApplication(id, status));
		md.addAttribute("tour",(LocalTourTable)mobileService.getById("LocalTourTable", id));
		return "/mobile/loanApplication";
	}
	
	@RequestMapping("/mobile/loanApplicationOk")
	public void loanApplicationOk(HttpServletRequest request, @RequestParam int id){
		mobileService.loanApplicationOk(request, id);
	}
	
	@RequestMapping("/mobile/loanApplicationAllOk")
	public void loanApplicationAllOk(HttpServletRequest request, @RequestBody int[] ids){
		mobileService.loanApplicationAllOk(request, ids);
	}
	
	@RequestMapping("/mobile/loanApplicationCancel")
	public void loanApplicationCancel(HttpServletRequest request, @RequestParam int id){
		mobileService.loanApplicationCancel(request, id);
	}
	
	/*付款申请*/
	@RequestMapping("/mobile/payApplication")
	public String payApplication(@RequestParam int id, @RequestParam int status, Model md){
		md.addAttribute("full", mobileService.getAllPayApplication(id, status));
		md.addAttribute("tour",(LocalTourTable)mobileService.getById("LocalTourTable", id));
		return "/mobile/payApplication";
	}
	
	@RequestMapping("/mobile/payApplicationOk")
	public void payApplicationOk(HttpServletRequest request, @RequestParam int id, @RequestParam boolean change){
		mobileService.payApplicationOk(request, id, change);
	}
	
	@RequestMapping("/mobile/payApplicationAllOk")
	public void payApplicationAllOk(HttpServletRequest request, @RequestBody ArrayList<String[]> ids){
		mobileService.payApplicationAllOk(request, ids);
	}
	
	@RequestMapping("/mobile/payApplicationCancel")
	public void payApplicationCancel(HttpServletRequest request, @RequestParam int id, @RequestParam boolean change){
		mobileService.payApplicationCancel(id, change);
	}

	/*预借发票*/
	@RequestMapping("/mobile/loanInvoiceApplication")
	public String loanInvoiceApplication(@RequestParam int id, @RequestParam int status, Model md){
		md.addAttribute("loanInvoices", mobileService.getAllLoanInvoiceApplication(id, status));
		md.addAttribute("tour",(LocalTourTable)mobileService.getById("LocalTourTable", id));
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
	
	/*报账审核*/
	@RequestMapping("/mobile/reimbursementApplication")
	public String reimbursementApplication(@RequestParam int id, Model md){
		md.addAttribute("reimbursement", mobileService.getAllReimbursementApplication(id));
		md.addAttribute("tour",(LocalTourTable)mobileService.getById("LocalTourTable", id));
		return "/mobile/reimbursementApplication";
	}
	
	@RequestMapping("/mobile/reimbursementApplicationOk")
	public void reimbursementApplicationOk(@RequestParam int id){
		mobileService.reimbursementApplicationOk(id);
	}
	
	@RequestMapping("/mobile/reimbursementApplicationCancel")
	public void reimbursementApplicationCancel(@RequestParam int id){
		mobileService.reimbursementApplicationCancel(id);
	}
	
	/*供应商挂账付款申请*/
	@RequestMapping("/mobile/billApplication")
	public String billApplication(@RequestParam int id, @RequestParam int status, Model md){
		md.addAttribute("full", mobileService.getAllBillApplication(id, status));
		md.addAttribute("supplier",(SupplierTable)mobileService.getById("SupplierTable", id));
		return "/mobile/billApplication";
	}
	
	@RequestMapping("/mobile/billApplicationOk")
	public void billApplicationOk(@RequestParam int id, @RequestParam boolean change){
		mobileService.billApplicationOk(id, change);
	}
	
	@RequestMapping("/mobile/billApplicationCancel")
	public void billApplicationCancel(@RequestParam int id, @RequestParam boolean change){
		mobileService.billApplicationCancel(id, change);
	}
	
	/*发送测试*/
	/*@RequestMapping("/sendTest")
	public void sendTest(){
		WeiXinUtil.sendTextMessage("hanguangjun@ctssd.com", "http://erp.ctssd.com/mobile/payApplication?id=77&status=2", "付款申请", "0");
	}*/
}
