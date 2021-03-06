package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.viewModel.DeptGainsViewModel;
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
	public ArrayList<DeptGainsViewModel> getDeptGains(String[] dataDeptIds, Date start, Date end) {
		return deptGainsViewModel.getAllDeptGainsViewModels(dataDeptIds, start, end);
	}
	public ArrayList<SupplierGainsViewModel> getSupplierGains(String supplierIds, Date start, Date end) {
		return supplierGainsViewModel.getSupplierGainsViewModelAll(supplierIds, start, end);
	}
	public ArrayList<TourDetailsViewModel> getTourDetails(Date start, Date end) {
		return tourDetailsViewModel.getTourDetailsViewModelAll(start, end);
	}

}
