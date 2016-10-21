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
	@SuppressWarnings("unchecked")
	public ArrayList<LoanViewModel> getAllLoanViewModel(int tourId){
		ArrayList<LoanTable> loanTables = (ArrayList<LoanTable>) baseService.getAllByString("LoanTable", "tourId=?", tourId);
		ArrayList<LoanViewModel> loans = new ArrayList<LoanViewModel>();
		for (int i = 0; i < loanTables.size(); i++) {
			LoanViewModel loan = new LoanViewModel();
			loan.setLoanTable(loanTables.get(i));
			loan.setLenderRealName(((UserTable)baseService.getById("UserTable", loanTables.get(i).getLenderId())).getRealName());
			if(loanTables.get(i).isLended()){
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
}
