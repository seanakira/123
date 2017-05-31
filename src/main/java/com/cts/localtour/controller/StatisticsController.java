package com.cts.localtour.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.DeptService;
import com.cts.localtour.service.StatisticsService;
import com.cts.localtour.viewModel.DeptGainsViewModel;
import com.cts.localtour.viewModel.FinancialSettlementStatisticModel;
import com.cts.localtour.viewModel.SupplierGainsViewModel;
import com.cts.localtour.viewModel.TourDetailsViewModel;

@Controller
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private DeptService deptService;
	/*部门盈利表*/
	@RequestMapping("/deptGains")
	public String deptGains(Model md){
		md.addAttribute("depts", deptService.getDataDept());
		return "statistics/deptGains";
	}
	
	@RequestMapping("/deptGains/get")
	public @ResponseBody ArrayList<DeptGainsViewModel> getDeptGains (@RequestParam Date start, @RequestParam Date end, @RequestParam String deptIds, @RequestParam String tourNo, @RequestParam int status){
		return statisticsService.getDeptGains(start, end, deptIds, tourNo, status);
	}
	
	/*供应商分析表*/
	@RequestMapping("/supplierGains")
	public String supplierGains(){
		return "statistics/supplierGains";
	}
	
	@RequestMapping("/supplierGains/get")
	public @ResponseBody ArrayList<SupplierGainsViewModel> getSupplierGains (@RequestParam String supplierIds,@RequestParam Date start, @RequestParam Date end){
		return statisticsService.getSupplierGains(supplierIds, start, end);
	}
	
	@RequestMapping("/supplierGains/init")
	public @ResponseBody ArrayList<SupplierTable> init (){
		return (ArrayList<SupplierTable>) statisticsService.getAllByString("SupplierTable", "", null);
	}
	
	/*台账统计*/
	@RequestMapping("/tourDetails")
	public String tourDetails(){
		return "statistics/tourDetails";
	}
	
	@RequestMapping("/tourDetails/get")
	public @ResponseBody ArrayList<TourDetailsViewModel> getTourDetails(@RequestParam Date start, @RequestParam Date end){
		return statisticsService.getTourDetails(start, end);
	}
	
	/*财务结算表*/
	@RequestMapping("/financialSettlementStatistic")
	public String financialSettlementStatistic(Model md){
		md.addAttribute("depts", deptService.getDataDept());
		return "statistics/financialSettlementStatistic";
	}
	
	@RequestMapping("/financialSettlementStatistic/get")
	public @ResponseBody ArrayList<FinancialSettlementStatisticModel> getfinancialSettlementStatistic(@RequestParam Date start, @RequestParam Date end, @RequestParam String deptIds, @RequestParam String tourNo){
		return statisticsService.getFinancialSettlementStatistic(start, end, deptIds, tourNo);
	}
}
