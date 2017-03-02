package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.LoanTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.BaseService;
@Component
public class LoanViewModel {
	private LoanTable loanTable;
	private String lenderRealName;
	private String applicationerRealName;
	private String status;
	private String managerName;
	private String bossName;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	public LoanTable getLoanTable() {
		return loanTable;
	}
	public void setLoanTable(LoanTable loanTable) {
		this.loanTable = loanTable;
	}
	public String getLenderRealName() {
		return lenderRealName;
	}
	public void setLenderRealName(String lenderRealName) {
		this.lenderRealName = lenderRealName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplicationerRealName() {
		return applicationerRealName;
	}
	public void setApplicationerRealName(String applicationerRealName) {
		this.applicationerRealName = applicationerRealName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getBossName() {
		return bossName;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<LoanViewModel> getAllLoanViewModel(int tourId){
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) baseService.getAllByString("LoanTable", "tourId=?", tourId);
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (int i = 0; i < loanTables.size(); i++) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTables.get(i));
			loan.setLenderRealName(((UserTable)baseService.getById("UserTable", loanTables.get(i).getLenderId())).getRealName());
			if(loanTables.get(i).isLended()){
				loan.setStatus("已借出");
			}else{
				if(loanTables.get(i).getStatus()==0){
					loan.setStatus("新建");
				}else if(loanTables.get(i).getStatus()==1){
					loan.setStatus("可借");
				}else if(loanTables.get(i).getStatus()==2){
					loan.setStatus("已提交");
				}else if(loanTables.get(i).getStatus()==3){
					loan.setStatus("已审核");
				}else if(loanTables.get(i).getStatus()==4){
					loan.setStatus("已批准");
				}
			}
			loans.add(loan);
		}
		return loans;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<LoanViewModel> getAllLoanViewModel(int tourId, int status){
		ArrayList<LoanTable> loanTables;
		/*这里需要权限判断 如果是总经理 status》3*/
		if(status==-1){
			loanTables = (ArrayList<LoanTable>) baseService.getAllByString("LoanTable", "tourId=? and status>2", tourId);
		}else{
			loanTables = (ArrayList<LoanTable>) baseService.getAllByString("LoanTable", "tourId=? and status=?", tourId, status);
		}
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (int i = 0; i < loanTables.size(); i++) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTables.get(i));
			loan.setApplicationerRealName(loanTables.get(i).getApplicationerId()==null?"":((UserTable)baseService.getById("UserTable", loanTables.get(i).getApplicationerId())).getRealName());
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
	public ArrayList<LoanViewModel> getPrintViewModel(int tourId){
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) baseService.getAllByString("LoanTable", "tourId=? and status=4 and lended=false", tourId);
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (LoanTable loanTable : loanTables) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTable);
			loan.setApplicationerRealName(((UserTable)baseService.getById("UserTable", loanTable.getApplicationerId())).getRealName());
			loan.setLenderRealName(((UserTable)baseService.getById("UserTable", loanTable.getLenderId())).getRealName());
			loan.setManagerName(((UserTable)baseService.getById("UserTable", loanTable.getManagerId())).getRealName());
			loan.setBossName(((UserTable)baseService.getById("UserTable", loanTable.getBossId())).getRealName());
			if(loanTable.isLended()){
				loan.setStatus("已借出");
			}else{
				if(loanTable.getStatus()==0){
					loan.setStatus("新建");
				}else if(loanTable.getStatus()==1){
					loan.setStatus("可借");
				}else if(loanTable.getStatus()==2){
					loan.setStatus("待审核");
				}else if(loanTable.getStatus()==3){
					loan.setStatus("待批准");
				}else if(loanTable.getStatus()==4){
					loan.setStatus("已批准");
				}
			}
			loans.add(loan);
		}
		return loans;
	}
}
