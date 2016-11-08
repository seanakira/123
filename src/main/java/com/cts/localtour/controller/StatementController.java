package com.cts.localtour.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.service.InvoiceService;
import com.cts.localtour.service.PayService;
import com.cts.localtour.service.RevenueService;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.FullRevenueViewModel;
import com.cts.localtour.viewModel.InvoiceViewModel;
import com.cts.localtour.viewModel.SimplPayViewModel;
import com.cts.localtour.viewModel.SimpleRevenueViewModel;

@Controller
public class StatementController {
	@Autowired
	private PayService payService;
	@Autowired
	private RevenueService revenueService;
	@Autowired
	private InvoiceService invoiceService;
	/*付款管理*/
	@RequestMapping("/payManage")
	public String getPayAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = payService.getCounts(key);
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
		ArrayList<SimplPayViewModel> pays = payService.getAll(key,page,maxResults);
		md.addAttribute("pays", pays);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/financeManage/payManage";
	}

	@RequestMapping("/payManage/find")
	public @ResponseBody FullPayViewModel findPay(@RequestParam int tourId){
		return payService.findPay(tourId);
	}
	
	@RequestMapping("/payManage/update")
	public @ResponseBody int updatePay(@RequestBody FullPayViewModel full){
		return payService.updatePay(full);
	}
	
	/*收款管理*/
	@RequestMapping("/revenueManage")
	public String getCollectManageAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = revenueService.getCounts(key);
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
		ArrayList<SimpleRevenueViewModel> revenues = revenueService.getAll(key,page,maxResults);
		md.addAttribute("revenues", revenues);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/financeManage/revenueManage";
	}
	
	@RequestMapping("/revenueManage/find")
	public @ResponseBody FullRevenueViewModel findRevenue(@RequestParam int tourId){
		return revenueService.findRevenue(tourId);
	}
	
	@RequestMapping("/revenueManage/update")
	public @ResponseBody int updateRevenue(@RequestBody FullRevenueViewModel full, HttpSession session){
		return revenueService.updateRevenue(full, session);
	}
	
	/*发票管理*/
	@RequestMapping("/invoiceManage/find")
	public @ResponseBody ArrayList<InvoiceViewModel> findInvoice(@RequestParam int tourId){
		return invoiceService.findInvoice(tourId);
	}
	
	@RequestMapping("/invoiceManage/save")
	public @ResponseBody int saveInvoice(@RequestBody ArrayList<InvoiceTable> invoiceTables){
		int errorCode = 0;
		ArrayList<InvoiceTable> invoices = new ArrayList<InvoiceTable>();
		for (InvoiceTable invoiceTable : invoiceTables) {
			if("".equals(invoiceTable.getInvoiceNo())||"".equals(invoiceTable.getInvoiceName())||"".equals(invoiceTable.getInvoiceAmount())){
				errorCode = -1;
			}else{
				invoices.add(invoiceTable);
			}
		}
		if(errorCode!=-1){
			try {
				invoiceService.saveInvoice(invoices);
			} catch (Exception e) {
				if(e instanceof DataIntegrityViolationException){
					errorCode = -2;
				}
			}
		}
		return errorCode;
	}
	
	/*核销管理*/
	@RequestMapping("/balanceManage")
	public String getTourVerifyAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/financeManage/balanceManage";
	}
	
	/*结算管理*/
	@RequestMapping("/settlementManage")
	public String getTourBalanceAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		return "/financeManage/settlementManage";
	}
}