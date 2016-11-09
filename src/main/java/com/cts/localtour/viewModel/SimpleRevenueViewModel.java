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
import com.cts.localtour.service.InvoiceService;
import com.cts.localtour.service.LoanInvoiceService;
import com.cts.localtour.service.RevenueService;
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
	private RevenueService revenueService;
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
			HashMap<String, Float> willAndReal = revenueService.getWillAndRealIncome(localTour.getId());
			simpleRevenueViewModel.setRealIncome(willAndReal.get("realIncome"));
			simpleRevenueViewModel.setWillIncome(willAndReal.get("willIncome"));
			simpleRevenueViewModel.setInvoice((new BigDecimal(invoiceService.getInvoiceSum(localTour.getId())).add(new BigDecimal(loanInvoiceService.getLoanInvoiceSum(localTour.getId())))).floatValue());
			if(localTour.getStatus()==0){
				simpleRevenueViewModel.setStatus("�½�");
			}else if(localTour.getStatus()==1){
				simpleRevenueViewModel.setStatus("���ύ");
			}else if(localTour.getStatus()==2){
				simpleRevenueViewModel.setStatus("�ѱ���");
			}else if(localTour.getStatus()==3){
				simpleRevenueViewModel.setStatus("�ɽ��");
			}else if(localTour.getStatus()==4){
				simpleRevenueViewModel.setStatus("������");
			}else if(localTour.getStatus()==5){
				simpleRevenueViewModel.setStatus("�ѽ���");
			}else if(localTour.getStatus()==6){
				simpleRevenueViewModel.setStatus("������");
			}else if(localTour.getStatus()==7){
				simpleRevenueViewModel.setStatus("�Ѻ���");
			}else if(localTour.getStatus()==8){
				simpleRevenueViewModel.setStatus("�ѽ���");
			}
			simpleRevenueViewModels.add(simpleRevenueViewModel);
		}
		return simpleRevenueViewModels;
	} 
}
