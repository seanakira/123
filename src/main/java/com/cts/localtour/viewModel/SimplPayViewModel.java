package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.pojo.CostInfo;
import com.cts.localtour.pojo.LoanInfo;
import com.cts.localtour.service.BaseService;
import com.cts.localtour.service.ChangeCostService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.LoanService;
import com.cts.localtour.service.ReimbursementCostService;
@Component
public class SimplPayViewModel {
	private LocalTourTable localTourTable;
	private float loan;
	private float canCost;
	private float canPay;
	private float willPay;
	private float realPay;
	private String realName;
	private float sumCache;
	private String status;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	@Autowired
	private CostService costService;
	@Autowired
	private ChangeCostService changeCostService;
	@Autowired
	private LoanService loanService;
	@Autowired
	private ReimbursementCostService reimbursementCostService;
	public LocalTourTable getLocalTourTable() {
		return localTourTable;
	}
	public void setLocalTourTable(LocalTourTable localTourTable) {
		this.localTourTable = localTourTable;
	}
	public float getLoan() {
		return loan;
	}
	public void setLoan(float loan) {
		this.loan = loan;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public float getSumCache() {
		return sumCache;
	}
	public void setSumCache(float sumCache) {
		this.sumCache = sumCache;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getCanPay() {
		return canPay;
	}
	public void setCanPay(float canPay) {
		this.canPay = canPay;
	}
	public float getWillPay() {
		return willPay;
	}
	public void setWillPay(float willPay) {
		this.willPay = willPay;
	}
	public float getCanCost() {
		return canCost;
	}
	public void setCanCost(float canCost) {
		this.canCost = canCost;
	}
	public float getRealPay() {
		return realPay;
	}
	public void setRealPay(float realPay) {
		this.realPay = realPay;
	}
	public ArrayList<SimplPayViewModel> getAllSimplPayViewModel(ArrayList<LocalTourTable> localTours){
		ArrayList<SimplPayViewModel> simplPayViewModels = new ArrayList<SimplPayViewModel>();
		for (LocalTourTable localTourTable : localTours) {
			SimplPayViewModel simplPayViewModel = new SimplPayViewModel();
			CostInfo costInfo = costService.getCostInfo(localTourTable.getId());
			CostInfo changeCostInfo = changeCostService.getCostInfo(localTourTable.getId());
			CostInfo reimbursementCostInfo = reimbursementCostService.getReimbursementCostInfo(localTourTable.getId());
			LoanInfo loanInfo = loanService.getLoanInfo(localTourTable.getId());
			simplPayViewModel.setLocalTourTable(localTourTable);
			simplPayViewModel.setLoan(loanInfo.getLoanSum().floatValue());
			simplPayViewModel.setCanCost(costInfo.getCanCostSum().add(changeCostInfo.getCanCostSum()).floatValue());
			simplPayViewModel.setCanPay(costInfo.getCanCostSum().add(changeCostInfo.getCanCostSum()).add(loanInfo.getLoanSum()).floatValue());
			simplPayViewModel.setWillPay(loanInfo.getWillLoanSum().add(costInfo.getWillCostSum()).add(changeCostInfo.getWillCostSum()).add(reimbursementCostInfo.getWillCostSum()).floatValue());
			simplPayViewModel.setRealPay(loanInfo.getRealLoanSum().add(costInfo.getRealCostSum()).add(changeCostInfo.getRealCostSum()).add(reimbursementCostInfo.getRealCostSum()).floatValue());
			simplPayViewModel.setRealName(((UserTable) baseService.getById("UserTable", localTourTable.getUserId())).getRealName());
			if(localTourTable.getStatus()==0){
				simplPayViewModel.setStatus("�½�");
			}else if (localTourTable.getStatus()==1) {
				simplPayViewModel.setStatus("���ύ");
			}else if (localTourTable.getStatus()==2) {
				simplPayViewModel.setStatus("�ѱ���");
			}else if (localTourTable.getStatus()==3) {
				simplPayViewModel.setStatus("�ɽ��");
			}else if (localTourTable.getStatus()==4) {
				simplPayViewModel.setStatus("������");
			}else if (localTourTable.getStatus()==5) {
				simplPayViewModel.setStatus("�ѽ���");
			}else if (localTourTable.getStatus()==6) {
				simplPayViewModel.setStatus("������");
			}else if (localTourTable.getStatus()==7) {
				simplPayViewModel.setStatus("�ѱ���");
			}else if (localTourTable.getStatus()==8) {
				simplPayViewModel.setStatus("������");
			}else if (localTourTable.getStatus()==9) {
				simplPayViewModel.setStatus("������");
			}else if (localTourTable.getStatus()==10) {
				simplPayViewModel.setStatus("�ѽ���");
			}
			simplPayViewModels.add(simplPayViewModel);
		}
		return simplPayViewModels;
	}
}
