package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.BaseDAO;
import com.cts.localtour.entity.BusinessTypeTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.viewModel.SimpleLocalTourViewModel;
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

	public T add(T t) {
		try {
			return baseDAO.add(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public T merge(T t){
		try {
			return baseDAO.merge(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
	public Integer getCountsByString(String tableName, String where ,Object... objects){
		String hql = "select count(*) from "+tableName+" where "+where;
		return baseDAO.getCountsByString(hql, objects);
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
	
	public void changeValue(String tableName ,String columnName ,String value , int id){
		String hql = "update "+tableName+" set "+columnName+"="+value+" where id="+id;
		baseDAO.updateHql(hql);
	}
	
	public void updateByString(String tableName, String set, String where, Object... objects){
		String hql = "update "+tableName+" set "+set+" where "+where;
		baseDAO.updateByParam(hql, objects);
	}
	
	public void update(T t){
		try {
			baseDAO.update(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllByString(String tableName, String where ,Object... objects){
		String hql = "from "+tableName+" where "+where;
		return baseDAO.getAllByString(hql, objects);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllByStringOrderBy(String tableName, String where , String orderBy, Object... objects){
		String hql = "from "+tableName+" where "+where+" order by "+orderBy;
		System.out.println(hql);
		return baseDAO.getAllByString(hql, objects);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllByStringOrderByLimit(String tableName, String where, String orderBy, int maxResults, Object... objects){
		String hql = "from "+tableName+" where "+where+" order by "+orderBy;
		return baseDAO.getAllBystringLimit(hql, maxResults, objects);
	}
	public void delete(T t){
		try {
			baseDAO.delete(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void delById(String tableName, int id) {
		String className = "com.cts.localtour.entity."+tableName;
		Class<?> clzz = null;
		try {
			clzz = Class.forName(className);
			T t = (T) baseDAO.getById(clzz, id);
			baseDAO.delete( t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteByString(String tableName,String where, Object...objects){
		String hql = "delete "+tableName+" where "+where;
		baseDAO.deleteByString(hql , objects);
	}
	
	public void delByIds(String tableName,String ids){
		String hql = "delete "+tableName+" where id in ("+ids+")";
		baseDAO.deleteByString(hql , null);
	}
	
	public Object getById(String tableName,int id){
		String className = "com.cts.localtour.entity."+tableName;
		Class<?> clzz = null;
		try {
			clzz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baseDAO.getById(clzz, id);
	}
	
	@SuppressWarnings("rawtypes")
	public List getByHql(String hql){
		return baseDAO.findHql(hql);
	}
	
	@SuppressWarnings("rawtypes")
	public List getSumByColumnName(String tableName, String columnName,String as,String where, Object... objects){
		String hql = "select sum("+columnName+") as "+as+" from "+tableName+" where "+where;
		return baseDAO.getAllByString(hql, objects);
	}
}
