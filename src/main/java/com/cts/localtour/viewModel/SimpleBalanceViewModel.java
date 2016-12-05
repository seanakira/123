package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.pojo.IncomeInfo;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.UserService;

@Component
public class SimpleBalanceViewModel {
	private LocalTourTable localTourTable;
	private float willPaySum;
	private float realPaySum;
	private float willIncomeSum;
	private float realIncomeSum;
	private float reimbursementSum;
	private String status;
	private String userRealName;
	@Autowired
	private CostService costService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private UserService userService;
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
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
	public float getReimbursementSum() {
		return reimbursementSum;
	}
	public void setReimbursementSum(float reimbursementSum) {
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
			IncomeInfo incomeInfo = incomeService.getIncomeInfo(localTourTable.getId());
			balanceViewModel.setWillPaySum(costInfo.getWillCostSum().floatValue());
			balanceViewModel.setRealPaySum(costInfo.getRealCostSum().floatValue());
			balanceViewModel.setWillIncomeSum(incomeInfo.getRealIncomeSum().floatValue());
			balanceViewModel.setRealIncomeSum(incomeInfo.getIncomedSum().floatValue());
			balanceViewModel.setReimbursementSum(costInfo.getReimbursementSum().floatValue());
			balanceViewModel.setUserRealName(userService.getUserRealName(localTourTable.getUserId()));
			if(localTourTable.getStatus()==0){
				balanceViewModel.setStatus("�½�");
			}else if(localTourTable.getStatus()==1){
				balanceViewModel.setStatus("���ύ");
			}else if(localTourTable.getStatus()==2){
				balanceViewModel.setStatus("�ѱ���");
			}else if(localTourTable.getStatus()==3){
				balanceViewModel.setStatus("�ɽ��");
			}else if(localTourTable.getStatus()==4){
				balanceViewModel.setStatus("������");
			}else if(localTourTable.getStatus()==5){
				balanceViewModel.setStatus("�ѽ���");
			}else if(localTourTable.getStatus()==6){
				balanceViewModel.setStatus("������");
			}else if(localTourTable.getStatus()==7){
				balanceViewModel.setStatus("�ѱ���");
			}else if(localTourTable.getStatus()==8){
				balanceViewModel.setStatus("�Ѻ���");
			}else if(localTourTable.getStatus()==9){
				balanceViewModel.setStatus("�ѽ���");
			}
			balanceViewModels.add(balanceViewModel);
		}
		return balanceViewModels;
	}
}