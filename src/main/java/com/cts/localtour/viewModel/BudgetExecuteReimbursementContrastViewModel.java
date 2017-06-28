package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.pojo.IncomeInfo;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.DeptService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.ReimbursementCostService;
import com.cts.localtour.service.ReimbursementIncomeService;
import com.cts.localtour.service.UserService;

@Component
public class BudgetExecuteReimbursementContrastViewModel {
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
	
	private float reimbursementCostSum;
	private String reimbursementCostSumInfo;
	private float reimbursementIncomeSum;
	private String reimbursementIncomeSumInfo;
	private float reimbursementGrossProfit;
	private float reimbursementGrossMargin;
	
	private String tourNo;
	private String deptName;
	private String userRealName;
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
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;
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
	public String getTourNo() {
		return tourNo;
	}
	public void setTourNo(String tourNo) {
		this.tourNo = tourNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<BudgetExecuteReimbursementContrastViewModel> getBudgetExecuteReimbursementContrastViewModelAll(Date start, Date end,String deptIds, String tourNo){
		ArrayList<BudgetExecuteReimbursementContrastViewModel> budgetExecuteReimbursementContrastViewModels = new ArrayList<BudgetExecuteReimbursementContrastViewModel>();
		deptIds = deptService.getDownerDpetIds(deptIds);
		ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) baseService.getAllByString("LocalTourTable", "startTime between ? and ? and deptId in ("+deptIds+")"+("".equals(tourNo)?"":" and tourNo like '%"+tourNo+"%'"+" and status>2"), start, end);
		for (LocalTourTable localTourTable : localTourTables) {
			BudgetExecuteReimbursementContrastViewModel budgetExecuteReimbursementContrastViewModel = new BudgetExecuteReimbursementContrastViewModel();
			CostInfo costInfo = costService.getCostInfo(localTourTable.getId());
			CostInfo changeCostInfo = changeCostService.getCostInfo(localTourTable.getId());
			CostInfo reimbursementCostInfo = reimbursementCostService.getReimbursementCostInfo(localTourTable.getId());
			IncomeInfo incomeInfo = incomeService.getIncomeInfo(localTourTable.getId());
			IncomeInfo changeIncomeInfo = changeIncomeService.getIncomeInfo(localTourTable.getId());
			IncomeInfo reimbursementIncomeInfo = reimbursementIncomeService.getIncomeInfo(localTourTable.getId());
			
			budgetExecuteReimbursementContrastViewModel.setBudgetCostSum(costInfo.getCostSum().floatValue());
			budgetExecuteReimbursementContrastViewModel.setBudgetCostSumInfo(costInfo.getCostSumInfo());
			budgetExecuteReimbursementContrastViewModel.setBudgetIncomeSum(incomeInfo.getIncomeSum().floatValue());
			budgetExecuteReimbursementContrastViewModel.setBudgetIncomeSumInfo(incomeInfo.getIncomeSumInfo());
			budgetExecuteReimbursementContrastViewModel.setBudgetGrossProfit(incomeInfo.getIncomeSum().subtract(costInfo.getCostSum()).floatValue());
			if(incomeInfo.getIncomeSum().floatValue()!=0){
				budgetExecuteReimbursementContrastViewModel.setBudgetGrossMargin((incomeInfo.getIncomeSum().subtract(costInfo.getCostSum()).divide(incomeInfo.getIncomeSum(),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).floatValue()));
			}
			
			budgetExecuteReimbursementContrastViewModel.setExecuteCostSum(costInfo.getCostSum().add(changeCostInfo.getCostSum()).floatValue());
			budgetExecuteReimbursementContrastViewModel.setExecuteCostSumInfo(costInfo.getCostSumInfo()+changeCostInfo.getCostSumInfo());
			budgetExecuteReimbursementContrastViewModel.setExecuteIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).floatValue());
			budgetExecuteReimbursementContrastViewModel.setExecuteIncomeSumInfo(incomeInfo.getIncomeSumInfo()+changeIncomeInfo.getIncomeSumInfo());
			budgetExecuteReimbursementContrastViewModel.setExecuteGrossProfit((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum())).subtract(costInfo.getCostSum().add(changeCostInfo.getCostSum())).floatValue());
			if(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).floatValue()!=0){
				budgetExecuteReimbursementContrastViewModel.setExecuteGrossMargin(((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum())).subtract(costInfo.getCostSum().add(changeCostInfo.getCostSum()))).divide(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).floatValue());
			}
			
			budgetExecuteReimbursementContrastViewModel.setReimbursementCostSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum().add(reimbursementCostInfo.getReimbursementSum())).floatValue());
			budgetExecuteReimbursementContrastViewModel.setReimbursementCostSumInfo(costInfo.getReimbursementSumInfo()+changeCostInfo.getReimbursementSumInfo()+reimbursementCostInfo.getReimbursementSumInfo());
			budgetExecuteReimbursementContrastViewModel.setReimbursementIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum())).floatValue());
			budgetExecuteReimbursementContrastViewModel.setReimbursementIncomeSumInfo(incomeInfo.getIncomeSumInfo()+changeIncomeInfo.getIncomeSumInfo()+reimbursementIncomeInfo.getIncomeSumInfo());
			budgetExecuteReimbursementContrastViewModel.setReimbursementGrossProfit((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum()))).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum())).floatValue());
			if(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum())).floatValue()!=0){
				budgetExecuteReimbursementContrastViewModel.setReimbursementGrossMargin(((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum()))).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum()))).divide(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum())),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).floatValue());
			}
			
			budgetExecuteReimbursementContrastViewModel.setTourNo(localTourTable.getTourNo());
			budgetExecuteReimbursementContrastViewModel.setDeptName(deptService.getDeptName(localTourTable.getDeptId()));
			budgetExecuteReimbursementContrastViewModel.setUserRealName(userService.getUserRealName(localTourTable.getUserId()));
			budgetExecuteReimbursementContrastViewModels.add(budgetExecuteReimbursementContrastViewModel);
		}
		return budgetExecuteReimbursementContrastViewModels;
	}
	
}
