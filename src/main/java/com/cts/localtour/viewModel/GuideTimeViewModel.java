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
	@SuppressWarnings("unchecked")
	public ArrayList<GuideTimeViewModel> getAllGuideTimeViewModel(int tourId){
		ArrayList<GuideTimeTable> guideTimeTables = (ArrayList<GuideTimeTable>)baseService.getAllByString("GuideTimeTable", "tourId=?", tourId);
		ArrayList<GuideTimeViewModel> guideTimes = new ArrayList<GuideTimeViewModel>();
		for (int i = 0; i < guideTimeTables.size(); i++) {
			GuideTimeViewModel guideTime = new GuideTimeViewModel();
			guideTime.setRealName(((UserTable)baseService.getById("UserTable", ((GuideTable)baseService.getById("GuideTable", guideTimeTables.get(i).getGuideId())).getUserId())).getRealName());
			guideTime.setGuideId(guideTimeTables.get(i).getGuideId());
			guideTimes.add(guideTime);
		}
		return guideTimes;
	}
}
