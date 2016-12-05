package com.cts.localtour.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.BillApplicationTable;
import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
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
			/*ArrayList<SupplierTable> supplierTables = this.getAllByParam("SupplierTable", null, null, page, maxResults);*/
			ArrayList<SupplierTable> supplierTables = this.getAllByHql("SELECT distinct s FROM SupplierTable s,CostTable c , ChangeCostTable cc WHERE (c.bill=true and c.supplierId=s.id) and (cc.bill=true and cc.supplierId=s.id and cc.status=3)", page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables);
		}else{
			ArrayList<SupplierTable> supplierTables = this.getAllByHql("SELECT distinct s FROM SupplierTable s,CostTable c, ChangeCostTable cc  WHERE (c.bill=true and c.supplierId=s.id) and (cc.bill=true and cc.supplierId=s.id and cc.status=3) and supplierName like '%"+key+"%'", page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleBillCheckViewModel> getAll(String key, int page,int maxResults, int payStatus) {
		if(key.equals("")){
			/*ArrayList<SupplierTable> supplierTables = this.getAllByParam("SupplierTable", null, null, page, maxResults);*/
			ArrayList<SupplierTable> supplierTables = this.getAllByHql("SELECT distinct s FROM SupplierTable s,CostTable c , ChangeCostTable cc WHERE (c.bill=true and c.supplierId=s.id and c.payStatus=3 and c.remittanced=false) and (cc.bill=true and cc.supplierId=s.id and cc.status=3 and cc.remittanced=false)", page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables);
		}else{
			ArrayList<SupplierTable> supplierTables = this.getAllByHql("SELECT distinct s FROM SupplierTable s,CostTable c, ChangeCostTable cc  WHERE (c.bill=true and c.supplierId=s.id and c.payStatus=3 and c.remittanced=false) and (cc.bill=true and cc.supplierId=s.id and cc.status=3 and cc.remittanced=false) and supplierName like '%"+key+"%'", page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables);
		}
	}

	public FullBillViewModel findBill(int supplierId) {
		return fullBillViewModel.getFullBillCheckViewModel(supplierId);
	}
	
	public FullBillViewModel findBill(int supplierId, int payStatus) {
		return fullBillViewModel.getFullBillCheckViewModel(supplierId,payStatus);
	}

	@SuppressWarnings("unchecked")
	public void updateBill(FullBillViewModel full, HttpSession session) {
		for (CostTable costTable : full.getCostTables()) {
			this.updateByString("CostTable", "payStatus=1", "id=?", costTable.getId());
		}
		for (ChangeCostTable changeCostTable : full.getChangeCostTables()) {
			this.updateByString("ChangeCostTable", "payStatus=1", "id=?", changeCostTable.getId());
		}
		if(this.getAllByString("BillApplicationTable", "supplierId=?", !full.getCostTables().isEmpty()?full.getCostTables().get(0).getSupplierId():!full.getChangeCostTables().isEmpty()?full.getChangeCostTables().get(0).getSupplierId():0).isEmpty()){
			BillApplicationTable bill = new BillApplicationTable();
			bill.setDeptId(((UserTable)session.getAttribute("user")).getDeptId());
			bill.setSupplierId(!full.getCostTables().isEmpty()?full.getCostTables().get(0).getSupplierId():!full.getChangeCostTables().isEmpty()?full.getChangeCostTables().get(0).getSupplierId():0);
			this.add(bill);
		}
	}

	public void updateBill(FullBillViewModel full) {
		for (CostTable costTable : full.getCostTables()) {
			this.updateByString("CostTable", "remittanced=true", "id=?", costTable.getId());
		}
		for (ChangeCostTable changeCostTable : full.getChangeCostTables()) {
			this.updateByString("ChangeCostTable", "remittanced=true", "id=?", changeCostTable.getId());
		}
	}

}
