package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
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
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "(tourNO like '%"+key+"%' or tourName like '%"+key+"%') and status>=2 and status<=5", null, page, maxResults);
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
			float realRemittanceSum = 0;
			for (int j = 0; j < costs.size(); j++) {
				costSum =  costSum + (float)(costs.get(j).getCost()*costs.get(j).getCount()*costs.get(j).getDays());
				remittanceSum = remittanceSum + costs.get(j).getRealCost();
				if(costs.get(i).isIsRemittance()){
					realRemittanceSum = realRemittanceSum + costs.get(j).getRealCost();
				}
			}
			simplPayViewModel.setCost(costSum);
			simplPayViewModel.setLocalTourTable(localTours.get(i));
			ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=?", localTours.get(i).getId());
			float loan = 0;
			float realLoan = 0;
			for (int j = 0; j < loanTables.size(); j++) {
				loan = loan + loanTables.get(i).getLoanAmount();
				if(loanTables.get(i).isIsLend()){
					realLoan = realLoan + loanTables.get(i).getLoanAmount();
				}
			}
			simplPayViewModel.setLoan(loan);
			simplPayViewModel.setRemittance(remittanceSum);
			simplPayViewModel.setRealName(((UserTable) this.getById("UserTable", localTours.get(i).getUserId())).getRealName());
			simplPayViewModel.setRealPay(realRemittanceSum+realLoan);
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
	
}
