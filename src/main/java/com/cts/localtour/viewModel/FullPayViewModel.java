package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;
@Component
public class FullPayViewModel {
	private ArrayList<CostViewModel> costs;
	private ArrayList<LoanViewModel> loans;
	private ArrayList<CostTable> costTables;
	private ArrayList<LoanTable> loanTables;
	private ArrayList<ChangeCostViewModel> changeCosts;
	private ArrayList<ChangeIncomeViewModel> changeIncomes;
	private ArrayList<ChangeCostTable> changeCostTables;
	private ArrayList<ChangeIncomeTable> changeIncomeTables;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private UserService userService;
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
			cost.setContentName(((SupplierContentTable)baseService.getById("SupplierContentTable", costTable.getContentId())).getContentName());
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
			changeCost.setContentName(((SupplierContentTable)baseService.getById("SupplierContentTable", changeCostTable.getContentId())).getContentName());
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
}
