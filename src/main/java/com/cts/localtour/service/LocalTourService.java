package com.cts.localtour.service;

import java.math.BigDecimal;
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
import com.cts.localtour.entity.TripTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.entity.VisitorTypeTable;
import com.cts.localtour.util.WeiXinUtil;
import com.cts.localtour.entity.ArrTable;
import com.cts.localtour.entity.BusinessTypeTable;
import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.DepartTable;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.GuideTable;
import com.cts.localtour.entity.GuideTimeTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.viewModel.ArrDepViewModel;
import com.cts.localtour.viewModel.ChangeCostIncomeViewModel;
import com.cts.localtour.viewModel.ChangeCostViewModel;
import com.cts.localtour.viewModel.ChangeIncomeViewModel;
import com.cts.localtour.viewModel.CostViewModel;
import com.cts.localtour.viewModel.CreateInfoViewModel;
import com.cts.localtour.viewModel.FullLocalTourViewModel;
import com.cts.localtour.viewModel.GuideTimeViewModel;
import com.cts.localtour.viewModel.SimpleLocalTourViewModel;
import com.cts.localtour.viewModel.IncomeViewModel;

@SuppressWarnings("rawtypes")
@Service
public class LocalTourService extends BaseService{
	@Autowired
	private LocalTourDAO localTourDAO;
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
		this.updateByParam("LocalTourTable", "status=?", "id="+id, status);
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
		ArrayList<SupplierTable> list = (ArrayList<SupplierTable>) this.getByHql("SELECT a FROM SupplierTable a,SupplierBusinessTable b WHERE a.id=b.supplierId and b.supplierScopeId='"+supplierScopeID+"'");
		return list;
	}
	@SuppressWarnings("unchecked")
	public FullLocalTourViewModel find(int id) {
		FullLocalTourViewModel full = new FullLocalTourViewModel();
		LocalTourTable localTour = (LocalTourTable) this.getById("LocalTourTable", id);
		full.setLocalTourTable(localTour);
		
		Hashtable<String, String> tourInfo = new Hashtable<String, String>();
		tourInfo.put("businessTypeName", ((BusinessTypeTable)this.getById("BusinessTypeTable", localTour.getBusinessTypeId())).getBusinessTypeName());
		tourInfo.put("tourTypeName", ((TourTypeTable)this.getById("TourTypeTable", localTour.getTourTypeId())).getTourTypeName());
		tourInfo.put("regionName", ((RegionTable)this.getById("RegionTable", localTour.getRegionId())).getRegionName());
		tourInfo.put("visitorTypeName", ((VisitorTypeTable)this.getById("VisitorTypeTable", localTour.getVisitorTypeId())).getVisitorTypeName());
		tourInfo.put("customerAgencyName", ((CustomerAgencyTable)this.getById("CustomerAgencyTable", localTour.getCustomerAgencyId())).getCustomerAgencyName());
		full.setTourInfo(tourInfo);
		
		ArrayList<GuideTimeTable> guideTimeTables = (ArrayList<GuideTimeTable>)this.getAllByString("GuideTimeTable", "tourId=?", id);
		ArrayList<GuideTimeViewModel> guideTimes = new ArrayList<GuideTimeViewModel>();
		for (int i = 0; i < guideTimeTables.size(); i++) {
			GuideTimeViewModel guideTime = new GuideTimeViewModel();
			guideTime.setRealName(((UserTable)this.getById("UserTable", ((GuideTable)this.getById("GuideTable", guideTimeTables.get(i).getGuideId())).getUserId())).getRealName());
			guideTime.setGuideId(guideTimeTables.get(i).getGuideId());
			guideTimes.add(guideTime);
		}
		full.setGuideTimes(guideTimes);
		
		ArrayList<ArrTable> arrTables = (ArrayList<ArrTable>) this.getAllByString("ArrTable", "tourId=?", id);
		ArrayList<ArrDepViewModel> arrs = new ArrayList<ArrDepViewModel>();
		for (int i = 0; i < arrTables.size(); i++) {
			ArrDepViewModel arr = new ArrDepViewModel();
			arr.setArrTable(arrTables.get(i));
			arr.setRegion1(((RegionTable)this.getById("RegionTable", arrTables.get(i).getOriginId())).getRegionName());
			arr.setRegion2(((RegionTable)this.getById("RegionTable", arrTables.get(i).getArrRegionId())).getRegionName());
			arrs.add(arr);
		}
		full.setArrTables(arrTables);
		full.setArrs(arrs);
		
		ArrayList<DepartTable> departTables = (ArrayList<DepartTable>) this.getAllByString("DepartTable", "tourId=?", id);
		ArrayList<ArrDepViewModel> departs = new ArrayList<ArrDepViewModel>();
		for (int i = 0; i < departTables.size(); i++) {
			ArrDepViewModel depart = new ArrDepViewModel();
			depart.setDepartTable(departTables.get(i));
			depart.setRegion1(((RegionTable)this.getById("RegionTable", departTables.get(i).getDestId())).getRegionName());
			depart.setRegion2(((RegionTable)this.getById("RegionTable", departTables.get(i).getDepartRegionId())).getRegionName());
			departs.add(depart);
		}
		full.setDepartTables(departTables);
		full.setDeparts(departs);
		full.setTripTables((ArrayList<TripTable>) this.getAllByString("TripTable", "tourId=?", id));
		
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) this.getAllByString("CostTable", "tourId=?", id);
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (int i = 0; i < costTables.size(); i++) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTables.get(i));
			cost.setContentName((costTables.get(i).getContentId()==null||costTables.get(i).getContentId()==0)?"":((SupplierContentTable)this.getById("SupplierContentTable", costTables.get(i).getContentId())).getContentName());
			cost.setSupplierName(((SupplierTable)this.getById("SupplierTable", costTables.get(i).getSupplierId())).getSupplierName());
			cost.setBorrowUserName((costTables.get(i).getBorrowUserId()==null||costTables.get(i).getBorrowUserId()==0)?"":((UserTable)this.getById("UserTable", costTables.get(i).getBorrowUserId())).getRealName());
			costs.add(cost);
		}
		full.setCosts(costs);
		
		ArrayList<IncomeTable> incomeTables = (ArrayList<IncomeTable>) this.getAllByString("IncomeTable", "tourId=?", id);
		ArrayList<IncomeViewModel> incomes = new ArrayList<IncomeViewModel>();
		for (int i = 0; i < incomeTables.size(); i++) {
			IncomeViewModel income = new IncomeViewModel();
			income.setIncomeTable(incomeTables.get(i));
			income.setCustomerAgencyName(((CustomerAgencyTable)this.getById("CustomerAgencyTable", incomeTables.get(i).getCustomerAgencyId())).getCustomerAgencyName());
			BigDecimal invoiceAmount = new BigDecimal(0);
			ArrayList<InvoiceTable> invoiceTables =  (ArrayList<InvoiceTable>) this.getAllByString("InvoiceTable", "incomeId=?", incomeTables.get(i).getId());
			for (int j = 0; j < invoiceTables.size(); j++) {
				invoiceAmount = invoiceAmount.add(new BigDecimal(invoiceTables.get(i).getInvoiceAmount()));
			}
			income.setInvoiceAmount(invoiceAmount.floatValue());
			incomes.add(income);
		}
		full.setIncomes(incomes);
		
		ArrayList<ChangeCostTable> changeCostTables = (ArrayList<ChangeCostTable>) this.getAllByString("ChangeCostTable", "tourId=? and status=3", id);
		ArrayList<ChangeCostViewModel> changeCosts = new ArrayList<ChangeCostViewModel>();
		for (int i = 0; i < changeCostTables.size(); i++) {
			ChangeCostViewModel changeCost = new ChangeCostViewModel();
			changeCost.setBorrowUserName((changeCostTables.get(i).getBorrowUserId()==null||changeCostTables.get(i).getBorrowUserId()==0)?"":((UserTable)this.getById("UserTable", changeCostTables.get(i).getBorrowUserId())).getRealName());
			changeCost.setContentName((changeCostTables.get(i).getContentId()==null||changeCostTables.get(i).getContentId()==0)?"":((SupplierContentTable)this.getById("SupplierContentTable", changeCostTables.get(i).getContentId())).getContentName());
			changeCost.setCostTable(changeCostTables.get(i));
			changeCost.setSupplierName(((SupplierTable)this.getById("SupplierTable", changeCostTables.get(i).getSupplierId())).getSupplierName());
			changeCosts.add(changeCost);
		}
		full.setChangeCosts(changeCosts);
		
		ArrayList<ChangeIncomeTable> changeIncomeTables = (ArrayList<ChangeIncomeTable>) this.getAllByString("ChangeIncomeTable", "tourId=? and status=3", id);
		ArrayList<ChangeIncomeViewModel> changeIncomes = new ArrayList<ChangeIncomeViewModel>();
		for (int i = 0; i < changeIncomeTables.size(); i++) {
			ChangeIncomeViewModel changeIncome = new ChangeIncomeViewModel();
			changeIncome.setCustomerAgencyName(((CustomerAgencyTable)this.getById("CustomerAgencyTable", changeIncomeTables.get(i).getCustomerAgencyId())).getCustomerAgencyName());
			changeIncome.setIncomeTable(changeIncomeTables.get(i));
			BigDecimal invoiceAmount = new BigDecimal(0);
			ArrayList<InvoiceTable> invoiceTables =  (ArrayList<InvoiceTable>) this.getAllByString("InvoiceTable", "changeIncomeId=?", changeIncomeTables.get(i).getId());
			for (int j = 0; j < invoiceTables.size(); j++) {
				invoiceAmount = invoiceAmount.add(new BigDecimal(invoiceTables.get(i).getInvoiceAmount()));
			}
			changeIncome.setInvoiceAmount(invoiceAmount.floatValue());
		}
		full.setChangeIncomes(changeIncomes);
		
		return full;
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
	@SuppressWarnings("unchecked")
	public ChangeCostIncomeViewModel chanageCostIncomeFind(int tourId) {
		ChangeCostIncomeViewModel changeCostIncomeViewModel = new ChangeCostIncomeViewModel();
		ArrayList<ChangeCostTable> costTables = (ArrayList<ChangeCostTable>) this.getAllByString("ChangeCostTable", "tourId=? and status<3", tourId);
		ArrayList<ChangeCostViewModel> costs = new ArrayList<ChangeCostViewModel>();
		for (int i = 0; i < costTables.size(); i++) {
			ChangeCostViewModel cost = new ChangeCostViewModel();
			cost.setCostTable(costTables.get(i));
			cost.setBorrowUserName((costTables.get(i).getBorrowUserId()==null||costTables.get(i).getBorrowUserId()==0)?"":((UserTable)this.getById("UserTable", costTables.get(i).getBorrowUserId())).getRealName());
			SupplierContentTable supplierContentTable = (SupplierContentTable)this.getById("SupplierContentTable", costTables.get(i).getContentId());
			cost.setContentName(supplierContentTable==null?"":supplierContentTable.getContentName());
			SupplierTable supplierTable = (SupplierTable)this.getById("SupplierTable", costTables.get(i).getSupplierScopeId());
			cost.setSupplierName(supplierTable==null?"":supplierTable.getSupplierName());
			costs.add(cost);
		}
		ArrayList<ChangeIncomeTable> incomeTables = (ArrayList<ChangeIncomeTable>) this.getAllByString("ChangeIncomeTable", "tourId=? and status<3", tourId);
		ArrayList<ChangeIncomeViewModel> incomes = new ArrayList<ChangeIncomeViewModel>();
		for (int i = 0; i < incomeTables.size(); i++) {
			ChangeIncomeViewModel income = new ChangeIncomeViewModel();
			income.setIncomeTable(incomeTables.get(i));
			income.setCustomerAgencyName(((CustomerAgencyTable)this.getById("CustomerAgencyTable", incomeTables.get(i).getCustomerAgencyId())).getCustomerAgencyName());
			BigDecimal invoiceAmount = new BigDecimal(0);
			ArrayList<InvoiceTable> invoiceTables =  (ArrayList<InvoiceTable>) this.getAllByString("InvoiceTable", "incomeId=?", incomeTables.get(i).getId());
			for (int j = 0; j < invoiceTables.size(); j++) {
				invoiceAmount = invoiceAmount.add(new BigDecimal(invoiceTables.get(i).getInvoiceAmount()));
			}
			income.setInvoiceAmount(invoiceAmount.floatValue());
			incomes.add(income);
		}
		changeCostIncomeViewModel.setCosts(costs);
		changeCostIncomeViewModel.setIncomes(incomes);
		return changeCostIncomeViewModel;
	}
	
	public void sendMassage(int tourId, int status, HttpServletRequest request, HttpSession session){
		StringBuffer path = request.getRequestURL();  
		String tempContextUrl = path.delete(path.length() - request.getRequestURI().length(), path.length()).append(request.getServletContext().getContextPath()).append("/").toString();
	    /*URL需要做权限判断*/
		String url = tempContextUrl+"mobile/changeCostIncomeApproval?tourId="+tourId+"&status="+status;
	    UserTable user = (UserTable) session.getAttribute("user");
	    DeptTable dept = (DeptTable) this.getById("DeptTable", user.getDeptId());
	    String managerIds = dept.getManagerIds();
	    String[] ids = managerIds.split(",");
	    for (int i = 0; i < ids.length; i++) {
	    	UserTable manager = (UserTable)super.getById("UserTable", Integer.parseInt(ids[i]));
	    	WeiXinUtil.sendTextMessage(manager.getUserName(), url, "您有待审核的新增成本收入，点击进行审核", "0");
		}
	}
}
