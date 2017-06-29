package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.pojo.IncomeInfo;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.CustomerAgencyService;
import com.cts.localtour.service.DeptService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.RefundService;
import com.cts.localtour.service.ReimbursementCostService;
import com.cts.localtour.service.ReimbursementIncomeService;
import com.cts.localtour.service.UserService;
@Component
public class SettlementTourStatisticViewModel {
	private String tourNo;
	private String tourName;
	private String customerAgencyName;
	private BigDecimal willIncomeSum;
	private BigDecimal realIncomeSum;
	private BigDecimal notIncomeSum;
	private BigDecimal reimbursementCostSum;
	private BigDecimal grossProfit;
	private String grossProfitMargin;
	private String userRealName;
	private float peopleNo;
	private String deptName;
	private Date settlementTime;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private CustomerAgencyService customerAgencyService;
	@Autowired
	private CostService costService;
	@Autowired
	private ChangeCostService changeCostService;
	@Autowired
	private ReimbursementCostService reimbursementCostService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@Autowired
	private ReimbursementIncomeService reimbursementIncomeService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	public String getTourNo() {
		return tourNo;
	}

	public void setTourNo(String tourNo) {
		this.tourNo = tourNo;
	}

	public String getCustomerAgencyName() {
		return customerAgencyName;
	}

	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}

	

	public BigDecimal getWillIncomeSum() {
		return willIncomeSum;
	}

	public void setWillIncomeSum(BigDecimal willIncomeSum) {
		this.willIncomeSum = willIncomeSum;
	}
	
	public BigDecimal getReimbursementCostSum() {
		return reimbursementCostSum;
	}

	public void setReimbursementCostSum(BigDecimal reimbursementCostSum) {
		this.reimbursementCostSum = reimbursementCostSum;
	}

	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}

	public String getGrossProfitMargin() {
		return grossProfitMargin;
	}

	public void setGrossProfitMargin(String grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}
	
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public float getPeopleNo() {
		return peopleNo;
	}

	public void setPeopleNo(float peopleNo) {
		this.peopleNo = peopleNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public BigDecimal getRealIncomeSum() {
		return realIncomeSum;
	}

	public void setRealIncomeSum(BigDecimal realIncomeSum) {
		this.realIncomeSum = realIncomeSum;
	}

	public BigDecimal getNotIncomeSum() {
		return notIncomeSum;
	}

	public void setNotIncomeSum(BigDecimal notIncomeSum) {
		this.notIncomeSum = notIncomeSum;
	}
	
	public Date getSettlementTime() {
		return settlementTime;
	}

	public void setSettlementTime(Date settlementDate) {
		this.settlementTime = settlementDate;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SettlementTourStatisticViewModel> getSettlementTourStatisticAll(Date start, Date end, String deptIds, String userIds, String tourNo, String status) {
		ArrayList<SettlementTourStatisticViewModel> settlementTourStatisticViewModels = new ArrayList<SettlementTourStatisticViewModel>();
		deptIds = deptService.getDownerDpetIds(deptIds);
		if("".equals(userIds)){
			userIds = userService.getDataUserIds();
		}
		ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) baseService.getAllByString("LocalTourTable", "deptId in ("+deptIds+") and userId in("+userIds+")"+("".equals(tourNo)?"":" and tourNo like '%"+tourNo+"%'")+("".equals(status)?" and startTime between ? and ? ":"10".equals(status)?" and status=10 and settlementTime between ? and ? ":" and status<10 and startTime between ? and ? "), start, end);
		for (LocalTourTable localTourTable : localTourTables) {
			SettlementTourStatisticViewModel settlementTourStatisticViewModel = new SettlementTourStatisticViewModel();
			settlementTourStatisticViewModel.setTourNo(localTourTable.getTourNo());
			settlementTourStatisticViewModel.setTourName(localTourTable.getTourName());
			settlementTourStatisticViewModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(localTourTable.getId()));
			IncomeInfo incomeInfo = incomeService.getIncomeInfo(localTourTable.getId());
			IncomeInfo changeIncomeInfo = changeIncomeService.getIncomeInfo(localTourTable.getId());
			IncomeInfo reimbursementIncomeInfo = reimbursementIncomeService.getIncomeInfo(localTourTable.getId());
			IncomeInfo refundIncomeInfo = refundService.getIncomeInfo(localTourTable.getId());
			settlementTourStatisticViewModel.setWillIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).add(reimbursementIncomeInfo.getIncomeSum()));
			settlementTourStatisticViewModel.setRealIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).subtract(refundIncomeInfo.getRealIncomeSum()));
			settlementTourStatisticViewModel.setNotIncomeSum(settlementTourStatisticViewModel.getWillIncomeSum().subtract(settlementTourStatisticViewModel.getRealIncomeSum()));
			settlementTourStatisticViewModel.setReimbursementCostSum(costService.getCostInfo(localTourTable.getId()).getReimbursementSum().add(changeCostService.getCostInfo(localTourTable.getId()).getReimbursementSum()).add(reimbursementCostService.getReimbursementCostInfo(localTourTable.getId()).getReimbursementSum()));
			settlementTourStatisticViewModel.setGrossProfit(settlementTourStatisticViewModel.getWillIncomeSum().subtract(settlementTourStatisticViewModel.getReimbursementCostSum()));
			if(settlementTourStatisticViewModel.getWillIncomeSum().compareTo(new BigDecimal(0))!=0){
				settlementTourStatisticViewModel.setGrossProfitMargin((settlementTourStatisticViewModel.getWillIncomeSum().subtract(settlementTourStatisticViewModel.getReimbursementCostSum())).divide(settlementTourStatisticViewModel.getWillIncomeSum(), 4).multiply(new BigDecimal(100))+"%");
			}else{
				settlementTourStatisticViewModel.setGrossProfitMargin("0%");
			}
			settlementTourStatisticViewModel.setUserRealName(userService.getUserRealName(localTourTable.getUserId()));
			settlementTourStatisticViewModel.setDeptName(deptService.getDeptName(localTourTable.getDeptId()));
			settlementTourStatisticViewModel.setPeopleNo(localTourTable.getAdultNo()+(localTourTable.getChildrenNo()==null?0:localTourTable.getChildrenNo()));
			settlementTourStatisticViewModel.setSettlementTime(localTourTable.getSettlementTime());
			settlementTourStatisticViewModels.add(settlementTourStatisticViewModel);
		}
		return settlementTourStatisticViewModels;
	}

}
