package com.cts.localtour.viewModel;

import java.util.ArrayList;

import com.cts.localtour.entity.ArrTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.DepartTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.TripTable;

public class FullLocalTourViewModel {
	private LocalTourTable localTourTable;
	private ArrayList<ArrTable> arrTables;
	private ArrayList<DepartTable> departTables;
	private ArrayList<TripTable> tripTables;
	private ArrayList<CostTable> costTables;
	private ArrayList<IncomeTable> incomeTables;
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
	
}
