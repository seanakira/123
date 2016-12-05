package com.cts.localtour.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.viewModel.FullBillViewModel;
import com.cts.localtour.viewModel.SimpleBillCheckViewModel;

@SuppressWarnings("rawtypes")
@Service 
public class BillApplicationService extends BaseService{
	@Autowired
	private SimpleBillCheckViewModel simpleBillCheckViewModel;
	@Autowired
	private FullBillViewModel fullBillViewModel;
	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		/*这里需要做数据权限*/
		if(key.equals("")){
			return this.getCountsByParam("BillApplicationTable", "", null);
		}else{
			return this.getCountsByParam("BillApplicationTable", "supplierId like '%"+key+"%'", null);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SimpleBillCheckViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			/*这里需要做数据权限*/
			/*这里需要权限判断中心经理payStatus=1 总经理payStatus=2*/
			ArrayList<SupplierTable> supplierTables = (ArrayList<SupplierTable>) this.getByHql("select distinct s from SupplierTable s, BillApplicationTable b ,CostTable c, ChangeCostTable cc where (s.id=b.supplierId and b.supplierId=c.supplierId and c.bill=true and c.remittanced=false and c.payStatus=1) or (s.id=b.supplierId and b.supplierId=cc.supplierId and cc.bill=true and cc.remittanced=false and cc.payStatus=1 and cc.status=3)");
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables);
		}else{ArrayList<SupplierTable> supplierTables = (ArrayList<SupplierTable>) this.getByHql("select distinct s from SupplierTable s, BillApplicationTable b ,CostTable c, ChangeCostTable cc where (s.id=b.supplierId and b.supplierId=c.supplierId and c.bill=true and c.remittanced=false and c.payStatus=1) or (s.id=b.supplierId and b.supplierId=cc.supplierId and cc.bill=true and cc.remittanced=false and cc.payStatus=1 and cc.status=3) and s.supplierName like '%"+key+"%'");
			return simpleBillCheckViewModel.getAllSimpleBillCheckViewModel(supplierTables);
		}
	}

	public FullBillViewModel findbillApplication(int supplierId ,int payStatus) {
		return fullBillViewModel.getFullBillCheckViewModel(supplierId, payStatus);
	}

	public void okbillApplication(int supplierId, String[] costIds, String[] changeCostIds, int payStatus) {
		for (String id : costIds) {
			if(!"".equals(id)){
				this.updateByString("CostTable", "payStatus=?", "id=?", payStatus, Integer.parseInt(id));
			}
		}
		for (String id : changeCostIds) {
			if(!"".equals(id)){
				this.updateByString("ChangeCostTable", "payStatus=?", "id=?", payStatus, Integer.parseInt(id));
			}
		}
		if(this.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2)", supplierId).isEmpty()&&this.getAllByString("ChangeCostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2) and status=3", supplierId).isEmpty()){
			this.deleteByString("BillApplicationTable", "supplierId=?", supplierId);
		}
	}

	public void cancelbillApplication(int supplierId, String[] costIds, String[] changeCostIds) {
		for (String id : costIds) {
			if(!"".equals(id)){
				this.updateByString("CostTable", "payStatus=?,realCost=0,reimbursement=null", "id=?", 0, Integer.parseInt(id));
			}
		}
		for (String id : changeCostIds) {
			if(!"".equals(id)){
				this.updateByString("ChangeCostTable", "payStatus=?,realCost=0,reimbursement=null", "id=?", 0, Integer.parseInt(id));
			}
		}
		if(this.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2)", supplierId).isEmpty()&&this.getAllByString("ChangeCostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2) and status=3", supplierId).isEmpty()){
			this.deleteByString("BillApplicationTable", "supplierId=?", supplierId);
		}
	}

	public int checkStatusbillApplication(int supplierId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
