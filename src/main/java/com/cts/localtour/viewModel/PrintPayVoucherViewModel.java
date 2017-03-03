package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.service.BaseService;

@Component
public class PrintPayVoucherViewModel {
	private SupplierTable supplierTable;
	private ArrayList<CostViewModel> costs;
	private ArrayList<ChangeCostViewModel> changeCosts;
	@Autowired
	private CostViewModel costViewModel;
	@Autowired
	private ChangeCostViewModel changeCostViewModel;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	public SupplierTable getSupplierTable() {
		return supplierTable;
	}
	public void setSupplierTable(SupplierTable supplierTable) {
		this.supplierTable = supplierTable;
	}
	public ArrayList<CostViewModel> getCosts() {
		return costs;
	}
	public void setCosts(ArrayList<CostViewModel> costs) {
		this.costs = costs;
	}
	public ArrayList<ChangeCostViewModel> getChangeCosts() {
		return changeCosts;
	}
	public void setChangeCosts(ArrayList<ChangeCostViewModel> changeCosts) {
		this.changeCosts = changeCosts;
	}
	public ArrayList<PrintPayVoucherViewModel> getPrintViewModel(int tourId) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<CostViewModel> costs = costViewModel.getPrintViewModel(tourId);
		for (CostViewModel costViewModel : costs) {
			if(!ids.contains(costViewModel.getCostTable().getSupplierId())) {  
				ids.add(costViewModel.getCostTable().getSupplierId());  
	        } 
		}
		ArrayList<ChangeCostViewModel> changeCosts = changeCostViewModel.getPrintViewModel(tourId);
		for (ChangeCostViewModel changeCostViewModel : changeCosts) {
			if(!ids.contains(changeCostViewModel.getCostTable().getSupplierId())) {  
				ids.add(changeCostViewModel.getCostTable().getSupplierId());  
	        } 
		}
		
		ArrayList<PrintPayVoucherViewModel> printPayVoucherViewModels = new ArrayList<PrintPayVoucherViewModel>();
		for (Integer id : ids) {
			PrintPayVoucherViewModel payVoucherViewModel = new PrintPayVoucherViewModel();
			payVoucherViewModel.setSupplierTable((SupplierTable) baseService.getById("SupplierTable", id));
			
			ArrayList<CostViewModel> costViewModels = new ArrayList<CostViewModel>();
			for (CostViewModel costViewModel : costs) {
				if(id==costViewModel.getCostTable().getSupplierId()){
					costViewModels.add(costViewModel);
				}
			}
			payVoucherViewModel.setCosts(costViewModels);
			
			ArrayList<ChangeCostViewModel> changeCostViewModels = new ArrayList<ChangeCostViewModel>();
			for (ChangeCostViewModel changeCostViewModel : changeCosts) {
				if(id==changeCostViewModel.getCostTable().getSupplierId()){
					changeCostViewModels.add(changeCostViewModel);
				}
			}
			payVoucherViewModel.setChangeCosts(changeCostViewModels);
			
			printPayVoucherViewModels.add(payVoucherViewModel);
		}
		return printPayVoucherViewModels;
	}
}
