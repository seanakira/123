package com.cts.localtour.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

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
import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.DepartTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.LoanInvoiceTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.RefundTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.TripTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.pdf.PdfMaker;
import com.cts.localtour.service.ArrService;
import com.cts.localtour.service.BillService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.DepartService;
import com.cts.localtour.service.DeptService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.LocalTourService;
import com.cts.localtour.service.PrintService;
import com.cts.localtour.service.TripService;
import com.cts.localtour.service.UserService;
import com.cts.localtour.util.WeiXinUtil;
import com.cts.localtour.viewModel.ChangeCostIncomeViewModel;
import com.cts.localtour.viewModel.CreateInfoViewModel;
import com.cts.localtour.viewModel.FullBillViewModel;
import com.cts.localtour.viewModel.FullLoanInvoiceViewModel;
import com.cts.localtour.viewModel.FullLocalTourViewModel;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.FullRefundViewModel;
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
	@Autowired
	private PdfMaker pdfMaker;
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@RequestMapping("/localTourManage")
	public String getLocalTourAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key,@RequestParam(defaultValue="2000/05/01") Date start,@RequestParam(defaultValue="2100/05/01") Date end, @RequestParam(defaultValue="") String deptIds, @RequestParam(defaultValue="") String userIds, @RequestParam(defaultValue="-1") int status, Model md){
		deptIds = deptService.getDownerDpetIds(deptIds);
		int counts = localTourService.getCounts(key, start, end, deptIds, userIds, status);
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
		ArrayList<SimpleLocalTourViewModel> localTours = localTourService.getAll(key,page,maxResults,start,end,deptIds,userIds,status);
		md.addAttribute("localTours", localTours);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		md.addAttribute("start", start);
		md.addAttribute("end", end);
		md.addAttribute("deptIds", deptIds);
		md.addAttribute("userIds", userIds);
		md.addAttribute("status", status);
		md.addAttribute("depts", deptService.getDataDept());
		md.addAttribute("users", userService.getDataUser());
		return "/tourManage/localTourManage";
	}
	
	@RequestMapping("/localTourManage/getCreateInfo")
	public @ResponseBody CreateInfoViewModel getCreateInfo(){
		return localTourService.getCreateInfo();
	}
	
	@RequestMapping("/localTourManage/findCustomer")
	public @ResponseBody CustomerAgencyTable findCustomer(@RequestParam int tourId){
		LocalTourTable tour = (LocalTourTable) localTourService.getById("LocalTourTable", tourId);
		return (CustomerAgencyTable) localTourService.getById("CustomerAgencyTable", tour.getCustomerAgencyId());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/localTourManage/save")
	public @ResponseBody int save(@RequestBody FullLocalTourViewModel full,HttpSession session){
		LocalTourTable localTour = full.getLocalTourTable();
//		ArrayList<GuideTimeTable> guideTimeTables = full.getGuideTimeTables();
		ArrayList<ArrTable> arrTables = full.getArrTables();
		ArrayList<DepartTable> departTables = full.getDepartTables();
		ArrayList<TripTable> tripTables = full.getTripTables();
		ArrayList<CostTable> costTables = full.getCostTables();
		ArrayList<IncomeTable> incomeTables = full.getIncomeTables();
		if((Boolean) session.getAttribute("isMice")&&(localTour.getIsNewCustomer()==null||localTour.getRetainagePeriod()==null||localTour.getAdvanced()==null)){
			return 0;
		}
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
						costTables.get(i).setRealCost(new BigDecimal(0));
						costService.add(costTables.get(i));
					}
				}
				/*保存收入*/
				if(!incomeTables.isEmpty()){
					for (int i = 0; i < incomeTables.size(); i++) {
						incomeTables.get(i).setTourId(tourId);
						incomeTables.get(i).setRealIncome(new BigDecimal(0));
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
			localTourService.sendMessageToFinance(id, " 已经报送，请及时进行导游借款操作。");
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
	
	/*取消申请结算*/
	@RequestMapping("/localTourManage/unBalance")
	public @ResponseBody boolean unBalanceLocalTour(@RequestParam int id){
		if(((LocalTourTable) localTourService.getById("LocalTourTable", id)).getStatus()==6){
			localTourService.changeStatus(id,5);
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
	public @ResponseBody Integer update(@RequestBody FullLocalTourViewModel full, HttpSession session){
		LocalTourTable localTour = full.getLocalTourTable();
//		ArrayList<GuideTimeTable> guideTimeTables = full.getGuideTimeTables();
		ArrayList<ArrTable> arrTables = full.getArrTables();
		ArrayList<DepartTable> departTables = full.getDepartTables();
		Hashtable<String, String> delIds = full.getDelIds();
		ArrayList<TripTable> tripTables = full.getTripTables();
		ArrayList<CostTable> costTables = full.getCostTables();
		ArrayList<IncomeTable> incomeTables = full.getIncomeTables();
		if((Boolean) session.getAttribute("isMice")&&localTour.getIsNewCustomer()==null){
			return -1;
		}
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
						costTables.get(i).setRealCost(new BigDecimal(0));
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
						incomeTables.get(i).setRealIncome(new BigDecimal(0));
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
	public @ResponseBody int saveChangeCost(@RequestBody ChangeCostIncomeViewModel changeCostIncomeViewModel){
		/*添加成本*/
		if(!changeCostIncomeViewModel.getCostTables().isEmpty()){
			for (ChangeCostTable changeCostTable : changeCostIncomeViewModel.getCostTables()) {
				if(changeCostTable.getSupplierId()==0){
					return -4;
				}else{
					/*如果成本小计小于0，为供应商退款*/
					/*if(changeCostTable.getCost()*changeCostTable.getCount()*changeCostTable.getDays()<0){
						changeCostTable.setRealCost(new BigDecimal(changeCostTable.getCost()).multiply(new BigDecimal(changeCostTable.getCount())).multiply(new BigDecimal(changeCostTable.getDays())));
						changeCostTable.setPayStatus(3);
						changeCostTable.setRemittanced(true);
					}*/
					changeCostTable.setStatus(3);/*设置状态*/
					changeCostTable.setApplicationerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
					changeCostTable.setRealCost(new BigDecimal(0));
					costService.add(changeCostTable);
				}
			}
		}
		/*添加收入*/
		if(!changeCostIncomeViewModel.getIncomeTables().isEmpty()){
			for (ChangeIncomeTable changeIncomeTable : changeCostIncomeViewModel.getIncomeTables()) {
				if(changeIncomeTable.getCustomerAgencyId()==0){
					return -5;
				}else{
					changeIncomeTable.setStatus(3);
					changeIncomeTable.setApplicationerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
					changeIncomeTable.setRealIncome(new BigDecimal(0));
					incomeService.add(changeIncomeTable);
				}
			}
		}
		/*if(!changeCostIncomeViewModel.getCostTables().isEmpty()||!changeCostIncomeViewModel.getIncomeTables().isEmpty()){
			这里或许需要判断用户权限
			localTourService.sendMessage("changeCostIncomeApplication", changeCostIncomeViewModel.getCostTables().size()==0?changeCostIncomeViewModel.getIncomeTables().get(0).getTourId():changeCostIncomeViewModel.getCostTables().get(0).getTourId(), 1, "您有 "+localTourService.getTourNoAndTourName(changeCostIncomeViewModel.getCostTables().isEmpty()?changeCostIncomeViewModel.getIncomeTables().get(0).getTourId():changeCostIncomeViewModel.getCostTables().get(0).getTourId())+" 待审核(变更成本收入)，点击进行审核");
		}*/
		return 1;
	}
	/*借款管理*/
	@RequestMapping("/localTourManage/findLend")
	public @ResponseBody ArrayList<LoanViewModel> findLend(@RequestParam int tourId){
		return localTourService.findLend(tourId);
	}
	@RequestMapping("/localTourManage/loanApplication")
	public @ResponseBody boolean loanApplication(@RequestBody ArrayList<LoanTable> loans, HttpSession session){
		if(!loans.isEmpty()){
			boolean hasMainManage = false;
			boolean hasViceManager = false;
			for (LoanTable loanTable : loans) {
				if(((LoanTable)localTourService.getById("LoanTable", loanTable.getId())).getLoanAmount().compareTo(new BigDecimal(10000))==1){
					hasMainManage = true;
				}else{
					hasViceManager = true;
				}
				if(hasMainManage&&hasViceManager){
					break;
				}
			}
			localTourService.loanApplication(loans);
			if((Boolean) session.getAttribute("isMice")){
				return localTourService.sendMessageMice("loanApplication", loans.get(0).getTourId(), 2, "您有 "+localTourService.getTourNoAndTourName(loans.get(0).getTourId())+" 待审核的(导游借款)，点击进行审核", hasMainManage, hasViceManager);
			}else{
				return localTourService.sendMessage("loanApplication", loans.get(0).getTourId(), 2, "您有 "+localTourService.getTourNoAndTourName(loans.get(0).getTourId())+" 待审核的(导游借款)，点击进行审核");
			}
		}
		return false;
	}

	@RequestMapping("/localTourManage/loanApplicationAgain")
	public @ResponseBody boolean loanApplicationAgain(@RequestParam int tourId){
		return localTourService.sendMessageAgain("loanApplication", tourId, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(导游借款)，点击进行审核");
	}
	
	/*付款管理*/
	@RequestMapping("/localTourManage/findPay")
	public @ResponseBody FullPayViewModel findPay(@RequestParam int tourId){
		return localTourService.findPay(tourId);
	}
	@RequestMapping("/localTourManage/payApplication")
	public @ResponseBody int payApplication(@RequestBody FullPayViewModel full, HttpSession session){
		int errorCode = 0;
		boolean hasMainManager = false;
		boolean hasViceManager = false;
		if(!full.getCostTables().isEmpty()||!full.getChangeCostTables().isEmpty()){
			for (CostTable cost : full.getCostTables()) {
				if(cost.getRealCost().compareTo(new BigDecimal(10000))==1){
					hasMainManager = true;
				}else{
					hasViceManager = true;
				}
				if(hasMainManager&&hasViceManager){
					break;
				}
			}
			for (ChangeCostTable changeCost : full.getChangeCostTables()) {
				if(changeCost.getRealCost().compareTo(new BigDecimal(10000))==1){
					hasMainManager = true;
				}else{
					hasViceManager = true;
				}
				if(hasMainManager&&hasViceManager){
					break;
				}
			}
			errorCode = localTourService.payApplication(full.getCostTables(), full.getChangeCostTables());
			if(errorCode!=-1){
				int tourId = !full.getCostTables().isEmpty()?full.getCostTables().get(0).getTourId():full.getChangeCostTables().get(0).getTourId();
				if((Boolean) session.getAttribute("isMice")){
					if(!localTourService.sendMessageMice("payApplication", tourId, 1, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(付款申请)，点击进行审核", hasMainManager, hasViceManager)){
						errorCode = -2;
					}
				}else{
					if(!localTourService.sendMessage("payApplication", tourId, 1, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(付款申请)，点击进行审核")){
						errorCode = -2;
					}
				}
			}
		}
		return errorCode;
	}
	@RequestMapping("/localTourManage/payApplicationAgain")
	public @ResponseBody boolean payApplicationAgain(@RequestParam int tourId){
		return localTourService.sendMessageAgain("payApplication", tourId, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(付款申请)，点击进行审核");
	}
	
	/*付款申请补款*/
	@RequestMapping("/localTourManage/paySupplement")
	public @ResponseBody int paySupplement(@RequestParam int id, @RequestParam String type){
		return localTourService.paySupplement(id,type);
	}
	
	/*预借发票*/
	@RequestMapping("/localTourManage/findBorrowInvoice")
	public @ResponseBody FullLoanInvoiceViewModel findBorrowInvoice(@RequestParam int tourId){
		return localTourService.findBorrowInvoice(tourId);
	}
	
	@RequestMapping("/localTourManage/saveBorrowInvoice")
	public @ResponseBody int saveBorrowInvoice(@RequestBody ArrayList<LoanInvoiceTable> loanInvoiceTables, HttpSession session){
		int errorCode = 0;
		if(!loanInvoiceTables.isEmpty()){
			ArrayList<LoanInvoiceTable> loanInvoices = new ArrayList<LoanInvoiceTable>();
			int applicationerId = ((UserTable) SecurityUtils.getSubject().getPrincipal()).getId();
			for (LoanInvoiceTable loanInvoiceTable : loanInvoiceTables) {
				if("".equals(loanInvoiceTable.getRemark())||loanInvoiceTable.getInvoiceAmount().compareTo(new BigDecimal(0))==0){
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
				if((Boolean) session.getAttribute("isMice")){
					if(!localTourService.sendMessageMice("loanInvoiceApplication", loanInvoiceTables.get(0).getTourId(), 1, "您有 "+localTourService.getTourNoAndTourName(loanInvoiceTables.get(0).getTourId())+" 待审核的(预借发票)，点击进行审核", true)){
						errorCode = -1;
					}
				}else{
					if(!localTourService.sendMessage("loanInvoiceApplication", loanInvoiceTables.get(0).getTourId(), 1, "您有 "+localTourService.getTourNoAndTourName(loanInvoiceTables.get(0).getTourId())+" 待审核的(预借发票)，点击进行审核")){
						errorCode = -1;
					}
				}
				return errorCode;
			}
		}
		return -1;
	}
	@RequestMapping("/localTourManage/borrowInvoiceAgain")
	public @ResponseBody boolean borrowInvoiceAgain(@RequestParam int tourId, HttpSession session){
		if(!localTourService.getAllByString("LoanInvoiceTable", "tourId=? and status=1", tourId).isEmpty()){
			if((Boolean) session.getAttribute("isMice")){
				return localTourService.sendMessageMice("loanInvoiceApplication", tourId, 1, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(预借发票)，点击进行审核", true);
			}else{
				return localTourService.sendMessage("loanInvoiceApplication", tourId, 1, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(预借发票)，点击进行审核");
			} 
		}
		return false;
	}
	
	/*退款申请*/
	@RequestMapping("/localTourManage/findRefund")
	public @ResponseBody FullRefundViewModel findRefund(@RequestParam int tourId){
		return localTourService.findRefund(tourId);
	}
	@RequestMapping("/localTourManage/refundApplication")
	public @ResponseBody int refundApplication(@RequestBody ArrayList<RefundTable> refundTables, HttpSession session){
		int errorCode = 0;
		boolean hasMainManager = false;
		boolean hasViceManager = false;
		if(!refundTables.isEmpty()){
			for (RefundTable refund : refundTables) {
				if(refund.getRefundAmount().compareTo(new BigDecimal(10000))==1){
					hasMainManager = true;
				}else{
					hasViceManager = true;
				}
				if(hasMainManager&&hasViceManager){
					break;
				}
			}
			errorCode = localTourService.refundApplication(refundTables);
			if(errorCode!=-1&&errorCode!=-3&&!refundTables.isEmpty()){
				int tourId = refundTables.get(0).getTourId();
				if((Boolean) session.getAttribute("isMice")){
					if(!localTourService.sendMessageMice("refundApplication", tourId, 1, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(退款申请)，点击进行审核", hasMainManager, hasViceManager)){
						errorCode = -2;
					}
				}else{
					if(!localTourService.sendMessage("refundApplication", tourId, 1, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(退款申请)，点击进行审核")){
						errorCode = -2;
					}
				}
			}
		}
		return errorCode;
	}
	@RequestMapping("/localTourManage/refundApplicationAgain")
	public @ResponseBody boolean refundApplication(@RequestParam int tourId){
		return localTourService.sendMessageAgain("refundApplication", tourId, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(退款申请)，点击进行审核");
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
	public void updateReimbursement(@RequestBody FullReimbursementViewModel full){
		if(!full.getChangeCostTables().isEmpty()||!full.getCostTables().isEmpty()||!full.getReimbursementCostTables().isEmpty()||!full.getNewReimbursementCostTables().isEmpty()||!full.getReimbursementIncomeTables().isEmpty()){
			localTourService.updateReimbursement(full);
		}
	}
	
	@RequestMapping("/reimbursementManage/auditingReimbursement")
	public @ResponseBody boolean auditingReimbursement(@RequestParam int tourId){
		if(!localTourService.checkReimbursement(tourId)){
			return false;
		}
		if(localTourService.auditingReimbursement(tourId)!=0){
//			localTourService.sendMessage("reimbursementApplication", tourId, 0, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的(团队报账)，点击进行审核");
			return false;
		}
		return true;
	}
	/*打印pdf*/
	@RequestMapping("/reimbursementManage/printReimbursement")
	public @ResponseBody boolean printReimbursement(@RequestBody String info){
		return pdfMaker.make(info.split("&")[0].substring(7),info.split("&")[1].substring(5));
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
	public @ResponseBody int updateBill(@RequestBody FullBillViewModel full, HttpSession session){
		billService.updateBill(full);
		int errorCode = 0;
		boolean hasMainManager = false;
		boolean hasViceManager = false;
		if(!full.getCostTables().isEmpty()||!full.getChangeCostTables().isEmpty()||!full.getReimbursementCostTables().isEmpty()){
			for (CostTable cost : full.getCostTables()) {
				if(cost.getCost().multiply(new BigDecimal(cost.getCount())).multiply(new BigDecimal(cost.getDays())).compareTo(new BigDecimal(10000))==1){
					hasMainManager = true;
				}else{
					hasViceManager = true;
				}
				if(hasMainManager&&hasViceManager){
					break;
				}
			}
			for (ChangeCostTable changeCost : full.getChangeCostTables()) {
				if(changeCost.getCost().multiply(new BigDecimal(changeCost.getCount())).multiply(new BigDecimal(changeCost.getDays())).compareTo(new BigDecimal(10000))==1){
					hasMainManager = true;
				}else{
					hasViceManager = true;
				}
				if(hasMainManager&&hasViceManager){
					break;
				}
			}
			for (ReimbursementCostTable reimbursementCostTable : full.getReimbursementCostTables()) {
				if(reimbursementCostTable.getCost().multiply(new BigDecimal(reimbursementCostTable.getCount())).multiply(new BigDecimal(reimbursementCostTable.getDays())).compareTo(new BigDecimal(10000))==1){
					hasMainManager = true;
				}else{
					hasViceManager = true;
				}
				if(hasMainManager&&hasViceManager){
					break;
				}
			}
			errorCode = billService.updateBill(full);
			int tourId = !full.getCostTables().isEmpty()?full.getCostTables().get(0).getTourId():!full.getChangeCostTables().isEmpty()?full.getChangeCostTables().get(0).getTourId():full.getReimbursementCostTables().get(0).getTourId();
			if((Boolean) session.getAttribute("isMice")){
				if(!localTourService.sendMessageMice("billApplication", tourId, 1, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的（供应商挂账付款申请），点击进行审核", hasMainManager, hasViceManager)){
					errorCode = -2;
				}
			}else{
				if(!localTourService.sendMessage("billApplication", tourId, 1, "您有 "+localTourService.getTourNoAndTourName(tourId)+" 待审核的（供应商挂账付款申请），点击进行审核")){
					errorCode = -2;
				}
			}
		}
		return errorCode;
	}
	
	/*打印导游借款、供应商付款凭证*/
	@RequestMapping("/localTourManage/printVoucher")
	public @ResponseBody PrintVoucherViewModel printPayVoucher(@RequestParam String type, @RequestParam int tourId){
		return printService.printPayVoucher(type, tourId);
	}
	@RequestMapping("/localTourManage/printCountPlus")
	public void printCountPlus(@RequestParam String ids, @RequestParam(defaultValue="") String type){
		printService.printCountPlus(ids.split(","), type);
	}
	
	@RequestMapping("/localTourManage/printCountPlus2")
	public void printCountPlus2(@RequestParam String costIds, @RequestParam String changeCostIds){
		printService.printCountPlus(costIds.split(","),changeCostIds.split(","));
	}
	
	/*测试*/
	@RequestMapping("/test")
	public void test(@RequestBody String info){
		/*String path = "D://STSworkSpaces/localtour/src/main/webapp/resources/pdfTemp/";*/
		String path = "/driver/apache-tomcat-8.0.39/webapps/localtour/resources/pdfTemp/";
		File file = new File(path);
		String[] list = file.list();
		for (String name : list) {
			new File(path+name).delete();
		}
		System.out.println("???");
	}
	@RequestMapping("/sendTest")
	public void sendTest(@RequestParam String touser){
		System.out.println(touser);
		boolean ok = WeiXinUtil.sendTextMessage(touser, "", "测试消息", "0");
		System.out.println(ok);
	}
}