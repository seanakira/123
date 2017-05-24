package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.pojo.IncomeInfo;
import com.cts.localtour.pojo.LoanInfo;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.LoanService;
import com.cts.localtour.service.ReimbursementCostService;
import com.cts.localtour.service.ReimbursementIncomeService;

@Component
public class DeptGainsViewModel {
	private String headerName;
	private String type;
	
	private float willCostSum;
	private float willIncomeSum;
	private float willGrossProfit;
	private float willGrossMargin;
	
	private float realCostSum;
	private float realIncomeSum;
	private float realGrossProfit;
	private float realGrossMargin;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private CostService costService;
	@Autowired
	private ChangeCostService changeCostService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@Autowired
	private ReimbursementCostService reimbursementCostService;
	@Autowired
	private ReimbursementIncomeService reimbursementIncomeService;
	@Autowired
	private LoanService loanService;
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public float getWillCostSum() {
		return willCostSum;
	}
	public void setWillCostSum(float willCostSum) {
		this.willCostSum = willCostSum;
	}
	public float getWillIncomeSum() {
		return willIncomeSum;
	}
	public void setWillIncomeSum(float willIncomeSum) {
		this.willIncomeSum = willIncomeSum;
	}
	public float getWillGrossProfit() {
		return willGrossProfit;
	}
	public void setWillGrossProfit(float willGrossProfit) {
		this.willGrossProfit = willGrossProfit;
	}
	public float getWillGrossMargin() {
		return willGrossMargin;
	}
	public void setWillGrossMargin(float willGrossMargin) {
		this.willGrossMargin = willGrossMargin;
	}
	public float getRealCostSum() {
		return realCostSum;
	}
	public void setRealCostSum(float realCostSum) {
		this.realCostSum = realCostSum;
	}
	public float getRealIncomeSum() {
		return realIncomeSum;
	}
	public void setRealIncomeSum(float realIncomeSum) {
		this.realIncomeSum = realIncomeSum;
	}
	public float getRealGrossProfit() {
		return realGrossProfit;
	}
	public void setRealGrossProfit(float realGrossProfit) {
		this.realGrossProfit = realGrossProfit;
	}
	public float getRealGrossMargin() {
		return realGrossMargin;
	}
	public void setRealGrossMargin(float realGrossMargin) {
		this.realGrossMargin = realGrossMargin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<DeptGainsViewModel> getAllDeptGainsViewModels(Date start, Date end, String deptIds, String tourNo, int status){
		ArrayList<DeptGainsViewModel> deptGainsViewModels = new ArrayList<DeptGainsViewModel>();
		if("".equals(deptIds)){
			deptIds= ((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds();
		}
		for (String dataDeptId : deptIds.split(", ")) {
			DeptGainsViewModel dept = new DeptGainsViewModel();
			dept.setHeaderName(((DeptTable)baseService.getById("DeptTable", Integer.parseInt(dataDeptId))).getDeptName());
			dept.setType("dept");
			deptGainsViewModels.add(dept);
			ArrayList<UserTable> users = (ArrayList<UserTable>) baseService.getAllByString("UserTable", "deptId=? and enable=true", Integer.parseInt(dataDeptId));
			for (UserTable userTable : users) {
				DeptGainsViewModel user = new DeptGainsViewModel();
				ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) baseService.getAllByString("LocalTourTable", "userId=? and enable=true and startTime between ? and ? and tourNo like '%"+tourNo+"%'"+(status==-1?"":status==7?" and status>=7":" and status<7"), userTable.getId(), start, end);
				if(!localTourTables.isEmpty()){
					user.setHeaderName(userTable.getRealName());
					user.setType("user");
					deptGainsViewModels.add(user);
				}
				for (LocalTourTable localTourTable : localTourTables) {
					DeptGainsViewModel tour = new DeptGainsViewModel();
					
					CostInfo costInfo = costService.getCostInfo(localTourTable.getId());
					CostInfo changeCostInfo = changeCostService.getCostInfo(localTourTable.getId());
					CostInfo reimbursementCostInfo = reimbursementCostService.getReimbursementCostInfo(localTourTable.getId());
					IncomeInfo incomeInfo = incomeService.getIncomeInfo(localTourTable.getId());
					IncomeInfo changeIncomeInfo = changeIncomeService.getIncomeInfo(localTourTable.getId());
					IncomeInfo reimbursementIncomeInfo = reimbursementIncomeService.getIncomeInfo(localTourTable.getId());
					LoanInfo loanInfo = loanService.getLoanInfo(localTourTable.getId());
					
					tour.setHeaderName(localTourTable.getTourNo()+" "+localTourTable.getTourName());
					tour.setWillCostSum(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).add(reimbursementCostInfo.getWillCostSum()).add(loanInfo.getWillLoanSum()).floatValue());
					tour.setWillIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).add(reimbursementIncomeInfo.getIncomeSum()).floatValue());
					tour.setWillGrossProfit(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).add(reimbursementIncomeInfo.getIncomeSum()).subtract(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).add(reimbursementCostInfo.getWillCostSum()).add(loanInfo.getWillLoanSum())).floatValue());
					if(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).add(reimbursementIncomeInfo.getIncomeSum()).floatValue()!=0){
						tour.setWillGrossMargin((incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).subtract(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).add(reimbursementCostInfo.getWillCostSum()).add(loanInfo.getWillLoanSum()))).divide(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).floatValue());
					}
					
					tour.setRealCostSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum()).floatValue());
					tour.setRealIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).add(reimbursementIncomeInfo.getRealIncomeSum()).floatValue());
					tour.setRealGrossProfit((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).add(reimbursementIncomeInfo.getRealIncomeSum())).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum())).floatValue());
					if(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).add(reimbursementIncomeInfo.getRealIncomeSum()).floatValue()!=0){
						tour.setRealGrossMargin(((incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).add(reimbursementIncomeInfo.getRealIncomeSum())).subtract(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum()))).divide(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).add(reimbursementIncomeInfo.getRealIncomeSum()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).floatValue());
					}
					deptGainsViewModels.add(tour);
					
					user.setWillCostSum(new BigDecimal(user.getWillCostSum()).add(new BigDecimal(tour.getWillCostSum())).floatValue());
					user.setWillIncomeSum(new BigDecimal(user.getWillIncomeSum()).add(new BigDecimal(tour.getWillIncomeSum())).floatValue());
					user.setWillGrossProfit(new BigDecimal(user.getWillGrossProfit()).add(new BigDecimal(tour.getWillGrossProfit())).floatValue());
					if(user.getWillGrossMargin()==0){
						user.setWillGrossMargin(new BigDecimal(tour.getWillGrossMargin()).floatValue());
					}else{
						user.setWillGrossMargin((new BigDecimal(user.getWillGrossMargin()).add(new BigDecimal(tour.getWillGrossMargin()))).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP).floatValue());
					}
					user.setRealCostSum(new BigDecimal(user.getRealCostSum()).add(new BigDecimal(tour.getRealCostSum())).floatValue());
					user.setRealIncomeSum(new BigDecimal(user.getRealIncomeSum()).add(new BigDecimal(tour.getRealIncomeSum())).floatValue());
					user.setRealGrossProfit(new BigDecimal(user.getRealGrossProfit()).add(new BigDecimal(tour.getRealGrossProfit())).floatValue());
					if(user.getRealGrossMargin()==0){
						user.setRealGrossMargin(new BigDecimal(tour.getRealGrossMargin()).floatValue());
					}else{
						user.setRealGrossMargin((new BigDecimal(user.getRealGrossMargin()).add(new BigDecimal(tour.getRealGrossMargin()))).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP).floatValue());
					}
				}
				dept.setWillCostSum(new BigDecimal(dept.getWillCostSum()).add(new BigDecimal(user.getWillCostSum())).floatValue());
				dept.setWillIncomeSum(new BigDecimal(dept.getWillIncomeSum()).add(new BigDecimal(user.getWillIncomeSum())).floatValue());
				dept.setWillGrossProfit(new BigDecimal(dept.getWillGrossProfit()).add(new BigDecimal(user.getWillGrossProfit())).floatValue());
				if(dept.getWillGrossMargin()==0){
					dept.setWillGrossMargin(new BigDecimal(user.getWillGrossMargin()).floatValue());
				}else{
					dept.setWillGrossMargin((new BigDecimal(dept.getWillGrossMargin()).add(new BigDecimal(user.getWillGrossMargin()))).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP).floatValue());
				}
				dept.setRealCostSum(new BigDecimal(dept.getRealCostSum()).add(new BigDecimal(user.getRealCostSum())).floatValue());
				dept.setRealIncomeSum(new BigDecimal(dept.getRealIncomeSum()).add(new BigDecimal(user.getRealIncomeSum())).floatValue());
				dept.setRealGrossProfit(new BigDecimal(dept.getRealGrossProfit()).add(new BigDecimal(user.getRealGrossProfit())).floatValue());
				if(dept.getRealGrossMargin()==0){
					dept.setRealGrossMargin(new BigDecimal(user.getRealGrossMargin()).floatValue());
				}else{
					dept.setRealGrossMargin((new BigDecimal(dept.getRealGrossMargin()).add(new BigDecimal(user.getRealGrossMargin()))).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP).floatValue());
				}
			}
		}
		return deptGainsViewModels;
	}
}
