package com.cts.localtour.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.viewModel.PayVoucherViewModel;


@SuppressWarnings("rawtypes")
@Service
public class PrintService extends BaseService{
	@Autowired
	private PayVoucherViewModel payVoucherViewModel;
	public PayVoucherViewModel printPayVoucher(String type, int tourId) {
		if("lend".equals(type)){
			return payVoucherViewModel.getPrintViewModel(tourId);
		}else if("pay".equals(type)){
			
			
		}
		return null;
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
			
		}
	}

}
