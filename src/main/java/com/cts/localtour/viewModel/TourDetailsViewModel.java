package com.cts.localtour.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.BusinessTypeTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.UserService;

@Component
public class TourDetailsViewModel {
	private String month;
	private String customerAgencyName;
	private LocalTourTable localTourTable; 
	private String tourBusinessTypeName;
	private BigDecimal willIncomeSum;
	private BigDecimal realIncomeSum;
	private BigDecimal realGrossProfit;
	private BigDecimal realGrossMargin;
	private String userName;
	private String status;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private FullReimbursementApplicationViewModel fullReimbursementApplicationViewModel;
	@Autowired
	private UserService userService;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
	}
	public String getTourBusinessTypeName() {
		return tourBusinessTypeName;
	}
	public void setTourBusinessTypeName(String tourBusinessTypeName) {
		this.tourBusinessTypeName = tourBusinessTypeName;
	}
	public BigDecimal getWillIncomeSum() {
		return willIncomeSum;
	}
	public void setWillIncomeSum(BigDecimal willIncomeSum) {
		this.willIncomeSum = willIncomeSum;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public ArrayList<TourDetailsViewModel> getTourDetailsViewModelAll(Date start, Date end){
		String dataDeptIds = ((UserTable) SecurityUtils.getSubject().getPrincipal()).getDataDeptIds();
		ArrayList<TourDetailsViewModel> tourDetailsViewModels = new ArrayList<TourDetailsViewModel>();
		ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) baseService.getAllByString("LocalTourTable", "deptId in ("+dataDeptIds+") and startTime between ? and ?", start,end);
		for (LocalTourTable localTourTable : localTourTables) {
			TourDetailsViewModel tourDetailsViewModel = new TourDetailsViewModel();
			tourDetailsViewModel.setMonth((localTourTable.getStartTime().getMonth()+1)+"");
			tourDetailsViewModel.setCustomerAgencyName(((CustomerAgencyTable)baseService.getById("CustomerAgencyTable", localTourTable.getCustomerAgencyId())).getCustomerAgencyName());
			tourDetailsViewModel.setLocalTourTable(localTourTable);
			tourDetailsViewModel.setTourBusinessTypeName(((BusinessTypeTable)baseService.getById("BusinessTypeTable", localTourTable.getBusinessTypeId())).getBusinessTypeName());
			FullReimbursementApplicationViewModel full = fullReimbursementApplicationViewModel.getFullReimbursementApplicationViewModel(localTourTable.getId());
			tourDetailsViewModel.setWillIncomeSum(full.getWillIncomeSum());
			tourDetailsViewModel.setRealIncomeSum(full.getRealIncomeSum());
			tourDetailsViewModel.setRealGrossProfit(full.getRealGrossProfit());
			tourDetailsViewModel.setRealGrossMargin(full.getRealGrossMargin());
			tourDetailsViewModel.setUserName(userService.getUserRealName(localTourTable.getUserId()));
			if(localTourTable.getStatus()       ==0){
				tourDetailsViewModel.setStatus("新建");
			}else if (localTourTable.getStatus()==1) {
				tourDetailsViewModel.setStatus("已提交");
			}else if (localTourTable.getStatus()==2) {
				tourDetailsViewModel.setStatus("已报送");
			}else if (localTourTable.getStatus()==3) {
				tourDetailsViewModel.setStatus("可借款");
			}else if (localTourTable.getStatus()==4) {
				tourDetailsViewModel.setStatus("进行中");
			}else if (localTourTable.getStatus()==5) {
				tourDetailsViewModel.setStatus("已结束");
			}else if (localTourTable.getStatus()==6) {
				tourDetailsViewModel.setStatus("结算中");
			}else if (localTourTable.getStatus()==7) {
				tourDetailsViewModel.setStatus("已报账");
			}else if (localTourTable.getStatus()==8) {
				tourDetailsViewModel.setStatus("待核销");
			}else if (localTourTable.getStatus()==9) {
				tourDetailsViewModel.setStatus("待结算");
			}else if (localTourTable.getStatus()==10) {
				tourDetailsViewModel.setStatus("已结算");
			}
			tourDetailsViewModels.add(tourDetailsViewModel);
		}
		return tourDetailsViewModels;
	}
}
