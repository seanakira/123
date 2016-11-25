package com.cts.localtour.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.viewModel.SimpleSettlementViewModel;

@SuppressWarnings("rawtypes")
@Service
public class SettlementService extends BaseService{
	@Autowired
	private SimpleSettlementViewModel simpleSettlementViewModel;
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleSettlementViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			/*这里需要做数据权限*/
			ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) this.getAllByString("LocalTourTable", "status=8");
			return simpleSettlementViewModel.getAllSimpleSettlementViewModel(localTourTables);
		}else{
			ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) this.getAllByString("LocalTourTable", "(tourNo like '%"+key+"%' or tourName like '%"+key+"%') and status=8");
			return simpleSettlementViewModel.getAllSimpleSettlementViewModel(localTourTables);
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		/*这里需要做数据权限*/
		if(key.equals("")){
			return this.getCountsByParam("LocalTourTable", "status=8", null);
		}else{
			return this.getCountsByParam("LocalTourTable", "(tourNo like '%"+key+"%' or tourName like '%"+key+"%') and status=8", null);
		}
	}

	public void okSettlement(int tourId) {
		if(((LocalTourTable)this.getById("LocalTourTable", tourId)).getStatus()==8){
			this.updateByString("LocalTourTable", "status=9", "id=?", tourId);
		}
	}

	public void cancelSettlement(int tourId) {
		if(((LocalTourTable)this.getById("LocalTourTable", tourId)).getStatus()==8){
			this.updateByString("LocalTourTable", "status=7", "id=?", tourId);
		}
	}

	public int checkStatusSettlement(int tourId) {
		if(((LocalTourTable)this.getById("LocalTourTable", tourId)).getStatus()==9){
			return 1;
		}
		return 0;
	}
}
