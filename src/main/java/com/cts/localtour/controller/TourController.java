package com.cts.localtour.controller;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.ArrTable;
import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.DepartTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.LoanInvoiceTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.TripTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.ArrService;
import com.cts.localtour.service.BillService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.DepartService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.LocalTourService;
import com.cts.localtour.service.PrintService;
import com.cts.localtour.service.TripService;
import com.cts.localtour.viewModel.ChangeCostIncomeViewModel;
import com.cts.localtour.viewModel.CreateInfoViewModel;
import com.cts.localtour.viewModel.FullBillViewModel;
import com.cts.localtour.viewModel.FullLoanInvoiceViewModel;
import com.cts.localtour.viewModel.FullLocalTourViewModel;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.FullReimbursementViewModel;
import com.cts.localtour.viewModel.LoanViewModel;
import com.cts.localtour.viewModel.PrintVoucherViewModel;
import com.cts.localtour.viewModel.SimpleBillCheckViewModel;
import com.cts.localtour.viewModel.SimpleLocalTourViewModel;

@Controller
public class TourController {
	@Autowired
	private LocalTourService localTourService;
//	@Autowired
//	private GuideTimeService guideTimeService;
	@Autowired
	private ArrService arrService;
	@Autowired
	private DepartService departService;
	@Autowired
	private TripService tripService;
	@Autowired
	private CostService costService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private BillService billService;
	@Autowired
	private PrintService printService;
	@RequestMapping("/localTourManage")
	public String getLocalTourAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = localTourService.getCounts(key);
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
		ArrayList<SimpleLocalTourViewModel> localTours = localTourService.getAll(key,page,maxResults);
		md.addAttribute("localTours", localTours);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/tourManage/localTourManage";
	}
	
	@RequestMapping("/localTourManage/getCreateInfo")
	public @ResponseBody CreateInfoViewModel getCreateInfo(){
		return localTourService.getCreateInfo();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/localTourManage/save")
	public @ResponseBody int save(@RequestBody FullLocalTourViewModel full){
		LocalTourTable localTour = full.getLocalTourTable();
//		ArrayList<GuideTimeTable> guideTimeTables = full.getGuideTimeTables();
		ArrayList<ArrTable> arrTables = full.getArrTables();
		ArrayList<DepartTable> departTables = full.getDepartTables();
		ArrayList<TripTable> tripTables = full.getTripTables();
		ArrayList<CostTable> costTables = full.getCostTables();
		ArrayList<IncomeTable> incomeTables = full.getIncomeTables();
		if(localTour.getAdultNo()==0||localTour.getBusinessTypeId()==0||localTour.getCustomerAgencyId()==0||localTour.getEndTime()==null||localTour.getOrganizor().equals("")||localTour.getRegionId()==0||localTour.getStartTime()==null||localTour.getTourName().equals("")||localTour.getTourNo().equals("")||localTour.getTourTypeId()==0||localTour.getVisitorTypeId()==0){
			return 0;
		}else{
			for (int i = 0; i < costTables.size(); i++) {
				if(costTables.get(i).getSupplierId()==0){
					return 0;
				}
			}
			for (int i = 0; i < incomeTables.size(); i++) {
				if(incomeTables.get(i).getCustomerAgencyId()==0){
					return 0;
				}
			}
			int tourId = localTourService.addLocalTour(localTour);
			if(tourId!=0){
				/*保存排团信息*/
				/*if(!guideTimeTables.isEmpty()){
					for (int i = 0; i < guideTimeTables.size(); i++) {
						guideTimeTables.get(i).setTourId(tourId);
						guideTimeService.add(guideTimeTables.get(i));
					}
				}*/
				/*保存抵达离开信息*/
				if(!arrTables.isEmpty()){
					for (int i = 0; i < arrTables.size(); i++) {
						arrTables.get(i).setTourId(tourId);
						arrService.add(arrTables.get(i));
					}
				}
				if(!departTables.isEmpty()){
					for (int i = 0; i < departTables.size(); i++) {
						departTables.get(i).setTourId(tourId);
						departService.add(departTables.get(i));
					}
				}
				/*保存行程*/
				if(!tripTables.isEmpty()){
					for (int i = 0; i < tripTables.size(); i++) {
						tripTables.get(i).setTourId(tourId);
						tripService.add(tripTables.get(i));
					}
				}
				/*保存成本*/
				if(!costTables.isEmpty()){
					for (int i = 0; i < costTables.size(); i++) {
						costTables.get(i).setTourId(tourId);
						costService.add(costTables.get(i));
					}
				}
				/*保存收入*/
				if(!incomeTables.isEmpty()){
					for (int i = 0; i < incomeTables.size(); i++) {
						incomeTables.get(i).setTourId(tourId);
						incomeService.add(incomeTables.get(i));
					}
				}
			}
			return tourId;
		}
	}
	
	/*删除*/
	@RequestMapping("/localTourManage/del")
	public @ResponseBody boolean delLocalTour(@RequestParam int id){
		if(((LocalTourTable) localTourService.getById("LocalTourTable", id)).getStatus()==0){
			localTourService.del(id);
			return true;
		}
		return false;
	}
	
	/*恢复*/
	@RequestMapping("/localTourManage/recover")
	public @ResponseBody boolean recoverLocalTour(@RequestParam int id){
		if(!((LocalTourTable) localTourService.getById("LocalTourTable", id)).isEnable()){
			localTourService.recover(id);
			return true;
		}
		return false;
	}
	
	/*提交待审*/
	@RequestMapping("/localTourManage/auditing")
	public @ResponseBody boolean auditingLocalTour(@RequestParam int id){
		if(((LocalTourTable) localTourService.getById("LocalTourTable", id)).getStatus()==0){
			localTourService.changeStatus(id,1);
			return true;
		}
		return false;
	}
	/*退回编辑*/
	@RequestMapping("/localTourManage/unAuditing")
	public @ResponseBody boolean unAuditingLocalTour(@RequestParam int id){
		if(((LocalTourTable) localTourService.getById("LocalTourTable", id)).getStatus()==1){
			localTourService.changeStatus(id,0);
			return true;
		}
		return false;
	}
	
	/*报送财务*/
	@RequestMapping("/localTourManage/finance")
	public @ResponseBody boolean financeLocalTour(@RequestParam int id){
		if(((LocalTourTable) localTourService.getById("LocalTourTable", id)).getStatus()==1){
			localTourService.changeStatus(id,2);
			return true;
		}
		return false;
	}
	
	/*取消报送*/
	@RequestMapping("/localTourManage/cancelFinance")
	public @ResponseBody int cancelFinance(@RequestParam int id){
		int errorCode = 0;
		if(((LocalTourTable) localTourService.getById("LocalTourTable", id)).getStatus()==2){
			localTourService.changeStatus(id,0);
		}else{
			errorCode = -1;
		}
		return errorCode;
	}
	
	/*申请结算*/
	@RequestMapping("/localTourManage/balance")
	public @ResponseBody boolean balanceLocalTour(@RequestParam int id){
		if(((LocalTourTable) localTourService.getById("LocalTourTable", id)).getStatus()==5){
			localTourService.changeStatus(id,6);
			return true;
		}
		return false;
	}
	
	@RequestMapping("/localTourManage/find")
	public @ResponseBody FullLocalTourViewModel find(@RequestParam int tourId){
		return localTourService.find(tourId);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/localTourManage/update")
	public @ResponseBody Integer update(@RequestBody FullLocalTourViewModel full){
		LocalTourTable localTour = full.getLocalTourTable();
//		ArrayList<GuideTimeTable> guideTimeTables = full.getGuideTimeTables();
		ArrayList<ArrTable> arrTables = full.getArrTables();
		ArrayList<DepartTable> departTables = full.getDepartTables();
		Hashtable<String, String> delIds = full.getDelIds();
		ArrayList<TripTable> tripTables = full.getTripTables();
		ArrayList<CostTable> costTables = full.getCostTables();
		ArrayList<IncomeTable> incomeTables = full.getIncomeTables();
		if(localTour.getAdultNo()==0||localTour.getBusinessTypeId()==0||localTour.getCustomerAgencyId()==0||localTour.getEndTime()==null||localTour.getOrganizor().equals("")||localTour.getRegionId()==0||localTour.getStartTime()==null||localTour.getTourName().equals("")||localTour.getTourNo().equals("")||localTour.getTourTypeId()==0||localTour.getVisitorTypeId()==0||localTour.getStartTime().getTime()>localTour.getEndTime().getTime()){
			return -1;
		}else{
			if(!localTourService.updateLocalTour(localTour)){
				return -1;
			}
			
			/*更新排团信息*/
			/*guideTimeService.deleteByString("GuideTimeTable", "tourId=?", localTour.getId());
			if(!guideTimeTables.isEmpty()){
				for (int i = 0; i < guideTimeTables.size(); i++) {
					guideTimeService.add(guideTimeTables.get(i));
				}
			}*/
			/*更新抵达离开信息*/
			if(!arrTables.isEmpty()){
				for (int i = 0; i < arrTables.size(); i++) {
					if(arrTables.get(i).getArrRegionId()==0||arrTables.get(i).getOriginId()==0){
						return -2;
					}else{
						arrService.merge(arrTables.get(i));
					}
				}
			}
			if(!departTables.isEmpty()){
				for (int i = 0; i < departTables.size(); i++) {
					if(departTables.get(i).getDepartRegionId()==0||departTables.get(i).getDestId()==0){
						return -3;
					}else{
						departService.merge(departTables.get(i));
					}
				}
			}
			/*更新行程*/
			if(!tripTables.isEmpty()){
				for (int i = 0; i < tripTables.size(); i++) {
					tripService.merge(tripTables.get(i));
				}
			}
			/*更新成本*/
			if(!costTables.isEmpty()){
				for (int i = 0; i < costTables.size(); i++) {
					if(costTables.get(i).getSupplierId()==0){
						return -4;
					}else{
						costService.merge(costTables.get(i));
					}
				}
			}
			/*更新收入*/
			if(!incomeTables.isEmpty()){
				for (int i = 0; i < incomeTables.size(); i++) {
					if(incomeTables.get(i).getCustomerAgencyId()==0){
						return -5;
					}else{
						incomeService.merge(incomeTables.get(i));
					}
				}
			}
			if(!delIds.isEmpty()){
				for (String tableName : delIds.keySet()) {
					if(!delIds.get(tableName).equals("")){
						localTourService.delByIds(tableName, delIds.get(tableName));
					}
				}
			}
		}
		return 0;
	}
	/*成本收入变更*/
	@RequestMapping("/localTourManage/findChangeCost")
	public @ResponseBody ChangeCostIncomeViewModel findChangeCost(@RequestParam int tourId){
		return localTourService.chanageCostIncomeFind(tourId);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/localTourManage/saveChangeCost")
	public @ResponseBody int saveChangeCost(@RequestBody ChangeCostIncomeViewModel changeCostIncomeViewModel, HttpServletRequest request){
		/*添加成本*/
		if(!changeCostIncomeViewModel.getCostTables().isEmpty()){
			for (int i = 0; i < changeCostIncomeViewModel.getCostTables().size(); i++) {
				if(changeCostIncomeViewModel.getCostTables().get(i).getSupplierId()==0){
					return -4;
				}else{
					changeCostIncomeViewModel.getCostTables().get(i).setStatus(3);/*设置状态*/
					changeCostIncomeViewModel.getCostTables().get(i).setApplicationerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
					costService.add(changeCostIncomeViewModel.getCostTables().get(i));
				}
			}
		}
		/*添加收入*/
		if(!changeCostIncomeViewModel.getIncomeTables().isEmpty()){
			for (int i = 0; i < changeCostIncomeViewModel.getIncomeTables().size(); i++) {
				if(changeCostIncomeViewModel.getIncomeTables().get(i).getCustomerAgencyId()==0){
					return -5;
				}else{
					changeCostIncomeViewModel.getIncomeTables().get(i).setStatus(3);/*设置状态*/
					changeCostIncomeViewModel.getIncomeTables().get(i).setApplicationerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
					incomeService.add(changeCostIncomeViewModel.getIncomeTables().get(i));
				}
			}
		}
		/*if(!changeCostIncomeViewModel.getCostTables().isEmpty()||!changeCostIncomeViewModel.getIncomeTables().isEmpty()){
			这里或许需要判断用户权限
			localTourService.sendMassage("changeCostIncomeApplication", changeCostIncomeViewModel.getCostTables().size()==0?changeCostIncomeViewModel.getIncomeTables().get(0).getTourId():changeCostIncomeViewModel.getCostTables().get(0).getTourId(), 1, "您有 "+localTourService.getTourNoAndTourName(changeCostIncomeViewModel.getCostTables().isEmpty()?changeCostIncomeViewModel.getIncomeTables().get(0).getTourId():changeCostIncomeViewModel.getCostTables().get(0).getTourId())+" 待审核(变更成本收入)，点击进行审核");
		}*/
		return 1;
	}
	/*借款管理*/
	@RequestMapping("/localTourManage/findLend")
	public @ResponseBody ArrayList<LoanViewModel> findLend(@RequestParam int tourId){
		return localTourService.findLend(tourId);
	}
	@RequestMapping("/localTourManage/loanApplication")
	public @ResponseBody boolean loanApplication(@RequestParam int tourId, @RequestParam String ids){
		if(!"".equals(ids)){
			localTourService.loanApplication(ids);
			return localTourService.sendMassage("loanApplication", tourId, 2, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(导游借款)，点击进行审核");
		}
		return false;
	}

	@RequestMapping("/localTourManage/loanApplicationAgain")
	public @ResponseBody boolean loanApplicationAgain(@RequestParam int tourId){
		return localTourService.sendMessageAgain("loanApplication", tourId, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(付款申请)，点击进行审核");
	}
	/*付款管理*/
	@RequestMapping("/localTourManage/findPay")
	public @ResponseBody FullPayViewModel findPay(@RequestParam int tourId){
		return localTourService.findPay(tourId);
	}
	@RequestMapping("/localTourManage/payApplication")
	public @ResponseBody int payApplication(@RequestBody FullPayViewModel full){
		int errorCode = 0;
		if(!full.getCostTables().isEmpty()||!full.getChangeCostTables().isEmpty()){
			errorCode = localTourService.payApplication(full.getCostTables(), full.getChangeCostTables());
			if(!localTourService.sendMassage("payApplication", !full.getCostTables().isEmpty()?full.getCostTables().get(0).getTourId():full.getChangeCostTables().get(0).getTourId(), 1, "您有 "+localTourService.getTourNoAndTourName(!full.getCostTables().isEmpty()?full.getCostTables().get(0).getTourId():full.getChangeCostTables().get(0).getTourId())+" 待审核的(付款申请)，点击进行审核")){
				errorCode = -2;
			}
		}
		return errorCode;
	}
	@RequestMapping("/localTourManage/payApplicationAgain")
	public @ResponseBody boolean payApplicationAgain(@RequestParam int tourId){
		return localTourService.sendMassage("payApplication", tourId, 1, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(付款申请)，点击进行审核");
	}
	
	/*预借发票*/
	@RequestMapping("/localTourManage/findBorrowInvoice")
	public @ResponseBody FullLoanInvoiceViewModel findBorrowInvoice(@RequestParam int tourId){
		return localTourService.findBorrowInvoice(tourId);
	}
	
	@RequestMapping("/localTourManage/saveBorrowInvoice")
	public @ResponseBody int saveBorrowInvoice(@RequestBody ArrayList<LoanInvoiceTable> loanInvoiceTables, HttpServletRequest request){
		int errorCode = 0;
		ArrayList<LoanInvoiceTable> loanInvoices = new ArrayList<LoanInvoiceTable>();
		int applicationerId = ((UserTable) SecurityUtils.getSubject().getPrincipal()).getId();
		for (LoanInvoiceTable loanInvoiceTable : loanInvoiceTables) {
			if("".equals(loanInvoiceTable.getRemark())||loanInvoiceTable.getInvoiceAmount()==0){
				errorCode = -1;
				break;
			}else{
				loanInvoiceTable.setApplicationerId(applicationerId);
				loanInvoiceTable.setRemark(loanInvoiceTable.getRemark().replaceAll("\n", "<br>"));
				loanInvoices.add(loanInvoiceTable);
			}
		}
		if(errorCode==0){
			localTourService.saveBorrowInvoice(loanInvoices);
			if(!loanInvoiceTables.isEmpty()){
				localTourService.sendMassage("loanInvoiceApplication", loanInvoiceTables.get(0).getTourId(), 1, "您有 "+localTourService.getTourNoAndTourName(loanInvoiceTables.get(0).getTourId())+" 待审核的(预借发票)，点击进行审核");
			}
		}
		return errorCode;
	}
	@RequestMapping("/localTourManage/borrowInvoiceAgain")
	public @ResponseBody boolean borrowInvoiceAgain(@RequestParam int tourId){
		return localTourService.sendMassage("loanInvoiceApplication", tourId, 1, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(预借发票)，点击进行审核");
	}
	
	/*团队报账*/
	@RequestMapping("/reimbursementManage/findReimbursement")
	public @ResponseBody FullReimbursementViewModel findReimbursement(@RequestParam int tourId){
		return localTourService.findReimbursement(tourId);
	}
	
	@RequestMapping("/reimbursementManage/checkReimbursement")
	public @ResponseBody boolean checkReimbursement(@RequestParam int tourId){
		return localTourService.checkReimbursement(tourId);
	}
	
	@RequestMapping("/reimbursementManage/updateReimbursement")
	public @ResponseBody int updateReimbursement(@RequestBody FullReimbursementViewModel full, HttpServletRequest request){
		int errorCode = 0;
		for (CostTable costTable : full.getCostTables()) {
			if(((CostTable)localTourService.getById("CostTable", costTable.getId())).getReimbursement()!=null){
				errorCode = -1;
				return errorCode;
			}
		}
		for (ChangeCostTable changeCostTable : full.getChangeCostTables()) {
			if(((ChangeCostTable)localTourService.getById("ChangeCostTable", changeCostTable.getId())).getReimbursement()!=null){
				errorCode = -1;
				return errorCode;
			}
		}
		for (ReimbursementCostTable reimbursementCostTable : full.getReimbursementCostTables()) {
			if(((ReimbursementCostTable)localTourService.getById("ReimbursementCostTable", reimbursementCostTable.getId())).getReimbursement()!=null){
				errorCode = -1;
				return errorCode;
			}
		}
		if(/*full.getReimbursementTable().getHeadAmount()==0||*/!localTourService.getAllByString("ReimbursementTable", "tourId=?", full.getReimbursementTable().getId()).isEmpty()){
			errorCode = -2;
			return errorCode;
		}
		
		if(errorCode == 0){
			if(!full.getChangeCostTables().isEmpty()||!full.getCostTables().isEmpty()||!full.getReimbursementCostTables().isEmpty()||!full.getNewReimbursementCostTables().isEmpty()){
				localTourService.updateReimbursement(full);
				localTourService.sendMassage("reimbursementApplication", full.getReimbursementTable().getTourId(), 0, "您有 "+localTourService.getTourNoAndTourName(full.getReimbursementTable().getTourId())+" 待审核的(团队报账)，点击进行审核");
			}
		}
		return errorCode;
	}
	
	/*签单管理*/
	@RequestMapping("/billCheckManage")
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
		ArrayList<SimpleBillCheckViewModel> bills = billService.getAll(key,page,maxResults);
		md.addAttribute("bills", bills);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/tourManage/billCheckManage";
	}
	
	@RequestMapping("/billCheckManage/find")
	public @ResponseBody FullBillViewModel findBill(@RequestParam int supplierId, @RequestParam(defaultValue="0") int relativePeriod){
		return billService.findBill(supplierId,relativePeriod);
	}
	
	@RequestMapping("/billCheckManage/update")
	public void updateBill(@RequestBody FullBillViewModel full){
		billService.updateBill(full);
	}
	
	/*打印导游借款、供应商付款凭证*/
	@RequestMapping("/localTourManage/printVoucher")
	public @ResponseBody PrintVoucherViewModel printPayVoucher(@RequestParam String type, @RequestParam int tourId){
		return printService.printPayVoucher(type, tourId);
	}
	@RequestMapping("/localTourManage/printCountPlus")
	public void printCountPlus(@RequestParam String type, @RequestParam int tourId){
		printService.printCountPlus(type, tourId);
	}
}
