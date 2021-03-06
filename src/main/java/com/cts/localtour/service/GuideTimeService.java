package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.GuideTable;
import com.cts.localtour.entity.GuideTimeTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.GuideTimeViewModel;
import com.cts.localtour.viewModel.GuideViewModel;

@SuppressWarnings("rawtypes")
@Service
public class GuideTimeService extends BaseService{

	@SuppressWarnings("unchecked")
	public ArrayList<GuideTimeViewModel> getAll(Date from ,Date to) {
		ArrayList<GuideTimeViewModel> guideTimes = new ArrayList<GuideTimeViewModel>();
		ArrayList<GuideTable> guides = (ArrayList<GuideTable>) this.getAllByString("GuideTable", "enable=?", true);
		for (int i = 0; i < guides.size(); i++) {
			GuideTimeViewModel guideTime = new GuideTimeViewModel();
			int guideId = guides.get(i).getId();
			Hashtable<String, GuideTimeTable> tourInfo = new Hashtable<String, GuideTimeTable>();
			ArrayList<GuideTimeTable> gTimes = (ArrayList<GuideTimeTable>) this.getAllByStringOrderBy("GuideTimeTable", "guideId=? and (startTime between ? and ? or endTime between ? and ?)", "startTime asc", guideId, from, to, from, to);
			for (int j = 0; j < gTimes.size(); j++) {
				if(gTimes.get(j).getTourId()!=null){
					int tourId = gTimes.get(j).getTourId();
					LocalTourTable tour = (LocalTourTable)this.getById("LocalTourTable", tourId);
					tourInfo.put(tour.getTourNo()+"  "+tour.getTourName(), gTimes.get(j));
				}else{
					if(gTimes.get(j).getRemark()==null||"".equals(gTimes.get(j).getRemark())){
						tourInfo.put(j+"", gTimes.get(j));
					}else{
						tourInfo.put("备注："+gTimes.get(j).getRemark(), gTimes.get(j));
					}
				}
			}
			guideTime.setGuideId(guideId);
			guideTime.setRealName(((UserTable)this.getById("UserTable", guides.get(i).getUserId())).getRealName());
			guideTime.setTourInfo(tourInfo);
			guideTimes.add(guideTime);
		}
		return guideTimes;
	}
/*新增检查*/
	@SuppressWarnings("unchecked")
	public boolean checkTime(int guideId, Date startTime, Date endTime) {
		ArrayList<GuideTimeTable> guideTimeTables = ((ArrayList<GuideTimeTable>) this.getAllByStringOrderByLimit("GuideTimeTable", "guideId=? and ((startTime>=? and startTime<=?) or (endTime>=? and endTime<=?))", "startTime asc",  1, guideId, startTime, endTime, startTime, endTime));
		if(guideTimeTables.size()==0){
			return true;
		}else{
			return false;
		}
		
	}
/*更新检查*/
	@SuppressWarnings("unchecked")
	public boolean checkTime(int id, int guideId, Date startTime, Date endTime) {
		ArrayList<GuideTimeTable> guideTimeTables = ((ArrayList<GuideTimeTable>) this.getAllByStringOrderByLimit("GuideTimeTable", "id<>? and guideId=? and ((startTime>=? and startTime<=?) or (endTime>=? and endTime<=?))", "startTime asc",  1, id, guideId, startTime, endTime, startTime, endTime));
		if(guideTimeTables.size()==0){
			return true;
		}else{
			return false;
		}
		
	}
/*空闲检查*/
	@SuppressWarnings("unchecked")
	public ArrayList<GuideViewModel> checkTime(Date startTime, Date endTime) {
		ArrayList<GuideTable> guides = (ArrayList<GuideTable>) this.getAllByString("GuideTable", "enable=?", true);
		ArrayList<GuideViewModel> guideViewModels = new ArrayList<GuideViewModel>();
		for (int i = 0; i < guides.size(); i++) {
			/*检查导游排团表是否有空*/
			/*ArrayList<GuideTimeTable> guideTimeTables = ((ArrayList<GuideTimeTable>) this.getAllByStringOrderByLimit("GuideTimeTable", "guideId=? and ((startTime>=? and startTime<=?) or (endTime>=? and endTime<=?))", "startTime asc",  1, guides.get(i).getId(), startTime, endTime, startTime, endTime));
			if(guideTimeTables.size()==0){*/
				GuideViewModel guideViewModel = new GuideViewModel();
				guideViewModel.setGuideTable(guides.get(i));
				UserTable user = (UserTable) this.getById("UserTable", guides.get(i).getUserId());
				user.setPwd("");
				guideViewModel.setUserTable(user);
				guideViewModels.add(guideViewModel);
			/*}*/
		}
		return guideViewModels;
	}
}
