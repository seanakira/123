package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.service.CustomerAgencyService;
@Component
public class FullRefundViewModel {
	private ArrayList<RefundViewModel> refunds;
	private String customerAgencyName;
	@Autowired
	private RefundViewModel refundViewModel;
	@Autowired
	private CustomerAgencyService customerAgencyService;
	public ArrayList<RefundViewModel> getRefunds() {
		return refunds;
	}
	public void setRefunds(ArrayList<RefundViewModel> refunds) {
		this.refunds = refunds;
	}
	public String getCustomerAgencyName() {
		return customerAgencyName;
	}
	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}
	public FullRefundViewModel getFullRefundViewModel(int tourId) {
		FullRefundViewModel fullRefundViewModel = new FullRefundViewModel();
		fullRefundViewModel.setRefunds(refundViewModel.getRefundViewModelAll(tourId));
		fullRefundViewModel.setCustomerAgencyName(customerAgencyService.getCustomerAgencyName(tourId));
		return fullRefundViewModel;
	}

}
