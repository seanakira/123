package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.viewModel.DeptGainsViewModel;
@SuppressWarnings("rawtypes")
@Service
public class StatisticsService extends BaseService{
	@Autowired
	private DeptGainsViewModel deptGainsViewModel;
	public ArrayList<DeptGainsViewModel> getDeptGains(String[] dataDeptIds, Date start, Date end) {
		return deptGainsViewModel.getAllDeptGainsViewModels(dataDeptIds, start, end);
	}
	public ArrayList<DeptGainsViewModel> getSupplierGains(String[] split, Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

}
