package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.pojo.IncomeInfo;
import com.cts.localtour.pojo.LoanInfo;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.LoanService;
import com.cts.localtour.service.RefundService;
import com.cts.localtour.service.UserService;

@Component
public class SimpleSettlementViewModel {
	private LocalTourTable localTourTable;
	private BigDecimal costSum;
	private BigDecimal willPaySum;
	private BigDecimal realPaySum;
	private BigDecimal reimbursementSum;
	private BigDecimal willIncomeSum;
	private BigDecimal realIncomeSum;
	private BigDecimal realGrossProfit;
	private String realGrossMargin;
	private String userRealName;
	private String status;
	@Autowired
	private CostService costService;
	@Autowired
	private ChangeCostService changeCostService;
	@Autowired
	private LoanService loanService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@Autowired
	private UserService userService;
	@Autowired
	private RefundService refundService;
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
	}
	public BigDecimal getCostSum() {
		return costSum;
	}
	public void setCostSum(BigDecimal costSum) {
		this.costSum = costSum;
	}
	public BigDecimal getWillPaySum() {
		return willPaySum;
	}
	public void setWillPaySum(BigDecimal willPaySum) {
		this.willPaySum = willPaySum;
	}
	public BigDecimal getRealPaySum() {
		return realPaySum;
	}
	public void setRealPaySum(BigDecimal realPaySum) {
		this.realPaySum = realPaySum;
	}
	public BigDecimal getReimbursementSum() {
		return reimbursementSum;
	}
	public void setReimbursementSum(BigDecimal reimbursementSum) {
		this.reimbursementSum = reimbursementSum;
	}
	public BigDecimal getWillIncomeSum() {
		return willIncomeSum;
	}
	public void setWillIncomeSum(BigDecimal willIncomeSum) {
		this.willIncomeSum = willIncomeSum;
	}
	public BigDecimal getRealIncomeSum() {
		return realIncomeSum;
	}
	public void setRealIncomeSum(BigDecimal realIncomeSum) {
		this.realIncomeSum = realIncomeSum;
	}
	public BigDecimal getRealGrossProfit() {
		return realGrossProfit;
	}
	public void setRealGrossProfit(BigDecimal realGrossProfit) {
		this.realGrossProfit = realGrossProfit;
	}
	public String getRealGrossMargin() {
		return realGrossMargin;
	}
	public void setRealGrossMargin(String realGrossMargin) {
		this.realGrossMargin = realGrossMargin;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<SimpleSettlementViewModel> getAllSimpleSettlementViewModel(ArrayList<LocalTourTable> localTourTables){
		ArrayList<SimpleSettlementViewModel> simpleSettlementViewModels = new ArrayList<SimpleSettlementViewModel>();
		for (LocalTourTable localTourTable : localTourTables) {
			SimpleSettlementViewModel simpleSettlementViewModel = new SimpleSettlementViewModel();
			CostInfo costInfo = costService.getCostInfo(localTourTable.getId());
			CostInfo changeCostInfo = changeCostService.getCostInfo(localTourTable.getId());
			LoanInfo loanInfo = loanService.getLoanInfo(localTourTable.getId());
			IncomeInfo incomeInfo = incomeService.getIncomeInfo(localTourTable.getId());
			IncomeInfo changeIncomeInfo = changeIncomeService.getIncomeInfo(localTourTable.getId());
			IncomeInfo refundIncomeInfo = refundService.getIncomeInfo(localTourTable.getId());
			simpleSettlementViewModel.setLocalTourTable(localTourTable);
			simpleSettlementViewModel.setCostSum(costInfo.getCostSum().add(changeCostInfo.getCostSum()));
			simpleSettlementViewModel.setWillPaySum(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).add(loanInfo.getLoanSum()));
			simpleSettlementViewModel.setRealPaySum(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()).add(loanInfo.getRealLoanSum()));
			simpleSettlementViewModel.setReimbursementSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()));
			simpleSettlementViewModel.setWillIncomeSum(incomeInfo.getIncomeSum());
			simpleSettlementViewModel.setRealIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum().subtract(refundIncomeInfo.getRealIncomeSum())));
			simpleSettlementViewModel.setRealGrossProfit(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum())));
			if(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).compareTo(new BigDecimal(0))!=0){
				simpleSettlementViewModel.setRealGrossMargin((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()))).divide(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).toString()+"%");
			}
			simpleSettlementViewModel.setUserRealName(userService.getUserRealName(localTourTable.getUserId()));
			if(localTourTable.getStatus()==0){
				simpleSettlementViewModel.setStatus("新建");
			}else if(localTourTable.getStatus()==1){
				simpleSettlementViewModel.setStatus("已提交");
			}else if(localTourTable.getStatus()==2){
				simpleSettlementViewModel.setStatus("已报送");
			}else if(localTourTable.getStatus()==3){
				simpleSettlementViewModel.setStatus("可借款");
			}else if(localTourTable.getStatus()==4){
				simpleSettlementViewModel.setStatus("进行中");
			}else if(localTourTable.getStatus()==5){
				simpleSettlementViewModel.setStatus("已结束");
			}else if(localTourTable.getStatus()==6){
				simpleSettlementViewModel.setStatus("结算中");
			}else if(localTourTable.getStatus()==7){
				simpleSettlementViewModel.setStatus("已报账");
			}else if(localTourTable.getStatus()==8){
				simpleSettlementViewModel.setStatus("待核销");
			}else if(localTourTable.getStatus()==9){
				simpleSettlementViewModel.setStatus("待结算");
			}else if(localTourTable.getStatus()==10){
				simpleSettlementViewModel.setStatus("已结算");
			}
			simpleSettlementViewModels.add(simpleSettlementViewModel);
		}
		return simpleSettlementViewModels;
	}

}
