package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.LocalTourDAO;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.TourTypeTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.entity.VisitorTypeTable;
import com.cts.localtour.entity.BusinessTypeTable;
import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.viewModel.ChangeCostIncomeViewModel;
import com.cts.localtour.viewModel.ChangeCostViewModel;
import com.cts.localtour.viewModel.CostViewModel;
import com.cts.localtour.viewModel.CreateInfoViewModel;
import com.cts.localtour.viewModel.FullLocalTourViewModel;
import com.cts.localtour.viewModel.FullPayViewModel;
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
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleLocalTourViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<LocalTourTable> localTours = this.getAllByTableName("LocalTourTable", page, maxResults);
			return setMd(localTours);
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("tourNO", "%"+key+"%");
			param.put("tourName", "%"+key+"%");
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "tourNO like :tourNO or tourName like :tourName", param, page, maxResults);
			return setMd(localTours);
		}
	}
	public ArrayList<SimpleLocalTourViewModel> setMd(ArrayList<LocalTourTable> localTours){
		ArrayList<SimpleLocalTourViewModel> simpleLocalTourViewModels = new ArrayList<SimpleLocalTourViewModel>();
		for (int i = 0; i < localTours.size(); i++) {
			SimpleLocalTourViewModel simpleLocalTourViewModel = new SimpleLocalTourViewModel();
//			String regionName = ((ArrayList<RegionTable>) this.getByWhere("RegionTable", "id", localTours.get(i).getRegionId()+"")).get(0).getRegionName();
			String realName = ((UserTable)this.getById("UserTable", localTours.get(i).getUserId())).getRealName();
			
			simpleLocalTourViewModel.setLocalTourTable(localTours.get(i));
			int days = (int) ((localTours.get(i).getEndTime().getTime()-localTours.get(i).getStartTime().getTime())/1000/60/60/24)+1;
			simpleLocalTourViewModel.setDays(days);
			simpleLocalTourViewModel.setRealName(realName);
			if(localTours.get(i).getStatus()       ==0){
				simpleLocalTourViewModel.setStatus("新建");
			}else if (localTours.get(i).getStatus()==1) {
				simpleLocalTourViewModel.setStatus("已提交");
			}else if (localTours.get(i).getStatus()==2) {
				simpleLocalTourViewModel.setStatus("已报送");
			}else if (localTours.get(i).getStatus()==3) {
				simpleLocalTourViewModel.setStatus("可借款");
			}else if (localTours.get(i).getStatus()==4) {
				simpleLocalTourViewModel.setStatus("进行中");
			}else if (localTours.get(i).getStatus()==5) {
				simpleLocalTourViewModel.setStatus("已结束");
			}else if (localTours.get(i).getStatus()==6) {
				simpleLocalTourViewModel.setStatus("结算中");
			}else if (localTours.get(i).getStatus()==7) {
				simpleLocalTourViewModel.setStatus("已核销");
			}else if (localTours.get(i).getStatus()==8) {
				simpleLocalTourViewModel.setStatus("已结算");
			}
			simpleLocalTourViewModels.add(simpleLocalTourViewModel);
		}
		return simpleLocalTourViewModels;
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("LocalTourTable");
		}else{
			String where = "tourNO like :tourNO or tourName like :tourName";
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
		this.updateByString("LocalTourTable", "status=?", "id="+id, status);
	}
	
	@SuppressWarnings("unchecked")
	public int addLocalTour(LocalTourTable localTour){
		try {
			return ((LocalTourTable)localTourDAO.add(localTour)).getId();
		} catch (Exception e) {
			//团号重复			
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
	
	public void sendMassage(String mobileControllerMapping, int tourId, int status, String message, HttpServletRequest request, HttpSession session){
		mobileService.sendMessage(mobileControllerMapping, tourId, status, message, request, session);
	}
	
	public ArrayList<LoanViewModel> findLend(int tourId) {
		return loanViewModel.getAllLoanViewModel(tourId);
	}
	
	public String getTourNoAndTourName(int id){
		LocalTourTable tour = (LocalTourTable)this.getById("LocalTourTable", id);
		return tour.getTourNo()+" "+tour.getTourName();
	}
	
	public FullPayViewModel findPay(int tourId) {
		FullPayViewModel pay = new FullPayViewModel();
		pay.setCosts(costViewModel.getAllCostViewModel(tourId));
		pay.setChangeCosts(changeCostViewModel.getAllChangeCostViewModell(tourId));
		return pay;
	}
	@SuppressWarnings("unchecked")
	public void payApplication(String costIds, String changeCostIds, HttpSession session) {
		if(!"".equals(costIds)){
			String[] ids = costIds.split(",");
			for (String id : ids) {
				CostTable costTable = (CostTable)this.getById("CostTable", Integer.parseInt(id));
				if(costTable.getPayStatus()==0){
					costTable.setPayStatus(1);
					costTable.setPayApplicationerId(((UserTable)session.getAttribute("user")).getId());
					this.update(costTable);
				}
			}
		}
		if(!"".equals(changeCostIds)){
			String[] ids = changeCostIds.split(",");
			for (String id : ids) {
				ChangeCostTable changeCostTable = (ChangeCostTable)this.getById("ChangeCostTable", Integer.parseInt(id));
				if(changeCostTable.getPayStatus()==0){
					changeCostTable.setPayStatus(1);
					changeCostTable.setPayApplicationerId(((UserTable)session.getAttribute("user")).getId());
					this.update(changeCostTable);
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	public void loanApplication(String ids) {
		String[] idss = ids.split(",");
		for (String id : idss) {
			LoanTable loanTable = (LoanTable)this.getById("LoanTable", Integer.parseInt(id));
			if(loanTable.getStatus()==1){
				loanTable.setStatus(2);
				this.update(loanTable);
			}
		}
	}
}
