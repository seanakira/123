package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.viewModel.DeptGainsViewModel;
import com.cts.localtour.viewModel.FinancialSettlementStatisticModel;
import com.cts.localtour.viewModel.SupplierGainsViewModel;
import com.cts.localtour.viewModel.TourDetailsViewModel;
@SuppressWarnings("rawtypes")
@Service
public class StatisticsService extends BaseService{
	@Autowired
	private DeptGainsViewModel deptGainsViewModel;
	@Autowired
	private SupplierGainsViewModel supplierGainsViewModel;
	@Autowired
	private TourDetailsViewModel tourDetailsViewModel;
	@Autowired
	private FinancialSettlementStatisticModel financialSettlementStatisticModel;
	public ArrayList<DeptGainsViewModel> getDeptGains(Date start, Date end, String deptIds, String tourNo, int status) {
		return deptGainsViewModel.getAllDeptGainsViewModels(start, end, deptIds, tourNo, status);
	}
	public ArrayList<SupplierGainsViewModel> getSupplierGains(String supplierIds, Date start, Date end) {
		return supplierGainsViewModel.getSupplierGainsViewModelAll(supplierIds, start, end);
	}
	public ArrayList<TourDetailsViewModel> getTourDetails(Date start, Date end) {
		return tourDetailsViewModel.getTourDetailsViewModelAll(start, end);
	}
	public ArrayList<FinancialSettlementStatisticModel> getFinancialSettlementStatistic(Date start, Date end, String deptIds, String tourNo) {
		return financialSettlementStatisticModel.getFinancialSettlementStatisticAll(start, end, deptIds, tourNo);
	}
	public ArrayList<FinancialSettlementStatisticModel> getInvoiceStatistic(Date start, Date end, String deptIds,
			String tourNo) {
		// TODO Auto-generated method stub
		return null;
	}
}
