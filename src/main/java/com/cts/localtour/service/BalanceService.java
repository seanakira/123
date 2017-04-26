package com.cts.localtour.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.viewModel.FullBalanceViewModel;
import com.cts.localtour.viewModel.SimpleBalanceViewModel;

@SuppressWarnings("rawtypes")
@Service
public class BalanceService extends BaseService{
	@Autowired
	private SimpleBalanceViewModel simpleBalanceViewModel;
	@Autowired
	private FullBalanceViewModel fullBalanceViewModel;
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleBalanceViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "status=8 and enable=true", null, page, maxResults);
			return simpleBalanceViewModel.getAllSimpleRevenueViewModel(localTours);
		}else{
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "(tourNO like '%"+key+"%' or tourName like '%"+key+"%') and status=8 and enable=true", null, page, maxResults);
			return simpleBalanceViewModel.getAllSimpleRevenueViewModel(localTours);
		}
	}
	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsByParam("LocalTourTable", "status=8", null);
		}else{
			return this.getCountsByParam("LocalTourTable", "(tourNO like '%"+key+"%' or tourName like '%"+key+"%') and status=8", null);
		}
	}
	public FullBalanceViewModel findBalance(int tourId) {
		return fullBalanceViewModel.getFullBalanceViewModel(tourId);
	}
	
	public void updateBalance(int tourId) {
		if(((LocalTourTable) this.getById("LocalTourTable", tourId)).getStatus()==8){
			this.updateByString("LocalTourTable", "status=9", "id=?", tourId);
		}
	}
	public void cancelBalance(int tourId) {
		if(((LocalTourTable) this.getById("LocalTourTable", tourId)).getStatus()==8){
			this.updateByString("LocalTourTable", "status=6", "id=?", tourId);
			this.updateByString("ReimbursementCostTable", "payStatus=?", "tourId=?", 0, tourId);
		}
	}

}
