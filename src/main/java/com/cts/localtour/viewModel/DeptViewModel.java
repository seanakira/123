package com.cts.localtour.viewModel;

import com.cts.localtour.entity.DeptTable;

public class DeptViewModel {
	private DeptTable deptTable;
	private String upperDeptName;
	private String managerName;
	public DeptTable getDeptTable() {
		return deptTable;
	}
	public void setDeptTable(DeptTable deptTable) {
		this.deptTable = deptTable;
	}
	public String getUpperDeptName() {
		return upperDeptName;
	}
	public void setUpperDeptName(String upperDeptName) {
		this.upperDeptName = upperDeptName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
}
