package com.cts.localtour.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.viewModel.FullBillViewModel;
import com.cts.localtour.viewModel.SimpleBillCheckViewModel;

@SuppressWarnings("rawtypes")
@Service
public class BillService extends BaseService{
	@Autowired
	private SimpleBillCheckViewModel simpleBillCheckViewModel;
	@Autowired
	private FullBillViewModel fullBillViewModel;
	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsByParam("SupplierTable", null, null);
		}else{
			return this.getCountsByParam("SupplierTable", "supplierName like '%"+key+"%'", null);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SimpleBillCheckViewModel> getAll(String key, int page,int maxResults) {
		if(key.equals("")){
			ArrayList<SupplierTable> supplierTables = this.getAllByParam("SupplierTable", null, null, page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables);
		}else{
			ArrayList<SupplierTable> supplierTables = this.getAllByParam("SupplierTable", "supplierName like '%"+key+"%'", null, page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables);
		}
	}

	public FullBillViewModel findBill(int supplierId) {
		return fullBillViewModel.getFullBillCheckViewModel(supplierId);
	}

}
