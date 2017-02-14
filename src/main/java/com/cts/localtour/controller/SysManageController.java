package com.cts.localtour.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.PermissionTable;
import com.cts.localtour.entity.RolePermissionTable;
import com.cts.localtour.entity.RoleTable;
import com.cts.localtour.entity.UserRoleTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.pojo.TreeElement;
import com.cts.localtour.service.DeptTableService;
import com.cts.localtour.service.PermissionService;
import com.cts.localtour.service.RoleService;
import com.cts.localtour.service.UserService;
import com.cts.localtour.viewModel.DeptViewModel;
import com.cts.localtour.viewModel.RolePermissionViewModel;
import com.cts.localtour.viewModel.UserViewModel;

@Controller
public class SysManageController {
	@Autowired
	private UserService userService;
	@Autowired
	private DeptTableService deptService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RolePermissionViewModel roleViewModel;
/*
 * 
 * 用户管理
 * 
 * */
	@RequestMapping("/userManage")
	public String getUserAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = userService.getCounts(key);
		int pageMax = counts/maxResults;
		if(counts%maxResults>0){
			pageMax++;
		}
		if(page>pageMax){
			page=pageMax;
		}
		if(page<1){
			page=1;
		}
		ArrayList<UserViewModel> users = userService.getAll(key,page,maxResults);
		md.addAttribute("users", users);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/sysManage/userManage";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/userManage/initRole")
	public @ResponseBody ArrayList<RoleTable> initRole(){
		return (ArrayList<RoleTable>) userService.getAllByString("RoleTable", null, null);
	}
	
	@RequestMapping("/userManage/save")
	public @ResponseBody int add(@RequestBody UserViewModel userViewModel){
		return userService.addUser(userViewModel);
	}
	
	@RequestMapping("/userManage/del")
	public @ResponseBody boolean del(@RequestParam int id,@RequestParam String userName){
		userService.del(id);
		userService.changWeiXinEnable(userName, "0");
		return true;
	}
	
	@RequestMapping("/userManage/recover")
	public @ResponseBody boolean recover(@RequestParam int id,@RequestParam String userName){
		userService.recover(id);
		userService.changWeiXinEnable(userName, "1");
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/userManage/update")
	public @ResponseBody int update(@RequestBody UserViewModel userViewModel){
		if(userViewModel.getUserTable().getUserName()==null&&userViewModel.getUserTable().getRealName()==null&&userViewModel.getUserTable().getDeptId()==0){
			userService.deleteByString("UserRoleTable", "userId=?", userViewModel.getUserTable().getId());
			for (RoleTable roleTable : userViewModel.getRoleTables()) {
				UserRoleTable userRoleTable = new UserRoleTable();
				userRoleTable.setRoleId(roleTable.getId());
				userRoleTable.setUserId(userViewModel.getUserTable().getId());
				userService.add(userRoleTable);
			}
			userService.updateToWeiXin(userService.update(userViewModel.getUserTable()));
		}else{
			return -1;
		}
		return 1;
	}
	
	@RequestMapping("/userManage/find")
	public @ResponseBody UserViewModel find(@RequestParam int userId){
		return userService.find(userId);
	}
	
	@RequestMapping("/userManage/reset")
	public @ResponseBody boolean reset(@RequestParam int id){
		userService.reset(id);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/userManage/getCreateInfo")
	public @ResponseBody ArrayList<UserTable> getCreateInfo(){
		return (ArrayList<UserTable>) userService.getAllByString("UserTable", "enable=?", true);
	}
	
/*
 * 
 * 部门管理
 * 
 * */
	@RequestMapping("/deptManage")
	public String getDeptAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,Model md){
		int counts = deptService.getCounts(null,null);
		int pageMax = counts/maxResults;
		if(counts%maxResults>0){
			pageMax++;
		}
		if(page>pageMax){
			page=pageMax;
		}
		if(page<1){
			page=1;
		}
		ArrayList<DeptViewModel> deptViewModes = deptService.getAll(null,null,page,maxResults);
		md.addAttribute("deptViewModes", deptViewModes);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		return "/sysManage/deptManage";
	}
	
	@RequestMapping("/deptManage/save")
	public @ResponseBody int save(@RequestBody DeptTable deptTable){
		return deptService.add(deptTable);
	}
	
	@RequestMapping("/deptManage/update")
	public @ResponseBody int update(@RequestBody DeptTable deptTable){
		return deptService.mergeDept(deptTable);
	}
	@RequestMapping("/deptManage/del")
	public @ResponseBody boolean del(@RequestBody DeptTable deptTable){
		return deptService.update(deptTable, true);
	}
	
	@RequestMapping("/deptManage/getUserByDept")
	public @ResponseBody ArrayList<UserTable> getUserByDept(@RequestParam int id){
		return deptService.getUserByDept(id);
	}
	
	@RequestMapping("/deptManage/getTree")
	public @ResponseBody HashMap<Integer,TreeElement> getTree(){
		return deptService.getTree();
	}
	
	@RequestMapping("/deptManage/getDeptTree")
	public @ResponseBody ArrayList<DeptTable> getDeptTree(){
		return deptService.getDeptTree();
	}
	
	@RequestMapping("/deptManage/search")
	public String search(@RequestParam String key ,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="20") int maxResults,Model md){
		String hql = "select count(*) from DeptTable d where d.deptName like :deptName";
		Hashtable<String, String> param = new Hashtable<String, String>();
		param.put("deptName", "%"+key+"%");
		int counts = deptService.getCounts(hql,param);
		int pageMax = counts/maxResults;
		if(counts%maxResults>0){
			pageMax++;
		}
		if(page>pageMax){
			page=pageMax;
		}
		if(page<1){
			page=1;
		}
		hql = "from DeptTable d where d.deptName like :deptName";
		ArrayList<DeptViewModel> deptViewModes = deptService.getAll(hql,param,page,maxResults);
		md.addAttribute("deptViewModes", deptViewModes);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		return "/sysManage/deptManage";
	}
	
	/*角色管理*/
	@RequestMapping("/roleManage")
	public String getRoleAll(Model md){
		md.addAttribute("roles", roleViewModel.getRoleViewModel());
		return "/sysManage/roleManage";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/roleManage/save")
	public @ResponseBody int saveRole(@RequestBody RoleTable roleTable){
		return ((RoleTable)roleService.add(roleTable)).getId();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/roleManage/update")
	public void updateRole(@RequestBody RoleTable roleTable){
		roleService.update(roleTable);
	}
	
	@RequestMapping("/permissionSManage/find")
	public @ResponseBody ArrayList<PermissionTable> findPermissions(@RequestParam int roleId){
		return permissionService.findPermissions(roleId);
	}
	
	@RequestMapping("/permissionSManage/update")
	public @ResponseBody int savePermissions(@RequestBody ArrayList<RolePermissionTable> rolePermissionTables){
		permissionService.savePermissions(rolePermissionTables);
		return 1;
	}
	
	/*部门结构*/
	@RequestMapping("/deptStructure")
	public String getDeptStructureAll(Model md){
		ArrayList<DeptTable> depts = deptService.getAllStructure();
		md.addAttribute("depts", depts);
		return "/sysManage/deptStructure";
	} 
	
	@RequestMapping("/deptStructure/getTree")
	public @ResponseBody ArrayList<DeptTable> getDeptStructureTree(Model md, @RequestParam int upperDeptId){
		ArrayList<DeptTable> depts = deptService.getStructureTree(upperDeptId);
		return depts;
	}
	
	@RequestMapping("/deptStructure/getUserTree")
	public @ResponseBody ArrayList<UserTable> getUserStructureTree(Model md, @RequestParam int deptId){
		ArrayList<UserTable> users = userService.getUserStructureTree(deptId);
		return users;
	}
	
	@RequestMapping("/deptStructure/getUserView")
	public @ResponseBody UserViewModel getUserView(Model md, @RequestParam int userId){
		UserViewModel user = userService.getUserView(userId);
		return user;
	}
	
	@RequestMapping("/tree")
	public String tree(){
		return "/sysManage/treeview";
	}
}
