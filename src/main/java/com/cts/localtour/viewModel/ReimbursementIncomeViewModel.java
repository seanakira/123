package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.ReimbursementIncomeTable;
import com.cts.localtour.service.BaseService;
@Component
public class ReimbursementIncomeViewModel {
	private ReimbursementIncomeTable incomeTable;
	private String customerAgencyName;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	
	public ReimbursementIncomeTable getIncomeTable() {
		return incomeTable;
	}

	public void setIncomeTable(ReimbursementIncomeTable incomeTable) {
		this.incomeTable = incomeTable;
	}

	public String getCustomerAgencyName() {
		return customerAgencyName;
	}

	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ReimbursementIncomeViewModel> getAllIncomeViewModel(int tourId){
		ArrayList<ReimbursementIncomeTable> incomeTables = (ArrayList<ReimbursementIncomeTable>) baseService.getAllByString("ReimbursementIncomeTable", "tourId=?", tourId);
		ArrayList<ReimbursementIncomeViewModel> incomes = new ArrayList<ReimbursementIncomeViewModel>();
		for (int i = 0; i < incomeTables.size(); i++) {
			ReimbursementIncomeViewModel income = new ReimbursementIncomeViewModel();
			income.setIncomeTable(incomeTables.get(i));
			income.setCustomerAgencyName(((CustomerAgencyTable)baseService.getById("CustomerAgencyTable", incomeTables.get(i).getCustomerAgencyId())).getCustomerAgencyName());
			incomes.add(income);
		}
		return incomes;
	}
}
