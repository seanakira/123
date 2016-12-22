package com.cts.localtour.viewModel;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.pojo.IncomeInfo;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.IncomeService;

@Component
public class FullReimbursementApplicationViewModel {
	private float budgetCostSum;
	private String budgetCostSumInfo;
	private float budgetIncomeSum;
	private String budgetIncomeSumInfo;
	private float budgetGrossProfit;
	private float budgetGrossMargin;
	
	private float executeCostSum;
	private String executeCostSumInfo;
	private float executeIncomeSum;
	private String executeIncomeSumInfo;
	private float executeGrossProfit;
	private float executeGrossMargin;
	
	private float willCostSum;
	private String willCostSumInfo;
	private float willIncomeSum;
	private String willIncomeSumInfo;
	private float willGrossProfit;
	private float willGrossMargin;
	
	private float realCostSum;
	private String realCostSumInfo;
	private float realIncomeSum;
	private String realIncomeSumInfo;
	private float realGrossProfit;
	private float realGrossMargin;
	
	private float reimbursementCostSum;
	private String reimbursementCostSumInfo;
	private float reimbursementIncomeSum;
	private String reimbursementIncomeSumInfo;
	private float reimbursementGrossProfit;
	private float reimbursementGrossMargin;
	@Autowired
	private CostService costService;
	@Autowired
	private ChangeCostService changeCostService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	public float getBudgetCostSum() {
		return budgetCostSum;
	}
	public void setBudgetCostSum(float budgetCostSum) {
		this.budgetCostSum = budgetCostSum;
	}
	public String getBudgetCostSumInfo() {
		return budgetCostSumInfo;
	}
	public void setBudgetCostSumInfo(String budgetCostSumInfo) {
		this.budgetCostSumInfo = budgetCostSumInfo;
	}
	public float getBudgetIncomeSum() {
		return budgetIncomeSum;
	}
	public void setBudgetIncomeSum(float budgetIncomeSum) {
		this.budgetIncomeSum = budgetIncomeSum;
	}
	public String getBudgetIncomeSumInfo() {
		return budgetIncomeSumInfo;
	}
	public void setBudgetIncomeSumInfo(String budgetIncomeSumInfo) {
		this.budgetIncomeSumInfo = budgetIncomeSumInfo;
	}
	public float getBudgetGrossProfit() {
		return budgetGrossProfit;
	}
	public void setBudgetGrossProfit(float budgetGrossProfit) {
		this.budgetGrossProfit = budgetGrossProfit;
	}
	public float getBudgetGrossMargin() {
		return budgetGrossMargin;
	}
	public void setBudgetGrossMargin(float budgetGrossMargin) {
		this.budgetGrossMargin = budgetGrossMargin;
	}
	public float getExecuteCostSum() {
		return executeCostSum;
	}
	public void setExecuteCostSum(float executeCostSum) {
		this.executeCostSum = executeCostSum;
	}
	public String getExecuteCostSumInfo() {
		return executeCostSumInfo;
	}
	public void setExecuteCostSumInfo(String executeCostSumInfo) {
		this.executeCostSumInfo = executeCostSumInfo;
	}
	public float getExecuteIncomeSum() {
		return executeIncomeSum;
	}
	public void setExecuteIncomeSum(float executeIncomeSum) {
		this.executeIncomeSum = executeIncomeSum;
	}
	public String getExecuteIncomeSumInfo() {
		return executeIncomeSumInfo;
	}
	public void setExecuteIncomeSumInfo(String executeIncomeSumInfo) {
		this.executeIncomeSumInfo = executeIncomeSumInfo;
	}
	public float getExecuteGrossProfit() {
		return executeGrossProfit;
	}
	public void setExecuteGrossProfit(float executeGrossProfit) {
		this.executeGrossProfit = executeGrossProfit;
	}
	public float getExecuteGrossMargin() {
		return executeGrossMargin;
	}
	public void setExecuteGrossMargin(float executeGrossMargin) {
		this.executeGrossMargin = executeGrossMargin;
	}
	public float getWillCostSum() {
		return willCostSum;
	}
	public void setWillCostSum(float willCostSum) {
		this.willCostSum = willCostSum;
	}
	public String getWillCostSumInfo() {
		return willCostSumInfo;
	}
	public void setWillCostSumInfo(String willCostSumInfo) {
		this.willCostSumInfo = willCostSumInfo;
	}
	public float getWillIncomeSum() {
		return willIncomeSum;
	}
	public void setWillIncomeSum(float willIncomeSum) {
		this.willIncomeSum = willIncomeSum;
	}
	public String getWillIncomeSumInfo() {
		return willIncomeSumInfo;
	}
	public void setWillIncomeSumInfo(String willIncomeSumInfo) {
		this.willIncomeSumInfo = willIncomeSumInfo;
	}
	public float getWillGrossProfit() {
		return willGrossProfit;
	}
	public void setWillGrossProfit(float willGrossProfit) {
		this.willGrossProfit = willGrossProfit;
	}
	public float getWillGrossMargin() {
		return willGrossMargin;
	}
	public void setWillGrossMargin(float willGrossMargin) {
		this.willGrossMargin = willGrossMargin;
	}
	public float getRealCostSum() {
		return realCostSum;
	}
	public void setRealCostSum(float realCostSum) {
		this.realCostSum = realCostSum;
	}
	public String getRealCostSumInfo() {
		return realCostSumInfo;
	}
	public void setRealCostSumInfo(String realCostSumInfo) {
		this.realCostSumInfo = realCostSumInfo;
	}
	public float getRealIncomeSum() {
		return realIncomeSum;
	}
	public void setRealIncomeSum(float realIncomeSum) {
		this.realIncomeSum = realIncomeSum;
	}
	public String getRealIncomeSumInfo() {
		return realIncomeSumInfo;
	}
	public void setRealIncomeSumInfo(String realIncomeSumInfo) {
		this.realIncomeSumInfo = realIncomeSumInfo;
	}
	public float getRealGrossProfit() {
		return realGrossProfit;
	}
	public void setRealGrossProfit(float realGrossProfit) {
		this.realGrossProfit = realGrossProfit;
	}
	public float getRealGrossMargin() {
		return realGrossMargin;
	}
	public void setRealGrossMargin(float realGrossMargin) {
		this.realGrossMargin = realGrossMargin;
	}
	public float getReimbursementCostSum() {
		return reimbursementCostSum;
	}
	public void setReimbursementCostSum(float reimbursementCostSum) {
		this.reimbursementCostSum = reimbursementCostSum;
	}
	public String getReimbursementCostSumInfo() {
		return reimbursementCostSumInfo;
	}
	public void setReimbursementCostSumInfo(String reimbursementCostSumInfo) {
		this.reimbursementCostSumInfo = reimbursementCostSumInfo;
	}
	public float getReimbursementIncomeSum() {
		return reimbursementIncomeSum;
	}
	public void setReimbursementIncomeSum(float reimbursementIncomeSum) {
		this.reimbursementIncomeSum = reimbursementIncomeSum;
	}
	public String getReimbursementIncomeSumInfo() {
		return reimbursementIncomeSumInfo;
	}
	public void setReimbursementIncomeSumInfo(String reimbursementIncomeSumInfo) {
		this.reimbursementIncomeSumInfo = reimbursementIncomeSumInfo;
	}
	public float getReimbursementGrossProfit() {
		return reimbursementGrossProfit;
	}
	public void setReimbursementGrossProfit(float reimbursementGrossProfit) {
		this.reimbursementGrossProfit = reimbursementGrossProfit;
	}
	public float getReimbursementGrossMargin() {
		return reimbursementGrossMargin;
	}
	public void setReimbursementGrossMargin(float reimbursementGrossMargin) {
		this.reimbursementGrossMargin = reimbursementGrossMargin;
	}
	public FullReimbursementApplicationViewModel getFullReimbursementApplicationViewModel(int tourId){
		FullReimbursementApplicationViewModel fullReimbursementApplicationViewModel = new FullReimbursementApplicationViewModel();
		CostInfo costInfo = costService.getCostInfo(tourId);
		CostInfo changeCostInfo = changeCostService.getCostInfo(tourId);
		IncomeInfo incomeInfo = incomeService.getIncomeInfo(tourId);
		IncomeInfo changeIncomeInfo = changeIncomeService.getIncomeInfo(tourId);
		fullReimbursementApplicationViewModel.setBudgetCostSum(costInfo.getCostSum().floatValue());
		fullReimbursementApplicationViewModel.setBudgetCostSumInfo(costInfo.getCostSumInfo());
		fullReimbursementApplicationViewModel.setBudgetIncomeSum(incomeInfo.getIncomeSum().floatValue());
		fullReimbursementApplicationViewModel.setBudgetIncomeSumInfo(incomeInfo.getIncomeSumInfo());
		fullReimbursementApplicationViewModel.setBudgetGrossProfit(incomeInfo.getIncomeSum().subtract(costInfo.getCostSum()).floatValue());
		if(incomeInfo.getIncomeSum().floatValue()!=0){
			fullReimbursementApplicationViewModel.setBudgetGrossMargin((incomeInfo.getIncomeSum().subtract(costInfo.getCostSum()).divide(incomeInfo.getIncomeSum(),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).floatValue()));
		}
		
		fullReimbursementApplicationViewModel.setExecuteCostSum(costInfo.getCostSum().add(changeCostInfo.getCostSum()).floatValue());
		fullReimbursementApplicationViewModel.setExecuteCostSumInfo(costInfo.getCostSumInfo()+changeCostInfo.getCostSumInfo());
		fullReimbursementApplicationViewModel.setExecuteIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).floatValue());
		fullReimbursementApplicationViewModel.setExecuteIncomeSumInfo(incomeInfo.getIncomeSumInfo()+changeIncomeInfo.getIncomeSumInfo());
		fullReimbursementApplicationViewModel.setExecuteGrossProfit((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum())).subtract(costInfo.getCostSum().add(changeCostInfo.getCostSum())).floatValue());
		if(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).floatValue()!=0){
			fullReimbursementApplicationViewModel.setExecuteGrossMargin(((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum())).subtract(costInfo.getCostSum().add(changeCostInfo.getCostSum()))).divide(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).floatValue());
		}
		
		fullReimbursementApplicationViewModel.setWillCostSum(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).floatValue());
		fullReimbursementApplicationViewModel.setWillCostSumInfo(costInfo.getWillCostSumInfo()+changeCostInfo.getWillCostSumInfo());
		fullReimbursementApplicationViewModel.setWillIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).floatValue());
		fullReimbursementApplicationViewModel.setWillIncomeSumInfo(incomeInfo.getRealIncomeSumInfo()+changeIncomeInfo.getRealIncomeSumInfo());
		fullReimbursementApplicationViewModel.setWillGrossProfit(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).subtract(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum())).floatValue());
		if(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).floatValue()!=0){
			fullReimbursementApplicationViewModel.setWillGrossMargin((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).subtract(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()))).divide(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).floatValue());
		}
		
		fullReimbursementApplicationViewModel.setRealCostSum(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()).floatValue());
		fullReimbursementApplicationViewModel.setRealCostSumInfo(costInfo.getRealCostSumInfo()+changeCostInfo.getRealCostSumInfo());
		fullReimbursementApplicationViewModel.setRealIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).floatValue());
		fullReimbursementApplicationViewModel.setRealIncomeSumInfo(incomeInfo.getRealIncomeSumInfo()+changeIncomeInfo.getRealIncomeSumInfo());
		fullReimbursementApplicationViewModel.setRealGrossProfit((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum())).subtract(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum())).floatValue());
		if(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).floatValue()!=0){
			fullReimbursementApplicationViewModel.setRealGrossMargin(((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum())).subtract(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()))).divide(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).floatValue());
		}
		
		fullReimbursementApplicationViewModel.setReimbursementCostSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).floatValue());
		fullReimbursementApplicationViewModel.setReimbursementCostSumInfo(costInfo.getReimbursementSumInfo()+changeCostInfo.getReimbursementSumInfo());
		fullReimbursementApplicationViewModel.setReimbursementIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).floatValue());
		fullReimbursementApplicationViewModel.setReimbursementIncomeSumInfo(incomeInfo.getRealIncomeSumInfo()+changeIncomeInfo.getRealIncomeSumInfo());
		fullReimbursementApplicationViewModel.setReimbursementGrossProfit((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum())).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum())).floatValue());
		if(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).floatValue()!=0){
			fullReimbursementApplicationViewModel.setReimbursementGrossMargin(((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum())).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()))).divide(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).floatValue());
		}
		return fullReimbursementApplicationViewModel;
	}
	
}
