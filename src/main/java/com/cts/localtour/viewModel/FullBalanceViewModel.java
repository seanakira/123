package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.ReimbursementTable;
import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.ContentService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.LoanService;
import com.cts.localtour.service.ReimbursementCostService;
import com.cts.localtour.service.SupplierInfoService;
import com.cts.localtour.service.UserService;

@Component
public class FullBalanceViewModel {
	private ArrayList<CostViewModel> costs;
	private ArrayList<LoanViewModel> loans;
	private ArrayList<ChangeCostViewModel> changeCosts;
	private ArrayList<ReimbursementCostViewModel> reimbursementCosts;
	private float realIncomeSum;
	private float realPaySum;
	private float reimbursementSum;
	private ReimbursementTable reimbursementTable;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@Autowired
	private CostService costService;
	@Autowired
	private ChangeCostService changeCostService;
	@Autowired
	private ReimbursementCostService reimbursementCostService;
	@Autowired
	private LoanService loanService;
	@Autowired
	private SupplierInfoService supplierInfoService;
	@Autowired
	private ContentService contentService;
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
	public ArrayList<ChangeCostViewModel> getChangeCosts() {
		return changeCosts;
	}
	public void setChangeCosts(ArrayList<ChangeCostViewModel> changeCosts) {
		this.changeCosts = changeCosts;
	}
	public ReimbursementTable getReimbursementTable() {
		return reimbursementTable;
	}
	public void setReimbursementTable(ReimbursementTable reimbursementTable) {
		this.reimbursementTable = reimbursementTable;
	}
	public float getRealIncomeSum() {
		return realIncomeSum;
	}
	public void setRealIncomeSum(float realIncomeSum) {
		this.realIncomeSum = realIncomeSum;
	}
	public float getRealPaySum() {
		return realPaySum;
	}
	public void setRealPaySum(float realPaySum) {
		this.realPaySum = realPaySum;
	}
	public float getReimbursementSum() {
		return reimbursementSum;
	}
	public void setReimbursementSum(float reimbursementSum) {
		this.reimbursementSum = reimbursementSum;
	}
	public ArrayList<ReimbursementCostViewModel> getReimbursementCosts() {
		return reimbursementCosts;
	}
	public void setReimbursementCosts(ArrayList<ReimbursementCostViewModel> reimbursementCosts) {
		this.reimbursementCosts = reimbursementCosts;
	}
	@SuppressWarnings("unchecked")
	public FullBalanceViewModel getFullBalanceViewModel (int tourId){
		FullBalanceViewModel full = new FullBalanceViewModel();
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "tourId=? and (payStatus=3 or lend=true or bill=true)", tourId);
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (int i = 0; i < costTables.size(); i++) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTables.get(i));
			cost.setContentName(contentService.getContentName(costTables.get(i).getContentId()));
			cost.setSupplierName(supplierInfoService.getSupplierName(costTables.get(i).getSupplierId()));
			cost.setBorrowUserName(userService.getUserRealName(costTables.get(i).getBorrowUserId()));
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
			changeCost.setContentName(contentService.getContentName(changeCostTables.get(i).getContentId()));
			changeCost.setSupplierName(supplierInfoService.getSupplierName(changeCostTables.get(i).getSupplierId()));
			changeCost.setBorrowUserName(userService.getUserRealName(changeCostTables.get(i).getBorrowUserId()));
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
		
		ArrayList<ReimbursementCostTable> reimbursementCostTables = (ArrayList<ReimbursementCostTable>) baseService.getAllByString("ReimbursementCostTable", "tourId=? and payStatus=1", tourId);
		ArrayList<ReimbursementCostViewModel> reimbursementCosts = new ArrayList<ReimbursementCostViewModel>();
		for (ReimbursementCostTable reimbursementCostTable : reimbursementCostTables) {
			ReimbursementCostViewModel reimbursementCostViewModel = new ReimbursementCostViewModel();
			reimbursementCostViewModel.setCostTable(reimbursementCostTable);
			reimbursementCostViewModel.setSupplierName(supplierInfoService.getSupplierName(reimbursementCostTable.getSupplierId()));
			reimbursementCostViewModel.setContentName(contentService.getContentName(reimbursementCostTable.getContentId()));
			reimbursementCosts.add(reimbursementCostViewModel);
		}
		full.setReimbursementCosts(reimbursementCosts);
		
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) baseService.getAllByString("LoanTable", "tourId=? and status=4", tourId);
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (int i = 0; i < loanTables.size(); i++) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTables.get(i));
			loan.setLenderRealName(userService.getUserRealName(loanTables.get(i).getLenderId()));
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
		full.setRealIncomeSum(incomeService.getIncomeInfo(tourId).getRealIncomeSum().add(changeIncomeService.getIncomeInfo(tourId).getRealIncomeSum()).floatValue());
		CostInfo costInfo = costService.getCostInfo(tourId);
		CostInfo changeCostInfo = changeCostService.getCostInfo(tourId);
		CostInfo reimbursementCostInfo = reimbursementCostService.getReimbursementCostInfo(tourId);
		full.setRealPaySum(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()).add(loanService.getLoanInfo(tourId).getRealLoanSum()).floatValue());
		full.setReimbursementSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum().add(reimbursementCostInfo.getReimbursementSum())).floatValue());
		ArrayList<ReimbursementTable> reimbursementTables = (ArrayList<ReimbursementTable>) baseService.getAllByString("ReimbursementTable", "tourId=?", tourId);
		full.setReimbursementTable(reimbursementTables.isEmpty()?null:reimbursementTables.get(0));
		return full;
	}
}
