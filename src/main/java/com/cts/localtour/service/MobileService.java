package com.cts.localtour.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cts.localtour.entity.ChangeCostTable;
import com.cts.localtour.entity.ChangeIncomeTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.DeptTable;
import com.cts.localtour.entity.LoanInvoiceTable;
import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.util.WeiXinUtil;
import com.cts.localtour.viewModel.ChangeCostIncomeViewModel;
import com.cts.localtour.viewModel.FullBillViewModel;
import com.cts.localtour.viewModel.FullPayViewModel;
import com.cts.localtour.viewModel.FullReimbursementApplicationViewModel;
import com.cts.localtour.viewModel.LoanInvoiceViewModel;
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
	@Autowired
	private LoanInvoiceViewModel loanInvoiceViewModel;
	@Autowired
	private FullReimbursementApplicationViewModel fullReimbursementApplicationViewModel;
	@Autowired
	private ReimbursementApplicationService reimbursementApplicationService;
	@Autowired
	private FullBillViewModel billViewModel;
	
	/*成本变更审核*/
	public ChangeCostIncomeViewModel getAllChangCostIncome(int tourId, int status) {
		return changeCostIncomeViewModel.getAllChangeCostIncomeViewModel(tourId, status);
	}
	
	@SuppressWarnings("unchecked")
	public void updateChangeCost(HttpServletRequest request, int id) {
		int status = this.getRoleCode();
		if(status!=0){
			ChangeCostTable cost = (ChangeCostTable)this.getById("ChangeCostTable", id);
			/*状态验证*/
			if(cost.getStatus()==status-1){
				cost.setStatus(status);
				this.update(cost);
				if(status==2){
					this.sendMessage("changeCostIncomeApplication", cost.getTourId(), 2, "您有 "+localTourService.getTourNoAndTourName(cost.getTourId())+" 待审核(成本收入变更)，点击进行审核");
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateChangeIcome(HttpServletRequest request, int id) {
		int status = this.getRoleCode();
		if(status!=0){
			ChangeIncomeTable income = (ChangeIncomeTable)this.getById("ChangeIncomeTable", id);
			/*状态验证*/
			if(income.getStatus()==status-1){
				income.setStatus(status);
				this.update(income);
				if(status==2){
					 this.sendMessage("changeCostIncomeApplication", income.getTourId(), 2, "您有 "+localTourService.getTourNoAndTourName(income.getTourId())+" 待审核(变更成本收入)，点击进行审核");
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void cancelChangeCost(HttpServletRequest request, int id) {
		ChangeCostTable cost = (ChangeCostTable)this.getById("ChangeCostTable", id);
		this.delete(cost);
		WeiXinUtil.sendTextMessage(((UserTable)this.getById("UserTable", cost.getApplicationerId())).getUserName(), null, "您的<"+localTourService.getTourNoAndTourName(cost.getTourId())+">团，申请的成本变更已经被驳回，并且已经被删除，如需再次申请，请重新添加。", "0");
	}

	@SuppressWarnings("unchecked")
	public void cancelChangeIncome(HttpServletRequest request, int id) {
		ChangeIncomeTable income = (ChangeIncomeTable)this.getById("ChangeIncomeTable", id);
		this.delete(income);
		LocalTourTable tour = (LocalTourTable) this.getById("LocalTourTable", income.getTourId());
		WeiXinUtil.sendTextMessage(((UserTable)this.getById("UserTable", income.getApplicationerId())).getUserName(), null, "您的"+tour.getTourNo()+"  "+tour.getTourName()+"团，申请的收入变更已经被驳回，并且已经被删除，如需再次申请，请重新添加。", "0");
	}
	
	/*导游借款审核*/
	public ArrayList<LoanViewModel> getAllLoanApplication(int tourId, int status) {
		return loanViewModel.getAllLoanViewModel(tourId, status);
	}

	@SuppressWarnings("unchecked")
	public void loanApplicationOk(HttpServletRequest request, int id) {
		int status = this.getRoleCode();
		if(status!=0){
			LoanTable loan = (LoanTable) this.getById("LoanTable", id);
			/*+1为数据库状态与code不符*/
			/*状态验证*/
			if(loan.getStatus()==status){
				loan.setStatus(status+1);
				if(status==2){
					this.sendMessage("loanApplication", loan.getTourId(), 3, "您有您有 "+localTourService.getTourNoAndTourName(loan.getTourId())+" 待审核(导游借款)，点击进行审核");
					loan.setManagerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
				}else if(status==3){
					loan.setBossId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
				}
				this.update(loan);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void loanApplicationAllOk(HttpServletRequest request, int[] ids) {
		int status = this.getRoleCode();
		if(status!=0){
			boolean isManager = false;
			LoanTable loan = null;
			for (int id : ids) {
				loan = (LoanTable) this.getById("LoanTable", id);
				/*+1为数据库状态与code不符*/
				/*状态验证*/
				if(loan.getStatus()==status){
					loan.setStatus(status+1);
					if(status==2){
						loan.setManagerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
						isManager = true;
					}else if(status==3){
						loan.setBossId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
					}
					this.update(loan);
				}
			}
			if(isManager&&loan!=null){
				this.sendMessage("loanApplication", loan.getTourId(), 3, "您有您有 "+localTourService.getTourNoAndTourName(loan.getTourId())+" 待审核(导游借款)，点击进行审核");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void loanApplicationCancel(HttpServletRequest request, int id) {
		LoanTable loan = (LoanTable) this.getById("LoanTable", id);
		loan.setStatus(1);
		this.update(loan);
		WeiXinUtil.sendTextMessage(userService.getUserName(loan.getApplicationerId()), null, "您的<"+localTourService.getTourNoAndTourName(loan.getTourId())+">申请的导游借款已经被驳回，如需再次申请，请联系财务修改借款内容后，再次提交", "0");
	}
	
	
	/*付款审核*/
	public FullPayViewModel getAllPayApplication(int tourId, int status) {
		return fullPayViewModel.getPayApplicationViewModel(tourId, status);
	}
	
	@SuppressWarnings("unchecked")
	public void payApplicationOk(HttpServletRequest request, int id, boolean change) {
		if(!change){
			int status = this.getRoleCode();
			if(status!=0){
				CostTable costTable = (CostTable) this.getById("CostTable", id);
				/*状态验证*/
				if(costTable.getPayStatus()==status-1){
					costTable.setPayStatus(status);
					if(status==2){
						costTable.setManagerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
						this.sendMessage("payApplication", costTable.getTourId(), 2, "您有 "+localTourService.getTourNoAndTourName(costTable.getTourId())+"待审核的(付款申请)，点击进行审核");
					}else if(status==3){
						costTable.setBossId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
					}
					this.update(costTable);
				}
			}
		}else{
			/*这里需要权限判断 如果是中心经理*/
			int status = this.getRoleCode();
			if(status!=0){
				ChangeCostTable changeCostTable = (ChangeCostTable) this.getById("ChangeCostTable", id);
				/*状态验证*/
				if(changeCostTable.getPayStatus()==status-1){
					changeCostTable.setPayStatus(status);
					if(status==2){
						changeCostTable.setManagerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
						this.sendMessage("payApplication", changeCostTable.getTourId(), 2, "您有 "+localTourService.getTourNoAndTourName(changeCostTable.getTourId())+"待审核的(付款申请)，点击进行审核");
					}else if(status==3){
						changeCostTable.setBossId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
					}
					this.update(changeCostTable);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void payApplicationAllOk(HttpServletRequest request, ArrayList<String[]> ids) {
		boolean isManager = false;
		int tourId = 0;
		for (String[] id : ids) {
			if(!"true".equals(id[1])){
				int status = this.getRoleCode();
				if(status!=0){
					CostTable costTable = (CostTable) this.getById("CostTable", Integer.parseInt(id[0]));
					/*状态验证*/
					if(costTable.getPayStatus()==status-1){
						costTable.setPayStatus(status);
						if(status==2){
							costTable.setManagerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
							isManager = true;
						}else if(status==3){
							costTable.setBossId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
						}
						this.update(costTable);
					}
					tourId = costTable.getTourId();
				}
			}else{
				/*这里需要权限判断 如果是中心经理*/
				int status = this.getRoleCode();
				if(status!=0){
					ChangeCostTable changeCostTable = (ChangeCostTable) this.getById("ChangeCostTable", Integer.parseInt(id[0]));
					/*状态验证*/
					if(changeCostTable.getPayStatus()==status-1){
						changeCostTable.setPayStatus(status);
						if(status==2){
							changeCostTable.setManagerId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
							isManager = true;
						}else if(status==3){
							changeCostTable.setBossId(((UserTable)SecurityUtils.getSubject().getPrincipal()).getId());
						}
						this.update(changeCostTable);
					}
					tourId = changeCostTable.getTourId();
				}
			}
		}
		if(isManager&&tourId!=0){
			this.sendMessage("payApplication", tourId, 2, "您有 "+localTourService.getTourNoAndTourName(tourId)+"待审核的(付款申请)，点击进行审核");
		}
	}

	@SuppressWarnings("unchecked")
	public void payApplicationCancel(int id, boolean change) {
		if(!change){
			CostTable costTable = (CostTable) this.getById("CostTable", id);
			if(costTable.getPayStatus()!=3){
				costTable.setPayStatus(0);
				this.update(costTable);
				WeiXinUtil.sendTextMessage(userService.getUserName(costTable.getPayApplicationerId()), null, "您的("+localTourService.getTourNoAndTourName(costTable.getTourId())+")申请的付款已经被驳回，如需再次申请，请再次提交", "0");
			}
			}else{
			ChangeCostTable changeCostTable = (ChangeCostTable) this.getById("ChangeCostTable", id);
			if(changeCostTable.getPayStatus()!=3){
				changeCostTable.setPayStatus(0);
				this.update(changeCostTable);
				WeiXinUtil.sendTextMessage(userService.getUserName(changeCostTable.getPayApplicationerId()), null, "您的("+localTourService.getTourNoAndTourName(changeCostTable.getTourId())+")申请的付款已经被驳回，如需再次申请，请再次提交", "0");
			}
		}
	}
	
	/*预借发票审核*/
	/*预借发票不需要总经理审核*/
	public ArrayList<LoanInvoiceViewModel> getAllLoanInvoiceApplication(int tourId, int status) {
		return loanInvoiceViewModel.getAllLoanInvoiceViewModel(tourId, status);
	}

	public void loanInvoiceApplicationOk(int id) {
		this.updateByString("LoanInvoiceTable", "status=2", "id=?", id);
	}

	@SuppressWarnings("unchecked")
	public void loanInvoiceApplicationCancel(int id) {
		LoanInvoiceTable loanInvoiceTable = (LoanInvoiceTable)this.getById("LoanInvoiceTable", id);
		this.delete(loanInvoiceTable);
		WeiXinUtil.sendTextMessage(userService.getUserName(loanInvoiceTable.getApplicationerId()), null, "您的("+localTourService.getTourNoAndTourName(loanInvoiceTable.getTourId())+")申请的预借发票已经被驳回，如需再次申请，请再次提交", "0");
	}
	/*报账审核*/
	public FullReimbursementApplicationViewModel getAllReimbursementApplication(int tourId) {
		return fullReimbursementApplicationViewModel.getFullReimbursementApplicationViewModel(tourId);
	}

	public void reimbursementApplicationOk(int id) {
		reimbursementApplicationService.reimbursementApplicationOk(id);
	}

	public void reimbursementApplicationCancel(int id) {
		reimbursementApplicationService.reimbursementApplicationCancel(id);
	}
	
	/*供应商挂账付款申请*/
	public FullBillViewModel getAllBillApplication(int id, int status) {
		return billViewModel.getFullBillCheckViewModel(id, status);
	}

	@SuppressWarnings("unchecked")
	public void billApplicationOk(int id, boolean change) {
		if(!change){
			int status = this.getRoleCode();
			if(status!=0){
				CostTable costTable = (CostTable) this.getById("CostTable", id);
				/*状态验证*/
				if(costTable.getPayStatus()==status-1){
					costTable.setPayStatus(status);
					this.update(costTable);
					if(status==2){
						this.sendMessage("billApplication", costTable.getSupplierId(), 2, "您有 待审核的(供应商管理挂账付款申请)，点击进行审核");
					}else if(status==3&&this.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2)", id).isEmpty()&&this.getAllByString("ChangeCostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2) and status=3", id).isEmpty()){
						this.deleteByString("BillApplicationTable", "supplierId=?", id);
					}
				}
			}
		}else{
			/*这里需要权限判断 如果是中心经理*/
			int status = this.getRoleCode();
			if(status!=0){
				ChangeCostTable changeCostTable = (ChangeCostTable) this.getById("ChangeCostTable", id);
				/*状态验证*/
				if(changeCostTable.getPayStatus()==status-1){
					changeCostTable.setPayStatus(status);
					this.update(changeCostTable);
					if(status==2){
						this.sendMessage("billApplication", changeCostTable.getSupplierId(), 2, "您有 待审核的(供应商挂账付款申请)，点击进行审核");
					}else if(status==3&&this.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2)", id).isEmpty()&&this.getAllByString("ChangeCostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2) and status=3", id).isEmpty()){
						this.deleteByString("BillApplicationTable", "supplierId=?", id);
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void billApplicationCancel(int id, boolean change) {
		if(!change){
			CostTable costTable = (CostTable) this.getById("CostTable", id);
			if(costTable.getPayStatus()!=3){
				costTable.setPayStatus(0);
				this.update(costTable);
				/*WeiXinUtil.sendTextMessage(userService.getUserName(costTable.getPayApplicationerId()), null, "您的<"+localTourService.getTourNoAndTourName(costTable.getTourId())+">申请的付款已经被驳回，如需再次申请，请再次提交", "0");*/
				if(this.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2)", id).isEmpty()&&this.getAllByString("ChangeCostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2) and status=3", id).isEmpty()){
					this.deleteByString("BillApplicationTable", "supplierId=?", id);
				}
			}
			}else{
			ChangeCostTable changeCostTable = (ChangeCostTable) this.getById("ChangeCostTable", id);
			if(changeCostTable.getPayStatus()!=3){
				changeCostTable.setPayStatus(0);
				this.update(changeCostTable);
				/*WeiXinUtil.sendTextMessage(userService.getUserName(changeCostTable.getPayApplicationerId()), null, "您的<"+localTourService.getTourNoAndTourName(changeCostTable.getTourId())+">申请的付款已经被驳回，如需再次申请，请再次提交", "0");*/
				if(this.getAllByString("CostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2)", id).isEmpty()&&this.getAllByString("ChangeCostTable", "supplierId=? and bill=true and remittanced=false and (payStatus=1 or payStatus=2) and status=3", id).isEmpty()){
					this.deleteByString("BillApplicationTable", "supplierId=?", id);
				}
			}
		}
	}

	
	/*向上级发送微信消息*/
	@SuppressWarnings("unused")
	public boolean sendMessage(String mobileControllerMapping, int tourId, int status, String message){
		/*验证是否打开微信消息接收开关*/
		if(((UserTable)SecurityUtils.getSubject().getPrincipal()).isWeiXinMessageSwitch()){
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			StringBuffer path = request.getRequestURL();  
			String tempContextUrl = path.delete(path.length() - request.getRequestURI().length(), path.length()).append(request.getServletContext().getContextPath()).append("/").toString();
			String url = tempContextUrl+"mobile/"+mobileControllerMapping+"?id="+tourId+"&status="+status;
		    UserTable user = (UserTable) SecurityUtils.getSubject().getPrincipal();
		    DeptTable dept = (DeptTable) this.getById("DeptTable", user.getDeptId());
		    String managerIds = dept.getManagerIds();
		    String[] ids = managerIds.split(",");
		    for (int i = 0; i < ids.length; i++) {
		    	UserTable manager = (UserTable)this.getById("UserTable", Integer.parseInt(ids[i]));
		    	return WeiXinUtil.sendTextMessage(manager.getUserName()+"@ctssd.com", url, message, "0");
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean sendMessageAgain(String mobileControllerMapping, int tourId, String message){
		/*验证是否打开微信消息接收开关*/
		boolean sendOk = true;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StringBuffer path = request.getRequestURL();
		String tempContextUrl = path.delete(path.length() - request.getRequestURI().length(), path.length()).append(request.getServletContext().getContextPath()).append("/").toString();
		if(((UserTable)SecurityUtils.getSubject().getPrincipal()).isWeiXinMessageSwitch()){
			boolean hasManager = false;
			boolean hasBoss = false;
			UserTable user = (UserTable) SecurityUtils.getSubject().getPrincipal();
			if("loanApplication".equals(mobileControllerMapping)){
				ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) this.getAllByString("LoanTable", "tourId=? and (status=2 or status=3)", tourId);
				for (LoanTable loanTable : loanTables) {
					if(loanTable.getStatus()==2){
						hasManager = true;
					}else if(loanTable.getStatus()==3){
						hasBoss = true;
					}
				}
				if(hasManager){
					String[] ids = userService.getManagerIds(user);
				    String url = tempContextUrl+"mobile/"+mobileControllerMapping+"?id="+tourId+"&status="+2;
				    for (int i = 0; i < ids.length; i++) {
				    	UserTable manager = (UserTable)this.getById("UserTable", Integer.parseInt(ids[i]));
				    	if(!WeiXinUtil.sendTextMessage(manager.getUserName()+"@ctssd.com", url, message, "0")){
				    		sendOk = false;
				    	}
					}
				}
				if(hasBoss){
					String[] ids = userService.getBossIds(user);
				    String url = tempContextUrl+"mobile/"+mobileControllerMapping+"?id="+tourId+"&status="+3;
				    for (int i = 0; i < ids.length; i++) {
				    	UserTable manager = (UserTable)this.getById("UserTable", Integer.parseInt(ids[i]));
				    	if(!WeiXinUtil.sendTextMessage(manager.getUserName()+"@ctssd.com", url, message, "0")){
				    		sendOk = false;
				    	}
					}
				}
			}else if("payApplication".equals(mobileControllerMapping)){
				ArrayList<CostTable> costTables = (ArrayList<CostTable>) this.getAllByString("CostTable", "tourId=? and (payStatus=1 or payStatus=2)", tourId);
				ArrayList<ChangeCostTable> changeCostTable = (ArrayList<ChangeCostTable>) this.getAllByString("ChangeCostTable", "tourId=? and (payStatus=1 or payStatus=2)", tourId);
				for (CostTable cost : costTables) {
					if(cost.getPayStatus()==1){
						hasManager = true;
					}else if(cost.getPayStatus()==2){
						hasBoss = true;
					}
				}
				for (ChangeCostTable changeCost : changeCostTable) {
					if(changeCost.getPayStatus()==1){
						hasManager = true;
					}else if(changeCost.getPayStatus()==2){
						hasBoss = true;
					}
				}
				if(hasManager){
					String[] ids = userService.getManagerIds(user);
				    String url = tempContextUrl+"mobile/"+mobileControllerMapping+"?id="+tourId+"&status="+1;
				    for (int i = 0; i < ids.length; i++) {
				    	UserTable manager = (UserTable)this.getById("UserTable", Integer.parseInt(ids[i]));
				    	if(!WeiXinUtil.sendTextMessage(manager.getUserName()+"@ctssd.com", url, message, "0")){
				    		sendOk = false;
				    	}
					}
				}
				if(hasBoss){
					String[] ids = userService.getBossIds(user);
				    String url = tempContextUrl+"mobile/"+mobileControllerMapping+"?id="+tourId+"&status="+2;
				    for (int i = 0; i < ids.length; i++) {
				    	UserTable manager = (UserTable)this.getById("UserTable", Integer.parseInt(ids[i]));
				    	if(!WeiXinUtil.sendTextMessage(manager.getUserName()+"@ctssd.com", url, message, "0")){
				    		sendOk = false;
				    	}
					}
				}
			}
		}
		return sendOk;
	}
}
