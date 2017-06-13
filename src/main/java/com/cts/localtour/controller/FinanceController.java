package com.cts.localtour.controller;

import java.math.BigDecimal;
import java.util.ArrayList;


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
import com.cts.localtour.service.BalanceService;
import com.cts.localtour.service.BillService;
import com.cts.localtour.service.InvoiceService;
import com.cts.localtour.service.LoanInvoiceService;
import com.cts.localtour.service.PayService;
import com.cts.localtour.service.ReimbursementApplicationService;
import com.cts.localtour.service.RevenueService;
import com.cts.localtour.service.SettlementService;
import com.cts.localtour.viewModel.FullBalanceViewModel;
import com.cts.localtour.viewModel.FullBillViewModel;
import com.cts.localtour.viewModel.FullInvoiceViewModel;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.FullReimbursementApplicationViewModel;
import com.cts.localtour.viewModel.FullRevenueViewModel;
import com.cts.localtour.viewModel.LoanInvoiceViewModel;
import com.cts.localtour.viewModel.SimplPayViewModel;
import com.cts.localtour.viewModel.SimpleBalanceViewModel;
import com.cts.localtour.viewModel.SimpleBillCheckViewModel;
import com.cts.localtour.viewModel.SimpleRevenueViewModel;
import com.cts.localtour.viewModel.SimpleSettlementViewModel;

@Controller
public class FinanceController {
	@Autowired
	private PayService payService;
	@Autowired
	private RevenueService revenueService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private LoanInvoiceService loanInvoiceService;
	@Autowired
	private BalanceService balanceService;
	@Autowired
	private SettlementService settlementService;
	@Autowired
	private ReimbursementApplicationService reimbursementApplicationService;
	@Autowired
	private BillService billService;
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
	public @ResponseBody int updateRevenue(@RequestBody FullRevenueViewModel full){
		return revenueService.updateRevenue(full);
	}
	
	/*发票管理*/
	@RequestMapping("/invoiceManage/find")
	public @ResponseBody FullInvoiceViewModel findInvoice(@RequestParam int tourId){
		return invoiceService.findInvoice(tourId);
	}
	
	@RequestMapping("/invoiceManage/save")
	public @ResponseBody int saveInvoice(@RequestBody ArrayList<InvoiceTable> invoiceTables){
		int errorCode = 0;
		BigDecimal invoice = new BigDecimal(0);
		ArrayList<InvoiceTable> invoices = new ArrayList<InvoiceTable>();
		for (InvoiceTable invoiceTable : invoiceTables) {
			if("".equals(invoiceTable.getInvoiceNo())||invoiceTable.getInvoiceAmount().floatValue()==0){
				errorCode = -1;
				break;
			}else{
				invoice = invoice.add(invoiceTable.getInvoiceAmount());
				invoices.add(invoiceTable);
			}
		}
		if(!invoices.isEmpty()){
			if(revenueService.invoiceGreaterThanIncome(invoice, invoices.get(0).getTourId())){
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
		BigDecimal newInvoiceSum = new BigDecimal(0);
		ArrayList<Integer> notInds = new ArrayList<Integer>();
		for (LoanInvoiceTable loanInvoiceTable : loanInvoiceTables) {
			newInvoiceSum = newInvoiceSum.add(loanInvoiceTable.getInvoiceAmount());
			/*验证发票是否8位*/
			/*if(loanInvoiceTable.getInvoiceNo().length()!=8){
				errorCode = -2;
			}*/
			notInds.add(loanInvoiceTable.getId());
		}
		ArrayList<LoanInvoiceTable> issueLoanInvoiceTable = (ArrayList<LoanInvoiceTable>) loanInvoiceService.getAllByString("LoanInvoiceTable", "tourId=? and status=? and id not in("+notInds.toString().substring(1, notInds.toString().length()-1)+")", loanInvoiceTables.get(0).getTourId(), 3);
		for (LoanInvoiceTable loanInvoiceTable : issueLoanInvoiceTable) {
			newInvoiceSum = newInvoiceSum.add(loanInvoiceTable.getInvoiceAmount());
		}
		if(!loanInvoiceTables.isEmpty()){
			if(revenueService.loanInvoiceGreaterThanIncome(newInvoiceSum, loanInvoiceTables.get(0).getTourId())){
				errorCode = -1;
			}else if(errorCode==0){
				loanInvoiceService.saveLoanInvoice(loanInvoiceTables);
			}
		}
		return errorCode;
	}
	/*核销管理*/
	@RequestMapping("/balanceManage")
	public String getBalanceAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = balanceService.getCounts(key);
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
		ArrayList<SimpleBalanceViewModel> balances = balanceService.getAll(key,page,maxResults);
		md.addAttribute("balances", balances);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/financeManage/balanceManage";
	}
	
	@RequestMapping("/balanceManage/find")
	public @ResponseBody FullBalanceViewModel findBalance(@RequestParam int tourId){
		return balanceService.findBalance(tourId);
	}
	
	@RequestMapping("/balanceManage/update")
	public void updateBalance(@RequestParam int tourId){
		balanceService.updateBalance(tourId);
	}
	
	@RequestMapping("/balanceManage/cancel")
	public void cancelBalance(@RequestParam int tourId){
		balanceService.cancelBalance(tourId);
	}
	
	/*结算管理*/
	@RequestMapping("/settlementManage")
	public String getSettlementAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = settlementService.getCounts(key);
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
		ArrayList<SimpleSettlementViewModel> settlements = settlementService.getAll(key,page,maxResults);
		md.addAttribute("settlements", settlements);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/financeManage/settlementManage";
	}
	
	@RequestMapping("/settlementManage/find")
	public @ResponseBody FullReimbursementApplicationViewModel findReimbursementApplication(@RequestParam int tourId){
		return reimbursementApplicationService.findReimbursementApplication(tourId);
	}
	
	@RequestMapping("/settlementManage/update")
	public void updateReimbursementApplication(@RequestParam int tourId){
		settlementService.updateSettlement(tourId);
	}
	
	@RequestMapping("/settlementManage/cancel")
	public void cancelReimbursementApplication(@RequestParam int tourId){
		settlementService.cancelSettlement(tourId);
	}
	
	@RequestMapping("/settlementManage/checkStatus")
	public @ResponseBody int checkStatusReimbursementApplication(@RequestParam int tourId){
		return settlementService.checkStatusSettlement(tourId);
	}
	
	/*挂账管理*/
	@RequestMapping("/billManage")
	public String getBillCheckAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = billService.getCounts(key);
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
		ArrayList<SimpleBillCheckViewModel> bills = billService.getAll(key,page,maxResults,3);
		md.addAttribute("bills", bills);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/financeManage/billManage";
	}
	
	@RequestMapping("/billManage/find")
	public @ResponseBody FullBillViewModel findBill(@RequestParam int supplierId, @RequestParam(defaultValue="0") int relativePeriod){
		return billService.findBill(supplierId,3,relativePeriod);
	}
	
	@RequestMapping("/billManage/update")
	public void updateBill(@RequestBody FullBillViewModel full){
		billService.remittanceBill(full);
	}
}