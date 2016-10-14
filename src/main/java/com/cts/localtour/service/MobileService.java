package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.SupplierContentTable;
import com.cts.localtour.entity.SupplierTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.util.WeiXinUtil;
import com.cts.localtour.viewModel.ChangeCostIncomeViewModel;
import com.cts.localtour.viewModel.ChangeCostViewModel;
import com.cts.localtour.viewModel.ChangeIncomeViewModel;
import com.cts.localtour.viewModel.LoanViewModel;

@SuppressWarnings("rawtypes")
@Service
public class MobileService extends BaseService{
	@SuppressWarnings("unchecked")
	public ChangeCostIncomeViewModel getAllChangCostIncome(int tourId, int status) {
		/*������ҪȨ���ж�  ������ܾ���*/
		ArrayList<ChangeCostTable> costTables;
		ArrayList<ChangeIncomeTable> incomeTables;
		if(status==-1){
			costTables = (ArrayList<ChangeCostTable>)this.getAllByString("ChangeCostTable", "tourId=? and status>1", tourId);
			incomeTables = (ArrayList<ChangeIncomeTable>) this.getAllByString("ChangeIncomeTable", "tourId=? and status>1", tourId);
		}else{
			costTables = (ArrayList<ChangeCostTable>)this.getAllByString("ChangeCostTable", "tourId=? and status=?", tourId, status);
			incomeTables = (ArrayList<ChangeIncomeTable>) this.getAllByString("ChangeIncomeTable", "tourId=? and status=?", tourId, status);
		}
		ChangeCostIncomeViewModel changeCostIncomeViewModel = new ChangeCostIncomeViewModel();
		ArrayList<ChangeCostViewModel> costs = new ArrayList<ChangeCostViewModel>();
		for (int i = 0; i < costTables.size(); i++) {
			ChangeCostViewModel cost = new ChangeCostViewModel();
			cost.setCostTable(costTables.get(i));
			cost.setApplicationerRealName(costTables.get(i).getApplicationerId()==null||costTables.get(i).getApplicationerId()==0?"":((UserTable)this.getById("UserTable", costTables.get(i).getApplicationerId())).getRealName());
			cost.setContentName(costTables.get(i).getContentId()==null||costTables.get(i).getContentId()==0?"":((SupplierContentTable)this.getById("SupplierContentTable", costTables.get(i).getContentId())).getContentName());
			cost.setSupplierName(((SupplierTable)this.getById("SupplierTable", costTables.get(i).getSupplierId())).getSupplierName());
			if(costTables.get(i).isRemittanced()){
				cost.setStatus("�ѵ��");
			}else{
				cost.setStatus(costTables.get(i).getStatus()==0?"�½�":costTables.get(i).getStatus()==1?"���ύ":costTables.get(i).getStatus()==2?"�����":costTables.get(i).getStatus()==3?"����׼":"");
			}
			costs.add(cost);
		}
		ArrayList<ChangeIncomeViewModel> incomes = new ArrayList<ChangeIncomeViewModel>();
		for (ChangeIncomeTable incomeTable : incomeTables) {
			ChangeIncomeViewModel income = new ChangeIncomeViewModel();
			income.setIncomeTable(incomeTable);
			income.setCustomerAgencyName(((CustomerAgencyTable)this.getById("CustomerAgencyTable", incomeTable.getCustomerAgencyId())).getCustomerAgencyName());
			income.setApplicationerRealName(incomeTable.getApplicationerId()==null||incomeTable.getApplicationerId()==0?"":((UserTable)this.getById("UserTable", incomeTable.getApplicationerId())).getRealName());
			if(incomeTable.getIncomed()){
				income.setStatus("���տ�");
			}else{
				income.setStatus(incomeTable.getStatus()==0?"�½�":incomeTable.getStatus()==1?"���ύ":incomeTable.getStatus()==2?"�����":incomeTable.getStatus()==3?"����׼":"");
			}
			BigDecimal invoiceAmount = new BigDecimal(0);
			ArrayList<InvoiceTable> invoiceTables =  (ArrayList<InvoiceTable>) this.getAllByString("InvoiceTable", "changeIncomeId=?", incomeTable.getId());
			for (InvoiceTable invoiceTable : invoiceTables) {
				invoiceAmount = invoiceAmount.add(new BigDecimal(invoiceTable.getInvoiceAmount()));
			}
			income.setInvoiceAmount(invoiceAmount.floatValue());
			incomes.add(income);
		}
		changeCostIncomeViewModel.setCosts(costs);
		changeCostIncomeViewModel.setIncomes(incomes);
		return changeCostIncomeViewModel;
	}
	
	@SuppressWarnings("unchecked")
	public void updateChangeCost(HttpServletRequest request, HttpSession session, int id) {
		ChangeCostTable cost = (ChangeCostTable)this.getById("ChangeCostTable", id);
		/*������ҪȨ���ж�  ��������ľ���*/
		cost.setStatus(2);
		this.update(cost);
		StringBuffer path = request.getRequestURL();  
		String tempContextUrl = path.delete(path.length() - request.getRequestURI().length(), path.length()).append(request.getServletContext().getContextPath()).append("/").toString();
		String url = tempContextUrl+"mobile/changeCostIncomeApproval?tourId="+cost.getTourId()+"&status=2";
	    UserTable user = (UserTable) session.getAttribute("user");
	    DeptTable dept = (DeptTable) this.getById("DeptTable", user.getDeptId());
	    String managerIds = dept.getManagerIds();
	    String[] ids = managerIds.split(",");
	    for (int i = 0; i < ids.length; i++) {
	    	UserTable manager = (UserTable)super.getById("UserTable", Integer.parseInt(ids[i]));
	    	WeiXinUtil.sendTextMessage(manager.getUserName(), url, "���д���˵�<�����ɱ�����>������������", "0");
		}
	    /*������ܾ���*/
	    /*cost.setStatus(3);
		this.update(cost);*/
	}

	@SuppressWarnings("unchecked")
	public ArrayList<LoanViewModel> getAllLoanApplication(int tourId, int status) {
		ArrayList<LoanTable> loanTables;
		/*������ҪȨ���ж� ������ܾ��� status��3*/
		if(status==-1){
			loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=? and status>2", tourId);
		}else{
			loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=? and status=?", tourId, status);
		}
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (int i = 0; i < loanTables.size(); i++) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTables.get(i));
			loan.setApplicationerRealName(((UserTable)this.getById("UserTable", loanTables.get(i).getApplicationerId())).getRealName());
			if(loanTables.get(i).getLended()){
				loan.setStatus("�ѽ��");
			}else{
				if(loanTables.get(i).getStatus()==0){
					loan.setStatus("�½�");
				}else if(loanTables.get(i).getStatus()==1){
					loan.setStatus("�ɽ�");
				}else if(loanTables.get(i).getStatus()==2){
					loan.setStatus("���ύ");
				}else if(loanTables.get(i).getStatus()==3){
					loan.setStatus("�����");
				}else if(loanTables.get(i).getStatus()==4){
					loan.setStatus("����׼");
				}
			}
			loans.add(loan);
		}
		return loans;
	}

	@SuppressWarnings("unchecked")
	public void updateLoanTable(HttpServletRequest request, HttpSession session, int id) {
		/*������ҪȨ���ж� ��������ľ���*/
		LoanTable loan = (LoanTable) this.getById("LoanTable", id);
		loan.setStatus(3);
		this.update(loan);
	    this.sendMessage("loanApplication", loan.getTourId(), 3, "���д���˵�<��������>������������", request, session);
		/*������ܾ���*/
	    /*LoanTable loan = (LoanTable) this.getById("LoanTable", id);
		loan.setStatus(4);
		this.update(loan);*/
	}

	public void sendMessage(String mobileControllerMapping, int tourId, int status, String message, HttpServletRequest request, HttpSession session){
		StringBuffer path = request.getRequestURL();  
		String tempContextUrl = path.delete(path.length() - request.getRequestURI().length(), path.length()).append(request.getServletContext().getContextPath()).append("/").toString();
	    /*URL��Ҫ��Ȩ���ж�*/
		String url = tempContextUrl+"mobile/"+mobileControllerMapping+"?tourId="+tourId+"&status="+status;
	    UserTable user = (UserTable) session.getAttribute("user");
	    DeptTable dept = (DeptTable) this.getById("DeptTable", user.getDeptId());
	    String managerIds = dept.getManagerIds();
	    String[] ids = managerIds.split(",");
	    for (int i = 0; i < ids.length; i++) {
	    	UserTable manager = (UserTable)this.getById("UserTable", Integer.parseInt(ids[i]));
	    	WeiXinUtil.sendTextMessage(manager.getUserName(), url, message, "0");
		}
	}
}
