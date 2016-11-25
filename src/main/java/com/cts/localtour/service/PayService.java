package com.cts.localtour.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.SimplPayViewModel;

@SuppressWarnings("rawtypes")
@Service
public class PayService extends BaseService{
	@Autowired
	private SimplPayViewModel simplPayViewModel;
	@Autowired
	private FullPayViewModel fullPayViewModel;
	@SuppressWarnings("unchecked")
	public ArrayList<SimplPayViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "status>=2 and status<=5", null, page, maxResults);
			return simplPayViewModel.getAllSimplPayViewModel(localTours);
		}else{
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "(tourNO like '%"+key+"%' or tourName like '%"+key+"%') and status>=2 and status<=5", null, page, maxResults);
			return simplPayViewModel.getAllSimplPayViewModel(localTours);
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsByParam("LocalTourTable", "status>=2 and status<=5", null);
		}else{
			return this.getCountsByParam("LocalTourTable", "(tourNO like '%"+key+"%' or tourName like '%"+key+"%') and status>=2 and status<=5", null);
		}
	}

	@SuppressWarnings("unchecked")
	public int updatePay(FullPayViewModel full) {
		ArrayList<CostTable> costTables = full.getCostTables();
		ArrayList<LoanTable> loanTables = full.getLoanTables();
		ArrayList<ChangeCostTable> changeCostTables = full.getChangeCostTables();
		ArrayList<CostTable> costCache =new ArrayList<CostTable>();
		ArrayList<ChangeCostTable> changeCostCache = new ArrayList<ChangeCostTable>();
		float maxLoan = 0;
		float total = 0;
		/*计算最大可借款额*/
		for (int i = 0; i < costTables.size(); i++) {
			if(costTables.get(i).isLend()&&costTables.get(i).isRemittanced()){
				return -1;
			}else{
				CostTable costTable = (CostTable) this.getById("CostTable", costTables.get(i).getId());
				if(!costTables.get(i).isLend()){
					if(costTables.get(i).getRealCost()>costTable.getCost()*costTable.getCount()*costTable.getDays()){
						return -2;
					}else{
						costTable.setRealCost(costTables.get(i).getRealCost());
						costTable.setRemark(costTables.get(i).getRemark());
						costTable.setBill(costTables.get(i).isBill());
						costTable.setRemittanced(costTables.get(i).isRemittanced());
						this.update(costTable);
					}
				}else{
					maxLoan = maxLoan + costTable.getCost()*costTable.getCount()*costTable.getDays();
					costCache.add(costTable);
					costCache.add(costTables.get(i));
				}
			}
		}
		for (int i = 0; i < changeCostTables.size(); i++) {
			if(changeCostTables.get(i).isLend()&&changeCostTables.get(i).isRemittanced()){
				return -1;
			}else{
				ChangeCostTable changeCostTable = (ChangeCostTable) this.getById("ChangeCostTable", changeCostTables.get(i).getId());
				if(!changeCostTables.get(i).isLend()){
					if(changeCostTables.get(i).getRealCost()>changeCostTable.getCost()*changeCostTable.getCount()*changeCostTable.getDays()){
						return -2;
					}else{
						changeCostTable.setRealCost(changeCostTables.get(i).getRealCost());
						changeCostTable.setRemark(changeCostTables.get(i).getRemark());
						changeCostTable.setBill(changeCostTables.get(i).isBill());
						changeCostTable.setRemittanced(changeCostTables.get(i).isRemittanced());
						this.update(changeCostTable);
					}
				}else{
					maxLoan = maxLoan + changeCostTable.getCost()*changeCostTable.getCount()*changeCostTable.getDays();
					changeCostCache.add(changeCostTable);
					changeCostCache.add(changeCostTables.get(i));
				}
			}
		}
		/*计算借款额*/
		for (int i = 0; i < loanTables.size(); i++) {
			total = total + loanTables.get(i).getLoanAmount();
		}
		if(total>maxLoan){
			return -3;
		}else{
			for (int j = 0; j < costCache.size(); j+=2) {
				costCache.get(j).setRealCost(costCache.get(j+1).getRealCost());
				costCache.get(j).setRemark(costCache.get(j+1).getRemark());
				costCache.get(j).setBill(false);
				costCache.get(j).setLend(true);
				this.update(costCache.get(j));
			}
			for (int j = 0; j < changeCostCache.size(); j+=2) {
				changeCostCache.get(j).setRealCost(changeCostCache.get(j+1).getRealCost());
				changeCostCache.get(j).setRemark(changeCostCache.get(j+1).getRemark());
				changeCostCache.get(j).setBill(false);
				changeCostCache.get(j).setLend(true);
				this.update(changeCostCache.get(j));
			}
			for (int i = 0; i < loanTables.size(); i++) {
				this.merge(loanTables.get(i));
			}
			int id = !costCache.isEmpty()?costCache.get(0).getTourId():!changeCostCache.isEmpty()?changeCostCache.get(0).getTourId():!loanTables.isEmpty()?loanTables.get(0).getTourId():0;
			if(id!=0){
				if(((LocalTourTable)this.getById("LocalTourTable", id)).getStatus()==2){
					this.updateByString("LocalTourTable", "status=3", "id=?", id);
				}
			}
		}
		return 1;
	}

	public FullPayViewModel findPay(int tourId) {
		return fullPayViewModel.getFullPayViewModel(tourId);
	}
}
