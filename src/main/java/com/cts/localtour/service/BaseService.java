package com.cts.localtour.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.BaseDAO;
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
	
	public ArrayList<T> getAllByParam(String tableName, String where, Map<String, Object> param, int pageNo, int pageSize) {
		String hql = "from "+tableName+" where "+where+" order by id desc";
		ArrayList<T> list = (ArrayList<T>) baseDAO.find(hql, param, pageNo, pageSize);	
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean add(T t) {
		try {
			baseDAO.add(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public List<T> getByOtherId(String tableName, String where,  String param) {
		String hql = "from "+tableName+" t where t."+where+"="+param+" order by t.id desc ";
		List<T> list = baseDAO.findHql(hql, null);
		return list;
	}
	
}
