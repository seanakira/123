package com.cts.localtour.service;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.FullRevenueViewModel;
import com.cts.localtour.viewModel.SimpleRevenueViewModel;

@SuppressWarnings("rawtypes")
@Service
public class RevenueService extends BaseService{
	@Autowired
	private SimpleRevenueViewModel simpleRevenueViewModel;
	@Autowired
	private FullRevenueViewModel fullRevenueViewModel;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private LoanInvoiceService loanInvoiceService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleRevenueViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "status>=2 and status<=7 and enable=true", null, page, maxResults);
			return simpleRevenueViewModel.getAllSimpleRevenueViewModel(localTours);
		}else{
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "(tourNO like '%"+key+"%' or tourName like '%"+key+"%') and status>=2 and status<=7 and enable=true", null, page, maxResults);
			return simpleRevenueViewModel.getAllSimpleRevenueViewModel(localTours);
		}
	}
	
	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsByParam("LocalTourTable", "status>=2 and status<=7", null);
		}else{
			return this.getCountsByParam("LocalTourTable", "(tourNO like '%"+key+"%' or tourName like '%"+key+"%') and status>=2 and status<=7", null);
		}
	}

	public FullRevenueViewModel findRevenue(int tourId) {
		return fullRevenueViewModel.getFullRevenueViewModel(tourId);
	}

	@SuppressWarnings("unchecked")
	public int updateRevenue(FullRevenueViewModel fullRevenueViewModel) {
		int errorCode = 0;
		for (IncomeTable incomeTable : fullRevenueViewModel.getIncomeTables()) {
			IncomeTable income = (IncomeTable)this.getById("IncomeTable", incomeTable.getId());
			if(income.getRealIncome()==0){
				income.setRealIncome(incomeTable.getRealIncome());
				income.setRemark(incomeTable.getRemark());
				income.setHandlerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
				this.update(income);
			}else{
				errorCode = -1;/*±£´æÊ§°Ü*/
			}
		}
		for (ChangeIncomeTable incomeTable : fullRevenueViewModel.getChangeIncomeTables()) {
			ChangeIncomeTable income = (ChangeIncomeTable)this.getById("ChangeIncomeTable", incomeTable.getId());
			if(income.getRealIncome()==0){
				income.setRealIncome(incomeTable.getRealIncome());
				income.setRemark(incomeTable.getRemark());
				income.setHandlerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
				this.update(income);
			}else{
				errorCode = -1;/*±£´æÊ§°Ü*/
			}
		}
		return errorCode;
	}
	
	public boolean invoiceGreaterThanIncome(float newInvoiceSum, int tourId){
		if(newInvoiceSum+invoiceService.getInvoiceSum(tourId)+loanInvoiceService.getLoanInvoiceSum(tourId)>(incomeService.getIncomeInfo(tourId).getRealIncomeSum().add(changeIncomeService.getIncomeInfo(tourId).getRealIncomeSum()).floatValue())){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean loanInvoiceGreaterThanIncome(float newInvoiceSum, int tourId){
		if(newInvoiceSum+invoiceService.getInvoiceSum(tourId)+loanInvoiceService.getLoanInvoiceSum(tourId)>(incomeService.getIncomeInfo(tourId).getIncomeSum().add(changeIncomeService.getIncomeInfo(tourId).getIncomeSum()).floatValue())){
			return true;
		}else{
			return false;
		}
	}
}
