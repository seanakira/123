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
	private float costSum;
	private float willPaySum;
	private float realPaySum;
	private float reimbursementSum;
	private float willIncomeSum;
	private float realIncomeSum;
	private float realGrossProfit;
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
	public float getCostSum() {
		return costSum;
	}
	public void setCostSum(float costSum) {
		this.costSum = costSum;
	}
	public float getWillPaySum() {
		return willPaySum;
	}
	public void setWillPaySum(float willPaySum) {
		this.willPaySum = willPaySum;
	}
	public float getRealPaySum() {
		return realPaySum;
	}
	public void setRealPaySum(float realPaySum) {
		this.realPaySum = realPaySum;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public float getReimbursementSum() {
		return reimbursementSum;
	}
	public void setReimbursementSum(float reimbursementSum) {
		this.reimbursementSum = reimbursementSum;
	}
	public float getWillIncomeSum() {
		return willIncomeSum;
	}
	public void setWillIncomeSum(float willIncomeSum) {
		this.willIncomeSum = willIncomeSum;
	}
	public float getRealIncomeSum() {
		return realIncomeSum;
	}
	public void setRealIncomeSum(float realIncomeSum) {
		this.realIncomeSum = realIncomeSum;
	}
	public float getRealGrossProfit() {
		return realGrossProfit;
	}
	public void setRealGrossProfit(float realGrossProfit) {
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
			reimbursementApplicationViewModel.setCostSum(costInfo.getCostSum().add(changeCostInfo.getCostSum()).add(reimbursementCostInfo.getReimbursementSum()).floatValue());
			reimbursementApplicationViewModel.setWillPaySum(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).add(reimbursementCostInfo.getWillCostSum()).add(loanInfo.getLoanSum()).floatValue());
			reimbursementApplicationViewModel.setRealPaySum(costInfo.getRealCostSum().add(changeCostInfo.getRealCostSum()).add(reimbursementCostInfo.getRealCostSum()).add(loanInfo.getRealLoanSum()).floatValue());
			reimbursementApplicationViewModel.setReimbursementSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum()).floatValue());
			reimbursementApplicationViewModel.setWillIncomeSum(incomeInfo.getIncomeSum().floatValue());
			reimbursementApplicationViewModel.setRealIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum().add(refundIncomeInfo.getRealIncomeSum())).floatValue());
			reimbursementApplicationViewModel.setRealGrossProfit(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum())).floatValue());
			if(incomeInfo.getRealIncomeSum().floatValue()==0){
				reimbursementApplicationViewModel.setRealGrossMargin("0");
			}else{
				reimbursementApplicationViewModel.setRealGrossMargin((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum().add(refundIncomeInfo.getRealIncomeSum())).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()))).divide(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).toString()+"%");
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
