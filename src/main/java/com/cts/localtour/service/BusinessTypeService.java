package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.BusinessTypeDAO;
import com.cts.localtour.entity.BusinessTypeTable;

@SuppressWarnings("rawtypes")
@Service
public class BusinessTypeService extends BaseService{
	@Autowired
	private BusinessTypeDAO businessTypeDAO;
	@SuppressWarnings("unchecked")
	public ArrayList<BusinessTypeTable> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<BusinessTypeTable> businessTypes = this.getAllByTableName("BusinessTypeTable", page, maxResults);
			return businessTypes;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("businessTypeName", "%"+key+"%");
			ArrayList<BusinessTypeTable> businessTypes = this.getAllByParam("BusinessTypeTable", "businessTypeName like :businessTypeName", param, page, maxResults);
			return businessTypes;
		}
	}

	public void del(int id) {
		this.changeValue("BusinessTypeTable","enable", "false", id);
	}
	
	public void recover(int id){
		this.changeValue("BusinessTypeTable","enable", "true", id);
	}

	public void update(BusinessTypeTable businessType) {
		this.updateByString("BusinessTypeTable", "businessTypeName=?", "id="+businessType.getId(), businessType.getBusinessTypeName());
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("BusinessTypeTable");
		}else{
			String where = "businessTypeName like :businessTypeName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("businessTypeName", "%"+key+"%");
			return this.getCountsByParam("BusinessTypeTable", where, param);
		}
	}

	
}
