package com.cts.localtour.viewModel;

import java.util.Hashtable;

import com.cts.localtour.entity.GuideTimeTable;

public class GuideTimeViewModel {
	private String realName;
	private int guideId;
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
}
