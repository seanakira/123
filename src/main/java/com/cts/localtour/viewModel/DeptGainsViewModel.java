package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.pojo.IncomeInfo;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.DeptService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.RefundService;
import com.cts.localtour.service.ReimbursementCostService;
import com.cts.localtour.service.ReimbursementIncomeService;

@Component
public class DeptGainsViewModel {
	private String headerName;
	private String type;
	
	private BigDecimal willCostSum;
	private BigDecimal willIncomeSum;
	private BigDecimal willGrossProfit;
	private BigDecimal willGrossMargin;
	
	private BigDecimal realCostSum;
	private BigDecimal realIncomeSum;
	private BigDecimal realGrossProfit;
	private BigDecimal realGrossMargin;
	
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
	private RefundService refundService;
	@Autowired
	private DeptService deptService;
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public BigDecimal getWillCostSum() {
		return willCostSum;
	}
	public void setWillCostSum(BigDecimal willCostSum) {
		this.willCostSum = willCostSum;
	}
	public BigDecimal getWillIncomeSum() {
		return willIncomeSum;
	}
	public void setWillIncomeSum(BigDecimal willIncomeSum) {
		this.willIncomeSum = willIncomeSum;
	}
	public BigDecimal getWillGrossProfit() {
		return willGrossProfit;
	}
	public void setWillGrossProfit(BigDecimal willGrossProfit) {
		this.willGrossProfit = willGrossProfit;
	}
	public BigDecimal getWillGrossMargin() {
		return willGrossMargin;
	}
	public void setWillGrossMargin(BigDecimal willGrossMargin) {
		this.willGrossMargin = willGrossMargin;
	}
	public BigDecimal getRealCostSum() {
		return realCostSum;
	}
	public void setRealCostSum(BigDecimal realCostSum) {
		this.realCostSum = realCostSum;
	}
	public BigDecimal getRealIncomeSum() {
		return realIncomeSum;
	}
	public void setRealIncomeSum(BigDecimal realIncomeSum) {
		this.realIncomeSum = realIncomeSum;
	}
	public BigDecimal getRealGrossProfit() {
		return realGrossProfit;
	}
	public void setRealGrossProfit(BigDecimal realGrossProfit) {
		this.realGrossProfit = realGrossProfit;
	}
	public BigDecimal getRealGrossMargin() {
		return realGrossMargin;
	}
	public void setRealGrossMargin(BigDecimal realGrossMargin) {
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
		deptIds = deptService.getDownerDpetIds(deptIds);
		for (String dataDeptId : deptIds.split(",")) {
			DeptGainsViewModel dept = new DeptGainsViewModel();
			dept.setHeaderName(((DeptTable)baseService.getById("DeptTable", Integer.parseInt(dataDeptId))).getDeptName());
			dept.setType("dept");
			BigDecimal init = new BigDecimal(0);
			dept.setWillCostSum(init);
			dept.setWillGrossMargin(init);
			dept.setWillGrossProfit(init);
			dept.setWillIncomeSum(init);
			dept.setRealCostSum(init);
			dept.setRealGrossMargin(init);
			dept.setRealGrossProfit(init);
			dept.setRealIncomeSum(init);
			deptGainsViewModels.add(dept);
			ArrayList<UserTable> users = (ArrayList<UserTable>) baseService.getAllByString("UserTable", "deptId=? and enable=true", Integer.parseInt(dataDeptId));
			for (UserTable userTable : users) {
				DeptGainsViewModel user = new DeptGainsViewModel();
				user.setWillCostSum(init);
				user.setWillGrossMargin(init);
				user.setWillGrossProfit(init);
				user.setWillIncomeSum(init);
				user.setRealCostSum(init);
				user.setRealGrossMargin(init);
				user.setRealGrossProfit(init);
				user.setRealIncomeSum(init);
				ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) baseService.getAllByString("LocalTourTable", "userId=? and enable=true and startTime between ? and ? and tourNo like '%"+tourNo+"%'"+(status==-1?"":status==7?" and status>=7":" and status<7"), userTable.getId(), start, end);
				if(!localTourTables.isEmpty()){
					user.setHeaderName(userTable.getRealName());
					user.setType("user");
					deptGainsViewModels.add(user);
				}
				for (LocalTourTable localTourTable : localTourTables) {
					DeptGainsViewModel tour = new DeptGainsViewModel();
					tour.setWillCostSum(init);
					tour.setWillGrossMargin(init);
					tour.setWillGrossProfit(init);
					tour.setWillIncomeSum(init);
					tour.setRealCostSum(init);
					tour.setRealGrossMargin(init);
					tour.setRealGrossProfit(init);
					tour.setRealIncomeSum(init);
					CostInfo costInfo = costService.getCostInfo(localTourTable.getId());
					CostInfo changeCostInfo = changeCostService.getCostInfo(localTourTable.getId());
					CostInfo reimbursementCostInfo = reimbursementCostService.getReimbursementCostInfo(localTourTable.getId());
					IncomeInfo incomeInfo = incomeService.getIncomeInfo(localTourTable.getId());
					IncomeInfo changeIncomeInfo = changeIncomeService.getIncomeInfo(localTourTable.getId());
					IncomeInfo reimbursementIncomeInfo = reimbursementIncomeService.getIncomeInfo(localTourTable.getId());
					IncomeInfo refundIncomeInfo = refundService.getIncomeInfo(localTourTable.getId());
					
					tour.setHeaderName(localTourTable.getTourNo()+" "+localTourTable.getTourName());
					tour.setWillCostSum(costInfo.getWillCostSum().add(changeCostInfo.getWillCostSum()).add(reimbursementCostInfo.getWillCostSum()));
					tour.setWillIncomeSum(incomeInfo.getIncomeSum().add(changeIncomeInfo.getIncomeSum()).add(reimbursementIncomeInfo.getIncomeSum()));
					tour.setWillGrossProfit(tour.getWillIncomeSum().subtract(tour.getWillCostSum()));
					if(tour.getWillIncomeSum().compareTo(new BigDecimal(0))!=0){
						tour.setWillGrossMargin(tour.getWillGrossProfit().divide(tour.getWillIncomeSum(),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
					}
					
					tour.setRealCostSum(costInfo.getReimbursementSum().add(changeCostInfo.getReimbursementSum()).add(reimbursementCostInfo.getReimbursementSum()));
					tour.setRealIncomeSum(incomeInfo.getRealIncomeSum().add(changeIncomeInfo.getRealIncomeSum()).subtract(refundIncomeInfo.getRealIncomeSum()));
					tour.setRealGrossProfit(tour.getRealIncomeSum().subtract(tour.getRealCostSum()));
					if(tour.getRealIncomeSum().compareTo(new BigDecimal(0))!=0){
						tour.setRealGrossMargin(tour.getRealGrossProfit().divide(tour.getRealIncomeSum(),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
					}
					deptGainsViewModels.add(tour);
					
					user.setWillCostSum(user.getWillCostSum().add(tour.getWillCostSum()));
					user.setWillIncomeSum(user.getWillIncomeSum().add(tour.getWillIncomeSum()));
					user.setWillGrossProfit(user.getWillGrossProfit().add(tour.getWillGrossProfit()));
					if(user.getWillGrossMargin().compareTo(new BigDecimal(0))==0){
						user.setWillGrossMargin(tour.getWillGrossMargin());
					}else{
						user.setWillGrossMargin((user.getWillGrossMargin().add(tour.getWillGrossMargin())).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP));
					}
					user.setRealCostSum(user.getRealCostSum().add(tour.getRealCostSum()));
					user.setRealIncomeSum(user.getRealIncomeSum().add(tour.getRealIncomeSum()));
					user.setRealGrossProfit(user.getRealGrossProfit().add(tour.getRealGrossProfit()));
					if(user.getRealGrossMargin().compareTo(new BigDecimal(0))==0){
						user.setRealGrossMargin(tour.getRealGrossMargin());
					}else{
						user.setRealGrossMargin((user.getRealGrossMargin().add(tour.getRealGrossMargin())).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP));
					}
				}
				dept.setWillCostSum(dept.getWillCostSum().add(user.getWillCostSum()));
				dept.setWillIncomeSum(dept.getWillIncomeSum().add(user.getWillIncomeSum()));
				dept.setWillGrossProfit(dept.getWillGrossProfit().add(user.getWillGrossProfit()));
				if(dept.getWillGrossMargin().compareTo(new BigDecimal(0))==0){
					dept.setWillGrossMargin(user.getWillGrossMargin());
				}else{
					dept.setWillGrossMargin((dept.getWillGrossMargin().add(user.getWillGrossMargin())).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP));
				}
				dept.setRealCostSum(dept.getRealCostSum().add(user.getRealCostSum()));
				dept.setRealIncomeSum(dept.getRealIncomeSum().add(user.getRealIncomeSum()));
				dept.setRealGrossProfit(dept.getRealGrossProfit().add(user.getRealGrossProfit()));
				if(dept.getRealGrossMargin().compareTo(new BigDecimal(0))==0){
					dept.setRealGrossMargin(user.getRealGrossMargin());
				}else{
					dept.setRealGrossMargin((dept.getRealGrossMargin().add(user.getRealGrossMargin())).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP));
				}
			}
		}
		return deptGainsViewModels;
	}
}
