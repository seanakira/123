package com.cts.localtour.viewModel;

import java.util.ArrayList;

import com.cts.localtour.entity.ArrDepartTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.TripTable;

public class FullLocalTourViewModel {
	private LocalTourTable localTourTable;
	private ArrDepartTable arrDepartTable;
	private ArrayList<TripTable> tripTables;
	private ArrayList<CostTable> costTables;
	private ArrayList<IncomeTable> incomeTables;
}
