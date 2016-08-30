package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.RegionTable;

@SuppressWarnings("rawtypes")
@Service
public class RegionService extends BaseService{
	@SuppressWarnings("unchecked")
	public ArrayList<RegionTable> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<RegionTable> regions = this.getAllByTableName("RegionTable", page, maxResults);
			return regions;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("regionName", "%"+key+"%");
			ArrayList<RegionTable> regions = this.getAllByParam("RegionTable", "regionName like :regionName", param, page, maxResults);
			return regions;
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("RegionTable");
		}else{
			String where = "regionName like :regionName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("regionName", "%"+key+"%");
			return this.getCountsByParam("RegionTable", where, param);
		}
	}

	public void del(int id) {
		this.changeValue("RegionTable", "enable", "false", id);
	}
	
	public void recover(int id){
		this.changeValue("RegionTable", "enable", "true", id);
	}

	public void update(RegionTable region) {
		this.updateByParam("RegionTable", "regionName=?", "id="+region.getId(), region.getRegionName());
	}
}
