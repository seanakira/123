package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.RefundTable;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.CustomerAgencyService;
import com.cts.localtour.service.UserService;

@Component
public class RefundViewModel {
	private RefundTable refundTable;
	private String customerAgencyName;
	private String applicationerName;
	private String managerName;
	private String bossName;
	private String handlerName;
	private String status;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerAgencyService customerAgencyService;
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	public String getApplicationerName() {
		return applicationerName;
	}
	public void setApplicationerName(String applicationerName) {
		this.applicationerName = applicationerName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getBossName() {
		return bossName;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public RefundTable getRefundTable() {
		return refundTable;
	}
	public void setRefundTable(RefundTable refundTable) {
		this.refundTable = refundTable;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<RefundViewModel> getRefundViewModelAll(int tourId){
		ArrayList<RefundViewModel> refundTableViewModels = new ArrayList<RefundViewModel>();
		ArrayList<RefundTable> refundTables = (ArrayList<RefundTable>) baseService.getAllByString("RefundTable", "tourId=?", tourId);
		for (RefundTable refundTable : refundTables) {
			RefundViewModel refundTableViewModel = new RefundViewModel();
			refundTableViewModel.setRefundTable(refundTable);
			refundTableViewModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(tourId));
			refundTableViewModel.setApplicationerName(userService.getUserRealName(refundTable.getApplicationerId()));
			refundTableViewModel.setManagerName(userService.getUserRealName(refundTable.getManagerId()));
			refundTableViewModel.setBossName(userService.getUserRealName(refundTable.getBossId()));
			refundTableViewModel.setHandlerName(userService.getUserRealName(refundTable.getHandlerId()));
			if(refundTable.isRefunded()){
				refundTableViewModel.setStatus("已退款");
			}else if(refundTable.getStatus()==0){
				refundTableViewModel.setStatus("新建");
			}else if(refundTable.getStatus()==1){
				refundTableViewModel.setStatus("待审核");
			}else if(refundTable.getStatus()==2){
				refundTableViewModel.setStatus("待批准");
			}else if(refundTable.getStatus()==3){
				refundTableViewModel.setStatus("已批准");
			}
			refundTableViewModels.add(refundTableViewModel);
		}
		return refundTableViewModels;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<RefundViewModel> getRefundViewModelAll(int tourId, int status) {
		if(status==-1){
			return this.getRefundViewModelAll(tourId);
		}
		ArrayList<RefundViewModel> refundTableViewModels = new ArrayList<RefundViewModel>();
		ArrayList<RefundTable> refundTables = (ArrayList<RefundTable>) baseService.getAllByString("RefundTable", "tourId=? and status=?", tourId, status);
		for (RefundTable refundTable : refundTables) {
			RefundViewModel refundTableViewModel = new RefundViewModel();
			refundTableViewModel.setRefundTable(refundTable);
			refundTableViewModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(tourId));
			refundTableViewModel.setApplicationerName(userService.getUserRealName(refundTable.getApplicationerId()));
			refundTableViewModel.setManagerName(userService.getUserRealName(refundTable.getManagerId()));
			refundTableViewModel.setBossName(userService.getUserRealName(refundTable.getBossId()));
			refundTableViewModel.setHandlerName(userService.getUserRealName(refundTable.getHandlerId()));
			if(refundTable.isRefunded()){
				refundTableViewModel.setStatus("已退款");
			}else if(refundTable.getStatus()==0){
				refundTableViewModel.setStatus("新建");
			}else if(refundTable.getStatus()==1){
				refundTableViewModel.setStatus("待审核");
			}else if(refundTable.getStatus()==2){
				refundTableViewModel.setStatus("待批准");
			}else if(refundTable.getStatus()==3){
				refundTableViewModel.setStatus("已批准");
			}
			refundTableViewModels.add(refundTableViewModel);
		}
		return refundTableViewModels;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<RefundViewModel> getPrintViewModel(int tourId) {
		ArrayList<RefundViewModel> refundTableViewModels = new ArrayList<RefundViewModel>();
		ArrayList<RefundTable> refundTables = (ArrayList<RefundTable>) baseService.getAllByString("RefundTable", "tourId=? and status=3 and refunded=false", tourId);
		for (RefundTable refundTable : refundTables) {
			RefundViewModel refundTableViewModel = new RefundViewModel();
			refundTableViewModel.setRefundTable(refundTable);
			refundTableViewModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(tourId));
			refundTableViewModel.setApplicationerName(userService.getUserRealName(refundTable.getApplicationerId()));
			refundTableViewModel.setManagerName(userService.getUserRealName(refundTable.getManagerId()));
			refundTableViewModel.setBossName(userService.getUserRealName(refundTable.getBossId()));
			refundTableViewModel.setHandlerName(userService.getUserRealName(refundTable.getHandlerId()));
			if(refundTable.isRefunded()){
				refundTableViewModel.setStatus("已退款");
			}else if(refundTable.getStatus()==0){
				refundTableViewModel.setStatus("新建");
			}else if(refundTable.getStatus()==1){
				refundTableViewModel.setStatus("待审核");
			}else if(refundTable.getStatus()==2){
				refundTableViewModel.setStatus("待批准");
			}else if(refundTable.getStatus()==3){
				refundTableViewModel.setStatus("已批准");
			}
			refundTableViewModels.add(refundTableViewModel);
		}
		return refundTableViewModels;
	}
}
