package com.cts.localtour.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.viewModel.PrintVoucherViewModel;


@SuppressWarnings("rawtypes")
@Service
public class PrintService extends BaseService{
	@Autowired
	private PrintVoucherViewModel payVoucherViewModel;
	public PrintVoucherViewModel printPayVoucher(String type, int tourId) {
		return payVoucherViewModel.getPrintViewModel(tourId,type);
	}
	@SuppressWarnings("unchecked")
	public void printCountPlus(String type, int tourId) {
		if("lend".equals(type)){
			ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=? and status=4 and lended=false", tourId);
			int printConunt = 0;
			for (LoanTable loanTable : loanTables) {
				if(loanTable.getPrintCount()!=null&&printConunt<loanTable.getPrintCount()){
					printConunt = loanTable.getPrintCount();
				}
			}
			printConunt++;
			this.updateByString("LoanTable", "printCount=?", "tourId=? and status=4 and lended=false", printConunt,tourId);
		}else if("pay".equals(type)){
			ArrayList<CostTable> costTables = (ArrayList<CostTable>) this.getAllByString("CostTable", "tourId=? and remittanced=false and lend=false and bill=false and payStatus=3", tourId);
			ArrayList<ChangeCostTable> changeCostTables = (ArrayList<ChangeCostTable>) this.getAllByString("ChangeCostTable", "tourId=? and remittanced=false and lend=false and bill=false and payStatus=3", tourId);
			int printConunt = 0;
			for (CostTable costTable : costTables) {
				if(costTable.getPrintCount()!=null&&printConunt<costTable.getPrintCount()){
					printConunt = costTable.getPrintCount();
				}
			}
			for (ChangeCostTable changeCostTable : changeCostTables) {
				if(changeCostTable.getPrintCount()!=null&&printConunt<changeCostTable.getPrintCount()){
					printConunt = changeCostTable.getPrintCount();
				}
			}
			printConunt++;
			this.updateByString("CostTable", "printCount=?", "tourId=? and remittanced=false and lend=false and bill=false and payStatus=3",  printConunt,tourId);
			this.updateByString("ChangeCostTable", "printCount=?", "tourId=? and remittanced=false and lend=false and bill=false and payStatus=3",  printConunt,tourId);
		}
	}

}
