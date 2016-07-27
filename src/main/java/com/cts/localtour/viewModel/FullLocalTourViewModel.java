package com.cts.localtour.viewModel;

import java.util.ArrayList;
import java.util.Hashtable;

import com.cts.localtour.entity.ArrTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.DepartTable;
import com.cts.localtour.entity.GuideTimeTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.TripTable;

public class FullLocalTourViewModel {
	private LocalTourTable localTourTable;
	private ArrayList<GuideTimeTable> guideTimeTables;
	private ArrayList<GuideTimeViewModel> guideTimes;
	private ArrayList<ArrTable> arrTables;
	private ArrayList<DepartTable> departTables;
	private ArrayList<ArrDepViewModel> arrs;
	private ArrayList<ArrDepViewModel> departs;
	private ArrayList<TripTable> tripTables;
	private ArrayList<CostTable> costTables;
	private ArrayList<CostViewModel> costs;
	private ArrayList<IncomeTable> incomeTables;
	private ArrayList<incomeViewModel> incomes;
	private Hashtable<String, String> tourInfo;
	private Hashtable<String, ArrayList> updateIds;
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
	public ArrayList<ArrDepViewModel> getArrs() {
		return arrs;
	}
	public void setArrs(ArrayList<ArrDepViewModel> arrs) {
		this.arrs = arrs;
	}
	public ArrayList<ArrDepViewModel> getDeparts() {
		return departs;
	}
	public void setDeparts(ArrayList<ArrDepViewModel> departs) {
		this.departs = departs;
	}
	public ArrayList<CostViewModel> getCosts() {
		return costs;
	}
	public void setCosts(ArrayList<CostViewModel> costs) {
		this.costs = costs;
	}
	public ArrayList<incomeViewModel> getIncomes() {
		return incomes;
	}
	public void setIncomes(ArrayList<incomeViewModel> incomes) {
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
	public Hashtable<String, ArrayList> getUpdateIds() {
		return updateIds;
	}
	public void setUpdateIds(Hashtable<String, ArrayList> updateIds) {
		this.updateIds = updateIds;
	}
}
