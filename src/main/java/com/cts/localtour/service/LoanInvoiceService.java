package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.InvoiceTable;
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

	public void saveLoanInvoice(ArrayList<LoanInvoiceTable> loanInvoiceTables) {
		for (LoanInvoiceTable loanInvoiceTable : loanInvoiceTables) {
			this.updateByString("LoanInvoiceTable", "status=3", "id=?", loanInvoiceTable.getId());
		}
	}

	@SuppressWarnings("unchecked")
	public float getLoanInvoiceSum(int tourId) {
		BigDecimal loanInvoiceSum = new BigDecimal(0);
		ArrayList<LoanInvoiceTable> loanInvoiceTables = (ArrayList<LoanInvoiceTable>) this.getAllByString("LoanInvoiceTable", "tourId=? and status=3", tourId);
		for (LoanInvoiceTable loanInvoiceTable : loanInvoiceTables) {
			loanInvoiceSum = loanInvoiceSum.add(new BigDecimal(loanInvoiceTable.getInvoiceAmount()));
		}
		return loanInvoiceSum.floatValue();
	}
	

}
