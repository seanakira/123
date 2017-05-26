package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.RefundTable;
import com.cts.localtour.service.ChangeIncomeService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.RefundService;
import com.cts.localtour.service.ReimbursementIncomeService;

@Component
public class FullRefundViewModel {
	private float maxRefund;
	private ArrayList<RefundViewModel> refundTableViewModels;
	private ArrayList<RefundTable> refundTables;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ChangeIncomeService changeIncomeService;
	@Autowired
	private ReimbursementIncomeService reimbursementIncomeService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private RefundViewModel refundViewModel;
	public float getMaxRefund() {
		return maxRefund;
	}
	public void setMaxRefund(float maxRefund) {
		this.maxRefund = maxRefund;
	}
	public ArrayList<RefundViewModel> getRefundTableViewModels() {
		return refundTableViewModels;
	}
	public void setRefundTableViewModels(ArrayList<RefundViewModel> refundTableViewModels) {
		this.refundTableViewModels = refundTableViewModels;
	}
	public ArrayList<RefundTable> getRefundTables() {
		return refundTables;
	}
	public void setRefundTables(ArrayList<RefundTable> refundTables) {
		this.refundTables = refundTables;
	}
	public FullRefundViewModel getFullRefundViewModel(int tourId) {
		FullRefundViewModel fullRefundViewModel = new FullRefundViewModel();
		fullRefundViewModel.setMaxRefund(incomeService.getIncomeInfo(tourId).getRealIncomeSum().add(changeIncomeService.getIncomeInfo(tourId).getRealIncomeSum()).add(reimbursementIncomeService.getIncomeInfo(tourId).getRealIncomeSum()).add(refundService.getIncomeInfo(tourId).getRealIncomeSum()).floatValue());
		fullRefundViewModel.setRefundTableViewModels(refundViewModel.getRefundTableViewModelAll(tourId));
		return null;
	}

}
