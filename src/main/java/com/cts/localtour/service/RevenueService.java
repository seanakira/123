package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

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
	public int updateRevenue(FullRevenueViewModel fullRevenueViewModel, HttpSession session) {
		int errorCode = 0;
		for (IncomeTable incomeTable : fullRevenueViewModel.getIncomeTables()) {
			IncomeTable income = (IncomeTable)this.getById("IncomeTable", incomeTable.getId());
			if(income.getRealIncome()==0){
				income.setRealIncome(incomeTable.getRealIncome());
				income.setRemark(incomeTable.getRemark());
				income.setHandlerId(((UserTable)session.getAttribute("user")).getId());
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
				income.setHandlerId(((UserTable)session.getAttribute("user")).getId());
				this.update(income);
			}else{
				errorCode = -1;/*±£´æÊ§°Ü*/
			}
		}
		return errorCode;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Float> getWillAndRealIncome(int tourId){
		HashMap<String, Float> willAndRealIncome = new HashMap<String, Float>();
		BigDecimal willIncomeSum = new BigDecimal(0);
		BigDecimal realIncomeSum = new BigDecimal(0);
		ArrayList<IncomeTable> incomeTables = (ArrayList<IncomeTable>) this.getAllByString("IncomeTable", "tourId=?", tourId);
		for (IncomeTable incomeTable : incomeTables) {
			willIncomeSum = willIncomeSum.add(new BigDecimal(incomeTable.getIncome()));
			realIncomeSum = realIncomeSum.add(new BigDecimal(incomeTable.getRealIncome()));
		}
		ArrayList<ChangeIncomeTable> changeIncomeTables = (ArrayList<ChangeIncomeTable>) this.getAllByString("ChangeIncomeTable", "tourId=? and status=3", tourId);
		for (ChangeIncomeTable changeIncomeTable : changeIncomeTables) {
			willIncomeSum = willIncomeSum.add(new BigDecimal(changeIncomeTable.getIncome()));
			realIncomeSum = realIncomeSum.add(new BigDecimal(changeIncomeTable.getRealIncome()));
		}
		willAndRealIncome.put("willIncome", willIncomeSum.floatValue());
		willAndRealIncome.put("realIncome", realIncomeSum.floatValue());
		return willAndRealIncome;
	}
	
	public boolean InvoiceGreaterThanIncome(float newInvoiceSum, int tourId){
		if(newInvoiceSum+invoiceService.getInvoiceSum(tourId)+loanInvoiceService.getLoanInvoiceSum(tourId)>this.getWillAndRealIncome(tourId).get("realIncome")){
			return true;
		}else{
			return false;
		}
	}
}
