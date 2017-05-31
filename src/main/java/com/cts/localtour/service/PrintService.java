package com.cts.localtour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.LoanInvoiceTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.RefundTable;
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
	public void printCountPlus(String[] ids, String type) {
		if("".equals(type)){
			for (String id : ids) {
				LoanTable loan = (LoanTable) this.getById("LoanTable", Integer.parseInt(id));
				loan.setPrintCount(loan.getPrintCount()==null?1:(loan.getPrintCount()+1));
				this.update(loan);
			}
		}else if("invoice".equals(type)){
			for (String id : ids) {
				LoanInvoiceTable loanInvoice = (LoanInvoiceTable) this.getById("LoanInvoiceTable", Integer.parseInt(id));
				loanInvoice.setPrintCount(loanInvoice.getPrintCount()==null?1:(loanInvoice.getPrintCount()+1));
				this.update(loanInvoice);
			}
		}else if("refund".equals(type)){
			for (String id : ids) {
				RefundTable refundTable = (RefundTable) this.getById("RefundTable", Integer.parseInt(id));
				refundTable.setPrintCount(refundTable.getPrintCount()==null?1:(refundTable.getPrintCount()+1));
				this.update(refundTable);
			}
		}
		
	}
	@SuppressWarnings("unchecked")
	public void printCountPlus(String[] costIds, String[] changeCostIds) {
		for (String id : costIds) {
			if(!id.equals("")){
				CostTable cost = (CostTable) this.getById("CostTable", Integer.parseInt(id));
				cost.setPrintCount(cost.getPrintCount()==null?1:(cost.getPrintCount()+1));
				this.update(cost);
			}
		}
		for (String id : changeCostIds) {
			if(!id.equals("")){
				ChangeCostTable cost = (ChangeCostTable) this.getById("ChangeCostTable", Integer.parseInt(id));
				cost.setPrintCount(cost.getPrintCount()==null?1:(cost.getPrintCount()+1));
				this.update(cost);
			}
		}
	}

}
