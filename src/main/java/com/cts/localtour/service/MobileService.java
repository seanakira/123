package com.cts.localtour.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.util.WeiXinUtil;
import com.cts.localtour.viewModel.ChangeCostIncomeViewModel;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.LoanViewModel;

@SuppressWarnings("rawtypes")
@Service
public class MobileService extends BaseService{
	@Autowired
	private UserService userService;
	@Autowired
	private LocalTourService localTourService;
	@Autowired
	private FullPayViewModel fullPayViewModel;
	@Autowired
	private LoanViewModel loanViewModel;
	@Autowired
	private ChangeCostIncomeViewModel changeCostIncomeViewModel;
	public ChangeCostIncomeViewModel getAllChangCostIncome(int tourId, int status) {
		return changeCostIncomeViewModel.getAllChangeCostIncomeViewModel(tourId, status);
	}
	
	@SuppressWarnings("unchecked")
	public void updateChangeCost(HttpServletRequest request, HttpSession session, int id) {
		ChangeCostTable cost = (ChangeCostTable)this.getById("ChangeCostTable", id);
		/*������ҪȨ���ж�  ��������ľ���*/
		cost.setStatus(2);
		this.update(cost);
		this.sendMessage("changeCostIncomeApproval", cost.getTourId(), 2, "���д���˵�<����ɱ�����>������������", request, session);
	    /*������ܾ���*/
	    /*cost.setStatus(3);
		this.update(cost);*/
	}
	
	@SuppressWarnings("unchecked")
	public void updateChangeIcome(HttpServletRequest request, HttpSession session, int id) {
		ChangeIncomeTable income = (ChangeIncomeTable)this.getById("ChangeIncomeTable", id);
		/*������ҪȨ���ж�  ��������ľ���*/
		income.setStatus(2);
		this.update(income);
	    this.sendMessage("changeCostIncomeApproval", income.getTourId(), 2, "���д���˵�<����ɱ�����>������������", request, session);
	    /*������ܾ���*/
	    /*cost.setStatus(3);
		this.update(cost);*/
	}
	
	@SuppressWarnings("unchecked")
	public void cancalChangeCost(HttpServletRequest request, HttpSession session, int id) {
		ChangeCostTable cost = (ChangeCostTable)this.getById("ChangeCostTable", id);
		this.delete(cost);
		WeiXinUtil.sendTextMessage(((UserTable)this.getById("UserTable", cost.getApplicationerId())).getUserName(), null, "����<"+localTourService.getTourNoAndTourName(cost.getTourId())+">�ţ�����ĳɱ�����Ѿ������أ������Ѿ���ɾ���������ٴ����룬��������ӡ�", "0");
	}

	@SuppressWarnings("unchecked")
	public void cancalChangeIncome(HttpServletRequest request, HttpSession session, int id) {
		ChangeIncomeTable income = (ChangeIncomeTable)this.getById("ChangeIncomeTable", id);
		this.delete(income);
		LocalTourTable tour = (LocalTourTable) this.getById("LocalTourTable", income.getTourId());
		WeiXinUtil.sendTextMessage(((UserTable)this.getById("UserTable", income.getApplicationerId())).getUserName(), null, "����"+tour.getTourNo()+"  "+tour.getTourName()+"�ţ�������������Ѿ������أ������Ѿ���ɾ���������ٴ����룬��������ӡ�", "0");
	}

	public ArrayList<LoanViewModel> getAllLoanApplication(int tourId, int status) {
		return loanViewModel.getAllLoanViewModel(tourId, status);
	}

	@SuppressWarnings("unchecked")
	public void loanApplicationOk(HttpServletRequest request, HttpSession session, int id) {
		/*������ҪȨ���ж� ��������ľ���*/
		LoanTable loan = (LoanTable) this.getById("LoanTable", id);
		loan.setStatus(3);
		this.update(loan);
	    this.sendMessage("loanApplication", loan.getTourId(), 3, "���д���˵�<�������>������������", request, session);
		/*������ܾ���*/
	    /*LoanTable loan = (LoanTable) this.getById("LoanTable", id);
		loan.setStatus(4);
		this.update(loan);*/
	}

	@SuppressWarnings("unchecked")
	public void loanApplicationCancel(HttpServletRequest request, HttpSession session, int id) {
		LoanTable loan = (LoanTable) this.getById("LoanTable", id);
		loan.setStatus(1);
		this.update(loan);
		WeiXinUtil.sendTextMessage(userService.getUserName(loan.getApplicationerId()), null, "����<"+localTourService.getTourNoAndTourName(loan.getTourId())+">����ĵ��ν���Ѿ������أ������ٴ����룬����ϵ�����޸Ľ�����ݺ��ٴ��ύ", "0");
	}
	
	public FullPayViewModel getAllPayApplication(int tourId, int status) {
		return fullPayViewModel.getPayApplicationViewModel(tourId, status);
	}
	
	@SuppressWarnings("unchecked")
	public void payApplicationOk(HttpServletRequest request, HttpSession session, int id, boolean change) {
		if(!change){
			/*������ҪȨ���ж� ��������ľ���*/
			CostTable costTable = (CostTable) this.getById("CostTable", id);
			costTable.setPayStatus(2);
			this.update(costTable);
		    this.sendMessage("payApplication", costTable.getTourId(), 2, "���д���˵�<��������>������������", request, session);
			/*������ܾ���*/
		    /*CostTable costTable = (CostTable) this.getById("CostTable", id);
			costTable.setPayStatus(3);
			this.update(costTable);*/
		}else{
			/*������ҪȨ���ж� ��������ľ���*/
			ChangeCostTable changeCostTable = (ChangeCostTable) this.getById("ChangeCostTable", id);
			changeCostTable.setPayStatus(2);
			this.update(changeCostTable);
		    this.sendMessage("payApplication", changeCostTable.getTourId(), 2, "���д���˵�<��������>������������", request, session);
			/*������ܾ���*/
		    /*CostTable costTable = (CostTable) this.getById("CostTable", id);
			costTable.setPayStatus(3);
			this.update(costTable);*/
		}
	}

	@SuppressWarnings("unchecked")
	public void payApplicationCancel(HttpServletRequest request, HttpSession session, int id) {
		CostTable costTable = (CostTable) this.getById("CostTable", id);
		costTable.setPayStatus(0);
		this.update(costTable);
		WeiXinUtil.sendTextMessage(userService.getUserName(costTable.getPayApplicationerId()), null, "����<"+localTourService.getTourNoAndTourName(costTable.getTourId())+">����ĵ��ν���Ѿ������أ������ٴ����룬����ϵ�����޸Ľ�����ݺ��ٴ��ύ", "0");
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
