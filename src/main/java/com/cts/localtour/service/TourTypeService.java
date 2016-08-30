package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.TourTypeTable;
import com.cts.localtour.entity.TourTypeTable;
import com.cts.localtour.entity.TourTypeTable;

@SuppressWarnings("rawtypes")
@Service
public class TourTypeService extends BaseService{

	@SuppressWarnings("unchecked")
	public ArrayList<TourTypeTable> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<TourTypeTable> tourTypes = this.getAllByTableName("TourTypeTable", page, maxResults);
			return tourTypes;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("tourTypeName", "%"+key+"%");
			ArrayList<TourTypeTable> tourTypes = this.getAllByParam("TourTypeTable", "tourTypeName like :tourTypeName", param, page, maxResults);
			return tourTypes;
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("TourTypeTable");
		}else{
			String where = "tourTypeName like :tourTypeName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("tourTypeName", "%"+key+"%");
			return this.getCountsByParam("TourTypeTable", where, param);
		}
	}

	public void del(int id) {
		this.changeValue("TourTypeTable", "enable", "false", id);
	}
	
	public void recover(int id){
		this.changeValue("TourTypeTable", "enable", "true", id);
	}

	public void update(TourTypeTable tourType) {
		this.updateByParam("TourTypeTable", "tourTypeName=?", "id="+tourType.getId(), tourType.getTourTypeName());
	}

}
