package com.cts.localtour.service;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.ReimbursementApplicationTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.viewModel.FullBillViewModel;
import com.cts.localtour.viewModel.FullReimbursementApplicationViewModel;
import com.cts.localtour.viewModel.SimpleReimbursementApplicationViewModel;

@SuppressWarnings("rawtypes")
@Service
public class ReimbursementApplicationService extends BaseService{
	@Autowired
	private SimpleReimbursementApplicationViewModel simpleReimbursementApplicationViewModel;
	@Autowired
	private FullReimbursementApplicationViewModel fullReimbursementApplicationViewModel;
	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		/*这里需要做数据权限*/
		if(key.equals("")){
			return this.getCountsByParam("ReimbursementApplicationTable", "", null);
		}else{
			return this.getCountsByParam("ReimbursementApplicationTable", "tourId like '%"+key+"%'", null);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SimpleReimbursementApplicationViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			/*这里需要做数据权限*/
			ArrayList<ReimbursementApplicationTable> reimbursementTables = (ArrayList<ReimbursementApplicationTable>) this.getAllByString("ReimbursementApplicationTable", "deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")");
			return simpleReimbursementApplicationViewModel.getAllSimpleReimbursementApplicationViewModel(reimbursementTables);
		}else{
			ArrayList<ReimbursementApplicationTable> reimbursementTables = (ArrayList<ReimbursementApplicationTable>) this.getAllByString("ReimbursementTable", "tourId like '%"+key+"%' and deptId in ("+((UserTable)SecurityUtils.getSubject().getPrincipal()).getDataDeptIds()+")");
			return simpleReimbursementApplicationViewModel.getAllSimpleReimbursementApplicationViewModel(reimbursementTables);
		}
	}

	public FullReimbursementApplicationViewModel findReimbursementApplication(int tourId) {
		return fullReimbursementApplicationViewModel.getFullReimbursementApplicationViewModel(tourId);
	}

	public void reimbursementApplicationOk(int tourId) {
		if(((LocalTourTable)this.getById("LocalTourTable", tourId)).getStatus()==6){
			this.updateByString("LocalTourTable", "status=?", "id=?", 7, tourId);
			this.updateByString("ReimbursementCostTable", "payStatus=1", "tourId=?", tourId);
			this.deleteByString("ReimbursementApplicationTable", "tourId=?", tourId);
		}
	}

	public void reimbursementApplicationCancel(int tourId) {
		if(((LocalTourTable)this.getById("LocalTourTable", tourId)).getStatus()==6){
			this.updateByString("CostTable", "reimbursement=null", "tourId=? and payStatus=3", tourId);
			this.updateByString("ChangeCostTable", "reimbursement=null", "tourId=? and ((payStatus=3 and status=3) or (status=3 and lend=true))", tourId);
			this.updateByString("ReimbursementCostTable", "reimbursement=null", "tourId=?", tourId);
			this.deleteByString("ReimbursementTable", "tourId=?", tourId);
			this.deleteByString("ReimbursementApplicationTable", "tourId=?", tourId);
		}
	}

	public int checkStatusReimbursementApplication(int tourId) {
		if(this.getAllByString("ReimbursementApplicationTable", "tourId=?", tourId).isEmpty()){
			return 1;
		}
		return 0;
	}

	public FullBillViewModel findbillApplication(int supplierId) {
		// TODO Auto-generated method stub
		return null;
	}
}
