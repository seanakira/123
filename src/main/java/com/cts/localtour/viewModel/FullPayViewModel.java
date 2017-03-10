package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;
@Component
public class FullPayViewModel {
	private int tourId;
	private ArrayList<CostViewModel> costs;
	private ArrayList<LoanViewModel> loans;
	private ArrayList<CostTable> costTables;
	private ArrayList<LoanTable> loanTables;
	private ArrayList<ChangeCostViewModel> changeCosts;
	private ArrayList<ChangeIncomeViewModel> changeIncomes;
	private ArrayList<ChangeCostTable> changeCostTables;
	private ArrayList<ReimbursementCostTable> reimbursementCostTables;
	private ArrayList<ReimbursementCostViewModel> reimbursementCosts;
	private ArrayList<ChangeIncomeTable> changeIncomeTables;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private UserService userService;
	@Autowired
	private ReimbursementCostViewModel reimbursementCostViewModel;
	public ArrayList<CostViewModel> getCosts() {
		return costs;
	}
	public void setCosts(ArrayList<CostViewModel> costs) {
		this.costs = costs;
	}
	public ArrayList<LoanViewModel> getLoans() {
		return loans;
	}
	public void setLoans(ArrayList<LoanViewModel> loans) {
		this.loans = loans;
	}
	public ArrayList<CostTable> getCostTables() {
		return costTables;
	}
	public void setCostTables(ArrayList<CostTable> costTables) {
		this.costTables = costTables;
	}
	public ArrayList<LoanTable> getLoanTables() {
		return loanTables;
	}
	public void setLoanTables(ArrayList<LoanTable> loanTables) {
		this.loanTables = loanTables;
	}
	public ArrayList<ChangeCostViewModel> getChangeCosts() {
		return changeCosts;
	}
	public void setChangeCosts(ArrayList<ChangeCostViewModel> changeCosts) {
		this.changeCosts = changeCosts;
	}
	public ArrayList<ChangeIncomeViewModel> getChangeIncomes() {
		return changeIncomes;
	}
	public void setChangeIncomes(ArrayList<ChangeIncomeViewModel> changeIncomes) {
		this.changeIncomes = changeIncomes;
	}
	public ArrayList<ChangeCostTable> getChangeCostTables() {
		return changeCostTables;
	}
	public void setChangeCostTables(ArrayList<ChangeCostTable> changeCostTables) {
		this.changeCostTables = changeCostTables;
	}
	public ArrayList<ChangeIncomeTable> getChangeIncomeTables() {
		return changeIncomeTables;
	}
	public void setChangeIncomeTables(ArrayList<ChangeIncomeTable> changeIncomeTables) {
		this.changeIncomeTables = changeIncomeTables;
	}
	public ArrayList<ReimbursementCostTable> getReimbursementCostTables() {
		return reimbursementCostTables;
	}
	public void setReimbursementCostTables(ArrayList<ReimbursementCostTable> reimbursementCostTables) {
		this.reimbursementCostTables = reimbursementCostTables;
	}
	public ArrayList<ReimbursementCostViewModel> getReimbursementCosts() {
		return reimbursementCosts;
	}
	public void setReimbursementCosts(ArrayList<ReimbursementCostViewModel> reimbursementCosts) {
		this.reimbursementCosts = reimbursementCosts;
	}
	public int getTourId() {
		return tourId;
	}
	public void setTourId(int tourId) {
		this.tourId = tourId;
	}
	@SuppressWarnings("unchecked")
	public FullPayViewModel getPayApplicationViewModel(int tourId, int status){
		FullPayViewModel full = new FullPayViewModel();
		ArrayList<CostTable>  costTables;
		ArrayList<ChangeCostTable> changeCostTables;
		if(status==-1){
			costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "tourId=? and payStatus>1", tourId);
			changeCostTables = (ArrayList<ChangeCostTable>) baseService.getAllByString("ChangeCostTable", "tourId=? and payStatus>1", tourId);
		}else{
			costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "tourId=? and payStatus=?", tourId, status);
			changeCostTables = (ArrayList<ChangeCostTable>) baseService.getAllByString("ChangeCostTable", "tourId=? and payStatus=?", tourId, status);
		}
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (CostTable costTable : costTables) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTable);
			cost.setPayApplicationerRealName(userService.getUserRealName(costTable.getPayApplicationerId()));
			cost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", costTable.getSupplierId())).getSupplierName());
			cost.setContentName(costTable.getContentId()==null?"":((SupplierContentTable)baseService.getById("SupplierContentTable", costTable.getContentId())).getContentName());
			if(costTable.isRemittanced()){
				cost.setPayStatus("已汇");
			}else{
				if(costTable.getPayStatus()==0){
					cost.setPayStatus("可付");
				}else if(costTable.getPayStatus()==1){
					cost.setPayStatus("待审");
				}else if(costTable.getPayStatus()==2){
					cost.setPayStatus("待批");
				}else if(costTable.getPayStatus()==3){
					cost.setPayStatus("已批准");
				}
			}
			costs.add(cost);
		}
		ArrayList<ChangeCostViewModel> changeCosts = new ArrayList<ChangeCostViewModel>();
		for (ChangeCostTable changeCostTable : changeCostTables) {
			ChangeCostViewModel changeCost = new ChangeCostViewModel();
			changeCost.setCostTable(changeCostTable);
			changeCost.setPayApplicationerRealName(userService.getUserRealName(changeCostTable.getPayApplicationerId()));
			changeCost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", changeCostTable.getSupplierId())).getSupplierName());
			changeCost.setContentName(changeCostTable.getContentId()==null?"":((SupplierContentTable)baseService.getById("SupplierContentTable", changeCostTable.getContentId())).getContentName());
			if(changeCostTable.isRemittanced()){
				changeCost.setPayStatus("已汇");
			}else{
				if(changeCostTable.getPayStatus()==0){
					changeCost.setPayStatus("可付");
				}else if(changeCostTable.getPayStatus()==1){
					changeCost.setPayStatus("待审");
				}else if(changeCostTable.getPayStatus()==2){
					changeCost.setPayStatus("待批");
				}else if(changeCostTable.getPayStatus()==3){
					changeCost.setPayStatus("已批准");
				}
			}
			changeCosts.add(changeCost);
		}
		full.setCosts(costs);
		full.setChangeCosts(changeCosts);
		return full;
	}
	
	@SuppressWarnings("unchecked")
	public FullPayViewModel getFullPayViewModel (int tourId){
		FullPayViewModel full = new FullPayViewModel();
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "tourId=?", tourId);
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (CostTable costTable : costTables) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTable);
			cost.setContentName(costTable.getContentId()==null||costTable.getContentId()==0?"":((SupplierContentTable)baseService.getById("SupplierContentTable", costTable.getContentId())).getContentName());
			cost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", costTable.getSupplierId())).getSupplierName());
			cost.setBorrowUserName(userService.getUserRealName(costTable.getBorrowUserId()));
			if(costTable.isRemittanced()){
				cost.setPayStatus("已汇");
			}else if(costTable.isLend()){
				cost.setPayStatus("借款");
			}else if(costTable.isBill()){
				cost.setPayStatus("挂账");
			}else{
				cost.setPayStatus(costTable.getPayStatus()==0?"可付":costTable.getPayStatus()==1?"待审":costTable.getPayStatus()==2?"待批":costTable.getPayStatus()==3?"已批准":"");
			}
			costs.add(cost);
		}
		full.setCosts(costs);
		
		ArrayList<ChangeCostTable> changeCostTables = (ArrayList<ChangeCostTable>) baseService.getAllByString("ChangeCostTable", "tourId=? and status=3", tourId);
		ArrayList<ChangeCostViewModel> changeCosts = new ArrayList<ChangeCostViewModel>();
		for (int i = 0; i < changeCostTables.size(); i++) {
			ChangeCostViewModel changeCost = new ChangeCostViewModel();
			changeCost.setCostTable(changeCostTables.get(i));
			changeCost.setContentName(changeCostTables.get(i).getContentId()==null||changeCostTables.get(i).getContentId()==0?"":((SupplierContentTable)baseService.getById("SupplierContentTable", changeCostTables.get(i).getContentId())).getContentName());
			changeCost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", changeCostTables.get(i).getSupplierId())).getSupplierName());
			changeCost.setBorrowUserName(changeCostTables.get(i).getBorrowUserId()==null||changeCostTables.get(i).getBorrowUserId()==0?"":((UserTable)baseService.getById("UserTable", changeCostTables.get(i).getBorrowUserId())).getRealName());
			if(changeCostTables.get(i).isRemittanced()){
				changeCost.setPayStatus("已汇");
			}else if(changeCostTables.get(i).isLend()){
				changeCost.setPayStatus("借款");
			}else if(changeCostTables.get(i).isBill()){
				changeCost.setPayStatus("挂账");
			}else{
				changeCost.setPayStatus(changeCostTables.get(i).getPayStatus()==0?"可付":changeCostTables.get(i).getPayStatus()==1?"待审":changeCostTables.get(i).getPayStatus()==2?"待批":changeCostTables.get(i).getPayStatus()==3?"已批准":"");
			}
			changeCosts.add(changeCost);
		}
		full.setChangeCosts(changeCosts);
		
		full.setReimbursementCosts(reimbursementCostViewModel.getAllReimbursementCostViewModel(tourId, 1));
		
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) baseService.getAllByString("LoanTable", "tourId=?", tourId);
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (int i = 0; i < loanTables.size(); i++) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTables.get(i));
			loan.setLenderRealName(((UserTable)baseService.getById("UserTable", loanTables.get(i).getLenderId())).getRealName());
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
