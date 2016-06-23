package com.cts.localtour.viewModel;

import com.cts.localtour.entity.GuideTable;
import com.cts.localtour.entity.UserTable;

public class GuideViewModel {
	private UserTable userTable;
	private GuideTable guideTable;
	public UserTable getUserTable() {
		return userTable;
	}
	public void setUserTable(UserTable userTable) {
		this.userTable = userTable;
	}
	public GuideTable getGuideTable() {
		return guideTable;
	}
	public void setGuideTable(GuideTable guideTable) {
		this.guideTable = guideTable;
	}
	
}
