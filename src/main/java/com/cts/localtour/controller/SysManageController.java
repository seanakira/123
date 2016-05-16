package com.cts.localtour.controller;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.DeptTableService;
import com.cts.localtour.service.UserService;
import com.cts.localtour.viewModel.DeptViewModel;
import com.cts.localtour.viewModel.UserViewModel;

@Controller
public class SysManageController {
	@Autowired
	private UserService userService;
	@Autowired
	private DeptTableService deptService;
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
//		dsadasdasd
		if(page>pageMax){
			page=pageMax;
		}
		if(page<1){
			page=1;
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
	@RequestMapping("/userManage/save")
	public @ResponseBody boolean add(@RequestBody UserTable user){
		user.setPwd("123456");
		user.setEnable(true);
		userService.add(user);
		return true;
	}
	
	@RequestMapping("/userManage/del")
	public @ResponseBody boolean del(@RequestParam int id){
		userService.del(id);
		return true;
	}
	
	@RequestMapping("/userManage/recover")
	public @ResponseBody boolean recover(@RequestParam int id){
		userService.recover(id);
		return true;
	}
	
	@RequestMapping("/userManage/update")
	public @ResponseBody boolean update(@RequestBody UserTable user){
		System.out.println(user.getId());
		userService.update(user);
		return true;
	}
	
	@RequestMapping("/userManage/reset")
	public @ResponseBody boolean reset(@RequestParam int id){
		userService.reset(id);
		return true;
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
	public @ResponseBody boolean save(@RequestBody DeptTable[] deptTables){
		for (int i = 0; i < deptTables.length; i++) {
			deptService.add(deptTables[i]);
		}
		return true;
	}
	
	@RequestMapping("/deptManage/del")
	public @ResponseBody boolean del(@RequestBody DeptTable deptTable){
		System.out.println(123);
		return deptService.update(deptTable, true);
	}
	
	@RequestMapping("/deptManage/update")
	public @ResponseBody boolean  update(@RequestBody DeptTable deptTable){
		return deptService.update(deptTable, false);
	}
	
	@RequestMapping("/deptManage/getUserByDept")
	public @ResponseBody ArrayList<UserTable> getUserByDept(@RequestParam int id){
		return deptService.getUserByDept(id);
	}
	
	@RequestMapping("/deptManage/getTree")
	public @ResponseBody ArrayList<DeptTable> getTree(){
		return deptService.getTree();
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
}
