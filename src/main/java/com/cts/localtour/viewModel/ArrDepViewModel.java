package com.cts.localtour.viewModel;

import com.cts.localtour.entity.ArrTable;
import com.cts.localtour.entity.DepartTable;

public class ArrDepViewModel {
	private String region1;
	private String region2;
	private ArrTable arrTable;
	private DepartTable departTable;
	public String getRegion1() {
		return region1;
	}
	public void setRegion1(String region1) {
		this.region1 = region1;
	}
	public String getRegion2() {
		return region2;
	}
	public void setRegion2(String region2) {
		this.region2 = region2;
	}
	public ArrTable getArrTable() {
		return arrTable;
	}
	public void setArrTable(ArrTable arrTable) {
		this.arrTable = arrTable;
	}
	public DepartTable getDepartTable() {
		return departTable;
	}
	public void setDepartTable(DepartTable departTable) {
		this.departTable = departTable;
	}
	
}
