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
	
	private BigDecimal reimbursementCostSum;
	private String reimbursementCostSumInfo;
	private BigDecimal reimbursementIncomeSum;
	private String reimbursementIncomeSumInfo;
	private BigDecimal reimbursementGrossProfit;
	private BigDecimal reimbursementGrossMargin;
	
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
			
			budgetExecuteReimbursementContrastViewModel.setBudgetCostSum(costInfo.getCostSum());
			budgetExecuteReimbursementContrastViewModel.setBudgetCostSumInfo(costInfo.getCostSumInfo());
			budgetExecuteReimbursementContrastViewModel.setBudgetIncomeSum(incomeInfo.getIncomeSum());
			budgetExecuteReimbursementContrastViewModel.setBudgetIncomeSumInfo(incomeInfo.getIncomeSumInfo());
			budgetExecuteReimbursementContrastViewModel.setBudgetGrossProfit(incomeInfo.getIncomeSum().subtract(costInfo.getCostSum()));
			if(incomeInfo.getIncomeSum().compareTo(new BigDecimal(0))!=0){
				budgetExecuteReimbursementContrastViewModel.setBudgetGrossMargin((incomeInfo.getIncomeSum().subtract(costInfo.getCostSum()).divide(incomeInfo.getIncomeSum(),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))));
			}
			
			budgetExecuteReimbursementContrastViewModel.setExecuteCostSum(costInfo.getCostSum().add(changeCostInfo.getCostSum()));
			budgetExecuteReimbursementContrastViewModel.setExecuteCostSumInfo(costInfo.getCostSumInfo()+changeCostInfo.getCostSumInfo());
			budgetExecuteReimbursementContrastViewModel.setExecuteIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()));
			budgetExecuteReimbursementContrastViewModel.setExecuteIncomeSumInfo(incomeInfo.getIncomeSumInfo()+changeIncomeInfo.getIncomeSumInfo());
			budgetExecuteReimbursementContrastViewModel.setExecuteGrossProfit((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum())).subtract(costInfo.getCostSum().add(changeCostInfo.getCostSum())));
			if(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).compareTo(new BigDecimal(0))!=0){
				budgetExecuteReimbursementContrastViewModel.setExecuteGrossMargin(((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum())).subtract(costInfo.getCostSum().add(changeCostInfo.getCostSum()))).divide(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
			}
			
			budgetExecuteReimbursementContrastViewModel.setReimbursementCostSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum().add(reimbursementCostInfo.getReimbursementSum())));
			budgetExecuteReimbursementContrastViewModel.setReimbursementCostSumInfo(costInfo.getReimbursementSumInfo()+changeCostInfo.getReimbursementSumInfo()+reimbursementCostInfo.getReimbursementSumInfo());
			budgetExecuteReimbursementContrastViewModel.setReimbursementIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum())));
			budgetExecuteReimbursementContrastViewModel.setReimbursementIncomeSumInfo(incomeInfo.getIncomeSumInfo()+changeIncomeInfo.getIncomeSumInfo()+reimbursementIncomeInfo.getIncomeSumInfo());
			budgetExecuteReimbursementContrastViewModel.setReimbursementGrossProfit((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum()))).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum())));
			if(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum())).compareTo(new BigDecimal(0))!=0){
				budgetExecuteReimbursementContrastViewModel.setReimbursementGrossMargin(((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum()))).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum()))).divide(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum().add(reimbursementIncomeInfo.getIncomeSum())),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
			}
			
			budgetExecuteReimbursementContrastViewModel.setTourNo(localTourTable.getTourNo());
			budgetExecuteReimbursementContrastViewModel.setDeptName(deptService.getDeptName(localTourTable.getDeptId()));
			budgetExecuteReimbursementContrastViewModel.setUserRealName(userService.getUserRealName(localTourTable.getUserId()));
			budgetExecuteReimbursementContrastViewModels.add(budgetExecuteReimbursementContrastViewModel);
		}
		return budgetExecuteReimbursementContrastViewModels;
	}
	
}
