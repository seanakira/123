package com.cts.localtour.viewModel;

import com.cts.localtour.entity.PermissionTable;

public class PermissionViewModel {
	private PermissionTable permissionTable;
	private String type;
	public PermissionTable getPermissionTable() {
		return permissionTable;
	}
	public void setPermissionTable(PermissionTable permissionTable) {
		this.permissionTable = permissionTable;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
