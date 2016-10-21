package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.ChangeCostViewModel;
import com.cts.localtour.viewModel.CostViewModel;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.LoanViewModel;
import com.cts.localtour.viewModel.SimplPayViewModel;

@SuppressWarnings("rawtypes")
@Service
public class PayService extends BaseService{
	@Autowired
	private UserService userService;
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
			BigDecimal costSum = new BigDecimal(0.00);
			float remittanceSum = 0;
			float realRemittanceSum = 0;
			for (int j = 0; j < costs.size(); j++) {
				BigDecimal cost = new BigDecimal(costs.get(j).getCost());
				costSum =  costSum.add((cost.multiply(new BigDecimal(costs.get(j).getCount())).multiply(new BigDecimal(costs.get(j).getDays()))));
				remittanceSum = remittanceSum + costs.get(j).getRealCost();
				if(costs.get(j).isRemittanced()){
					realRemittanceSum = realRemittanceSum + costs.get(j).getRealCost();
				}
			}
			simplPayViewModel.setCost(costSum.floatValue());
			simplPayViewModel.setLocalTourTable(localTours.get(i));
			ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=?", localTours.get(i).getId());
			float loan = 0;
			float realLoan = 0;
			for (int j = 0; j < loanTables.size(); j++) {
				loan = loan + loanTables.get(j).getLoanAmount();
				if(loanTables.get(j).isLended()){
					realLoan = realLoan + loanTables.get(j).getLoanAmount();
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
		ArrayList<ChangeCostTable> changeCostTables = full.getChangeCostTables();
		ArrayList<CostTable> costCache =new ArrayList<CostTable>();
		ArrayList<ChangeCostTable> changeCostCache = new ArrayList<ChangeCostTable>();
		float maxLoan = 0;
		float total = 0;
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
						if(costTables.get(i).isRemittanced()){
							costTable.setRemittanced(true);
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
						if(changeCostTables.get(i).isRemittanced()){
							changeCostTable.setRemittanced(true);
						}
						this.update(changeCostTable);
					}
				}else{
					maxLoan = maxLoan + changeCostTable.getCost()*changeCostTable.getCount()*changeCostTable.getDays();
					changeCostCache.add(changeCostTable);
					changeCostCache.add(changeCostTables.get(i));
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
				costCache.get(j).setLend(true);
				this.update(costCache.get(j));
			}
			for (int j = 0; j < changeCostCache.size(); j+=2) {
				changeCostCache.get(j).setRealCost(changeCostCache.get(j+1).getRealCost());
				changeCostCache.get(j).setRemark(changeCostCache.get(j+1).getRemark());
				changeCostCache.get(j).setLend(true);
				this.update(changeCostCache.get(j));
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
			cost.setContentName(costTables.get(i).getContentId()==null||costTables.get(i).getContentId()==0?"":((SupplierContentTable)this.getById("SupplierContentTable", costTables.get(i).getContentId())).getContentName());
			cost.setSupplierName(((SupplierTable)this.getById("SupplierTable", costTables.get(i).getSupplierId())).getSupplierName());
			cost.setBorrowUserName(costTables.get(i).getBorrowUserId()==null||costTables.get(i).getBorrowUserId()==0?"":((UserTable)this.getById("UserTable", costTables.get(i).getBorrowUserId())).getRealName());
			if(costTables.get(i).isRemittanced()){
				cost.setPayStatus("已汇");
			}else{
				cost.setPayStatus(costTables.get(i).getPayStatus()==0?"可付":costTables.get(i).getPayStatus()==1?"待审":costTables.get(i).getPayStatus()==2?"待批":costTables.get(i).getPayStatus()==3?"已批准":"");
			}
			costs.add(cost);
		}
		full.setCosts(costs);
		
		ArrayList<ChangeCostTable> changeCostTables = (ArrayList<ChangeCostTable>) this.getAllByString("ChangeCostTable", "tourId=? and status=3", tourId);
		ArrayList<ChangeCostViewModel> changeCosts = new ArrayList<ChangeCostViewModel>();
		for (int i = 0; i < changeCostTables.size(); i++) {
			ChangeCostViewModel changeCost = new ChangeCostViewModel();
			changeCost.setCostTable(changeCostTables.get(i));
			changeCost.setContentName(changeCostTables.get(i).getContentId()==null||changeCostTables.get(i).getContentId()==0?"":((SupplierContentTable)this.getById("SupplierContentTable", changeCostTables.get(i).getContentId())).getContentName());
			changeCost.setSupplierName(((SupplierTable)this.getById("SupplierTable", changeCostTables.get(i).getSupplierId())).getSupplierName());
			changeCost.setBorrowUserName(changeCostTables.get(i).getBorrowUserId()==null||changeCostTables.get(i).getBorrowUserId()==0?"":((UserTable)this.getById("UserTable", changeCostTables.get(i).getBorrowUserId())).getRealName());
			if(changeCostTables.get(i).isRemittanced()){
				changeCost.setPayStatus("已汇");
			}else{
				changeCost.setPayStatus(changeCostTables.get(i).getPayStatus()==0?"可付":changeCostTables.get(i).getPayStatus()==1?"待审":changeCostTables.get(i).getPayStatus()==2?"待批":changeCostTables.get(i).getPayStatus()==3?"已批准":"");
			}
			changeCosts.add(changeCost);
		}
		full.setChangeCosts(changeCosts);
		
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=?", tourId);
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (int i = 0; i < loanTables.size(); i++) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTables.get(i));
			loan.setLenderRealName(((UserTable)this.getById("UserTable", loanTables.get(i).getLenderId())).getRealName());
			if(loanTables.get(i).isLended()){
				loan.setStatus("已借出");
			}else{
				if(loanTables.get(i).getStatus()==0){
					loan.setStatus("新建");
				}else if(loanTables.get(i).getStatus()==1){
					loan.setStatus("可借");
				}else if(loanTables.get(i).getStatus()==2){
					loan.setStatus("待审核");
				}else if(loanTables.get(i).getStatus()==3){
					loan.setStatus("待批准");
				}else if(loanTables.get(i).getStatus()==4){
					loan.setStatus("已批准");
				}
			}
			loans.add(loan);
		}
		full.setLoans(loans);
		return full;
	}
}
