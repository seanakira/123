package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.BaseDAO;
import com.cts.localtour.entity.BusinessTypeTable;
@Service
public class BaseService<T> {
	@Autowired
	private BaseDAO<T> baseDAO;
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<T> getAllByTableName(String tableName,int pageNo, int pageSize) {
		String hql = "from "+tableName+" order by id desc";
		ArrayList<T> list = (ArrayList<T>) baseDAO.find(hql, null, pageNo, pageSize);	
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<T> getAllByParam(String tableName, String where, Map<String, Object> param, int pageNo, int pageSize) {
		String hql = "from "+tableName+" where "+where+" order by id desc";
		ArrayList<T> list = (ArrayList<T>) baseDAO.find(hql, param, pageNo, pageSize);	
		return list;
	}

	public boolean add(T t) {
		try {
			baseDAO.add(t);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int getCountsByParam(String tableName, String where, Map<String, Object> param) {
		String hql = "select count(*) from "+tableName+" where "+where;
		Integer counts = baseDAO.getCounts(hql, param);
		return counts.intValue();
	}
	
	public int getCountsAll(String tableName) {
		String hql = "select count(*) from "+tableName;
		Integer counts = baseDAO.getCounts(hql,null);
		return counts.intValue();
	}


	@SuppressWarnings("unchecked")
	public List<T> getByWhere(String tableName, String where,  String param) {
		String hql = "from "+tableName+" t where t."+where+"="+param+" order by t.id desc ";
		List<T> list = baseDAO.findHql(hql, null);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getByWhere(String tableName, String where,  String param, String orderBy) {
		String hql = "from "+tableName+" t where t."+where+"="+param+" order by "+orderBy;
		List<T> list = baseDAO.findHql(hql, null);
		return list;
	}
	
	public void changeEnable(String tableName , boolean enable , int id){
		String hql = "update "+tableName+" set enable="+enable+" where id="+id;
		baseDAO.updateHql(hql);
	}
	
	public void updateByParam(String tableName, String set, String where, Object... objects){
		String hql = "update "+tableName+" set "+set+" where "+where;
		baseDAO.updateByParam(hql, objects);
	}
	
	public void update(T t){
		try {
			baseDAO.update(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllByString(String tableName, String where ,Object... objects){
		String hql = "from "+tableName+" where "+where;
		return baseDAO.getAllByString(hql, objects);
	}
	
	public void delete(T t){
		try {
			baseDAO.delete(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteByString(String tableName,String where, Object...objects){
		String hql = "delete "+tableName+" where "+where;
		baseDAO.deleteByString(hql , objects);
	}
}
