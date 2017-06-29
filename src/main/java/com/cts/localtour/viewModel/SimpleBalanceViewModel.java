package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.pojo.IncomeInfo;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.RefundService;
import com.cts.localtour.service.ReimbursementCostService;
import com.cts.localtour.service.UserService;

@Component
public class SimpleBalanceViewModel {
	private LocalTourTable localTourTable;
	private BigDecimal willPaySum;
	private BigDecimal realPaySum;
	private BigDecimal willIncomeSum;
	private BigDecimal realIncomeSum;
	private BigDecimal reimbursementSum;
	private String status;
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
	private UserService userService;
	@Autowired
	private ReimbursementCostService reimbursementCostService;
	@Autowired
	private RefundService refundService;
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
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
	public BigDecimal getReimbursementSum() {
		return reimbursementSum;
	}
	public void setReimbursementSum(BigDecimal reimbursementSum) {
		this.reimbursementSum = reimbursementSum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public ArrayList<SimpleBalanceViewModel> getAllSimpleRevenueViewModel(ArrayList<LocalTourTable> localTours) {
		ArrayList<SimpleBalanceViewModel> balanceViewModels = new ArrayList<SimpleBalanceViewModel>();
		for (LocalTourTable localTourTable : localTours) {
			SimpleBalanceViewModel balanceViewModel = new SimpleBalanceViewModel();
			balanceViewModel.setLocalTourTable(localTourTable);
			CostInfo costInfo = costService.getCostInfo(localTourTable.getId());
			CostInfo changeCostInfo = changeCostService.getCostInfo(localTourTable.getId());
			CostInfo reimbursementCostInfo = reimbursementCostService.getReimbursementCostInfo(localTourTable.getId());
			IncomeInfo incomeInfo = incomeService.getIncomeInfo(localTourTable.getId());
			IncomeInfo changeIncomeInfo = changeIncomeService.getIncomeInfo(localTourTable.getId());
			IncomeInfo refundIncomeInfo = refundService.getIncomeInfo(localTourTable.getId());
			balanceViewModel.setWillPaySum(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()));
			balanceViewModel.setRealPaySum(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()));
			balanceViewModel.setWillIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()));
			balanceViewModel.setRealIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum().subtract(refundIncomeInfo.getRealIncomeSum())));
			balanceViewModel.setReimbursementSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum().add(reimbursementCostInfo.getReimbursementSum())));
			balanceViewModel.setUserRealName(userService.getUserRealName(localTourTable.getUserId()));
			if(localTourTable.getStatus()==0){
				balanceViewModel.setStatus("新建");
			}else if(localTourTable.getStatus()==1){
				balanceViewModel.setStatus("已提交");
			}else if(localTourTable.getStatus()==2){
				balanceViewModel.setStatus("已报送");
			}else if(localTourTable.getStatus()==3){
				balanceViewModel.setStatus("可借款");
			}else if(localTourTable.getStatus()==4){
				balanceViewModel.setStatus("进行中");
			}else if(localTourTable.getStatus()==5){
				balanceViewModel.setStatus("已结束");
			}else if(localTourTable.getStatus()==6){
				balanceViewModel.setStatus("结算中");
			}else if(localTourTable.getStatus()==7){
				balanceViewModel.setStatus("已报账");
			}else if(localTourTable.getStatus()==8){
				balanceViewModel.setStatus("待核销");
			}else if(localTourTable.getStatus()==9){
				balanceViewModel.setStatus("待结算");
			}else if(localTourTable.getStatus()==10){
				balanceViewModel.setStatus("已结算");
			}
			balanceViewModels.add(balanceViewModel);
		}
		return balanceViewModels;
	}
}
