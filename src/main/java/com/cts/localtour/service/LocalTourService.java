package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cts.localtour.DAO.LocalTourDAO;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.TourTypeTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.entity.VisitorTypeTable;
import com.cts.localtour.util.WeiXinUtil;
import com.cts.localtour.entity.BusinessTypeTable;
import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.LoanInvoiceTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.ReimbursementIncomeTable;
import com.cts.localtour.entity.ReimbursementTable;
import com.cts.localtour.viewModel.ChangeCostIncomeViewModel;
import com.cts.localtour.viewModel.ChangeCostViewModel;
import com.cts.localtour.viewModel.CostViewModel;
import com.cts.localtour.viewModel.CreateInfoViewModel;
import com.cts.localtour.viewModel.FullLoanInvoiceViewModel;
import com.cts.localtour.viewModel.FullLocalTourViewModel;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.FullReimbursementViewModel;
import com.cts.localtour.viewModel.SimpleLocalTourViewModel;
import com.cts.localtour.viewModel.LoanViewModel;

@SuppressWarnings("rawtypes")
@Service
public class LocalTourService extends BaseService{
	@Autowired
	private LocalTourDAO localTourDAO;
	@Autowired
	private MobileService mobileService;
	@Autowired
	private CostViewModel costViewModel;
	@Autowired
	private ChangeCostViewModel changeCostViewModel;
	@Autowired
	private LoanViewModel loanViewModel;
	@Autowired
	private ChangeCostIncomeViewModel changeCostIncomeViewModel;
	@Autowired
	private FullLocalTourViewModel fullLocalTourViewModel;
	@Autowired
	private FullReimbursementViewModel fullReimbursementViewModel;
	@Autowired
	private FullLoanInvoiceViewModel fullLoanInvoiceViewModel;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@Autowired
	private LoanInvoiceService loanInvoiceService;
	@Autowired
	private UserService userService;
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleLocalTourViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")", null, page, maxResults);
			return setMd(localTours);
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("tourNO", "%"+key+"%");
			param.put("tourName", "%"+key+"%");
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "(tourNO like :tourNO or tourName like :tourName) and deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")", param, page, maxResults);
			return setMd(localTours);
		}
	}
	public ArrayList<SimpleLocalTourViewModel> setMd(ArrayList<LocalTourTable> localTours){
		ArrayList<SimpleLocalTourViewModel> simpleLocalTourViewModels = new ArrayList<SimpleLocalTourViewModel>();
		for (int i = 0; i < localTours.size(); i++) {
			SimpleLocalTourViewModel simpleLocalTourViewModel = new SimpleLocalTourViewModel();
			String realName = ((UserTable)this.getById("UserTable", localTours.get(i).getUserId())).getRealName();
			
			simpleLocalTourViewModel.setLocalTourTable(localTours.get(i));
			int days = (int) ((localTours.get(i).getEndTime().getTime()-localTours.get(i).getStartTime().getTime())/1000/60/60/24)+1;
			simpleLocalTourViewModel.setDays(days);
			simpleLocalTourViewModel.setRealName(realName);
			if(localTours.get(i).getStatus()       ==0){
				simpleLocalTourViewModel.setStatus("�½�");
			}else if (localTours.get(i).getStatus()==1) {
				simpleLocalTourViewModel.setStatus("���ύ");
			}else if (localTours.get(i).getStatus()==2) {
				simpleLocalTourViewModel.setStatus("�ѱ���");
			}else if (localTours.get(i).getStatus()==3) {
				simpleLocalTourViewModel.setStatus("�ɽ��");
			}else if (localTours.get(i).getStatus()==4) {
				simpleLocalTourViewModel.setStatus("������");
			}else if (localTours.get(i).getStatus()==5) {
				simpleLocalTourViewModel.setStatus("�ѽ���");
			}else if (localTours.get(i).getStatus()==6) {
				simpleLocalTourViewModel.setStatus("������");
			}else if (localTours.get(i).getStatus()==7) {
				simpleLocalTourViewModel.setStatus("�ѱ���");
			}else if (localTours.get(i).getStatus()==8) {
				simpleLocalTourViewModel.setStatus("������");
			}else if (localTours.get(i).getStatus()==9) {
				simpleLocalTourViewModel.setStatus("������");
			}else if (localTours.get(i).getStatus()==10) {
				simpleLocalTourViewModel.setStatus("�ѽ���");
			}
			simpleLocalTourViewModels.add(simpleLocalTourViewModel);
		}
		return simpleLocalTourViewModels;
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			String where = "deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")";
			return this.getCountsByParam("LocalTourTable", where, null);
		}else{
			String where = "(tourNO like :tourNO or tourName like :tourName) and deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("tourNO", "%"+key+"%");
			param.put("tourName", "%"+key+"%");
			return this.getCountsByParam("LocalTourTable", where, param);
		}
	}

	public void del(int id) {
		this.changeValue("LocalTourTable", "enable", "false", id);
	}
	
	public void recover(int id){
		this.changeValue("LocalTourTable", "enable", "true", id);
	}
	
	public void changeStatus(int id, int status) {
		this.updateByString("LocalTourTable", "status=?", "id=?", status, id);
	}
	
	@SuppressWarnings("unchecked")
	public int addLocalTour(LocalTourTable localTour){
		try {
			return ((LocalTourTable)localTourDAO.add(localTour)).getId();
		} catch (Exception e) {
			//�ź��ظ�			
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public CreateInfoViewModel getCreateInfo() {
		CreateInfoViewModel createInfoViewModel = new CreateInfoViewModel();
		createInfoViewModel.setBusinessTypes((ArrayList<BusinessTypeTable>) this.getAllByString("BusinessTypeTable", "enable=?", true));
		createInfoViewModel.setTourTypes((ArrayList<TourTypeTable>) this.getAllByString("TourTypeTable", "enable=?", true));
		createInfoViewModel.setRegions((ArrayList<RegionTable>) this.getAllByString("RegionTable", "enable=?", true));
		createInfoViewModel.setVisitorTypes((ArrayList<VisitorTypeTable>) this.getAllByString("VisitorTypeTable", "enable=?", true));
		createInfoViewModel.setCustomerAgencys((ArrayList<CustomerAgencyTable>) this.getAllByString("CustomerAgencyTable", "enable=?", true));
		createInfoViewModel.setFlightContents(this.getContents(1));
		createInfoViewModel.setFlightSuppliers(this.getSuppliers(1));
		createInfoViewModel.setHotelContents(this.getContents(2));
		createInfoViewModel.setHotelSuppliers(this.getSuppliers(2));
		createInfoViewModel.setMealContents(this.getContents(3));
		createInfoViewModel.setMealSuppliers(this.getSuppliers(3));
		createInfoViewModel.setTicketContents(this.getContents(4));
		createInfoViewModel.setTicketSuppliers(this.getSuppliers(4));
		createInfoViewModel.setShuttleContents(this.getContents(5));
		createInfoViewModel.setShuttleSuppliers(this.getSuppliers(5));
		createInfoViewModel.setTicketsContents(this.getContents(6));
		createInfoViewModel.setTicketsSuppliers(this.getSuppliers(6));
		createInfoViewModel.setComprehensiveContents(this.getContents(7));
		createInfoViewModel.setComprehensiveSuppliers(this.getSuppliers(7));
		createInfoViewModel.setOtherContents(this.getContents(8));
		createInfoViewModel.setOtherSuppliers(this.getSuppliers(8));
		return createInfoViewModel;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<SupplierContentTable> getContents(int supplierScopeId){
		return (ArrayList<SupplierContentTable>) this.getAllByString("SupplierContentTable", "supplierScopeId=? and enable=true", supplierScopeId);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<SupplierTable> getSuppliers(int supplierScopeID){
		return (ArrayList<SupplierTable>) this.getByHql("SELECT a FROM SupplierTable a,SupplierBusinessTable b WHERE a.id=b.supplierId and b.supplierScopeId='"+supplierScopeID+"'");
	}
	
	public FullLocalTourViewModel find(int id) {
		return fullLocalTourViewModel.getFullLocalTourViewModel(id);
	}
	@SuppressWarnings("unchecked")
	public boolean updateLocalTour(LocalTourTable localTourTable) {
		LocalTourTable localTour = (LocalTourTable) this.getById("LocalTourTable", localTourTable.getId());
		localTour.setAdultNo(localTourTable.getAdultNo());
		localTour.setBusinessTypeId(localTourTable.getBusinessTypeId());
		localTour.setChildrenNo(localTourTable.getChildrenNo());
		localTour.setCustomerAgencyId(localTourTable.getCustomerAgencyId());
		localTour.setEndTime(localTourTable.getEndTime());
		localTour.setOrganizor(localTourTable.getOrganizor());
		localTour.setQpGuideNo(localTourTable.getQpGuideNo());
		localTour.setRegionId(localTourTable.getRegionId());
		localTour.setRemark(localTourTable.getRemark());
		localTour.setStartTime(localTourTable.getStartTime());
		localTour.setTourName(localTourTable.getTourName());
		localTour.setTourNo(localTourTable.getTourNo());
		localTour.setTourTypeId(localTourTable.getTourTypeId());
		localTour.setVisitorTypeId(localTourTable.getVisitorTypeId());
		try {
			localTourDAO.update(localTour);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public ChangeCostIncomeViewModel chanageCostIncomeFind(int tourId) {
		return changeCostIncomeViewModel.getAllChangeCostIncomeViewModel(tourId);
	}
	
	public boolean sendMessage(String mobileControllerMapping, int tourId, int status, String message){
		return mobileService.sendMessage(mobileControllerMapping, tourId, status, message);
	}
	
	public boolean sendMessageAgain(String mobileControllerMapping, int tourId, String message) {
		return mobileService.sendMessageAgain(mobileControllerMapping, tourId, message);
	}
	
	public boolean sendMessageMice(String mobileControllerMapping, int tourId, int status, String message, boolean hasMainManager, boolean hasViceManager){
		return mobileService.sendMessageMice(mobileControllerMapping, tourId, status, message ,hasMainManager, hasViceManager);
	}
	
	public boolean sendMessageMice(String mobileControllerMapping, int tourId, int status, String message, boolean hasDeptManager){
		return mobileService.sendMessageMice(mobileControllerMapping, tourId, status, message ,hasDeptManager);
	}
	
	public boolean sendMessageToMaker(int tourId, String message){
		LocalTourTable localTourTable = (LocalTourTable) this.getById("LocalTourTable", tourId);
		return WeiXinUtil.sendTextMessage(userService.getUserName(localTourTable.getUserId())+"@ctssd.com", "", localTourTable.getTourNo()+" "+localTourTable.getTourName()+message, "0");
	}
	
	public boolean sendMessageToFinance(int tourId, String message){
		boolean isMice = (Boolean) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("isMice");
		if(isMice){
			return WeiXinUtil.sendTextMessage("huangyumin@ctssd.com", "", this.getTourNoAndTourName(tourId)+message, "0");
		}else{
			return WeiXinUtil.sendTextMessage("lidi@ctssd.com", "", this.getTourNoAndTourName(tourId)+message, "0");
		}
		
	}
	
	public ArrayList<LoanViewModel> findLend(int tourId) {
		return loanViewModel.getAllLoanViewModel(tourId);
	}
	
	public String getTourNoAndTourName(int tourId){
		LocalTourTable tour = (LocalTourTable)this.getById("LocalTourTable", tourId);
		return tour.getTourNo()+" "+tour.getTourName();
	}
	
	public FullPayViewModel findPay(int tourId) {
		FullPayViewModel pay = new FullPayViewModel();
		pay.setCosts(costViewModel.getAllCostViewModel(tourId));
		pay.setChangeCosts(changeCostViewModel.getAllChangeCostViewModel(tourId,3));
		return pay;
	}
	@SuppressWarnings("unchecked")
	public int payApplication(ArrayList<CostTable> costTables, ArrayList<ChangeCostTable> changeCostTables) {
		int errorCode = 0;
		for (CostTable cost : costTables) {
			CostTable costTable = (CostTable)this.getById("CostTable", cost.getId());
			if(cost.getRealCost().compareTo(costTable.getCost().multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())))==1){
				errorCode = -1;
			}else{
				if(costTable.getPayStatus()==0){
					costTable.setPayStatus(1);
					costTable.setPayApplicationerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
					costTable.setSupplierId(cost.getSupplierId());
					costTable.setRealCost(cost.getRealCost());
					costTable.setRemark(cost.getRemark());
					this.update(costTable);
				}
			}
		}
		for (ChangeCostTable changeCost : changeCostTables) {
			ChangeCostTable costTable = (ChangeCostTable)this.getById("ChangeCostTable", changeCost.getId());
			if(changeCost.getRealCost().compareTo(costTable.getCost().multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())))==1){
				errorCode = -1;
			}else{
				ChangeCostTable changeCostTable = (ChangeCostTable)this.getById("ChangeCostTable", changeCost.getId());
				if(changeCostTable.getPayStatus()==0){
					changeCostTable.setPayStatus(1);
					changeCostTable.setPayApplicationerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
					changeCostTable.setSupplierId(changeCost.getSupplierId());
					changeCostTable.setRealCost(changeCost.getRealCost());
					changeCostTable.setRemark(changeCost.getRemark());
					this.update(changeCostTable);
				}
			}
		}
		return errorCode;
	}
	
	public boolean paySupplement(int id, String type) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void loanApplication(ArrayList<LoanTable> loans) {
		for (LoanTable loan : loans) {
			LoanTable loanTable = (LoanTable)this.getById("LoanTable", loan.getId());
			if(loanTable.getStatus()==1){
				loanTable.setStatus(2);
				loanTable.setRemark(loan.getRemark());
				loanTable.setApplicationerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
				this.update(loanTable);
			}
		}
	}
	public FullLoanInvoiceViewModel findBorrowInvoice(int tourId) {
		return fullLoanInvoiceViewModel.getFullLoanInvoiceViewModel(tourId);
	}
	@SuppressWarnings("unchecked")
	public void saveBorrowInvoice(ArrayList<LoanInvoiceTable> loanInvoiceTables) {
		for (LoanInvoiceTable loanInvoiceTable : loanInvoiceTables) {
			loanInvoiceTable.setStatus(1);
			loanInvoiceTable.setCustomerAgencyId(((LocalTourTable)this.getById("LocalTourTable", loanInvoiceTable.getTourId())).getCustomerAgencyId());
			this.add(loanInvoiceTable);
		}
	}
	
	public FullReimbursementViewModel findReimbursement(int tourId) {
		return fullReimbursementViewModel.getFullReimbursementViewModel(tourId);
	}
	
	public boolean checkReimbursement(int tourId) {
		return loanInvoiceService.getLoanInvoiceSum(tourId).floatValue()<=(incomeService.getIncomeInfo(tourId).getIncomeSum().add(changeIncomeService.getIncomeInfo(tourId).getIncomeSum()).floatValue());
	}
	
	@SuppressWarnings("unchecked")
	public void updateReimbursement(FullReimbursementViewModel full) {
		for (CostTable costTable : full.getCostTables()) {
			this.updateByString("CostTable", "reimbursement=?, supplierId=?, bill=?", "id=?", costTable.getReimbursement(),costTable.getSupplierId(),costTable.isBill(),costTable.getId());
		}
		for (ChangeCostTable changeCostTable : full.getChangeCostTables()) {
			this.updateByString("ChangeCostTable", "reimbursement=?, supplierId=?, bill=?", "id=?", changeCostTable.getReimbursement(),changeCostTable.getSupplierId(),changeCostTable.isBill(),changeCostTable.getId());
		}
		for (ReimbursementCostTable reimbursementCostTable : full.getReimbursementCostTables()) {
			if(reimbursementCostTable.getReimbursement()==null||reimbursementCostTable.getReimbursement().floatValue()==0){
				this.delete(reimbursementCostTable);
			}else{
				this.updateByString("ReimbursementCostTable", "reimbursement=?, supplierId=?, bill=?", "id=?", reimbursementCostTable.getReimbursement(),reimbursementCostTable.getSupplierId(),reimbursementCostTable.isBill(),reimbursementCostTable.getId());
			}
		}
		for (ReimbursementCostTable reimbursementCostTable : full.getNewReimbursementCostTables()) {
			if(reimbursementCostTable.getReimbursement()!=null&&reimbursementCostTable.getReimbursement().floatValue()!=0&&reimbursementCostTable.getCost()!=null){
				this.add(reimbursementCostTable);
			}
		}
		for (ReimbursementIncomeTable income : full.getReimbursementIncomeTables()) {
			if(income.getIncome().floatValue()!=0){
				this.merge(income);
			}else if(income.getId()!=null||income.getId()!=0){
				this.delete(income);
			}
		}
		
		
		/*�����ͷ��*/
		ArrayList<ReimbursementTable> reimbursementTables = (ArrayList<ReimbursementTable>) this.getAllByString("ReimbursementTable", "tourId=?", full.getReimbursementTable().getTourId());
		if(reimbursementTables.size()==1){
			if(full.getReimbursementTable().getHeadAmount().floatValue()!=0){
				reimbursementTables.get(0).setHeadAmount(full.getReimbursementTable().getHeadAmount());
				this.update(reimbursementTables.get(0));
			}else{
				this.delete(reimbursementTables.get(0));
			}
		}else{
			if(full.getReimbursementTable().getHeadAmount().floatValue()!=0){
				this.add(full.getReimbursementTable());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public int auditingReimbursement(int tourId) {
		LocalTourTable tour = (LocalTourTable)this.getById("LocalTourTable", tourId);
		if(tour.getStatus()==6){
			tour.setStatus(8);
			this.update(tour);
			/*�������*/
			/*if(this.getAllByString("ReimbursementApplicationTable", "tourId=?", tourId).isEmpty()){
				ReimbursementApplicationTable reimbursementApplicationTable = new ReimbursementApplicationTable();
				reimbursementApplicationTable.setTourId(tourId);
				reimbursementApplicationTable.setDeptId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getDeptId());
				this.add(reimbursementApplicationTable);
			}*/
		}else{
			return -1;
		}
		return 0;
	}
}
