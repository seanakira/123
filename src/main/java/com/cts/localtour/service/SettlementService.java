package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.ReimbursementCostTable;
import com.cts.localtour.entity.ReimbursementIncomeTable;
import com.cts.localtour.viewModel.SettlementChangeViewModel;
import com.cts.localtour.viewModel.SimpleSettlementViewModel;

@SuppressWarnings("rawtypes")
@Service
public class SettlementService extends BaseService{
	@Autowired
	private SimpleSettlementViewModel simpleSettlementViewModel;
	@Autowired
	private SettlementChangeViewModel settlementChangeViewModel;
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleSettlementViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			/*这里需要做数据权限*/
			ArrayList<LocalTourTable> localTourTables = this.getAllByParam("LocalTourTable", "status>=9 and enable=true", null, page, maxResults);
			return simpleSettlementViewModel.getAllSimpleSettlementViewModel(localTourTables);
		}else{
			ArrayList<LocalTourTable> localTourTables = this.getAllByParam("LocalTourTable", "(tourNO like '%"+key+"%' or tourName like '%"+key+"%') and status>=9 and enable=true", null, page, maxResults);
			return simpleSettlementViewModel.getAllSimpleSettlementViewModel(localTourTables);
		}
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		/*这里需要做数据权限*/
		if(key.equals("")){
			return this.getCountsByParam("LocalTourTable", "status>=9", null);
		}else{
			return this.getCountsByParam("LocalTourTable", "(tourNo like '%"+key+"%' or tourName like '%"+key+"%') and status>=9", null);
		}
	}

	public void updateSettlement(int tourId) {
		if(((LocalTourTable)this.getById("LocalTourTable", tourId)).getStatus()==9){
			this.updateByString("LocalTourTable", "status=10, settlementTime=?", "id=?",new Date() , tourId);
		}
	}

	public void cancelSettlement(int tourId) {
		if(((LocalTourTable)this.getById("LocalTourTable", tourId)).getStatus()==9){
			this.updateByString("LocalTourTable", "status=8", "id=?", tourId);
		}
	}

	public int checkStatusSettlement(int tourId) {
		if(((LocalTourTable)this.getById("LocalTourTable", tourId)).getStatus()==10){
			return 1;
		}
		return 0;
	}

	public SettlementChangeViewModel settlementChangeFind(int tourId) {
		return settlementChangeViewModel.getAllSettlementChangeViewModel(tourId);
	}

	@SuppressWarnings("unchecked")
	public int settlementChangeSave(SettlementChangeViewModel settlementChangeViewModel) {
		for (ReimbursementIncomeTable incomeTable : settlementChangeViewModel.getIncomeTables()) {
			if(incomeTable.getIncome()!=null&&incomeTable.getIncome().compareTo(new BigDecimal(0))!=0){
				incomeTable.setRemark("财务结算调整");
				this.add(incomeTable);
			}
		}
		for (ReimbursementCostTable costTable : settlementChangeViewModel.getCostTables()) {
			if(costTable.getSupplierId()==0||costTable.getCost()==null||costTable.getCount()==0||costTable.getDays()==0){
				return -1;
			}
			if(costTable.getReimbursement()!=null&&costTable.getReimbursement().compareTo(new BigDecimal(0))!=0){
				costTable.setBill(false);
				costTable.setPayStatus(1);
				costTable.setRemittanced(false);
				costTable.setRemark("财务结算调整");
				this.add(costTable);
			}
		}
		return 0;
	}
}
