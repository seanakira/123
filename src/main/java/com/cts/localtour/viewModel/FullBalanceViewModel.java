package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.ReimbursementTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.LoanService;

@Component
public class FullBalanceViewModel {
	private ArrayList<CostViewModel> costs;
	private ArrayList<LoanViewModel> loans;
	private ArrayList<ChangeCostViewModel> changeCosts;
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
	private LoanService loanService;
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
	@SuppressWarnings("unchecked")
	public FullBalanceViewModel getFullBalanceViewModel (int tourId){
		FullBalanceViewModel full = new FullBalanceViewModel();
		ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "tourId=? and (payStatus=3 or lend=true or bill=true)", tourId);
		ArrayList<CostViewModel> costs = new ArrayList<CostViewModel>();
		for (int i = 0; i < costTables.size(); i++) {
			CostViewModel cost = new CostViewModel();
			cost.setCostTable(costTables.get(i));
			cost.setContentName(costTables.get(i).getContentId()==null||costTables.get(i).getContentId()==0?"":((SupplierContentTable)baseService.getById("SupplierContentTable", costTables.get(i).getContentId())).getContentName());
			cost.setSupplierName(((SupplierTable)baseService.getById("SupplierTable", costTables.get(i).getSupplierId())).getSupplierName());
			cost.setBorrowUserName(costTables.get(i).getBorrowUserId()==null||costTables.get(i).getBorrowUserId()==0?"":((UserTable)baseService.getById("UserTable", costTables.get(i).getBorrowUserId())).getRealName());
			if(costTables.get(i).isRemittanced()){
				cost.setPayStatus("已汇");
			}else if(costTables.get(i).isLend()){
				cost.setPayStatus("借款");
			}else if(costTables.get(i).isBill()){
				cost.setPayStatus("挂账");
			}else{
				cost.setPayStatus(costTables.get(i).getPayStatus()==0?"可付":costTables.get(i).getPayStatus()==1?"待审":costTables.get(i).getPayStatus()==2?"待批":costTables.get(i).getPayStatus()==3?"已批准":"");
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
				changeCost.setPayStatus("已汇");
			}else if(changeCostTables.get(i).isLend()){
				changeCost.setPayStatus("借款");
			}else if(changeCostTables.get(i).isBill()){
				changeCost.setPayStatus("待汇");
			}else{
				changeCost.setPayStatus(changeCostTables.get(i).getPayStatus()==0?"可付":changeCostTables.get(i).getPayStatus()==1?"待审":changeCostTables.get(i).getPayStatus()==2?"待批":changeCostTables.get(i).getPayStatus()==3?"已批准":"");
			}
			changeCosts.add(changeCost);
		}
		full.setChangeCosts(changeCosts);
		
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) baseService.getAllByString("LoanTable", "tourId=? and status=4", tourId);
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
		full.setRealIncomeSum(incomeService.getIncomeInfo(tourId).getRealIncomeSum().add(changeIncomeService.getIncomeInfo(tourId).getRealIncomeSum()).floatValue());
		CostInfo costInfo = costService.getCostInfo(tourId);
		CostInfo changeCostInfo = changeCostService.getCostInfo(tourId);
		full.setRealPaySum(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()).add(loanService.getLoanInfo(tourId).getRealLoanSum()).floatValue());
		full.setReimbursementSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).floatValue());
		ArrayList<ReimbursementTable> reimbursementTables = (ArrayList<ReimbursementTable>) baseService.getAllByString("ReimbursementTable", "tourId=?", tourId);
		full.setReimbursementTable(reimbursementTables.isEmpty()?null:reimbursementTables.get(0));
		return full;
	}
}
