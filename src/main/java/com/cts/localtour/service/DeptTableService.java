package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.DeptTableDAO;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.pojo.TreeElement;
import com.cts.localtour.pojo.TreeChildren;
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
							if(user!=null){
								managerName.append(user.getRealName());
								managerName.append("  ");
								managerName.append(user.getUserName());
								managerName.append("    ");
							}
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

	@SuppressWarnings("unchecked")
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


	@SuppressWarnings("unchecked")
	public HashMap<Integer, TreeElement> getTree() {
		String hql = "from DeptTable d where d.enable=true";
		ArrayList<DeptTable> depts = (ArrayList<DeptTable>) deptTableDAO.findHql(hql);
		HashMap<Integer, TreeElement> treeMap = new HashMap<Integer, TreeElement>();
		int maxLevel = 0;
		for (DeptTable deptTable : depts) {
			if(deptTable.getDeptLevel()>maxLevel){
				maxLevel = deptTable.getDeptLevel();
			}
		}
		for (int i = 1; i <= maxLevel; i++) {
			TreeChildren children = new TreeChildren();
			for (DeptTable deptTable : depts) {
				if(deptTable.getDeptLevel()==i){
					if(i==1){
						TreeElement treeElement = new TreeElement();
						treeElement.setId(deptTable.getId());
						treeElement.setName(deptTable.getDeptName());
						treeElement.setType("folder");
						treeElement.setAdditionalParameters(null);
						treeMap.put(deptTable.getId(), treeElement);
					}else if(i==2){
						TreeElement treeElement = new TreeElement();
						treeElement.setId(deptTable.getId());
						treeElement.setName(deptTable.getDeptName());
						treeElement.setType("folder");
						treeElement.setAdditionalParameters(null);
						children.getChildren().put(deptTable.getId(), treeElement);
						treeMap.get(deptTable.getUpperDeptId()).setAdditionalParameters(children);
					}else if(i>2){
						TreeElement treeElement = new TreeElement();
						treeElement.setId(deptTable.getId());
						treeElement.setName(deptTable.getDeptName());
						treeElement.setType("folder");
						treeElement.setAdditionalParameters(null);
						children.getChildren().put(deptTable.getId(), treeElement);
						int[] upId = new int[i-1];
						int id = deptTable.getUpperDeptId();
						upId[0] = id;
						for (int j = 0; j < i-1; j++) {
							for (DeptTable dept : depts) {
								if(id==dept.getId()){
									if(dept.getUpperDeptId()!=null){
										upId[j+1] = dept.getUpperDeptId();
										id = dept.getUpperDeptId();
									}
								}
							}
						}
						HashMap<Integer, TreeElement> child = treeMap.get(upId[i-2]).getAdditionalParameters().getChildren();
						for (int j = upId.length-2; j >= 0 ; j--) {
							if(child.get(upId[j]).getAdditionalParameters()!=null){
								child = child.get(upId[j]).getAdditionalParameters().getChildren();
							}
						}
						if(child.get(deptTable.getUpperDeptId())!=null){
							child.get(deptTable.getUpperDeptId()).setAdditionalParameters(children);
						}
					}
				}
			}
		}
		return treeMap;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<DeptTable> getAllStructure(){
		String hql = "from DeptTable d where d.enable = 1 and d.upperDeptId = null";
		ArrayList<DeptTable> depts = (ArrayList<DeptTable>)deptTableDAO.findHql(hql);
		return depts;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<DeptTable> getStructureTree(int upperDeptId){
		String hql = "from DeptTable d where d.upperDeptId = "+ upperDeptId;
		ArrayList<DeptTable> depts = (ArrayList<DeptTable>)deptTableDAO.findHql(hql);
		return depts;
	}


	@SuppressWarnings("unchecked")
	public ArrayList<DeptTable> getDeptTree() {
		return (ArrayList<DeptTable>) this.getAllByString("DeptTable", "enable=?", true);
	}

}
