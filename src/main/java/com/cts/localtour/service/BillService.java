package com.cts.localtour.service;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.BillApplicationTable;
import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.ReimbursementCostTable;
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
	@Autowired
	private MobileService mobileService;
	
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getByHql("SELECT distinct s FROM SupplierTable s, CostTable c, ChangeCostTable cc WHERE (c.bill=true and c.supplierId=s.id) or (cc.bill=true and cc.supplierId=s.id and cc.status=3)").size();
		}else{
			return this.getByHql("SELECT distinct s FROM SupplierTable s, CostTable c, ChangeCostTable cc WHERE (c.bill=true and c.supplierId=s.id) or (cc.bill=true and cc.supplierId=s.id and cc.status=3) and supplierName like '%"+key+"%'").size();
		}
	}
	/*团队管理 签单管理*/
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleBillCheckViewModel> getAll(String key, int page,int maxResults) {
		if(key.equals("")){
			ArrayList<SupplierTable> supplierTables = this.getAllByHql("SELECT distinct s FROM SupplierTable s, CostTable c, ChangeCostTable cc WHERE (c.bill=true and c.supplierId=s.id) or (cc.bill=true and cc.supplierId=s.id and cc.status=3)", page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables,0);
		}else{
			ArrayList<SupplierTable> supplierTables = this.getAllByHql("SELECT distinct s FROM SupplierTable s, CostTable c, ChangeCostTable cc WHERE (c.bill=true and c.supplierId=s.id) or (cc.bill=true and cc.supplierId=s.id and cc.status=3) and supplierName like '%"+key+"%'", page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables,0);
		}
	}
	
	/*财务管理挂账管理*/
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleBillCheckViewModel> getAll(String key, int page,int maxResults, int payStatus) {
		if(key.equals("")){
			ArrayList<SupplierTable> supplierTables = this.getAllByHql("SELECT distinct s FROM SupplierTable s,CostTable c , ChangeCostTable cc WHERE (c.bill=true and c.supplierId=s.id and c.payStatus=3 and c.remittanced=false) or (cc.bill=true and cc.supplierId=s.id and cc.status=3 and cc.remittanced=false)", page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables, 3, 0);
		}else{
			ArrayList<SupplierTable> supplierTables = this.getAllByHql("SELECT distinct s FROM SupplierTable s,CostTable c, ChangeCostTable cc  WHERE (c.bill=true and c.supplierId=s.id and c.payStatus=3 and c.remittanced=false) or (cc.bill=true and cc.supplierId=s.id and cc.status=3 and cc.remittanced=false) and supplierName like '%"+key+"%'", page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables, 3, 0);
		}
	}

	public FullBillViewModel findBill(int supplierId, int relativePeriod) {
		return fullBillViewModel.getFullBillCheckViewModel(supplierId, relativePeriod);
	}
	
	public FullBillViewModel findBill(int supplierId, int payStatus, int relativePeriod) {
		return fullBillViewModel.getFullBillCheckViewModel(supplierId,payStatus,relativePeriod);
	}

	/*挂账付款申请*/
	@SuppressWarnings("unchecked")
	public void updateBill(FullBillViewModel full) {
		for (CostTable costTable : full.getCostTables()) {
			this.updateByString("CostTable", "payStatus=1, payApplicationerId=?", "id=?", ((UserTable)SecurityUtils.getSubject().getPrincipal()).getId(), costTable.getId());
		}
		for (ChangeCostTable changeCostTable : full.getChangeCostTables()) {
			this.updateByString("ChangeCostTable", "payStatus=1, payApplicationerId=?", "id=?", ((UserTable)SecurityUtils.getSubject().getPrincipal()).getId(), changeCostTable.getId());
		}
		if(this.getAllByString("BillApplicationTable", "supplierId=?", !full.getCostTables().isEmpty()?full.getCostTables().get(0).getSupplierId():!full.getChangeCostTables().isEmpty()?full.getChangeCostTables().get(0).getSupplierId():0).isEmpty()){
			BillApplicationTable bill = new BillApplicationTable();
			bill.setDeptId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getDeptId());
			bill.setSupplierId(!full.getCostTables().isEmpty()?full.getCostTables().get(0).getSupplierId():!full.getChangeCostTables().isEmpty()?full.getChangeCostTables().get(0).getSupplierId():0);
			this.add(bill);
		}
		if(!full.getCostTables().isEmpty()||!full.getChangeCostTables().isEmpty()){
			mobileService.sendMessage("billApplication", !full.getCostTables().isEmpty()?full.getCostTables().get(0).getSupplierId():!full.getChangeCostTables().isEmpty()?full.getChangeCostTables().get(0).getSupplierId():0, 1, "您有待审核的（供应商挂账付款申请），点击进行审核");
		}
	}
	
	/*财务确认挂账汇款*/
	public void remittanceBill(FullBillViewModel full) {
		for (CostTable costTable : full.getCostTables()) {
			this.updateByString("CostTable", "remittanced=true", "id=?", costTable.getId());
		}
		for (ChangeCostTable changeCostTable : full.getChangeCostTables()) {
			this.updateByString("ChangeCostTable", "remittanced=true", "id=?", changeCostTable.getId());
		}
		for (ReimbursementCostTable reimbursementCostTable : full.getReimbursementCostTables()) {
			this.updateByString("ReimbursementCostTable", "remittanced=true", "id=?", reimbursementCostTable.getId());
		}
	}

}
