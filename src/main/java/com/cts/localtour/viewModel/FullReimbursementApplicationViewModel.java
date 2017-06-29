package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ReimbursementTable;
import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.pojo.IncomeInfo;
import com.cts.localtour.pojo.LoanInfo;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.LoanService;
import com.cts.localtour.service.RefundService;
import com.cts.localtour.service.ReimbursementCostService;
import com.cts.localtour.service.ReimbursementIncomeService;

@Component
public class FullReimbursementApplicationViewModel {
	private BigDecimal budgetCostSum;
	private String budgetCostSumInfo;
	private BigDecimal budgetIncomeSum;
	private String budgetIncomeSumInfo;
	private BigDecimal budgetGrossProfit;
	private BigDecimal budgetGrossMargin;
	
	private BigDecimal executeCostSum;
	private String executeCostSumInfo;
	private BigDecimal executeIncomeSum;
	private String executeIncomeSumInfo;
	private BigDecimal executeGrossProfit;
	private BigDecimal executeGrossMargin;
	
	private BigDecimal willCostSum;
	private String willCostSumInfo;
	private BigDecimal willIncomeSum;
	private String willIncomeSumInfo;
	private BigDecimal willGrossProfit;
	private BigDecimal willGrossMargin;
	
	private BigDecimal realCostSum;
	private String realCostSumInfo;
	private BigDecimal realIncomeSum;
	private String realIncomeSumInfo;
	private BigDecimal realGrossProfit;
	private BigDecimal realGrossMargin;
	
	private BigDecimal reimbursementCostSum;
	private String reimbursementCostSumInfo;
	private BigDecimal reimbursementIncomeSum;
	private String reimbursementIncomeSumInfo;
	private BigDecimal reimbursementGrossProfit;
	private BigDecimal reimbursementGrossMargin;
	
	private BigDecimal headAmount;
	@Autowired
	private CostService costService;
	@Autowired
	private ChangeCostService changeCostService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@Autowired
	private ReimbursementCostService reimbursementCostService;
	@Autowired
	private ReimbursementIncomeService reimbursementIncomeService;
	@Autowired
	private LoanService loanService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private RefundService refundService;
	public BigDecimal getBudgetCostSum() {
		return budgetCostSum;
	}
	public void setBudgetCostSum(BigDecimal budgetCostSum) {
		this.budgetCostSum = budgetCostSum;
	}
	public String getBudgetCostSumInfo() {
		return budgetCostSumInfo;
	}
	public void setBudgetCostSumInfo(String budgetCostSumInfo) {
		this.budgetCostSumInfo = budgetCostSumInfo;
	}
	public BigDecimal getBudgetIncomeSum() {
		return budgetIncomeSum;
	}
	public void setBudgetIncomeSum(BigDecimal budgetIncomeSum) {
		this.budgetIncomeSum = budgetIncomeSum;
	}
	public String getBudgetIncomeSumInfo() {
		return budgetIncomeSumInfo;
	}
	public void setBudgetIncomeSumInfo(String budgetIncomeSumInfo) {
		this.budgetIncomeSumInfo = budgetIncomeSumInfo;
	}
	public BigDecimal getBudgetGrossProfit() {
		return budgetGrossProfit;
	}
	public void setBudgetGrossProfit(BigDecimal budgetGrossProfit) {
		this.budgetGrossProfit = budgetGrossProfit;
	}
	public BigDecimal getBudgetGrossMargin() {
		return budgetGrossMargin;
	}
	public void setBudgetGrossMargin(BigDecimal budgetGrossMargin) {
		this.budgetGrossMargin = budgetGrossMargin;
	}
	public BigDecimal getExecuteCostSum() {
		return executeCostSum;
	}
	public void setExecuteCostSum(BigDecimal executeCostSum) {
		this.executeCostSum = executeCostSum;
	}
	public String getExecuteCostSumInfo() {
		return executeCostSumInfo;
	}
	public void setExecuteCostSumInfo(String executeCostSumInfo) {
		this.executeCostSumInfo = executeCostSumInfo;
	}
	public BigDecimal getExecuteIncomeSum() {
		return executeIncomeSum;
	}
	public void setExecuteIncomeSum(BigDecimal executeIncomeSum) {
		this.executeIncomeSum = executeIncomeSum;
	}
	public String getExecuteIncomeSumInfo() {
		return executeIncomeSumInfo;
	}
	public void setExecuteIncomeSumInfo(String executeIncomeSumInfo) {
		this.executeIncomeSumInfo = executeIncomeSumInfo;
	}
	public BigDecimal getExecuteGrossProfit() {
		return executeGrossProfit;
	}
	public void setExecuteGrossProfit(BigDecimal executeGrossProfit) {
		this.executeGrossProfit = executeGrossProfit;
	}
	public BigDecimal getExecuteGrossMargin() {
		return executeGrossMargin;
	}
	public void setExecuteGrossMargin(BigDecimal executeGrossMargin) {
		this.executeGrossMargin = executeGrossMargin;
	}
	public BigDecimal getWillCostSum() {
		return willCostSum;
	}
	public void setWillCostSum(BigDecimal willCostSum) {
		this.willCostSum = willCostSum;
	}
	public String getWillCostSumInfo() {
		return willCostSumInfo;
	}
	public void setWillCostSumInfo(String willCostSumInfo) {
		this.willCostSumInfo = willCostSumInfo;
	}
	public BigDecimal getWillIncomeSum() {
		return willIncomeSum;
	}
	public void setWillIncomeSum(BigDecimal willIncomeSum) {
		this.willIncomeSum = willIncomeSum;
	}
	public String getWillIncomeSumInfo() {
		return willIncomeSumInfo;
	}
	public void setWillIncomeSumInfo(String willIncomeSumInfo) {
		this.willIncomeSumInfo = willIncomeSumInfo;
	}
	public BigDecimal getWillGrossProfit() {
		return willGrossProfit;
	}
	public void setWillGrossProfit(BigDecimal willGrossProfit) {
		this.willGrossProfit = willGrossProfit;
	}
	public BigDecimal getWillGrossMargin() {
		return willGrossMargin;
	}
	public void setWillGrossMargin(BigDecimal willGrossMargin) {
		this.willGrossMargin = willGrossMargin;
	}
	public BigDecimal getRealCostSum() {
		return realCostSum;
	}
	public void setRealCostSum(BigDecimal realCostSum) {
		this.realCostSum = realCostSum;
	}
	public String getRealCostSumInfo() {
		return realCostSumInfo;
	}
	public void setRealCostSumInfo(String realCostSumInfo) {
		this.realCostSumInfo = realCostSumInfo;
	}
	public BigDecimal getRealIncomeSum() {
		return realIncomeSum;
	}
	public void setRealIncomeSum(BigDecimal realIncomeSum) {
		this.realIncomeSum = realIncomeSum;
	}
	public String getRealIncomeSumInfo() {
		return realIncomeSumInfo;
	}
	public void setRealIncomeSumInfo(String realIncomeSumInfo) {
		this.realIncomeSumInfo = realIncomeSumInfo;
	}
	public BigDecimal getRealGrossProfit() {
		return realGrossProfit;
	}
	public void setRealGrossProfit(BigDecimal realGrossProfit) {
		this.realGrossProfit = realGrossProfit;
	}
	public BigDecimal getRealGrossMargin() {
		return realGrossMargin;
	}
	public void setRealGrossMargin(BigDecimal realGrossMargin) {
		this.realGrossMargin = realGrossMargin;
	}
	public BigDecimal getReimbursementCostSum() {
		return reimbursementCostSum;
	}
	public void setReimbursementCostSum(BigDecimal reimbursementCostSum) {
		this.reimbursementCostSum = reimbursementCostSum;
	}
	public String getReimbursementCostSumInfo() {
		return reimbursementCostSumInfo;
	}
	public void setReimbursementCostSumInfo(String reimbursementCostSumInfo) {
		this.reimbursementCostSumInfo = reimbursementCostSumInfo;
	}
	public BigDecimal getReimbursementIncomeSum() {
		return reimbursementIncomeSum;
	}
	public void setReimbursementIncomeSum(BigDecimal reimbursementIncomeSum) {
		this.reimbursementIncomeSum = reimbursementIncomeSum;
	}
	public String getReimbursementIncomeSumInfo() {
		return reimbursementIncomeSumInfo;
	}
	public void setReimbursementIncomeSumInfo(String reimbursementIncomeSumInfo) {
		this.reimbursementIncomeSumInfo = reimbursementIncomeSumInfo;
	}
	public BigDecimal getReimbursementGrossProfit() {
		return reimbursementGrossProfit;
	}
	public void setReimbursementGrossProfit(BigDecimal reimbursementGrossProfit) {
		this.reimbursementGrossProfit = reimbursementGrossProfit;
	}
	public BigDecimal getReimbursementGrossMargin() {
		return reimbursementGrossMargin;
	}
	public void setReimbursementGrossMargin(BigDecimal reimbursementGrossMargin) {
		this.reimbursementGrossMargin = reimbursementGrossMargin;
	}
	public BigDecimal getHeadAmount() {
		return headAmount;
	}
	public void setHeadAmount(BigDecimal headAmount) {
		this.headAmount = headAmount;
	}
	@SuppressWarnings("unchecked")
	public FullReimbursementApplicationViewModel getFullReimbursementApplicationViewModel(int tourId){
		FullReimbursementApplicationViewModel fullReimbursementApplicationViewModel = new FullReimbursementApplicationViewModel();
		CostInfo costInfo = costService.getCostInfo(tourId);
		CostInfo changeCostInfo = changeCostService.getCostInfo(tourId);
		CostInfo reimbursementCostInfo = reimbursementCostService.getReimbursementCostInfo(tourId);
		IncomeInfo incomeInfo = incomeService.getIncomeInfo(tourId);
		IncomeInfo changeIncomeInfo = changeIncomeService.getIncomeInfo(tourId);
		IncomeInfo reimbursementIncomeInfo = reimbursementIncomeService.getIncomeInfo(tourId);
		IncomeInfo refundIncomeInfo = refundService.getIncomeInfo(tourId);
		LoanInfo loanInfo = loanService.getLoanInfo(tourId);
		fullReimbursementApplicationViewModel.setBudgetCostSum(costInfo.getCostSum());
		fullReimbursementApplicationViewModel.setBudgetCostSumInfo(costInfo.getCostSumInfo());
		fullReimbursementApplicationViewModel.setBudgetIncomeSum(incomeInfo.getIncomeSum());
		fullReimbursementApplicationViewModel.setBudgetIncomeSumInfo(incomeInfo.getIncomeSumInfo());
		fullReimbursementApplicationViewModel.setBudgetGrossProfit(incomeInfo.getIncomeSum().subtract(costInfo.getCostSum()));
		if(incomeInfo.getIncomeSum().compareTo(new BigDecimal(0))!=0){
			fullReimbursementApplicationViewModel.setBudgetGrossMargin((incomeInfo.getIncomeSum().subtract(costInfo.getCostSum()).divide(incomeInfo.getIncomeSum(),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))));
		}
		
		fullReimbursementApplicationViewModel.setExecuteCostSum(costInfo.getCostSum().add(changeCostInfo.getCostSum()));
		fullReimbursementApplicationViewModel.setExecuteCostSumInfo(costInfo.getCostSumInfo()+changeCostInfo.getCostSumInfo());
		fullReimbursementApplicationViewModel.setExecuteIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()));
		fullReimbursementApplicationViewModel.setExecuteIncomeSumInfo(incomeInfo.getIncomeSumInfo()+changeIncomeInfo.getIncomeSumInfo());
		fullReimbursementApplicationViewModel.setExecuteGrossProfit((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum())).subtract(costInfo.getCostSum().add(changeCostInfo.getCostSum())));
		if(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).compareTo(new BigDecimal(0))!=0){
			fullReimbursementApplicationViewModel.setExecuteGrossMargin(((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum())).subtract(costInfo.getCostSum().add(changeCostInfo.getCostSum()))).divide(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		}
		
		fullReimbursementApplicationViewModel.setWillCostSum(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).add(reimbursementCostInfo.getWillCostSum()).add(loanInfo.getWillLoanSum()));
		fullReimbursementApplicationViewModel.setWillCostSumInfo(costInfo.getWillCostSumInfo()+loanInfo.getWillLoanSumInfo()+changeCostInfo.getWillCostSumInfo()+reimbursementCostInfo.getWillCostSumInfo());
		fullReimbursementApplicationViewModel.setWillIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()));
		fullReimbursementApplicationViewModel.setWillIncomeSumInfo(incomeInfo.getIncomeSumInfo()+changeIncomeInfo.getIncomeSumInfo());
		fullReimbursementApplicationViewModel.setWillGrossProfit(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).subtract(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).add(reimbursementCostInfo.getWillCostSum()).add(loanInfo.getWillLoanSum())));
		if(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).compareTo(new BigDecimal(0))!=0){
			fullReimbursementApplicationViewModel.setWillGrossMargin((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).subtract(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).add(reimbursementCostInfo.getWillCostSum()).add(loanInfo.getWillLoanSum()))).divide(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		}
		
		fullReimbursementApplicationViewModel.setRealCostSum(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()).add(reimbursementCostInfo.getRealCostSum()).add(loanInfo.getRealLoanSum()));
		fullReimbursementApplicationViewModel.setRealCostSumInfo(costInfo.getRealCostSumInfo()+loanInfo.getRealLoanSumInfo()+changeCostInfo.getRealCostSumInfo()+reimbursementCostInfo.getRealCostSumInfo());
		fullReimbursementApplicationViewModel.setRealIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).add(refundIncomeInfo.getRealIncomeSum()));
		fullReimbursementApplicationViewModel.setRealIncomeSumInfo(incomeInfo.getRealIncomeSumInfo()+changeIncomeInfo.getRealIncomeSumInfo()+refundIncomeInfo.getRealIncomeSumInfo());
		fullReimbursementApplicationViewModel.setRealGrossProfit((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).add(refundIncomeInfo.getRealIncomeSum())).subtract(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()).add(reimbursementCostInfo.getRealCostSum()).add(loanInfo.getRealLoanSum())));
		if(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum().add(refundIncomeInfo.getRealIncomeSum())).compareTo(new BigDecimal(0))!=0){
			fullReimbursementApplicationViewModel.setRealGrossMargin(((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).subtract(refundIncomeInfo.getRealIncomeSum())).subtract(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()).add(reimbursementCostInfo.getRealCostSum()).add(loanInfo.getRealLoanSum()))).divide(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		}
		
		fullReimbursementApplicationViewModel.setReimbursementCostSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum().add(reimbursementCostInfo.getReimbursementSum())));
		fullReimbursementApplicationViewModel.setReimbursementCostSumInfo(costInfo.getReimbursementSumInfo()+changeCostInfo.getReimbursementSumInfo()+reimbursementCostInfo.getReimbursementSumInfo());
		fullReimbursementApplicationViewModel.setReimbursementIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum())));
		fullReimbursementApplicationViewModel.setReimbursementIncomeSumInfo(incomeInfo.getIncomeSumInfo()+changeIncomeInfo.getIncomeSumInfo()+reimbursementIncomeInfo.getIncomeSumInfo());
		fullReimbursementApplicationViewModel.setReimbursementGrossProfit((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum()))).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum())));
		if(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum())).compareTo(new BigDecimal(0))!=0){
			fullReimbursementApplicationViewModel.setReimbursementGrossMargin(((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum()))).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum()))).divide(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum())),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		}
		ArrayList<ReimbursementTable> reimbursementTables = (ArrayList<ReimbursementTable>)baseService.getAllByString("ReimbursementTable", "tourId=?", tourId);
		if(!reimbursementTables.isEmpty()){
			fullReimbursementApplicationViewModel.setHeadAmount(reimbursementTables.get(0).getHeadAmount());
		}
		return fullReimbursementApplicationViewModel;
	}
	
}
