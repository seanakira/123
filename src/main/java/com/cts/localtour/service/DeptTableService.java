package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.DeptTableDAO;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.DeptViewModel;
@Service
public class DeptTableService {
	@Autowired
	private DeptTableDAO deptTableDAO;
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<DeptViewModel> getAll(String hql,Hashtable<String, String> param,int pageNo, int pageSize) {
		if(hql==null){
			hql = "from DeptTable order by id desc";
		}
		ArrayList<Object> depts = (ArrayList<Object>) deptTableDAO.find(hql, param, pageNo, pageSize);
		ArrayList<DeptViewModel> deptViewModes = new ArrayList<DeptViewModel>();
		
			for (int i = 0; i < depts.size(); i++) {
				DeptViewModel deptViewMode = new DeptViewModel();
				if(null==((DeptTable) depts.get(i)).getUpperDeptId()){
					deptViewMode.setUpperDeptName("");
					deptViewMode.setDeptTable((DeptTable)depts.get(i));
					deptViewModes.add(deptViewMode);
				}else{
					try {
						DeptTable dept = (DeptTable) deptTableDAO.getById(DeptTable.class, ((DeptTable) depts.get(i)).getUpperDeptId());
						deptViewMode.setUpperDeptName(dept.getDeptName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					deptViewMode.setDeptTable((DeptTable)depts.get(i));
					deptViewModes.add(deptViewMode);
				}
			}
		return deptViewModes;
	}
	

	@SuppressWarnings("unchecked")
	public boolean add(DeptTable deptTable) {
		try {
			deptTableDAO.add(deptTable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@SuppressWarnings("unchecked")
	public boolean update(DeptTable deptTable , boolean isDel) {
		try {
			DeptTable dept = (DeptTable) deptTableDAO.get(DeptTable.class, deptTable.getId());
			if(isDel){
				dept.setEnable(deptTable.isEnable());
			}
			if(null!=deptTable.getDeptLevel()){
				dept.setDeptLevel(deptTable.getDeptLevel());
			}
			if(null!=deptTable.getUpperDeptId()){
				dept.setUpperDeptId(deptTable.getUpperDeptId());
			}
			if(null!=deptTable.getDeptName()){
				dept.setDeptName(deptTable.getDeptName());
			}
			deptTableDAO.update(dept);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	public int getCounts(String hql,Hashtable<String,String> params) {
		if(hql==null){
		hql = "select count(*) from DeptTable";
		}
		Integer counts = deptTableDAO.getCounts(hql, params);
		return counts.intValue();
	}


	@SuppressWarnings("unchecked")
	public ArrayList<UserTable> getUserByDept(int id) {
		String hql = "FROM UserTable u where u.deptId=:deptId order by u.id desc ";
		Hashtable<String, Integer> param = new Hashtable<String, Integer>();
		param.put("deptId", id);
		ArrayList<UserTable> users = (ArrayList<UserTable>) deptTableDAO.findHql(hql, param);
		for (int i = 0; i < users.size(); i++) {
			users.get(i).setPwd("");
		}
		return users;
	}


	public ArrayList<DeptTable> getTree() {
		String hql = "from DeptTable d where d.enable=true";
		return (ArrayList<DeptTable>) deptTableDAO.findHql(hql);
	}
	
	public ArrayList<DeptTable> getAllStructure(){
		String hql = "from DeptTable d where d.enable = 1 and d.upperDeptId = null";
		ArrayList<DeptTable> depts = (ArrayList<DeptTable>)deptTableDAO.findHql(hql);
		return depts;
	}
	
	public ArrayList<DeptTable> getStructureTree(int upperDeptId){
		String hql = "from DeptTable d where d.upperDeptId = "+ upperDeptId;
		ArrayList<DeptTable> depts = (ArrayList<DeptTable>)deptTableDAO.findHql(hql);
		return depts;
	}

}
