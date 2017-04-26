package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.ReimbursementIncomeTable;
import com.cts.localtour.entity.ReimbursementTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;
@Component
public class FullReimbursementViewModel {
	private ArrayList<CostViewModel> costs;
	private ArrayList<LoanViewModel> loans;
	private ArrayList<ChangeCostViewModel> changeCosts;
	private ArrayList<CostTable> costTables;
	private ArrayList<ChangeCostTable> changeCostTables;
	private ReimbursementTable reimbursementTable;
	private ArrayList<ReimbursementCostTable> reimbursementCostTables;
	private ArrayList<ReimbursementCostTable> newReimbursementCostTables;
	private ArrayList<ReimbursementCostViewModel> reimbursementCosts;
	private ArrayList<IncomeViewModel> incomes;
	private ArrayList<ChangeIncomeViewModel> changeIncomes;
	private ArrayList<ReimbursementIncomeTable> reimbursementIncomeTables;
	private ArrayList<ReimbursementIncomeViewModel> reimbursementIncomes;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private UserService userService;
	@Autowired
	private ReimbursementCostViewModel reimbursementCostViewModel;
	@Autowired
	private IncomeViewModel incomeViewModel;
	@Autowired
	private ChangeIncomeViewModel changeIncomeViewModel;
	@Autowired
	private ReimbursementIncomeViewModel reimbursementIncomeViewModel;
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
	public ArrayList<ChangeCostViewModel> getChangeCosts() {
		return changeCosts;
	}
	public void setChangeCosts(ArrayList<ChangeCostViewModel> changeCosts) {
		this.changeCosts = changeCosts;
	}
	public ArrayList<ChangeCostTable> getChangeCostTables() {
		return changeCostTables;
	}
	public void setChangeCostTables(ArrayList<ChangeCostTable> changeCostTables) {
		this.changeCostTables = changeCostTables;
	}
	public ReimbursementTable getReimbursementTable() {
		return reimbursementTable;
	}
	public void setReimbursementTable(ReimbursementTable reimbursementTable) {
		this.reimbursementTable = reimbursementTable;
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
	public ArrayList<ReimbursementCostTable> getNewReimbursementCostTables() {
		return newReimbursementCostTables;
	}
	public void setNewReimbursementCostTables(ArrayList<ReimbursementCostTable> newReimbursementCostTables) {
		this.newReimbursementCostTables = newReimbursementCostTables;
	}
	public ArrayList<IncomeViewModel> getIncomes() {
		return incomes;
	}
	public void setIncomes(ArrayList<IncomeViewModel> incomes) {
		this.incomes = incomes;
	}
	public ArrayList<ChangeIncomeViewModel> getChangeIncomes() {
		return changeIncomes;
	}
	public void setChangeIncomes(ArrayList<ChangeIncomeViewModel> changeIncomes) {
		this.changeIncomes = changeIncomes;
	}
	public ArrayList<ReimbursementIncomeTable> getReimbursementIncomeTables() {
		return reimbursementIncomeTables;
	}
	public void setReimbursementIncomeTables(ArrayList<ReimbursementIncomeTable> reimbursementIncomeTables) {
		this.reimbursementIncomeTables = reimbursementIncomeTables;
	}
	public ArrayList<ReimbursementIncomeViewModel> getReimbursementIncomes() {
		return reimbursementIncomes;
	}
	public void setReimbursementIncomes(ArrayList<ReimbursementIncomeViewModel> reimbursementIncomes) {
		this.reimbursementIncomes = reimbursementIncomes;
	}
	@SuppressWarnings("unchecked")
	public FullReimbursementViewModel getFullReimbursementViewModel(int tourId, int payStatus){
		FullReimbursementViewModel full = new FullReimbursementViewModel();
		ArrayList<CostTable>  costTables;
		ArrayList<ChangeCostTable> changeCostTables;
		if(payStatus==-1){
			costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "tourId=? and payStatus>1", tourId);
			changeCostTables = (ArrayList<ChangeCostTable>) baseService.getAllByString("ChangeCostTable", "tourId=? and payStatus>1", tourId);
		}else{
			costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "tourId=? and payStatus=?", tourId, payStatus);
			changeCostTables = (ArrayList<ChangeCostTable>) baseService.getAllByString("ChangeCostTable", "tourId=? and payStatus=?", tourId, payStatus);
		}
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (CostTable costTable : costTables) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTable);
			cost.setPayApplicationerRealName(userService.getUserRealName(costTable.getPayApplicationerId()));
			cost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", costTable.getSupplierId())).getSupplierName());
			cost.setContentName(((SupplierContentTable)baseService.getById("SupplierContentTable", costTable.getContentId())).getContentName());
			if(costTable.isRemittanced()){
				cost.setPayStatus("�ѻ�");
			}else{
				if(costTable.getPayStatus()==0){
					cost.setPayStatus("�ɸ�");
				}else if(costTable.getPayStatus()==1){
					cost.setPayStatus("����");
				}else if(costTable.getPayStatus()==2){
					cost.setPayStatus("����");
				}else if(costTable.getPayStatus()==3){
					cost.setPayStatus("����׼");
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
				changeCost.setPayStatus("�ѻ�");
			}else{
				if(changeCostTable.getPayStatus()==0){
					changeCost.setPayStatus("�ɸ�");
				}else if(changeCostTable.getPayStatus()==1){
					changeCost.setPayStatus("����");
				}else if(changeCostTable.getPayStatus()==2){
					changeCost.setPayStatus("����");
				}else if(changeCostTable.getPayStatus()==3){
					changeCost.setPayStatus("����׼");
				}
			}
			changeCosts.add(changeCost);
		}
		full.setCosts(costs);
		full.setChangeCosts(changeCosts);
		return full;
	}
	
	@SuppressWarnings("unchecked")
	public FullReimbursementViewModel getFullReimbursementViewModel (int tourId){
		FullReimbursementViewModel full = new FullReimbursementViewModel();
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "tourId=? and (payStatus=3 or lend=true or bill=true)", tourId);
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (int i = 0; i < costTables.size(); i++) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTables.get(i));
			cost.setContentName(costTables.get(i).getContentId()==null||costTables.get(i).getContentId()==0?"":((SupplierContentTable)baseService.getById("SupplierContentTable", costTables.get(i).getContentId())).getContentName());
			cost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", costTables.get(i).getSupplierId())).getSupplierName());
			cost.setBorrowUserName(costTables.get(i).getBorrowUserId()==null||costTables.get(i).getBorrowUserId()==0?"":((UserTable)baseService.getById("UserTable", costTables.get(i).getBorrowUserId())).getRealName());
			if(costTables.get(i).isRemittanced()){
				cost.setPayStatus("�ѻ�");
			}else if(costTables.get(i).isLend()){
				cost.setPayStatus("���");
			}else if(costTables.get(i).isBill()){
				cost.setPayStatus("����");
			}else{
				cost.setPayStatus(costTables.get(i).getPayStatus()==0?"�ɸ�":costTables.get(i).getPayStatus()==1?"����":costTables.get(i).getPayStatus()==2?"����":costTables.get(i).getPayStatus()==3?"����׼":"");
			}
			costs.add(cost);
		}
		full.setCosts(costs);
		
		ArrayList<ChangeCostTable> changeCostTables = (ArrayList<ChangeCostTable>) baseService.getAllByString("ChangeCostTable", "tourId=? and status=3 and (payStatus=3 or lend=true or bill=true)", tourId);
		ArrayList<ChangeCostViewModel> changeCosts = new ArrayList<ChangeCostViewModel>();
		for (int i = 0; i < changeCostTables.size(); i++) {
			ChangeCostViewModel changeCost = new ChangeCostViewModel();
			changeCost.setCostTable(changeCostTables.get(i));
			changeCost.setContentName(changeCostTables.get(i).getContentId()==null||changeCostTables.get(i).getContentId()==0?"":((SupplierContentTable)baseService.getById("SupplierContentTable", changeCostTables.get(i).getContentId())).getContentName());
			changeCost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", changeCostTables.get(i).getSupplierId())).getSupplierName());
			changeCost.setBorrowUserName(changeCostTables.get(i).getBorrowUserId()==null||changeCostTables.get(i).getBorrowUserId()==0?"":((UserTable)baseService.getById("UserTable", changeCostTables.get(i).getBorrowUserId())).getRealName());
			if(changeCostTables.get(i).isRemittanced()){
				changeCost.setPayStatus("�ѻ�");
			}else if(changeCostTables.get(i).isLend()){
				changeCost.setPayStatus("���");
			}else if(changeCostTables.get(i).isBill()){
				changeCost.setPayStatus("����");
			}else{
				changeCost.setPayStatus(changeCostTables.get(i).getPayStatus()==0?"�ɸ�":changeCostTables.get(i).getPayStatus()==1?"����":changeCostTables.get(i).getPayStatus()==2?"����":changeCostTables.get(i).getPayStatus()==3?"����׼":"");
			}
			changeCosts.add(changeCost);
		}
		full.setChangeCosts(changeCosts);
		
		full.setReimbursementCosts(reimbursementCostViewModel.getAllReimbursementCostViewModel(tourId ,0));
		
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) baseService.getAllByString("LoanTable", "tourId=? and status=4", tourId);
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (int i = 0; i < loanTables.size(); i++) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTables.get(i));
			loan.setLenderRealName(((UserTable)baseService.getById("UserTable", loanTables.get(i).getLenderId())).getRealName());
			if(loanTables.get(i).isLended()){
				loan.setStatus("�ѽ��");
			}else{
				if(loanTables.get(i).getStatus()==0){
					loan.setStatus("�½�");
				}else if(loanTables.get(i).getStatus()==1){
					loan.setStatus("�ɽ�");
				}else if(loanTables.get(i).getStatus()==2){
					loan.setStatus("�����");
				}else if(loanTables.get(i).getStatus()==3){
					loan.setStatus("����׼");
				}else if(loanTables.get(i).getStatus()==4){
					loan.setStatus("����׼");
				}
			}
			loans.add(loan);
		}
		full.setLoans(loans);
		
		full.setIncomes(incomeViewModel.getAllIncomeViewModel(tourId));
		
		full.setChangeIncomes(changeIncomeViewModel.getAllChangeIncomeViewModel(tourId));
		
		full.setReimbursementIncomes(reimbursementIncomeViewModel.getAllIncomeViewModel(tourId));
		
		ArrayList<ReimbursementTable> reimbursementTables = (ArrayList<ReimbursementTable>) baseService.getAllByString("ReimbursementTable", "tourId=?", tourId);
		full.setReimbursementTable(reimbursementTables.isEmpty()?null:reimbursementTables.get(0));
		return full;
	}
}
