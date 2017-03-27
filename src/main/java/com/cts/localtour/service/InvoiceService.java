package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.viewModel.FullInvoiceViewModel;

@SuppressWarnings("rawtypes")
@Service
public class InvoiceService extends BaseService{
	@Autowired
	private FullInvoiceViewModel fullInvoiceViewModel;
	@Autowired
	private BaseService baseService;
	@SuppressWarnings("unchecked")
	public float getInvoiceSum(Integer tourId){
		BigDecimal invoiceSum = new BigDecimal(0);
		ArrayList<InvoiceTable> InvoiceTables = (ArrayList<InvoiceTable>) this.getAllByString("InvoiceTable", "tourId=?", tourId);
		for (InvoiceTable invoiceTable : InvoiceTables) {
			invoiceSum = invoiceSum.add(new BigDecimal(invoiceTable.getInvoiceAmount()));
		}
		return invoiceSum.floatValue();
	}

	public FullInvoiceViewModel findInvoice(int tourId) {
		return fullInvoiceViewModel.getFullInvoiceViewModel(tourId);
	}

	@SuppressWarnings("unchecked")
	public void saveInvoice(ArrayList<InvoiceTable> invoiceTables) throws Exception{
		for (InvoiceTable invoiceTable : invoiceTables) {
			if(invoiceTable.getId()==null){
				invoiceTable.setCustomerAgencyId(((LocalTourTable)baseService.getById("LocalTourTable", invoiceTable.getTourId())).getCustomerAgencyId());
				this.add(invoiceTable);
			}else{
				this.updateByString("InvoiceTable", "invoiceNo=?, invoiceAmount=?", "id=?", invoiceTable.getInvoiceNo(), invoiceTable.getInvoiceAmount(), invoiceTable.getId());
			}
		}
	}
}
