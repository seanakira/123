package com.cts.localtour.service;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.FullBillViewModel;
import com.cts.localtour.viewModel.SimpleBillCheckViewModel;

@SuppressWarnings("rawtypes")
@Service 
public class BillApplicationService extends BaseService{
	@Autowired
	private SimpleBillCheckViewModel simpleBillCheckViewModel;
	@Autowired
	private FullBillViewModel fullBillViewModel;
	
	public int getCounts(String key) {
		/*这里需要做数据权限*/
		/*这里需要权限判断中心经理payStatus=1 总经理payStatus=2*/
		int payStatus = this.getRoleCode()-1;
		if(key.equals("")){
			return this.getByHql("select distinct s from SupplierTable s, BillApplicationTable b ,CostTable c, ChangeCostTable cc, LocalTourTable l where (s.id=b.supplierId and b.supplierId=c.supplierId and c.bill=true and c.remittanced=false and c.payStatus="+payStatus+" and c.tourId=l.id and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")) or (s.id=b.supplierId and b.supplierId=cc.supplierId and cc.bill=true and cc.remittanced=false and cc.payStatus="+payStatus+" and cc.status=3 and cc.tourId=l.id and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+"))").size();
		}else{
			return this.getByHql("select distinct s from SupplierTable s, BillApplicationTable b ,CostTable c, ChangeCostTable cc, LocalTourTable l where (s.id=b.supplierId and b.supplierId=c.supplierId and c.bill=true and c.remittanced=false and c.payStatus="+payStatus+" and c.tourId=l.id and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")) or (s.id=b.supplierId and b.supplierId=cc.supplierId and cc.bill=true and cc.remittanced=false and cc.payStatus="+payStatus+" and cc.status=3 and cc.tourId=l.id and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")) and s.supplierName like '%"+key+"%'").size();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SimpleBillCheckViewModel> getAll(String key, int page, int maxResults) {
		/*这里需要做数据权限*/
		/*这里需要权限判断中心经理payStatus=1 总经理payStatus=2*/
		int payStatus = this.getRoleCode()-1;
		if(key.equals("")){
			ArrayList<SupplierTable> supplierTables = (ArrayList<SupplierTable>) this.getAllByHql("select distinct s from SupplierTable s, BillApplicationTable b ,CostTable c, ChangeCostTable cc, LocalTourTable l where (s.id=b.supplierId and b.supplierId=c.supplierId and c.bill=true and c.remittanced=false and c.payStatus="+payStatus+" and c.tourId=l.id and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")) or (s.id=b.supplierId and b.supplierId=cc.supplierId and cc.bill=true and cc.remittanced=false and cc.payStatus="+payStatus+" and cc.status=3 and cc.tourId=l.id and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+"))", page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillTodowModel(supplierTables,0);
		}else{
			ArrayList<SupplierTable> supplierTables = (ArrayList<SupplierTable>) this.getAllByHql("select distinct s from SupplierTable s, BillApplicationTable b ,CostTable c, ChangeCostTable cc, LocalTourTable l where (s.id=b.supplierId and b.supplierId=c.supplierId and c.bill=true and c.remittanced=false and c.payStatus="+payStatus+" and c.tourId=l.id and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")) or (s.id=b.supplierId and b.supplierId=cc.supplierId and cc.bill=true and cc.remittanced=false and cc.payStatus="+payStatus+" and cc.status=3 and cc.tourId=l.id and l.deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")) and s.supplierName like '%"+key+"%'", page, maxResults);
			return simpleBillCheckViewModel.getAllSimpleBillTodowModel(supplierTables,0);
		}
	}

	public FullBillViewModel findbillApplication(int supplierId, int relativePeriod) {
		/*这里需要判断用户权限 如果是中心经理1 总经理2*/
		return fullBillViewModel.getFullBillCheckViewModel(supplierId, this.getRoleCode()-1, relativePeriod);
	}

	public void okbillApplication(int supplierId, String[] costIds, String[] changeCostIds) {
		/*这里需要判断用户权限 如果是中心经理2 总经理3*/
		for (String id : costIds) {
			if(!"".equals(id)){
				this.updateByString("CostTable", "payStatus=?", "id=?", this.getRoleCode(), Integer.parseInt(id));
			}
		}
		for (String id : changeCostIds) {
			if(!"".equals(id)){
				this.updateByString("ChangeCostTable", "payStatus=?", "id=?", this.getRoleCode(), Integer.parseInt(id));
			}
		}
		if(this.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2)", supplierId).isEmpty()&&this.getAllByString("ChangeCostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2) and status=3", supplierId).isEmpty()){
			this.deleteByString("BillApplicationTable", "supplierId=?", supplierId);
		}
	}

	public void cancelbillApplication(int supplierId, String[] costIds, String[] changeCostIds) {
		for (String id : costIds) {
			if(!"".equals(id)){
				this.updateByString("CostTable", "payStatus=?,realCost=0", "id=?", 0, Integer.parseInt(id));
			}
		}
		for (String id : changeCostIds) {
			if(!"".equals(id)){
				this.updateByString("ChangeCostTable", "payStatus=?,realCost=0", "id=?", 0, Integer.parseInt(id));
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
