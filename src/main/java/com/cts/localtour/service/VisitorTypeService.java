package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.VisitorTypeTable;

@SuppressWarnings("rawtypes")
@Service
public class VisitorTypeService extends BaseService{

	@SuppressWarnings("unchecked")
	public ArrayList<VisitorTypeTable> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<VisitorTypeTable> visitorTypes = this.getAllByTableName("VisitorTypeTable", page, maxResults);
			return visitorTypes;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("visitorTypeName", "%"+key+"%");
			ArrayList<VisitorTypeTable> visitorTypes = this.getAllByParam("VisitorTypeTable", "visitorTypeName like :visitorTypeName", param, page, maxResults);
			return visitorTypes;
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("VisitorTypeTable");
		}else{
			String where = "visitorTypeName like :visitorTypeName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("visitorTypeName", "%"+key+"%");
			return this.getCountsByParam("VisitorTypeTable", where, param);
		}
	}

	public void del(int id) {
		this.changeValue("VisitorTypeTable", "enable", "false", id);
	}
	
	public void recover(int id){
		this.changeValue("VisitorTypeTable", "enable", "true", id);
	}

	public void update(VisitorTypeTable visitorType) {
		this.updateByString("VisitorTypeTable", "visitorTypeName=?", "id="+visitorType.getId(), visitorType.getVisitorTypeName());
	}

}
