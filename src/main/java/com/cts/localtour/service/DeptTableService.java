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
@SuppressWarnings("rawtypes")
@Service
public class DeptTableService extends BaseService{
	@Autowired
	private DeptTableDAO deptTableDAO;
	
	
	@SuppressWarnings({ "unchecked", "null" })
	public ArrayList<DeptViewModel> getAll(String hql,Hashtable<String, String> param,int pageNo, int pageSize) {
		if(hql==null){
			hql = "from DeptTable order by id desc";
		}
		ArrayList<DeptTable> depts = (ArrayList<DeptTable>) deptTableDAO.find(hql, param, pageNo, pageSize);
		ArrayList<DeptViewModel> deptViewModes = new ArrayList<DeptViewModel>();
		
			for (int i = 0; i < depts.size(); i++) {
				DeptViewModel deptViewMode = new DeptViewModel();
				if(null==((DeptTable) depts.get(i)).getUpperDeptId()){
					deptViewMode.setUpperDeptName("");
					deptViewMode.setDeptTable((DeptTable)depts.get(i));
					if(!"".equals(depts.get(i).getManagerIds())&&depts.get(i).getManagerIds()!=null){
						String[] managerIds = depts.get(i).getManagerIds().split(",");
						StringBuffer managerName = null;
						for (int j = 0; j < managerIds.length; j++) {
							UserTable user = (UserTable) this.getById("UserTable", Integer.parseInt(managerIds[j]));
							managerName.append(user.getRealName());
							managerName.append("  ");
							managerName.append(user.getUserName());
							managerName.append("    ");
						}
						deptViewMode.setManagerName(managerName.toString());
					}
					deptViewModes.add(deptViewMode);
				}else{
					deptViewMode.setUpperDeptName(((DeptTable)this.getById("DeptTable", depts.get(i).getUpperDeptId())).getDeptName());
					deptViewMode.setDeptTable((DeptTable)depts.get(i));
					if(!"".equals(depts.get(i).getManagerIds())&&depts.get(i).getManagerIds()!=null){
						String[] managerIds = depts.get(i).getManagerIds().split(",");
						StringBuffer managerName = new StringBuffer();
						for (int j = 0; j < managerIds.length; j++) {
							UserTable user = (UserTable) this.getById("UserTable", Integer.parseInt(managerIds[j]));
							managerName.append(user.getRealName());
							managerName.append("  ");
							managerName.append(user.getUserName());
							managerName.append("    ");
						}
						deptViewMode.setManagerName(managerName.toString());
					}
					deptViewModes.add(deptViewMode);
				}
			}
		return deptViewModes;
	}
	

	@SuppressWarnings("unchecked")
	public int add(DeptTable deptTable) {
		try {
			return ((DeptTable) deptTableDAO.add(deptTable)).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
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
	
	@SuppressWarnings("unchecked")
	public int mergeDept(DeptTable deptTable){
		return ((DeptTable)this.merge(deptTable)).getId();
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
