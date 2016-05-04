package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.BusinessTypeTable;

@SuppressWarnings("rawtypes")
@Service
public class BusinessTypeService extends BaseService{

	@SuppressWarnings("unchecked")
	public ArrayList<BusinessTypeTable> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<BusinessTypeTable> businessTypes = this.getAllByTableName("BusinessTypeTable", page, maxResults);
			return businessTypes;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("businessTypeName", key);
			ArrayList<BusinessTypeTable> businessTypes = this.getAllByParam("BusinessTypeTable", "businessTypeName=:businessTypeName", param, page, maxResults);
			return businessTypes;
		}
	}

}
