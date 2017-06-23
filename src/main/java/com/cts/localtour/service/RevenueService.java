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
import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.entity.LoanInvoiceTable;
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
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@Autowired
	private ReimbursementIncomeService reimbursementIncomeService;
	@Autowired
	private UserService userService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private LoanInvoiceService loanInvoiceService;
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
				errorCode = -1;保存失败
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
	
	@SuppressWarnings("unchecked")
	public boolean invoiceGreaterThanIncome(ArrayList<InvoiceTable> invoiceTables){
		if(!invoiceTables.isEmpty()){
			BigDecimal newInvoiceSum = new BigDecimal(0);
			ArrayList<Integer> notIds = new ArrayList<Integer>();
			for (InvoiceTable invoiceTable : invoiceTables) {
				newInvoiceSum = newInvoiceSum.add(invoiceTable.getInvoiceAmount());
				/*验证发票是否8位*/
				/*if(loanInvoiceTable.getInvoiceNo().length()!=8){
					errorCode = -2;
				}*/
				notIds.add(invoiceTable.getId());
			}
			ArrayList<InvoiceTable> issueInvoiceTables = (ArrayList<InvoiceTable>) this.getAllByString("InvoiceTable", "tourId=? and id not in("+notIds.toString().substring(1, notIds.toString().length()-1)+")", invoiceTables.get(0).getTourId());
			for (InvoiceTable invoiceTable : issueInvoiceTables) {
				newInvoiceSum = newInvoiceSum.add(invoiceTable.getInvoiceAmount());
			}
			newInvoiceSum = newInvoiceSum.add(loanInvoiceService.getLoanInvoiceSum(invoiceTables.get(0).getTourId()));
			BigDecimal willIncomeSum = incomeService.getIncomeInfo(invoiceTables.get(0).getTourId()).getIncomeSum().add(changeIncomeService.getIncomeInfo(invoiceTables.get(0).getTourId()).getIncomeSum()).add(reimbursementIncomeService.getIncomeInfo(invoiceTables.get(0).getTourId()).getIncomeSum());
			if(newInvoiceSum.compareTo(willIncomeSum)==1){
				ChangeIncomeTable changeIncomeTable = new ChangeIncomeTable();
				changeIncomeTable.setTourId(invoiceTables.get(0).getTourId());
				changeIncomeTable.setCustomerAgencyId(!issueInvoiceTables.isEmpty()?issueInvoiceTables.get(0).getCustomerAgencyId():((LocalTourTable)this.getById("LocalTourTable", invoiceTables.get(0).getTourId())).getCustomerAgencyId());
				changeIncomeTable.setIncome(newInvoiceSum.subtract(willIncomeSum));
				changeIncomeTable.setRealIncome(new BigDecimal(0));
				changeIncomeTable.setStatus(3);
				changeIncomeTable.setRemark("开票调整");
				this.add(changeIncomeTable);
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean loanInvoiceGreaterThanIncome(ArrayList<LoanInvoiceTable> loanInvoiceTables){
		if(!loanInvoiceTables.isEmpty()){
			BigDecimal newInvoiceSum = new BigDecimal(0);
			ArrayList<Integer> notIds = new ArrayList<Integer>();
			for (LoanInvoiceTable loanInvoiceTable : loanInvoiceTables) {
				newInvoiceSum = newInvoiceSum.add(loanInvoiceTable.getInvoiceAmount());
				/*验证发票是否8位*/
				/*if(loanInvoiceTable.getInvoiceNo().length()!=8){
					errorCode = -2;
				}*/
				notIds.add(loanInvoiceTable.getId());
			}
			ArrayList<LoanInvoiceTable> issueLoanInvoiceTables = (ArrayList<LoanInvoiceTable>) this.getAllByString("LoanInvoiceTable", "tourId=? and status=? and id not in("+notIds.toString().substring(1, notIds.toString().length()-1)+")", loanInvoiceTables.get(0).getTourId(), 3);
			for (LoanInvoiceTable loanInvoiceTable : issueLoanInvoiceTables) {
				newInvoiceSum = newInvoiceSum.add(loanInvoiceTable.getInvoiceAmount());
			}
			newInvoiceSum = newInvoiceSum.add(invoiceService.getInvoiceSum(loanInvoiceTables.get(0).getTourId()));
			BigDecimal willIncomeSum = incomeService.getIncomeInfo(loanInvoiceTables.get(0).getTourId()).getIncomeSum().add(changeIncomeService.getIncomeInfo(loanInvoiceTables.get(0).getTourId()).getIncomeSum()).add(reimbursementIncomeService.getIncomeInfo(loanInvoiceTables.get(0).getTourId()).getIncomeSum());
			if(newInvoiceSum.compareTo(willIncomeSum)==1){
				ChangeIncomeTable changeIncomeTable = new ChangeIncomeTable();
				changeIncomeTable.setTourId(loanInvoiceTables.get(0).getTourId());
				changeIncomeTable.setCustomerAgencyId(!issueLoanInvoiceTables.isEmpty()?issueLoanInvoiceTables.get(0).getCustomerAgencyId():((LocalTourTable)this.getById("LocalTourTable", loanInvoiceTables.get(0).getTourId())).getCustomerAgencyId());
				changeIncomeTable.setIncome(newInvoiceSum.subtract(willIncomeSum));
				changeIncomeTable.setRealIncome(new BigDecimal(0));
				changeIncomeTable.setStatus(3);
				changeIncomeTable.setRemark("借票调整");
				this.add(changeIncomeTable);
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
