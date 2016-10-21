package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.SupplierScopeTable;

@SuppressWarnings("rawtypes")
@Service
public class SupplierScopeService extends BaseService{
	@SuppressWarnings("unchecked")
	public ArrayList<SupplierScopeTable> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<SupplierScopeTable> supplierScopes = this.getAllByTableName("SupplierScopeTable", page, maxResults);
			return supplierScopes;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("supplierScopeName", "%"+key+"%");
			ArrayList<SupplierScopeTable> supplierScopes = this.getAllByParam("SupplierScopeTable", "supplierScopeName like :supplierScopeName", param, page, maxResults);
			return supplierScopes;
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("SupplierScopeTable");
		}else{
			String where = "supplierScopeName like :supplierScopeName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("supplierScopeName", "%"+key+"%");
			return this.getCountsByParam("SupplierScopeTable", where, param);
		}
	}

	public void del(int id) {
		this.changeValue("SupplierScopeTable", "enable", "false", id);
	}
	
	public void recover(int id){
		this.changeValue("SupplierScopeTable", "enable", "true", id);
	}

	public void update(SupplierScopeTable supplierScope) {
		this.updateByString("SupplierScopeTable", "supplierScopeName=?", "id="+supplierScope.getId(), supplierScope.getSupplierScopeName());
	}
}
