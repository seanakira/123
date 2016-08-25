package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.SimplPayViewModel;

@SuppressWarnings("rawtypes")
@Service
public class PayService extends BaseService{
	
	@SuppressWarnings("unchecked")
	public ArrayList<SimplPayViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "status>=2 and status<=5", null, page, maxResults);
			return setMd(localTours);
		}else{
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "tourNO like %"+key+"% or tourName like %"+key+"% and status>=2 and status<=5", null, page, maxResults);
			return setMd(localTours);
		}
	}

	@SuppressWarnings("unchecked")
	private ArrayList<SimplPayViewModel> setMd(ArrayList<LocalTourTable> localTours) {
		ArrayList<SimplPayViewModel> simplPayViewModels = new ArrayList<SimplPayViewModel>();
		for (int i = 0; i < localTours.size(); i++) {
			SimplPayViewModel simplPayViewModel = new SimplPayViewModel();
			ArrayList<CostTable> costs = (ArrayList<CostTable>) this.getAllByString("CostTable", "tourId=?", localTours.get(i).getId());
			float costSum = 0;
			float remittanceSum = 0;
			for (int j = 0; j < costs.size(); j++) {
				costSum =  costSum + (float)(costs.get(j).getCost()*costs.get(j).getCount()*costs.get(j).getDays());
				remittanceSum = remittanceSum + costs.get(j).getRealCost();
			}
			simplPayViewModel.setCost(costSum);
			simplPayViewModel.setLocalTourTable(localTours.get(i));
			ArrayList<SimplPayViewModel> loanTables = (ArrayList<SimplPayViewModel>) this.getSumByColumnName("LoanTable", "loanAmount", "sumCache","tourId=?", localTours.get(i).getId());
			simplPayViewModel.setLoan(loanTables.get(0)==null?0:loanTables.get(0).getSumCache());
			simplPayViewModel.setRemittance(remittanceSum);
			simplPayViewModel.setRealName(((UserTable) this.getById("UserTable", localTours.get(i).getUserId())).getRealName());
			if(localTours.get(i).getStatus()==2){
				simplPayViewModel.setStatus("已报送");
			}else if(localTours.get(i).getStatus()==3){
				simplPayViewModel.setStatus("可借款");
			}else if(localTours.get(i).getStatus()==4){
				simplPayViewModel.setStatus("进行中");
			}else if(localTours.get(i).getStatus()==5){
				simplPayViewModel.setStatus("已结束");
			}
			simplPayViewModels.add(simplPayViewModel);
		}
		return simplPayViewModels;
	}
	
	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("LocalTourTable");
		}else{
			String where = "tourNO like :tourNO or tourName like :tourName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("tourNO", "%"+key+"%");
			param.put("tourName", "%"+key+"%");
			return this.getCountsByParam("LocalTourTable", where, param);
		}
	}

	
}
