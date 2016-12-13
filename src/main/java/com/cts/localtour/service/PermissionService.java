package com.cts.localtour.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.PermissionTable;
import com.cts.localtour.entity.RolePermissionTable;

@SuppressWarnings("rawtypes")
@Service
public class PermissionService extends BaseService{

	@SuppressWarnings("unchecked")
	public ArrayList<PermissionTable> findPermissions(int roleId) {
		return (ArrayList<PermissionTable>) this.getByHql("SELECT p FROM RolePermissionTable r, PermissionTable p WHERE r.permissionId=p.id AND r.roleId="+roleId);
	}

	@SuppressWarnings("unchecked")
	public void savePermissions(ArrayList<RolePermissionTable> permissionTables) {
		if(!permissionTables.isEmpty()){
			int roleId = permissionTables.get(0).getRoleId();
			this.deleteByString("RolePermissionTable", "roleId=?", roleId);
			for (RolePermissionTable rolePermissionTable : permissionTables) {
				this.add(rolePermissionTable);
			}
		}
	}

}
