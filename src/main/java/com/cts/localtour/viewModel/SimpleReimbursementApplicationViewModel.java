package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.ReimbursementApplicationTable;
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
import com.cts.localtour.service.UserService;

@Component
public class SimpleReimbursementApplicationViewModel {
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
	@SuppressWarnings({"rawtypes" })
	@Autowired
	private BaseService baseService;
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
	private ReimbursementCostService reimbursementCostService;
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
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<SimpleReimbursementApplicationViewModel> getAllSimpleReimbursementApplicationViewModel(ArrayList<ReimbursementApplicationTable> reimbursementApplicationTables){
		ArrayList<SimpleReimbursementApplicationViewModel> reimbursementApplicationViewModels = new ArrayList<SimpleReimbursementApplicationViewModel>();
		for (ReimbursementApplicationTable reimbursementApplicationTable : reimbursementApplicationTables) {
			SimpleReimbursementApplicationViewModel reimbursementApplicationViewModel = new SimpleReimbursementApplicationViewModel();
			CostInfo costInfo = costService.getCostInfo(reimbursementApplicationTable.getTourId());
			CostInfo changeCostInfo = changeCostService.getCostInfo(reimbursementApplicationTable.getTourId());
			CostInfo reimbursementCostInfo = reimbursementCostService.getReimbursementCostInfo(reimbursementApplicationTable.getTourId());
			IncomeInfo incomeInfo = incomeService.getIncomeInfo(reimbursementApplicationTable.getTourId());
			IncomeInfo changeIncomeInfo = changeIncomeService.getIncomeInfo(reimbursementApplicationTable.getTourId());
			IncomeInfo refundIncomeInfo = refundService.getIncomeInfo(localTourTable.getId());
			LoanInfo loanInfo = loanService.getLoanInfo(reimbursementApplicationTable.getTourId());
			LocalTourTable localTourTable = (LocalTourTable) baseService.getById("LocalTourTable", reimbursementApplicationTable.getTourId());
			reimbursementApplicationViewModel.setLocalTourTable(localTourTable);
			reimbursementApplicationViewModel.setCostSum(costInfo.getCostSum().add(changeCostInfo.getCostSum()).add(reimbursementCostInfo.getReimbursementSum()));
			reimbursementApplicationViewModel.setWillPaySum(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).add(reimbursementCostInfo.getWillCostSum()).add(loanInfo.getLoanSum()));
			reimbursementApplicationViewModel.setRealPaySum(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()).add(reimbursementCostInfo.getRealCostSum()).add(loanInfo.getRealLoanSum()));
			reimbursementApplicationViewModel.setReimbursementSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum()));
			reimbursementApplicationViewModel.setWillIncomeSum(incomeInfo.getIncomeSum());
			reimbursementApplicationViewModel.setRealIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum().subtract(refundIncomeInfo.getRealIncomeSum())));
			reimbursementApplicationViewModel.setRealGrossProfit(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum())));
			if(incomeInfo.getRealIncomeSum().compareTo(new BigDecimal(0))==0){
				reimbursementApplicationViewModel.setRealGrossMargin("0");
			}else{
				reimbursementApplicationViewModel.setRealGrossMargin((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum().subtract(refundIncomeInfo.getRealIncomeSum())).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()))).divide(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).toString()+"%");
			}
			reimbursementApplicationViewModel.setUserRealName(userService.getUserRealName(localTourTable.getUserId()));
			if(reimbursementApplicationTable.getStatus()==0){
				reimbursementApplicationViewModel.setStatus("¥˝…Û");
			}else{
				reimbursementApplicationViewModel.setStatus("“—…Û∫À");
			}
			reimbursementApplicationViewModels.add(reimbursementApplicationViewModel);
		}
		return reimbursementApplicationViewModels;
	}
}
