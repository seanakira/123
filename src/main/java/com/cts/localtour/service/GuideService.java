package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.GuideTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.GuideViewModel;

@Service
@SuppressWarnings("rawtypes")
public class GuideService extends BaseService{
	@SuppressWarnings("unchecked")
	public ArrayList<GuideViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<GuideTable> guides = this.getAllByTableName("GuideTable", page, maxResults);
			ArrayList<GuideViewModel> guideViewModels = new ArrayList<GuideViewModel>();
			for (int i = 0; i < guides.size(); i++) {
				GuideViewModel guideViewModel = new GuideViewModel();
				guideViewModel.setGuideTable(guides.get(i));
				guideViewModel.setUserTable((UserTable) this.getById("UserTable", guides.get(i).getUserId()));
				guideViewModels.add(guideViewModel);
			}
			return guideViewModels;
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("userName", "%"+key+"%");
			param.put("realName", "%"+key+"%");
			ArrayList<UserTable> users = this.getAllByParam("UserTable", "userName like :userName or realName like :realName", param, page, maxResults);
			ArrayList<GuideViewModel> guideViewModels = new ArrayList<GuideViewModel>();
			for (int i = 0; i < users.size(); i++) {
				GuideViewModel guideViewModel = new GuideViewModel();
				if(!this.getAllByString("GuideTable", "userId=?", users.get(i).getId()).isEmpty()){
					guideViewModel.setUserTable(users.get(i));
					guideViewModel.setGuideTable((GuideTable) this.getAllByString("GuideTable", "userId=?", users.get(i).getId()).get(0));
					guideViewModels.add(guideViewModel);
				}
			}
			return guideViewModels;
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("GuideTable");
		}else{
			String where = "(u.userName like :userName or u.realName like :realName) and u.id=g.userId";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("userName", "%"+key+"%");
			param.put("realName", "%"+key+"%");
			return this.getCountsByParam("UserTable u,GuideTable g", where, param);
		}
	}

	public void del(int id) {
		this.changeValue("GuideTable", "enable", "false", id);
	}
	
	public void recover(int id){
		this.changeValue("GuideTable", "enable", "true", id);
	}

	public void update(GuideTable guide) {
		this.updateByString("GuideTable", "userId=?", "id=?", guide.getUserId(), guide.getId());
	}

	public boolean exist(int userId) {
		if(this.getAllByString("GuideTable", "userId=?", userId).size()>0){
			return true;
		}else{
			return false;
		}
	}
}
