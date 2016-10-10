package com.cts.localtour.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ContentTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierScopeTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.MobileService;
import com.cts.localtour.viewModel.ChangeCostViewModel;

@Controller
public class MobileController {
	@Autowired
	private MobileService mobileService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/mobile/changeCostIncomeApproval")
	public String changeCostApproval(@RequestParam int tourId, @RequestParam int status, Model md){
		ArrayList<ChangeCostViewModel> costViewModels = new ArrayList<ChangeCostViewModel>();
		ArrayList<ChangeCostTable> costs = (ArrayList<ChangeCostTable>)mobileService.getAllByString("ChangeCostTable", "tourId=? and status=?", tourId, status);
		for (int i = 0; i < costs.size(); i++) {
			ChangeCostViewModel costViewModel = new ChangeCostViewModel();
			costViewModel.setCostTable(costs.get(i));
			costViewModel.setBorrowUserName(costs.get(i).getBorrowUserId()==null||costs.get(i).getBorrowUserId()==0?"":((UserTable)mobileService.getById("UserTable", costs.get(i).getBorrowUserId())).getRealName());
			System.out.println(costs.get(i).getContentId());
			costViewModel.setContentName(costs.get(i).getContentId()==null||costs.get(i).getContentId()==0?"":((SupplierContentTable)mobileService.getById("SupplierContentTable", costs.get(i).getContentId())).getContentName());
			costViewModel.setSupplierName(((SupplierTable)mobileService.getById("SupplierTable", costs.get(i).getSupplierId())).getSupplierName());
			costViewModel.setStatus(costs.get(i).getStatus()==0?"新建":costs.get(i).getStatus()==1?"已提交":costs.get(i).getStatus()==2?"已审核":costs.get(i).getStatus()==3?"已确认":"");
			costViewModels.add(costViewModel);
		}
		md.addAttribute("changeCosts", costViewModels);
		md.addAttribute("tour",(LocalTourTable)mobileService.getById("LocalTourTable", tourId));
		return "/mobile/changeCostIncome";
	}
	
	@RequestMapping("/moblie/changeCostOk")
	public void changeCostOk(HttpServletRequest request, HttpSession session, @RequestParam int id){
		mobileService.updateChangeCost(request, session, id);
	}
}
