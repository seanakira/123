package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.service.InvoiceService;
@Component
public class FullRevenueViewModel {
	private ArrayList<IncomeViewModel> incomes;
	private ArrayList<ChangeIncomeViewModel> changeIncomes;
	private ArrayList<IncomeTable> incomeTables;
	private ArrayList<ChangeIncomeTable> changeIncomeTables;
	private float invoiceSum;
	@Autowired
	private IncomeViewModel incomeViewModel;
	@Autowired
	private ChangeIncomeViewModel changeIncomeViewModel;
	@Autowired
	private InvoiceService invoiceService;
	public ArrayList<IncomeViewModel> getIncomes() {
		return incomes;
	}
	public void setIncomes(ArrayList<IncomeViewModel> incomes) {
		this.incomes = incomes;
	}
	public ArrayList<ChangeIncomeViewModel> getChangeIncomes() {
		return changeIncomes;
	}
	public void setChangeIncomes(ArrayList<ChangeIncomeViewModel> changeIncomes) {
		this.changeIncomes = changeIncomes;
	}
	public float getInvoiceSum() {
		return invoiceSum;
	}
	public void setInvoiceSum(float invoiceSum) {
		this.invoiceSum = invoiceSum;
	}
	
	public ArrayList<IncomeTable> getIncomeTables() {
		return incomeTables;
	}
	public void setIncomeTables(ArrayList<IncomeTable> incomeTables) {
		this.incomeTables = incomeTables;
	}
	public ArrayList<ChangeIncomeTable> getChangeIncomeTables() {
		return changeIncomeTables;
	}
	public void setChangeIncomeTables(ArrayList<ChangeIncomeTable> changeIncomeTables) {
		this.changeIncomeTables = changeIncomeTables;
	}
	public FullRevenueViewModel getFullRevenueViewModel(int tourId){
		FullRevenueViewModel fullRevenueViewModel = new FullRevenueViewModel();
		fullRevenueViewModel.setIncomes(incomeViewModel.getAllIncomeViewModel(tourId));
		fullRevenueViewModel.setChangeIncomes(changeIncomeViewModel.getAllChangeIncomeViewModel(tourId,3));
		fullRevenueViewModel.setInvoiceSum(invoiceService.getInvoiceSum(tourId));
		return fullRevenueViewModel;
	}
}
