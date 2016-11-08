package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.InvoiceService;
import com.cts.localtour.service.UserService;

@Component
public class SimpleRevenueViewModel {
	private LocalTourTable localTourTable;
	private float willIncome;
	private float realIncome;
	private float invoice;
	private String status;
	private String userRealName;
	@Autowired
	private UserService userService;
	@Autowired
	private BaseService baseService;
	@Autowired
	private InvoiceService invoiceService;
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
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
	public float getWillIncome() {
		return willIncome;
	}
	public void setWillIncome(float willIncome) {
		this.willIncome = willIncome;
	}
	public float getRealIncome() {
		return realIncome;
	}
	public void setRealIncome(float realIncome) {
		this.realIncome = realIncome;
	}
	public float getInvoice() {
		return invoice;
	}
	public void setInvoice(float invoice) {
		this.invoice = invoice;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleRevenueViewModel> getAllSimpleRevenueViewModel(ArrayList<LocalTourTable> localTours){
		ArrayList<SimpleRevenueViewModel> simpleRevenueViewModels = new ArrayList<SimpleRevenueViewModel>();
		for (LocalTourTable localTour : localTours) {
			SimpleRevenueViewModel simpleRevenueViewModel = new SimpleRevenueViewModel();
			simpleRevenueViewModel.setLocalTourTable(localTour);
			simpleRevenueViewModel.setUserRealName(userService.getUserRealName(localTour.getUserId()));
			BigDecimal willIncomeSum = new BigDecimal(0);
			BigDecimal realIncomeSum = new BigDecimal(0);
			ArrayList<IncomeTable> incomeTables = (ArrayList<IncomeTable>) baseService.getAllByString("IncomeTable", "tourId=?", localTour.getId());
			for (IncomeTable incomeTable : incomeTables) {
				willIncomeSum = willIncomeSum.add(new BigDecimal(incomeTable.getIncome()));
				realIncomeSum = realIncomeSum.add(new BigDecimal(incomeTable.getRealIncome()));
			}
			ArrayList<ChangeIncomeTable> changeIncomeTables = (ArrayList<ChangeIncomeTable>) baseService.getAllByString("ChangeIncomeTable", "tourId=? and status=3", localTour.getId());
			for (ChangeIncomeTable changeIncomeTable : changeIncomeTables) {
				willIncomeSum = willIncomeSum.add(new BigDecimal(changeIncomeTable.getIncome()));
				realIncomeSum = realIncomeSum.add(new BigDecimal(changeIncomeTable.getRealIncome()));
			}
			simpleRevenueViewModel.setRealIncome(realIncomeSum.floatValue());
			simpleRevenueViewModel.setWillIncome(willIncomeSum.floatValue());
			simpleRevenueViewModel.setInvoice(invoiceService.getInvoiceSum(localTour.getId()));
			if(localTour.getStatus()==0){
				simpleRevenueViewModel.setStatus("新建");
			}else if(localTour.getStatus()==1){
				simpleRevenueViewModel.setStatus("已提交");
			}else if(localTour.getStatus()==2){
				simpleRevenueViewModel.setStatus("已报送");
			}else if(localTour.getStatus()==3){
				simpleRevenueViewModel.setStatus("可借款");
			}else if(localTour.getStatus()==4){
				simpleRevenueViewModel.setStatus("进行中");
			}else if(localTour.getStatus()==5){
				simpleRevenueViewModel.setStatus("已结束");
			}else if(localTour.getStatus()==6){
				simpleRevenueViewModel.setStatus("结算中");
			}else if(localTour.getStatus()==7){
				simpleRevenueViewModel.setStatus("已核销");
			}else if(localTour.getStatus()==8){
				simpleRevenueViewModel.setStatus("已结算");
			}
			simpleRevenueViewModels.add(simpleRevenueViewModel);
		}
		return simpleRevenueViewModels;
		
	}
}
