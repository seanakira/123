package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;
@Component
public class IncomeViewModel {
	private IncomeTable incomeTable;
	private String customerAgencyName;
	private String handlerRealName;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	UserService userService;
	public IncomeTable getIncomeTable() {
		return incomeTable;
	}
	public void setIncomeTable(IncomeTable incomeTable) {
		this.incomeTable = incomeTable;
	}
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	public String getHandlerRealName() {
		return handlerRealName;
	}
	public void setHandlerRealName(String handlerRealName) {
		this.handlerRealName = handlerRealName;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<IncomeViewModel> getAllIncomeViewModel(int tourId){
		ArrayList<IncomeTable> incomeTables = (ArrayList<IncomeTable>) baseService.getAllByString("IncomeTable", "tourId=?", tourId);
		ArrayList<IncomeViewModel> incomes = new ArrayList<IncomeViewModel>();
		for (int i = 0; i < incomeTables.size(); i++) {
			IncomeViewModel income = new IncomeViewModel();
			income.setIncomeTable(incomeTables.get(i));
			income.setCustomerAgencyName(((CustomerAgencyTable)baseService.getById("CustomerAgencyTable", incomeTables.get(i).getCustomerAgencyId())).getCustomerAgencyName());
			income.setHandlerRealName(incomeTables.get(i).getHandlerId()==null?"":userService.getUserRealName(incomeTables.get(i).getHandlerId()));
			incomes.add(income);
		}
		return incomes;
	}
}
