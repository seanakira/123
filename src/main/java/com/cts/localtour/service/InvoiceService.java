package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.BaseDAO;
import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.viewModel.InvoiceViewModel;

@SuppressWarnings("rawtypes")
@Service
public class InvoiceService extends BaseService{
	@Autowired
	private InvoiceViewModel invoiceViewModel;
	@Autowired
	private BaseDAO baseDAO;
	@SuppressWarnings("unchecked")
	public float getInvoiceSum(Integer tourId){
		BigDecimal invoiceSum = new BigDecimal(0);
		ArrayList<InvoiceTable> InvoiceTables = (ArrayList<InvoiceTable>) this.getAllByString("InvoiceTable", "tourId=?", tourId);
		for (InvoiceTable invoiceTable : InvoiceTables) {
			invoiceSum = invoiceSum.add(new BigDecimal(invoiceTable.getInvoiceAmount()));
		}
		return invoiceSum.floatValue();
	}

	public ArrayList<InvoiceViewModel> findInvoice(int tourId) {
		return invoiceViewModel.getAllInvoiceViewModel(tourId);
	}

	@SuppressWarnings("unchecked")
	public void saveInvoice(ArrayList<InvoiceTable> invoiceTables) throws Exception{
		for (InvoiceTable invoiceTable : invoiceTables) {
				baseDAO.add(invoiceTable);
		}
	}
}
