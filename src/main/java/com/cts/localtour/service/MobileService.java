package com.cts.localtour.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.InvoiceTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
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
	@Autowired
	private UserService userService;
	@Autowired
	private LocalTourService localTourService;
	@SuppressWarnings("unchecked")
	public ChangeCostIncomeViewModel getAllChangCostIncome(int tourId, int status) {
		/*这里需要权限判断  如果是总经理*/
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
				cost.setStatus("已电汇");
			}else{
				cost.setStatus(costTables.get(i).getStatus()==0?"新建":costTables.get(i).getStatus()==1?"待审核":costTables.get(i).getStatus()==2?"待批准":costTables.get(i).getStatus()==3?"已批准":"");
			}
			costs.add(cost);
		}
		ArrayList<ChangeIncomeViewModel> incomes = new ArrayList<ChangeIncomeViewModel>();
		for (ChangeIncomeTable incomeTable : incomeTables) {
			ChangeIncomeViewModel income = new ChangeIncomeViewModel();
			income.setIncomeTable(incomeTable);
			income.setCustomerAgencyName(((CustomerAgencyTable)this.getById("CustomerAgencyTable", incomeTable.getCustomerAgencyId())).getCustomerAgencyName());
			income.setApplicationerRealName(incomeTable.getApplicationerId()==null||incomeTable.getApplicationerId()==0?"":((UserTable)this.getById("UserTable", incomeTable.getApplicationerId())).getRealName());
			if(incomeTable.isIncomed()){
				income.setStatus("已收款");
			}else{
				income.setStatus(incomeTable.getStatus()==0?"新建":incomeTable.getStatus()==1?"待审核":incomeTable.getStatus()==2?"待批准":incomeTable.getStatus()==3?"已批准":"");
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
		/*这里需要权限判断  如果是中心经理*/
		cost.setStatus(2);
		this.update(cost);
		this.sendMessage("changeCostIncomeApproval", cost.getTourId(), 2, "您有待审核的<变更成本收入>，点击进行审核", request, session);
	    /*如果是总经理*/
	    /*cost.setStatus(3);
		this.update(cost);*/
	}
	
	@SuppressWarnings("unchecked")
	public void updateChangeIcome(HttpServletRequest request, HttpSession session, int id) {
		ChangeIncomeTable income = (ChangeIncomeTable)this.getById("ChangeIncomeTable", id);
		/*这里需要权限判断  如果是中心经理*/
		income.setStatus(2);
		this.update(income);
	    this.sendMessage("changeCostIncomeApproval", income.getTourId(), 2, "您有待审核的<变更成本收入>，点击进行审核", request, session);
	    /*如果是总经理*/
	    /*cost.setStatus(3);
		this.update(cost);*/
	}
	
	@SuppressWarnings("unchecked")
	public void cancalChangeCost(HttpServletRequest request, HttpSession session, int id) {
		ChangeCostTable cost = (ChangeCostTable)this.getById("ChangeCostTable", id);
		this.delete(cost);
		WeiXinUtil.sendTextMessage(((UserTable)this.getById("UserTable", cost.getApplicationerId())).getUserName(), null, "您的<"+localTourService.getTourNoAndTourName(cost.getTourId())+">团，申请的成本变更已经被驳回，并且已经被删除，如需再次申请，请重新添加。", "0");
	}

	@SuppressWarnings("unchecked")
	public void cancalChangeIncome(HttpServletRequest request, HttpSession session, int id) {
		ChangeIncomeTable income = (ChangeIncomeTable)this.getById("ChangeIncomeTable", id);
		this.delete(income);
		LocalTourTable tour = (LocalTourTable) this.getById("LocalTourTable", income.getTourId());
		WeiXinUtil.sendTextMessage(((UserTable)this.getById("UserTable", income.getApplicationerId())).getUserName(), null, "您的"+tour.getTourNo()+"  "+tour.getTourName()+"团，申请的收入变更已经被驳回，并且已经被删除，如需再次申请，请重新添加。", "0");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<LoanViewModel> getAllLoanApplication(int tourId, int status) {
		ArrayList<LoanTable> loanTables;
		/*这里需要权限判断 如果是总经理 status》3*/
		if(status==-1){
			loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=? and status>2", tourId);
		}else{
			loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=? and status=?", tourId, status);
		}
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (int i = 0; i < loanTables.size(); i++) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTables.get(i));
			loan.setApplicationerRealName(loanTables.get(i).getApplicationerId()==null?"":((UserTable)this.getById("UserTable", loanTables.get(i).getApplicationerId())).getRealName());
			if(loanTables.get(i).isLended()){
				loan.setStatus("已借出");
			}else{
				if(loanTables.get(i).getStatus()==0){
					loan.setStatus("新建");
				}else if(loanTables.get(i).getStatus()==1){
					loan.setStatus("可借");
				}else if(loanTables.get(i).getStatus()==2){
					loan.setStatus("待审核");
				}else if(loanTables.get(i).getStatus()==3){
					loan.setStatus("待批准");
				}else if(loanTables.get(i).getStatus()==4){
					loan.setStatus("已批准");
				}
			}
			loans.add(loan);
		}
		return loans;
	}

	@SuppressWarnings("unchecked")
	public void loanApplicationOk(HttpServletRequest request, HttpSession session, int id) {
		/*这里需要权限判断 如果是中心经理*/
		LoanTable loan = (LoanTable) this.getById("LoanTable", id);
		loan.setStatus(3);
		this.update(loan);
	    this.sendMessage("loanApplication", loan.getTourId(), 3, "您有待审核的<付款申请>，点击进行审核", request, session);
		/*如果是总经理*/
	    /*LoanTable loan = (LoanTable) this.getById("LoanTable", id);
		loan.setStatus(4);
		this.update(loan);*/
	}

	@SuppressWarnings("unchecked")
	public void loanApplicationCancel(HttpServletRequest request, HttpSession session, int id) {
		LoanTable loan = (LoanTable) this.getById("LoanTable", id);
		loan.setStatus(1);
		this.update(loan);
		WeiXinUtil.sendTextMessage(userService.getUserName(loan.getApplicationerId()), null, "您的<"+localTourService.getTourNoAndTourName(loan.getTourId())+">申请的导游借款已经被驳回，如需再次申请，请联系财务修改借款内容后，再次提交", "0");
	}
	
	public void sendMessage(String mobileControllerMapping, int tourId, int status, String message, HttpServletRequest request, HttpSession session){
		StringBuffer path = request.getRequestURL();  
		String tempContextUrl = path.delete(path.length() - request.getRequestURI().length(), path.length()).append(request.getServletContext().getContextPath()).append("/").toString();
	    /*URL需要做权限判断*/
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
