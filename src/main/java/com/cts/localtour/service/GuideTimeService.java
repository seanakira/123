package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.GuideTable;
import com.cts.localtour.entity.GuideTimeTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.GuideTimeViewModel;

@SuppressWarnings("rawtypes")
@Service
public class GuideTimeService extends BaseService{

	@SuppressWarnings("unchecked")
	public ArrayList<GuideTimeViewModel> getAll() {
		ArrayList<GuideTimeViewModel> guideTimes = new ArrayList<GuideTimeViewModel>();
		ArrayList<GuideTable> guides = (ArrayList<GuideTable>) this.getAllByString("GuideTable", "enable=?", true);
		for (int i = 0; i < guides.size(); i++) {
			GuideTimeViewModel guideTime = new GuideTimeViewModel();
			int guideId = guides.get(i).getId();
			Hashtable<String, GuideTimeTable> tourInfo = new Hashtable<String, GuideTimeTable>();
			ArrayList<GuideTimeTable> gTimes = (ArrayList<GuideTimeTable>) this.getAllByStringOrderBy("GuideTimeTable", "guideId=?", "startTime asc", guideId);
			for (int j = 0; j < gTimes.size(); j++) {
				if(gTimes.get(j).getTourId()!=null){
					int tourId = gTimes.get(j).getTourId();
					LocalTourTable tour = (LocalTourTable)this.getById("LocalTourTable", tourId);
					tourInfo.put(tour.getTourNo()+"  "+tour.getTourName(), gTimes.get(j));
				}else{
					tourInfo.put(""+j, gTimes.get(j));
				}
			}
			
			guideTime.setGuideId(guideId);
			guideTime.setRealName(((UserTable)this.getById("UserTable", guides.get(i).getUserId())).getRealName());
			guideTime.setTourInfo(tourInfo);
			guideTimes.add(guideTime);
		}
		return guideTimes;
	}

}
