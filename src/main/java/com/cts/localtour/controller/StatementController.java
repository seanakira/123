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
import com.cts.localtour.entity.LoanInvoiceTable;
import com.cts.localtour.service.InvoiceService;
import com.cts.localtour.service.LoanInvoiceService;
import com.cts.localtour.service.PayService;
import com.cts.localtour.service.RevenueService;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.FullRevenueViewModel;
import com.cts.localtour.viewModel.InvoiceViewModel;
import com.cts.localtour.viewModel.LoanInvoiceViewModel;
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
	@Autowired
	private LoanInvoiceService loanInvoiceService;
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
		float invoice = 0;
		ArrayList<InvoiceTable> invoices = new ArrayList<InvoiceTable>();
		for (InvoiceTable invoiceTable : invoiceTables) {
			if("".equals(invoiceTable.getInvoiceNo())||"".equals(invoiceTable.getInvoiceName())||invoiceTable.getInvoiceAmount()==0){
				errorCode = -1;
				break;
			}else{
				invoice = invoice + invoiceTable.getInvoiceAmount();
				invoices.add(invoiceTable);
			}
		}
		if(!invoices.isEmpty()){
			if(revenueService.InvoiceGreaterThanIncome(invoice, invoices.get(0).getTourId())){
				errorCode = -3;
			}else if(errorCode==0){
				try {
					invoiceService.saveInvoice(invoices);
				} catch (Exception e) {
					if(e instanceof DataIntegrityViolationException){
						errorCode = -2;
					}
				}
			}
		}
		return errorCode;
	}
	
	/*预借发票管理*/
	@RequestMapping("/loanInvoiceManage/find")
	public @ResponseBody ArrayList<LoanInvoiceViewModel> findLoanInvoice(@RequestParam int tourId){
		return loanInvoiceService.findInvoice(tourId);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/loanInvoiceManage/save")
	public @ResponseBody int saveLoanInvoice(@RequestBody ArrayList<LoanInvoiceTable> loanInvoiceTables){
		int errorCode = 0;
		float newInvoiceSum = 0;
		for (LoanInvoiceTable loanInvoiceTable : loanInvoiceTables) {
			ArrayList<LoanInvoiceTable> loanInvoiceTables2 = (ArrayList<LoanInvoiceTable>) loanInvoiceService.getAllByString("LoanInvoiceTable", "tourId=? and status=3", loanInvoiceTable.getId());
			newInvoiceSum = newInvoiceSum + (loanInvoiceTables2.isEmpty()?0:loanInvoiceTables2.get(0).getInvoiceAmount());
		}
		if(!loanInvoiceTables.isEmpty()){
			if(revenueService.InvoiceGreaterThanIncome(newInvoiceSum, loanInvoiceTables.get(0).getTourId())){
				errorCode = -1;
			}else if(errorCode==0){
				loanInvoiceService.saveLoanInvoice(loanInvoiceTables);
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