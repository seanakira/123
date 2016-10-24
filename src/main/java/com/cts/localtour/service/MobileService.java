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

	public ArrayList<LoanViewModel> getAllLoanApplication(int tourId, int status) {
		return loanViewModel.getAllLoanViewModel(tourId, status);
	}

	@SuppressWarnings("unchecked")
	public void loanApplicationOk(HttpServletRequest request, HttpSession session, int id) {
		/*这里需要权限判断 如果是中心经理*/
		LoanTable loan = (LoanTable) this.getById("LoanTable", id);
		loan.setStatus(3);
		this.update(loan);
	    this.sendMessage("loanApplication", loan.getTourId(), 3, "您有待审核的<借款申请>，点击进行审核", request, session);
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
	
	public FullPayViewModel getAllPayApplication(int tourId, int status) {
		return fullPayViewModel.getPayApplicationViewModel(tourId, status);
	}
	
	@SuppressWarnings("unchecked")
	public void payApplicationOk(HttpServletRequest request, HttpSession session, int id, boolean change) {
		if(!change){
			/*这里需要权限判断 如果是中心经理*/
			CostTable costTable = (CostTable) this.getById("CostTable", id);
			costTable.setPayStatus(2);
			this.update(costTable);
		    this.sendMessage("payApplication", costTable.getTourId(), 2, "您有待审核的<付款申请>，点击进行审核", request, session);
			/*如果是总经理*/
		    /*CostTable costTable = (CostTable) this.getById("CostTable", id);
			costTable.setPayStatus(3);
			this.update(costTable);*/
		}else{
			/*这里需要权限判断 如果是中心经理*/
			ChangeCostTable changeCostTable = (ChangeCostTable) this.getById("ChangeCostTable", id);
			changeCostTable.setPayStatus(2);
			this.update(changeCostTable);
		    this.sendMessage("payApplication", changeCostTable.getTourId(), 2, "您有待审核的<付款申请>，点击进行审核", request, session);
			/*如果是总经理*/
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
		WeiXinUtil.sendTextMessage(userService.getUserName(costTable.getPayApplicationerId()), null, "您的<"+localTourService.getTourNoAndTourName(costTable.getTourId())+">申请的导游借款已经被驳回，如需再次申请，请联系财务修改借款内容后，再次提交", "0");
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
