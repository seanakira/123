package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;
@Component
public class SimplPayViewModel {
	private LocalTourTable localTourTable;
	private float loan;
	private float remittance;
	private float cost;
	private float realPay;
	private float willPay;
	private String realName;
	private float sumCache;
	private String status;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
	}
	public float getLoan() {
		return loan;
	}
	public void setLoan(float loan) {
		this.loan = loan;
	}
	public float getRemittance() {
		return remittance;
	}
	public void setRemittance(float remittance) {
		this.remittance = remittance;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public float getSumCache() {
		return sumCache;
	}
	public void setSumCache(float sumCache) {
		this.sumCache = sumCache;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getRealPay() {
		return realPay;
	}
	public void setRealPay(float realPay) {
		this.realPay = realPay;
	}
	public float getWillPay() {
		return willPay;
	}
	public void setWillPay(float willPay) {
		this.willPay = willPay;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<SimplPayViewModel> getAllSimplPayViewModel(ArrayList<LocalTourTable> localTours){
		ArrayList<SimplPayViewModel> simplPayViewModels = new ArrayList<SimplPayViewModel>();
		for (int i = 0; i < localTours.size(); i++) {
			SimplPayViewModel simplPayViewModel = new SimplPayViewModel();
			
			ArrayList<CostTable> costTables = (ArrayList<CostTable>) baseService.getAllByString("CostTable", "tourId=?", localTours.get(i).getId());
			BigDecimal costSum = new BigDecimal(0.00);
			BigDecimal remittanceSum = new BigDecimal(0.00);
			BigDecimal realRemittanceSum = new BigDecimal(0.00);
			for (int j = 0; j < costTables.size(); j++) {
				BigDecimal cost = new BigDecimal(costTables.get(j).getCost());
				costSum =  costSum.add((cost.multiply(new BigDecimal(costTables.get(j).getCount())).multiply(new BigDecimal(costTables.get(j).getDays()))));
				remittanceSum = remittanceSum.add(new BigDecimal(costTables.get(j).getRealCost()));
				if(costTables.get(j).isRemittanced()){
					realRemittanceSum = realRemittanceSum.add(new BigDecimal(costTables.get(j).getRealCost()));
				}
			}
			
			ArrayList<ChangeCostTable> changeCostTables = (ArrayList<ChangeCostTable>) baseService.getAllByString("ChangeCostTable", "tourId=?", localTours.get(i).getId());
			for (ChangeCostTable changeCostTable : changeCostTables) {
				BigDecimal cost = new BigDecimal(changeCostTable.getCost());
				costSum =  costSum.add((cost.multiply(new BigDecimal(changeCostTable.getCount())).multiply(new BigDecimal(changeCostTable.getDays()))));
				remittanceSum = remittanceSum.add(new BigDecimal(changeCostTable.getRealCost()));
				if(changeCostTable.isRemittanced()){
					realRemittanceSum = realRemittanceSum.add(new BigDecimal(changeCostTable.getRealCost()));
				}
			}
			
			ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) baseService.getAllByString("LoanTable", "tourId=?", localTours.get(i).getId());
			BigDecimal loan = new BigDecimal(0.00);
			BigDecimal realLoan = new BigDecimal(0.00);
			for (int j = 0; j < loanTables.size(); j++) {
				loan = loan.add(new BigDecimal(loanTables.get(j).getLoanAmount()));
				if(loanTables.get(j).isLended()){
					realLoan = realLoan.add(new BigDecimal(loanTables.get(j).getLoanAmount()));
				}
			}
			
			simplPayViewModel.setLocalTourTable(localTours.get(i));
			simplPayViewModel.setCost(costSum.floatValue());
			simplPayViewModel.setRemittance(remittanceSum.floatValue());
			simplPayViewModel.setLoan(loan.floatValue());
			simplPayViewModel.setWillPay(loan.add(remittanceSum).floatValue());
			simplPayViewModel.setRealName(((UserTable) baseService.getById("UserTable", localTours.get(i).getUserId())).getRealName());
			simplPayViewModel.setRealPay(realRemittanceSum.add(realLoan).floatValue());
			if(localTours.get(i).getStatus()==2){
				simplPayViewModel.setStatus("已报送");
			}else if(localTours.get(i).getStatus()==3){
				simplPayViewModel.setStatus("可借款");
			}else if(localTours.get(i).getStatus()==4){
				simplPayViewModel.setStatus("进行中");
			}else if(localTours.get(i).getStatus()==5){
				simplPayViewModel.setStatus("已结束");
			}
			simplPayViewModels.add(simplPayViewModel);
		}
		return simplPayViewModels;
	}
}
