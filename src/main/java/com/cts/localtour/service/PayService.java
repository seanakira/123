package com.cts.localtour.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.CostViewModel;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.LoanViewModel;
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
			simplPayViewModel.setWillPay(loan+remittanceSum);
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
		ArrayList<CostTable> costCache =new ArrayList<CostTable>();
		float maxLoan = 0;
		float total = 0;
		for (int i = 0; i < costTables.size(); i++) {
			if(costTables.get(i).isIsLend()&&costTables.get(i).isIsRemittance()){
				return -1;
			}else{
				CostTable costTable = (CostTable) this.getById("CostTable", costTables.get(i).getId());
				if(!costTables.get(i).isIsLend()){
					if(costTables.get(i).getRealCost()>costTable.getCost()*costTable.getCount()*costTable.getDays()){
						return -2;
					}else{
						costTable.setRealCost(costTables.get(i).getRealCost());
						costTable.setRemark(costTables.get(i).getRemark());
						if(costTables.get(i).isIsRemittance()){
							costTable.setIsRemittance(true);
						}
						this.update(costTable);
					}
				}else{
					maxLoan = maxLoan + costTable.getCost()*costTable.getCount()*costTable.getDays();
					costCache.add(costTable);
					costCache.add(costTables.get(i));
				}
			}
		}
		for (int i = 0; i < loanTables.size(); i++) {
			total = total + loanTables.get(i).getLoanAmount();
		}
		if(total>maxLoan){
			return -3;
		}else{
			for (int j = 0; j < costCache.size(); j+=2) {
				costCache.get(j).setRealCost(costCache.get(j+1).getRealCost());
				costCache.get(j).setRemark(costCache.get(j+1).getRemark());
				costCache.get(j).setIsLend(true);
				this.update(costCache.get(j));
			}
			for (int i = 0; i < loanTables.size(); i++) {
				this.merge(loanTables.get(i));
			}
		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	public FullPayViewModel findPay(int tourId) {
		FullPayViewModel full = new FullPayViewModel();
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) this.getAllByString("CostTable", "tourId=?", tourId);
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (int i = 0; i < costTables.size(); i++) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTables.get(i));
			cost.setContentName(((SupplierContentTable)this.getById("SupplierContentTable", costTables.get(i).getContentId())).getContentName());
			cost.setSupplierName(((SupplierTable)this.getById("SupplierTable", costTables.get(i).getSupplierId())).getSupplierName());
			cost.setBorrowUserName(((UserTable)this.getById("UserTable", costTables.get(i).getBorrowUserId())).getRealName());
			costs.add(cost);
		}
		full.setCosts(costs);
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=?", tourId);
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (int i = 0; i < loanTables.size(); i++) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTables.get(i));
			loan.setLenderRealName(((UserTable)this.getById("UserTable", loanTables.get(i).getLender())).getRealName());
			loans.add(loan);
		}
		full.setLoans(loans);
		return full;
	}
	
}
