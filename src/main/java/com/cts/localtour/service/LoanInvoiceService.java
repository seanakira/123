package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.LoanInvoiceTable;
import com.cts.localtour.viewModel.LoanInvoiceViewModel;

@SuppressWarnings("rawtypes")
@Service
public class LoanInvoiceService extends BaseService{
	@Autowired
	private LoanInvoiceViewModel loanInvoiceViewModel;

	public ArrayList<LoanInvoiceViewModel> findInvoice(int tourId) {
		return loanInvoiceViewModel.getAllLoanInvoiceViewModel(tourId, -1);
	}

	@SuppressWarnings("unchecked")
	public void saveLoanInvoice(ArrayList<LoanInvoiceTable> loanInvoiceTables) {
		for (LoanInvoiceTable loanInvoiceTable : loanInvoiceTables) {
			LoanInvoiceTable invoice = (LoanInvoiceTable)this.getById("LoanInvoiceTable", loanInvoiceTable.getId());
			if(invoice.getStatus()==2||invoice.getStatus()==3){
				if(loanInvoiceTable.getInvoiceAmount().compareTo(new BigDecimal(0))==0){
					this.delete(invoice);
				}else{
					invoice.setStatus(3);
					invoice.setInvoiceNo(loanInvoiceTable.getInvoiceNo());
					invoice.setInvoiceAmount(loanInvoiceTable.getInvoiceAmount());
					this.update(invoice);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public BigDecimal getLoanInvoiceSum(int tourId) {
		BigDecimal loanInvoiceSum = new BigDecimal(0);
		ArrayList<LoanInvoiceTable> loanInvoiceTables = (ArrayList<LoanInvoiceTable>) this.getAllByString("LoanInvoiceTable", "tourId=? and status=3", tourId);
		for (LoanInvoiceTable loanInvoiceTable : loanInvoiceTables) {
			loanInvoiceSum = loanInvoiceSum.add(loanInvoiceTable.getInvoiceAmount());
		}
		return loanInvoiceSum;
	}
	

}
