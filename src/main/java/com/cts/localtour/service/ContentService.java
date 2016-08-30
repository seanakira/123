package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierScopeTable;
import com.cts.localtour.viewModel.ContentViewModel;

@SuppressWarnings("rawtypes")
@Service
public class ContentService extends BaseService{
	@SuppressWarnings("unchecked")
	public ArrayList<ContentViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<SupplierContentTable> contents = this.getAllByTableName("SupplierContentTable", page, maxResults);
			ArrayList<ContentViewModel> ContentViewModels = new ArrayList<ContentViewModel>();
			for (int i = 0; i < contents.size(); i++) {
				ContentViewModel contentViewModel = new ContentViewModel();
				contentViewModel.setContentTable(contents.get(i));
				String supplierScopeName = ((ArrayList<SupplierScopeTable>) this.getByWhere("SupplierScopeTable", "id", contents.get(i).getSupplierScopeId()+"")).get(0).getSupplierScopeName();
				contentViewModel.setSupplierScopeName(supplierScopeName);
				ContentViewModels.add(contentViewModel);
			}
			return ContentViewModels;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("contentName", "%"+key+"%");
			ArrayList<SupplierContentTable> contents = this.getAllByParam("SupplierContentTable", "contentName like :contentName", param, page, maxResults);
			ArrayList<ContentViewModel> ContentViewModels = new ArrayList<ContentViewModel>();
			for (int i = 0; i < contents.size(); i++) {
				ContentViewModel contentViewModel = new ContentViewModel();
				contentViewModel.setContentTable(contents.get(i));
				String supplierScopeName = ((ArrayList<SupplierScopeTable>) this.getByWhere("SupplierScopeTable", "id", contents.get(i).getSupplierScopeId()+"")).get(0).getSupplierScopeName();
				contentViewModel.setSupplierScopeName(supplierScopeName);
				ContentViewModels.add(contentViewModel);
			}
			return ContentViewModels;
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("SupplierContentTable");
		}else{
			String where = "contentName like :contentName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("contentName", "%"+key+"%");
			return this.getCountsByParam("SupplierContentTable", where, param);
		}
	}

	public void del(int id) {
		this.changeValue("SupplierContentTable", "enable", "false", id);
	}
	
	public void recover(int id){
		this.changeValue("SupplierContentTable", "enable", "true", id);
	}

	public void update(SupplierContentTable content) {
		this.updateByParam("SupplierContentTable", "contentName=?,supplierScopeId=?", "id="+content.getId(), content.getContentName(),content.getSupplierScopeId());
	}
}
