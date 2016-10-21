package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.DepartTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.service.BaseService;
@Component
public class DepartViewModel {
	private String dest;
	private String departRegion;
	private DepartTable departTable;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	
	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getDepartRegion() {
		return departRegion;
	}

	public void setDepartRegion(String departRegion) {
		this.departRegion = departRegion;
	}

	public DepartTable getDepartTable() {
		return departTable;
	}

	public void setDepartTable(DepartTable departTable) {
		this.departTable = departTable;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<DepartViewModel> getAllDepartViewModel(int tourId){
		ArrayList<DepartTable> departTables = (ArrayList<DepartTable>) baseService.getAllByString("DepartTable", "tourId=?", tourId);
		ArrayList<DepartViewModel> departs = new ArrayList<DepartViewModel>();
		for (int i = 0; i < departTables.size(); i++) {
			DepartViewModel depart = new DepartViewModel();
			depart.setDepartTable(departTables.get(i));
			depart.setDest(((RegionTable)baseService.getById("RegionTable", departTables.get(i).getDestId())).getRegionName());
			depart.setDepartRegion(((RegionTable)baseService.getById("RegionTable", departTables.get(i).getDepartRegionId())).getRegionName());
			departs.add(depart);
		}
		return departs;
	}
}
