package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.InvoiceService;
import com.cts.localtour.service.LoanInvoiceService;
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
	private InvoiceService invoiceService;
	@Autowired
	private LoanInvoiceService loanInvoiceService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
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
	public ArrayList<SimpleRevenueViewModel> getAllSimpleRevenueViewModel(ArrayList<LocalTourTable> localTours){
		ArrayList<SimpleRevenueViewModel> simpleRevenueViewModels = new ArrayList<SimpleRevenueViewModel>();
		for (LocalTourTable localTour : localTours) {
			SimpleRevenueViewModel simpleRevenueViewModel = new SimpleRevenueViewModel();
			simpleRevenueViewModel.setLocalTourTable(localTour);
			simpleRevenueViewModel.setUserRealName(userService.getUserRealName(localTour.getUserId()));
			simpleRevenueViewModel.setRealIncome(incomeService.getIncomeInfo(localTour.getId()).getRealIncomeSum().add(changeIncomeService.getIncomeInfo(localTour.getId()).getRealIncomeSum()).floatValue());
			simpleRevenueViewModel.setWillIncome(incomeService.getIncomeInfo(localTour.getId()).getIncomeSum().add(changeIncomeService.getIncomeInfo(localTour.getId()).getIncomeSum()).floatValue());
			simpleRevenueViewModel.setInvoice((new BigDecimal(invoiceService.getInvoiceSum(localTour.getId())).add(new BigDecimal(loanInvoiceService.getLoanInvoiceSum(localTour.getId())))).floatValue());
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
				simpleRevenueViewModel.setStatus("已报账");
			}else if(localTour.getStatus()==8){
				simpleRevenueViewModel.setStatus("待核销");
			}else if(localTour.getStatus()==9){
				simpleRevenueViewModel.setStatus("待结算");
			}else if(localTour.getStatus()==10){
				simpleRevenueViewModel.setStatus("已结算");
			}
			simpleRevenueViewModels.add(simpleRevenueViewModel);
		}
		return simpleRevenueViewModels;
	} 
}
