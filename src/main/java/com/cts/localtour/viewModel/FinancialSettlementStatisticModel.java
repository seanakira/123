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
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.ReimbursementCostService;
import com.cts.localtour.service.UserService;
@Component
public class FinancialSettlementStatisticModel {
	private String tourNo;
	private String customerAgencyName;
	private BigDecimal realIncomeSum;
	private BigDecimal realCostSum;
	private BigDecimal grossProfit;
	private String grossProfitMargin;
	private String userRealName;
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
	private UserService userService;
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

	
	public BigDecimal getRealIncomeSum() {
		return realIncomeSum;
	}

	public void setRealIncomeSum(BigDecimal realIncomeSum) {
		this.realIncomeSum = realIncomeSum;
	}

	public BigDecimal getRealCostSum() {
		return realCostSum;
	}

	public void setRealCostSum(BigDecimal realCostSum) {
		this.realCostSum = realCostSum;
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

	@SuppressWarnings("unchecked")
	public ArrayList<FinancialSettlementStatisticModel> getFinancialSettlementStatisticAll(Date start, Date end, String deptIds, String tourNo) {
		ArrayList<FinancialSettlementStatisticModel> financialSettlementStatisticModels = new ArrayList<FinancialSettlementStatisticModel>();
		ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) baseService.getAllByString("LocalTourTable", "status=10 and settlementTime between ? and ?"+("".equals(deptIds)?"":deptIds.split(",").length==1?" and deptId="+deptIds:" and deptId in ("+deptIds+")")+("".equals(tourNo)?"":" and tourNo like '%"+tourNo+"%'"), start, end);
		for (LocalTourTable localTourTable : localTourTables) {
			FinancialSettlementStatisticModel financialSettlementStatisticModel = new FinancialSettlementStatisticModel();
			financialSettlementStatisticModel.setTourNo(localTourTable.getTourNo());
			financialSettlementStatisticModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(localTourTable.getId()));
			financialSettlementStatisticModel.setRealIncomeSum(incomeService.getIncomeInfo(localTourTable.getId()).getRealIncomeSum().add(changeIncomeService.getIncomeInfo(localTourTable.getId()).getRealIncomeSum()));
			financialSettlementStatisticModel.setRealCostSum(costService.getCostInfo(localTourTable.getId()).getRealCostSum().add(changeCostService.getCostInfo(localTourTable.getId()).getRealCostSum()).add(reimbursementCostService.getReimbursementCostInfo(localTourTable.getId()).getRealCostSum()));
			financialSettlementStatisticModel.setGrossProfit(financialSettlementStatisticModel.getRealIncomeSum().subtract(financialSettlementStatisticModel.getRealCostSum()));
			financialSettlementStatisticModel.setGrossProfitMargin((financialSettlementStatisticModel.getRealIncomeSum().subtract(financialSettlementStatisticModel.getRealCostSum())).divide(financialSettlementStatisticModel.getRealIncomeSum(), 4).multiply(new BigDecimal(100))+"%");
			financialSettlementStatisticModel.setUserRealName(userService.getUserRealName(localTourTable.getUserId()));
			financialSettlementStatisticModels.add(financialSettlementStatisticModel);
		}
		return financialSettlementStatisticModels;
	}

}
