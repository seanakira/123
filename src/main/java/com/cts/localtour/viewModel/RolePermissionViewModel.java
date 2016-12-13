package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.PermissionTable;
import com.cts.localtour.entity.RoleTable;
import com.cts.localtour.service.BaseService;
@Component
public class RolePermissionViewModel {
	private ArrayList<RoleTable> roleTables;
	private ArrayList<PermissionViewModel> permissionViewModels;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	public ArrayList<RoleTable> getRoleTables() {
		return roleTables;
	}
	public void setRoleTables(ArrayList<RoleTable> roleTables) {
		this.roleTables = roleTables;
	}
	public ArrayList<PermissionViewModel> getPermissionViewModels() {
		return permissionViewModels;
	}
	public void setPermissionViewModels(ArrayList<PermissionViewModel> permissionViewModels) {
		this.permissionViewModels = permissionViewModels;
	}
	@SuppressWarnings("unchecked")
	public RolePermissionViewModel getRoleViewModel(){
		RolePermissionViewModel roleViewModel = new RolePermissionViewModel();
		roleViewModel.setRoleTables((ArrayList<RoleTable>) baseService.getAllByStringOrderBy("RoleTable", null, "id desc", null));
		ArrayList<PermissionTable> permissionTables = (ArrayList<PermissionTable>) baseService.getAllByStringOrderBy("PermissionTable", "enable=?", "remark", true);
		ArrayList<PermissionViewModel> permissionViewModels = new ArrayList<PermissionViewModel>();
		ArrayList<String> menuCache = new ArrayList<String>();
		int type = 0;
		int modular = 0;
		for (PermissionTable permissionTable : permissionTables) {
			PermissionViewModel permissionViewModel = new PermissionViewModel();
			String[] remark = permissionTable.getRemark().split("-");
			if(remark.length==2){
				boolean exist = false;
				for (String string : menuCache) {
					if(string.equals(remark[0])){
						exist = true;
						break;
					}
				}
				if(exist==false){
					PermissionViewModel menuModel = new PermissionViewModel();
					PermissionTable menu = new PermissionTable();
					menu.setRemark(remark[0]);
					menuModel.setPermissionTable(menu);
					type++;
					menuModel.setType("menu type"+type);
					permissionViewModels.add(menuModel);
					menuCache.add(remark[0]);
				}
				permissionTable.setRemark("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+remark[1]);
				modular++;
				permissionViewModel.setType("modular modular"+modular+" type"+type);
			}else if(remark.length>2){
				permissionTable.setRemark("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+remark[2]);
				permissionViewModel.setType("modular"+modular+" type"+type);
			}
			permissionViewModel.setPermissionTable(permissionTable);
			
			permissionViewModels.add(permissionViewModel);
		}
		roleViewModel.setPermissionViewModels(permissionViewModels);
		return roleViewModel;
	}
}
