package com.cts.localtour.viewModel;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ArrTable;
import com.cts.localtour.entity.BusinessTypeTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.DepartTable;
import com.cts.localtour.entity.GuideTimeTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.entity.TourTypeTable;
import com.cts.localtour.entity.TripTable;
import com.cts.localtour.entity.VisitorTypeTable;
import com.cts.localtour.service.BaseService;
@Component
public class FullLocalTourViewModel {
	private LocalTourTable localTourTable;
	private ArrayList<GuideTimeTable> guideTimeTables;
	private ArrayList<GuideTimeViewModel> guideTimes;
	private ArrayList<ArrTable> arrTables;
	private ArrayList<DepartTable> departTables;
	private ArrayList<ArrViewModel> arrs;
	private ArrayList<DepartViewModel> departs;
	private ArrayList<TripTable> tripTables;
	private ArrayList<CostTable> costTables;
	private ArrayList<CostViewModel> costs;
	private ArrayList<ChangeCostViewModel> changeCosts;
	private ArrayList<ReimbursementCostViewModel> reimbursementCosts;
	private ArrayList<IncomeTable> incomeTables;
	private ArrayList<IncomeViewModel> incomes;
	private ArrayList<ChangeIncomeViewModel> changeIncomes;
	private ArrayList<ReimbursementIncomeViewModel> reimbursementIncomes;
	private ArrayList<LoanViewModel> loans;
	private Hashtable<String, String> tourInfo;
	private Hashtable<String, String> delIds;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private GuideTimeViewModel guideTimeViewModel;
	@Autowired
	private ArrViewModel arrViewModel;
	@Autowired
	private DepartViewModel departViewModel;
	@Autowired
	private CostViewModel costViewModel;
	@Autowired
	private IncomeViewModel incomeViewModel;
	@Autowired
	private ChangeCostViewModel changeCostViewModel;
	@Autowired
	private ChangeIncomeViewModel changeIncomeViewModel;
	@Autowired
	private ReimbursementCostViewModel reimbursementCostViewModel;
	@Autowired
	private ReimbursementIncomeViewModel reimbursementIncomeViewModel;
	@Autowired
	private LoanViewModel loanViewModel;
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
	}
	public ArrayList<ArrTable> getArrTables() {
		return arrTables;
	}
	public void setArrTables(ArrayList<ArrTable> arrTables) {
		this.arrTables = arrTables;
	}
	public ArrayList<DepartTable> getDepartTables() {
		return departTables;
	}
	public void setDepartTables(ArrayList<DepartTable> departTables) {
		this.departTables = departTables;
	}
	public ArrayList<TripTable> getTripTables() {
		return tripTables;
	}
	public void setTripTables(ArrayList<TripTable> tripTables) {
		this.tripTables = tripTables;
	}
	public ArrayList<CostTable> getCostTables() {
		return costTables;
	}
	public void setCostTables(ArrayList<CostTable> costTables) {
		this.costTables = costTables;
	}
	public ArrayList<IncomeTable> getIncomeTables() {
		return incomeTables;
	}
	public void setIncomeTables(ArrayList<IncomeTable> incomeTables) {
		this.incomeTables = incomeTables;
	}
	public Hashtable<String, String> getTourInfo() {
		return tourInfo;
	}
	public void setTourInfo(Hashtable<String, String> tourInfo) {
		this.tourInfo = tourInfo;
	}
	public ArrayList<ArrViewModel> getArrs() {
		return arrs;
	}
	public void setArrs(ArrayList<ArrViewModel> arrs) {
		this.arrs = arrs;
	}
	public ArrayList<DepartViewModel> getDeparts() {
		return departs;
	}
	public void setDeparts(ArrayList<DepartViewModel> departs) {
		this.departs = departs;
	}
	public ArrayList<CostViewModel> getCosts() {
		return costs;
	}
	public void setCosts(ArrayList<CostViewModel> costs) {
		this.costs = costs;
	}
	public ArrayList<IncomeViewModel> getIncomes() {
		return incomes;
	}
	public void setIncomes(ArrayList<IncomeViewModel> incomes) {
		this.incomes = incomes;
	}
	public ArrayList<GuideTimeTable> getGuideTimeTables() {
		return guideTimeTables;
	}
	public void setGuideTimeTables(ArrayList<GuideTimeTable> guideTimeTables) {
		this.guideTimeTables = guideTimeTables;
	}
	public ArrayList<GuideTimeViewModel> getGuideTimes() {
		return guideTimes;
	}
	public void setGuideTimes(ArrayList<GuideTimeViewModel> guideTimes) {
		this.guideTimes = guideTimes;
	}
	public Hashtable<String, String> getDelIds() {
		return delIds;
	}
	public void setDelIds(Hashtable<String, String> delIds) {
		this.delIds = delIds;
	}
	public ArrayList<ChangeCostViewModel> getChangeCosts() {
		return changeCosts;
	}
	public void setChangeCosts(ArrayList<ChangeCostViewModel> changeCosts) {
		this.changeCosts = changeCosts;
	}
	public ArrayList<ChangeIncomeViewModel> getChangeIncomes() {
		return changeIncomes;
	}
	public void setChangeIncomes(ArrayList<ChangeIncomeViewModel> changeIncomes) {
		this.changeIncomes = changeIncomes;
	}
	public ArrayList<ReimbursementCostViewModel> getReimbursementCosts() {
		return reimbursementCosts;
	}
	public void setReimbursementCosts(ArrayList<ReimbursementCostViewModel> reimbursementCosts) {
		this.reimbursementCosts = reimbursementCosts;
	}
	public ArrayList<ReimbursementIncomeViewModel> getReimbursementIncomes() {
		return reimbursementIncomes;
	}
	public void setReimbursementIncomes(ArrayList<ReimbursementIncomeViewModel> reimbursementIncomes) {
		this.reimbursementIncomes = reimbursementIncomes;
	}
	public ArrayList<LoanViewModel> getLoans() {
		return loans;
	}
	public void setLoans(ArrayList<LoanViewModel> loans) {
		this.loans = loans;
	}
	@SuppressWarnings("unchecked")
	public FullLocalTourViewModel getFullLocalTourViewModel(int tourId){
		FullLocalTourViewModel full = new FullLocalTourViewModel();
		LocalTourTable localTour = (LocalTourTable) baseService.getById("LocalTourTable", tourId);
		full.setLocalTourTable(localTour);
		
		Hashtable<String, String> tourInfo = new Hashtable<String, String>();
		tourInfo.put("businessTypeName", ((BusinessTypeTable)baseService.getById("BusinessTypeTable", localTour.getBusinessTypeId())).getBusinessTypeName());
		tourInfo.put("tourTypeName", ((TourTypeTable)baseService.getById("TourTypeTable", localTour.getTourTypeId())).getTourTypeName());
		tourInfo.put("regionName", ((RegionTable)baseService.getById("RegionTable", localTour.getRegionId())).getRegionName());
		tourInfo.put("visitorTypeName", ((VisitorTypeTable)baseService.getById("VisitorTypeTable", localTour.getVisitorTypeId())).getVisitorTypeName());
		tourInfo.put("customerAgencyName", ((CustomerAgencyTable)baseService.getById("CustomerAgencyTable", localTour.getCustomerAgencyId())).getCustomerAgencyName());
		full.setTourInfo(tourInfo);
		full.setGuideTimes(guideTimeViewModel.getAllGuideTimeViewModel(tourId));
//		full.setArrTables((ArrayList<ArrTable>) baseService.getAllByString("ArrTable", "tourId=?", id));
		full.setArrs(arrViewModel.getAllArrDepViewModel(tourId));
		
//		full.setDepartTables(departTables);
		full.setDeparts(departViewModel.getAllDepartViewModel(tourId));
		
		full.setTripTables((ArrayList<TripTable>) baseService.getAllByString("TripTable", "tourId=?", tourId));
		
		full.setCosts(costViewModel.getAllCostViewModel(tourId));
		
		full.setIncomes(incomeViewModel.getAllIncomeViewModel(tourId));
		
		full.setChangeCosts(changeCostViewModel.getAllChangeCostViewModel(tourId,3));
		
		full.setChangeIncomes(changeIncomeViewModel.getAllChangeIncomeViewModel(tourId,3));
		
		full.setReimbursementCosts(reimbursementCostViewModel.getAllReimbursementCostViewModelAll(tourId));
		
		full.setReimbursementIncomes(reimbursementIncomeViewModel.getAllIncomeViewModel(tourId));
		
		full.setLoans(loanViewModel.getAllLoanViewModel(tourId, 4));
		return full;
	}
}
