package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;
@Component
public class ChangeIncomeViewModel {
	private ChangeIncomeTable incomeTable;
	private String customerAgencyName;
	private String applicationerRealName;
	private String handlerRealName;
	private String status;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private UserService userService;
	public ChangeIncomeTable getIncomeTable() {
		return incomeTable;
	}
	public void setIncomeTable(ChangeIncomeTable incomeTable) {
		this.incomeTable = incomeTable;
	}
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	public String getApplicationerRealName() {
		return applicationerRealName;
	}
	public void setApplicationerRealName(String applicationerRealName) {
		this.applicationerRealName = applicationerRealName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHandlerRealName() {
		return handlerRealName;
	}
	public void setHandlerRealName(String handlerRealName) {
		this.handlerRealName = handlerRealName;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<ChangeIncomeViewModel> getAllChangeIncomeViewModel(int tourId){
		ArrayList<ChangeIncomeTable> incomeTables = (ArrayList<ChangeIncomeTable>) baseService.getAllByString("ChangeIncomeTable", "tourId=?", tourId);
		ArrayList<ChangeIncomeViewModel> incomes = new ArrayList<ChangeIncomeViewModel>();
		for (int i = 0; i < incomeTables.size(); i++) {
			ChangeIncomeViewModel income = new ChangeIncomeViewModel();
			income.setIncomeTable(incomeTables.get(i));
			income.setCustomerAgencyName(((CustomerAgencyTable)baseService.getById("CustomerAgencyTable", incomeTables.get(i).getCustomerAgencyId())).getCustomerAgencyName());
			incomes.add(income);
		}
		return incomes;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<ChangeIncomeViewModel> getAllChangeIncomeViewModel(int tourId, int status){
		ArrayList<ChangeIncomeTable> incomeTables;
		/*这里需要权限判断  如果是总经理 status>2*/
		if(status==-1){
			incomeTables = (ArrayList<ChangeIncomeTable>)baseService.getAllByString("ChangeIncomeTable", "tourId=? and status>1", tourId);
		}else{
			incomeTables = (ArrayList<ChangeIncomeTable>)baseService.getAllByString("ChangeIncomeTable", "tourId=? and status=?", tourId, status);
		}
		ArrayList<ChangeIncomeViewModel> incomes = new ArrayList<ChangeIncomeViewModel>();
		for (ChangeIncomeTable incomeTable : incomeTables) {
			ChangeIncomeViewModel income = new ChangeIncomeViewModel();
			income.setIncomeTable(incomeTable);
			income.setCustomerAgencyName(((CustomerAgencyTable)baseService.getById("CustomerAgencyTable", incomeTable.getCustomerAgencyId())).getCustomerAgencyName());
			income.setApplicationerRealName(userService.getUserRealName(incomeTable.getApplicationerId()));
			income.setHandlerRealName(userService.getUserRealName(incomeTable.getHandlerId()));
			income.setStatus(incomeTable.getStatus()==0?"新建":incomeTable.getStatus()==1?"待审核":incomeTable.getStatus()==2?"待批准":incomeTable.getStatus()==3?"已批准":"");
			incomes.add(income);
		}
		return incomes;
	}
}
