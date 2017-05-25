package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.SimplPayViewModel;

@SuppressWarnings("rawtypes")
@Service
public class PayService extends BaseService{
	@Autowired
	private SimplPayViewModel simplPayViewModel;
	@Autowired
	private FullPayViewModel fullPayViewModel;
	@Autowired
	private LocalTourService localTourService;
	@SuppressWarnings("unchecked")
	public ArrayList<SimplPayViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "status>=2 and status<=10", null, page, maxResults);
			return simplPayViewModel.getAllSimplPayViewModel(localTours);
		}else{
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "(tourNO like '%"+key+"%' or tourName like '%"+key+"%') and status>=2 and status<=10", null, page, maxResults);
			return simplPayViewModel.getAllSimplPayViewModel(localTours);
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsByParam("LocalTourTable", "status>=2 and status<=10", null);
		}else{
			return this.getCountsByParam("LocalTourTable", "(tourNO like '%"+key+"%' or tourName like '%"+key+"%') and status>=2 and status<=10", null);
		}
	}

	@SuppressWarnings("unchecked")
	public int updatePay(FullPayViewModel full) {
		ArrayList<CostTable> costTables = full.getCostTables();
		ArrayList<LoanTable> loanTables = full.getLoanTables();
		ArrayList<ChangeCostTable> changeCostTables = full.getChangeCostTables();
		ArrayList<ReimbursementCostTable> reimbursementCostTables = full.getReimbursementCostTables();
		ArrayList<CostTable> costCache =new ArrayList<CostTable>();
		ArrayList<ChangeCostTable> changeCostCache = new ArrayList<ChangeCostTable>();
		BigDecimal maxLoan = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		/*计算最大可借款额*/
		for (int i = 0; i < costTables.size(); i++) {
			if(costTables.get(i).isLend()&&costTables.get(i).isRemittanced()){
				return -1;
			}else{
				CostTable costTable = (CostTable) this.getById("CostTable", costTables.get(i).getId());
				if(!costTables.get(i).isLend()){
					if(costTables.get(i).getRealCost().compareTo(costTable.getCost().multiply(new BigDecimal(costTable.getCount())).multiply(new BigDecimal(costTable.getDays())))==1){
						return -2;
					}else{
						costTable.setRealCost(costTables.get(i).getRealCost());
						costTable.setRemark(costTables.get(i).getRemark());
						costTable.setBill(costTables.get(i).isBill());
						costTable.setRemittanced(costTables.get(i).isRemittanced());
						this.update(costTable);
					}
				}else{
					maxLoan = maxLoan.add(costTable.getCost().multiply(new BigDecimal(costTable.getCount()).multiply(new BigDecimal(costTable.getDays()))));
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
					if(changeCostTables.get(i).getRealCost().compareTo(changeCostTable.getCost().multiply(new BigDecimal(changeCostTable.getCount())).multiply(new BigDecimal(changeCostTable.getDays())))>1){
						return -2;
					}else{
						changeCostTable.setRealCost(changeCostTables.get(i).getRealCost());
						changeCostTable.setRemark(changeCostTables.get(i).getRemark());
						changeCostTable.setBill(changeCostTables.get(i).isBill());
						changeCostTable.setRemittanced(changeCostTables.get(i).isRemittanced());
						this.update(changeCostTable);
					}
				}else{
					maxLoan = maxLoan.add(changeCostTable.getCost().multiply(new BigDecimal(changeCostTable.getCount())).multiply(new BigDecimal(changeCostTable.getDays())));
					changeCostCache.add(changeCostTable);
					changeCostCache.add(changeCostTables.get(i));
				}
			}
		}
		
		for (ReimbursementCostTable reimbursementCostTable : reimbursementCostTables) {
			if(reimbursementCostTable.isBill()&&reimbursementCostTable.isRemittanced()){
				return -1;
			}else{
				this.updateByString("ReimbursementCostTable", "bill=?, remittanced=?", "id=?", reimbursementCostTable.isBill(), reimbursementCostTable.isRemittanced(), reimbursementCostTable.getId());
			}
		}
		/*计算借款额*/
		for (int i = 0; i < loanTables.size(); i++) {
			if(loanTables.get(i).getLoanAmount()==null){
				total = total.add(((LoanTable)this.getById("LoanTable", loanTables.get(i).getId())).getLoanAmount());
			}else{
				total = total.add(loanTables.get(i).getLoanAmount());
			}
		}
		if(total.compareTo(maxLoan)==1){
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
				if(loanTables.get(i).getId()==null){
					this.add(loanTables.get(i));
				}else{
					this.updateByString("LoanTable", "lended=?", "id=?", loanTables.get(i).isLended(),loanTables.get(i).getId());
				}
			}
			LocalTourTable tour = (LocalTourTable)this.getById("LocalTourTable", full.getTourId());
			if(tour.getStatus()==2){
				if(new Date().getTime()>=tour.getEndTime().getTime()){
					this.updateByString("LocalTourTable", "status=5", "id=?", full.getTourId());
				}else{
					this.updateByString("LocalTourTable", "status=3", "id=?", full.getTourId());
				}
			}
			localTourService.sendMessageToMaker(costCache.size()!=0?costCache.get(0).getTourId():changeCostCache.size()!=0?changeCostCache.get(0).getTourId():loanTables.get(0).getTourId(), " 财务借款处理完毕，已可进行借款或付款申请");
		}
		return 1;
	}

	public FullPayViewModel findPay(int tourId) {
		return fullPayViewModel.getFullPayViewModel(tourId);
	}
}
