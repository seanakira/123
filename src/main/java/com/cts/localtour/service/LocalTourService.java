package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.LocalTourDAO;
import com.cts.localtour.entity.SupplierBusinessTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.TourTypeTable;
import com.cts.localtour.entity.TripTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.entity.VisitorTypeTable;
import com.cts.localtour.entity.ArrTable;
import com.cts.localtour.entity.BusinessTypeTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.DepartTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.viewModel.ArrDepViewModel;
import com.cts.localtour.viewModel.CostViewModel;
import com.cts.localtour.viewModel.CreateInfoViewModel;
import com.cts.localtour.viewModel.FullLocalTourViewModel;
import com.cts.localtour.viewModel.SimpleLocalTourViewModel;
import com.cts.localtour.viewModel.incomeViewModel;

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
		this.changeEnable("LocalTourTable", false, id);
	}
	
	public void recover(int id){
		this.changeEnable("LocalTourTable", true, id);
	}
	
	public void changeStatus(int id, int status) {
		this.updateByParam("LocalTourTable", "status=?", "id="+id, status);
	}

	public void update(LocalTourTable supplier) {
//		this.updateByParam("LocalTourTable", "supplierName=?,regionId=?,phone=?", "id="+supplier.getId(), supplier.getSupplierName(),supplier.getRegionId(),supplier.getPhone());
	}
	
	@SuppressWarnings("unchecked")
	public int add(LocalTourTable localTour){
		try {
			return ((LocalTourTable)localTourDAO.add(localTour)).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void addSupplierBusiness(int supplierId, String supplierScopeIds){
		try {
			String ids[] = supplierScopeIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				SupplierBusinessTable supplierBusinessTable = new SupplierBusinessTable();
				supplierBusinessTable.setSupplierId(supplierId);
				supplierBusinessTable.setSupplierScopeId(Integer.parseInt(ids[i]));
				this.add(supplierBusinessTable);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteSupplierBusiness(int supplierId) {
		this.deleteByString("SupplierBusinessTable", "supplierId=?", supplierId);
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
		if(!localTour.getGuideIds().equals("undefined")&&!localTour.getGuideIds().equals("")){
			String[] guideIds = localTour.getGuideIds().split(",");
			String guideNames = ((UserTable)this.getById("UserTable", Integer.parseInt(guideIds[0]))).getRealName();
			for (int i = 1; i < guideIds.length; i++) {
				guideNames = guideNames+","+((UserTable)this.getById("UserTable", Integer.parseInt(guideIds[i]))).getRealName();
			}
			tourInfo.put("guideNames", guideNames);
		}else{
			tourInfo.put("guideNames", "");
		}
		full.setTourInfo(tourInfo);
		ArrayList<ArrTable> arrTables = (ArrayList<ArrTable>) this.getAllByString("ArrTable", "tourId=?", id);
		ArrayList<ArrDepViewModel> arrs = new ArrayList<ArrDepViewModel>();
		for (int i = 0; i < arrTables.size(); i++) {
			ArrDepViewModel arr = new ArrDepViewModel();
			arr.setArrTable(arrTables.get(i));
			arr.setRegion1(((RegionTable)this.getById("RegionTable", arrTables.get(i).getOriginId())).getRegionName());
			arr.setRegion2(((RegionTable)this.getById("RegionTable", arrTables.get(i).getArrRegionId())).getRegionName());
			arrs.add(arr);
		}
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
		full.setDeparts(departs);
		full.setTripTables((ArrayList<TripTable>) this.getAllByString("TripTable", "tourId=?", id));
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) this.getAllByString("CostTable", "tourId=?", id);
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (int i = 0; i < costTables.size(); i++) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTables.get(i));
			cost.setContentName(((SupplierContentTable)this.getById("SupplierContentTable", costTables.get(i).getContentId())).getContentName());
			cost.setSupplierName(((SupplierTable)this.getById("SupplierTable", costTables.get(i).getSupplierId())).getSupplierName());
			cost.setBorrowUserName(((UserTable)this.getById("UserTable", costTables.get(i).getBorrowUserId())).getRealName());
			costs.add(cost);
		}
		full.setCosts(costs);
		ArrayList<IncomeTable> incomeTables = (ArrayList<IncomeTable>) this.getAllByString("IncomeTable", "tourId=?", id);
		ArrayList<incomeViewModel> incomes = new ArrayList<incomeViewModel>();
		for (int i = 0; i < incomeTables.size(); i++) {
			incomeViewModel income = new incomeViewModel();
			income.setIncomeTable(incomeTables.get(i));
			income.setCustomerAgencyName(((CustomerAgencyTable)this.getById("CustomerAgencyTable", incomeTables.get(i).getCustomerAgencyId())).getCustomerAgencyName());
			float invoiceAmount = 0;
			ArrayList<InvoiceTable> invoiceTables =  (ArrayList<InvoiceTable>) this.getAllByString("InvoiceTable", "incomeId=?", incomeTables.get(i).getId());
			for (int j = 0; j < invoiceTables.size(); j++) {
				invoiceAmount = invoiceAmount+invoiceTables.get(i).getInvoiceAmount();
			}
			income.setInvoiceAmount(invoiceAmount);
			incomes.add(income);
		}
		full.setIncomes(incomes);
		return full;
	}
}
