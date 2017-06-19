package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

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
	@Autowired
	private RefundService refundService;
	@Autowired
	private UserService userService;
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleRevenueViewModel> getAll(String key, int page, int maxResults, Date start, Date end, String deptIds, String userIds, int status) {
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		param.put("tourNO", "%"+key+"%");
		param.put("tourName", "%"+key+"%");
		param.put("start", start);
		param.put("end", end);
		ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "(tourNO like :tourNO or tourName like :tourName) and deptId in ("+("".equals(deptIds)?((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds():deptIds)+") and userId in ("+("".equals(userIds)?userService.getDataUserIds():userIds)+") and startTime between :start and :end"+(status==-1?" and status>=2 and enable=true":status==7?" and status>=7":" and status>=2 and status<7"), param, page, maxResults);
		return simpleRevenueViewModel.getAllSimpleRevenueViewModel(localTours);
	}
	
	@SuppressWarnings("unchecked")
	public int getCounts(String key, Date start, Date end, String deptIds, String userIds, int status) {
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		param.put("tourNO", "%"+key+"%");
		param.put("tourName", "%"+key+"%");
		param.put("start", start);
		param.put("end", end);
		return this.getCountsByParam("LocalTourTable", "(tourNO like :tourNO or tourName like :tourName) and deptId in ("+("".equals(deptIds)?((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds():deptIds)+") and userId in ("+("".equals(userIds)?userService.getDataUserIds():userIds)+") and startTime between :start and :end"+(status==-1?" and status>=2 and enable=true":status==7?" and status>=7":" and status>=2 and status<7"), param);
	}

	public FullRevenueViewModel findRevenue(int tourId) {
		return fullRevenueViewModel.getFullRevenueViewModel(tourId);
	}

	@SuppressWarnings("unchecked")
	public int updateRevenue(FullRevenueViewModel fullRevenueViewModel) {
		int errorCode = 0;
		for (IncomeTable incomeTable : fullRevenueViewModel.getIncomeTables()) {
			IncomeTable income = (IncomeTable)this.getById("IncomeTable", incomeTable.getId());
			/*if(income.getRealIncome()==0){*/
				income.setRealIncome(incomeTable.getRealIncome());
				income.setRemark(incomeTable.getRemark());
				income.setHandlerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
				this.update(income);
			/*}else{
				errorCode = -1;±£´æÊ§°Ü
			}*/
		}
		for (ChangeIncomeTable incomeTable : fullRevenueViewModel.getChangeIncomeTables()) {
			if(incomeTable.getId()==null){
				incomeTable.setHandlerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
				incomeTable.setIncome(new BigDecimal(0));
				incomeTable.setStatus(3);
				this.add(incomeTable);
			}else{
				ChangeIncomeTable income = (ChangeIncomeTable)this.getById("ChangeIncomeTable", incomeTable.getId());
				income.setRealIncome(incomeTable.getRealIncome());
				income.setRemark(incomeTable.getRemark());
				income.setHandlerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
				this.update(income);
			}
		}
		return errorCode;
	}
	
	public boolean invoiceGreaterThanIncome(BigDecimal newInvoiceSum, int tourId){
		if(newInvoiceSum.add(invoiceService.getInvoiceSum(tourId)).compareTo(incomeService.getIncomeInfo(tourId).getRealIncomeSum().add(changeIncomeService.getIncomeInfo(tourId).getRealIncomeSum()).add(refundService.getIncomeInfo(tourId).getRealIncomeSum()))==1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean loanInvoiceGreaterThanIncome(BigDecimal newInvoiceSum, int tourId){
		if(newInvoiceSum.compareTo(incomeService.getIncomeInfo(tourId).getIncomeSum().add(changeIncomeService.getIncomeInfo(tourId).getIncomeSum()).add(refundService.getIncomeInfo(tourId).getRealIncomeSum()))==1){
			return true;
		}else{
			return false;
		}
	}
}
