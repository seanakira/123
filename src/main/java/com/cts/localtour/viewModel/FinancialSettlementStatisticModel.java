package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.CustomerAgencyService;
import com.cts.localtour.service.DeptService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.ReimbursementCostService;
import com.cts.localtour.service.ReimbursementIncomeService;
import com.cts.localtour.service.UserService;
@Component
public class FinancialSettlementStatisticModel {
	private String tourNo;
	private String customerAgencyName;
	private BigDecimal willIncomeSum;
	private BigDecimal willCostSum;
	private BigDecimal grossProfit;
	private String grossProfitMargin;
	private String userRealName;
	private float peopleNo;
	private String deptName;
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

	public BigDecimal getWillCostSum() {
		return willCostSum;
	}

	public void setWillCostSum(BigDecimal willCostSum) {
		this.willCostSum = willCostSum;
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

	@SuppressWarnings("unchecked")
	public ArrayList<FinancialSettlementStatisticModel> getFinancialSettlementStatisticAll(Date start, Date end, String deptIds, String userIds, String tourNo, String status) {
		ArrayList<FinancialSettlementStatisticModel> financialSettlementStatisticModels = new ArrayList<FinancialSettlementStatisticModel>();
		deptIds = deptService.getDownerDpetIds(deptIds);
		if("".equals(userIds)){
			userIds = userService.getDataUserIds();
		}
		ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) baseService.getAllByString("LocalTourTable", "deptId in ("+deptIds+") and userId in("+userIds+")"+("".equals(tourNo)?"":" and tourNo like '%"+tourNo+"%'")+("".equals(status)?" and startTime between ? and ? ":"10".equals(status)?" and status=10 and settlementTime between ? and ? ":" and status<10 and startTime between ? and ? "), start, end);
		for (LocalTourTable localTourTable : localTourTables) {
			FinancialSettlementStatisticModel financialSettlementStatisticModel = new FinancialSettlementStatisticModel();
			financialSettlementStatisticModel.setTourNo(localTourTable.getTourNo());
			financialSettlementStatisticModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(localTourTable.getId()));
			financialSettlementStatisticModel.setWillIncomeSum(incomeService.getIncomeInfo(localTourTable.getId()).getIncomeSum().add(changeIncomeService.getIncomeInfo(localTourTable.getId()).getIncomeSum()).add(reimbursementIncomeService.getIncomeInfo(localTourTable.getId()).getIncomeSum()));
			financialSettlementStatisticModel.setWillCostSum(costService.getCostInfo(localTourTable.getId()).getReimbursementSum().add(changeCostService.getCostInfo(localTourTable.getId()).getReimbursementSum()).add(reimbursementCostService.getReimbursementCostInfo(localTourTable.getId()).getReimbursementSum()));
			financialSettlementStatisticModel.setGrossProfit(financialSettlementStatisticModel.getWillIncomeSum().subtract(financialSettlementStatisticModel.getWillCostSum()));
			if(financialSettlementStatisticModel.getWillIncomeSum().floatValue()!=0){
				financialSettlementStatisticModel.setGrossProfitMargin((financialSettlementStatisticModel.getWillIncomeSum().subtract(financialSettlementStatisticModel.getWillCostSum())).divide(financialSettlementStatisticModel.getWillIncomeSum(), 4).multiply(new BigDecimal(100))+"%");
			}else{
				financialSettlementStatisticModel.setGrossProfitMargin("0%");
			}
			financialSettlementStatisticModel.setUserRealName(userService.getUserRealName(localTourTable.getUserId()));
			financialSettlementStatisticModel.setDeptName(deptService.getDeptName(localTourTable.getDeptId()));
			financialSettlementStatisticModel.setPeopleNo(localTourTable.getAdultNo()+(localTourTable.getChildrenNo()==null?0:localTourTable.getChildrenNo()));
			financialSettlementStatisticModels.add(financialSettlementStatisticModel);
		}
		return financialSettlementStatisticModels;
	}

}
