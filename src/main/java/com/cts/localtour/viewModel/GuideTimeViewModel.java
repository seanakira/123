package com.cts.localtour.viewModel;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.GuideTable;
import com.cts.localtour.entity.GuideTimeTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;
@Component
public class GuideTimeViewModel {
	private String realName;
	private int guideId;
	private int userId;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	private Hashtable<String, GuideTimeTable> tourInfo;
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getGuideId() {
		return guideId;
	}
	public void setGuideId(int guideId) {
		this.guideId = guideId;
	}
	public Hashtable<String, GuideTimeTable> getTourInfo() {
		return tourInfo;
	}
	public void setTourInfo(Hashtable<String, GuideTimeTable> tourInfo) {
		this.tourInfo = tourInfo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<GuideTimeViewModel> getAllGuideTimeViewModel(int tourId){
		ArrayList<GuideTimeTable> guideTimeTables = (ArrayList<GuideTimeTable>)baseService.getAllByString("GuideTimeTable", "tourId=?", tourId);
		ArrayList<GuideTimeViewModel> guideTimes = new ArrayList<GuideTimeViewModel>();
		for (int i = 0; i < guideTimeTables.size(); i++) {
			GuideTimeViewModel guideTime = new GuideTimeViewModel();
			if(guideTimeTables.get(i).getGuideId()==0){
				/*清除了错误的数据*/
				baseService.delete(guideTimeTables.get(i));
				guideTime.setRealName("");
			}else{
				UserTable user = (UserTable)baseService.getById("UserTable", ((GuideTable)baseService.getById("GuideTable", guideTimeTables.get(i).getGuideId())).getUserId());
				guideTime.setRealName(user.getRealName());
				guideTime.setUserId(user.getId());
			}
			guideTime.setGuideId(guideTimeTables.get(i).getGuideId());
			guideTimes.add(guideTime);
		}
		return guideTimes;
	}
}
