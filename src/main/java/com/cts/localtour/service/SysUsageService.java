package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.SysUsageTable;
import com.cts.localtour.entity.UserTable;

@SuppressWarnings("rawtypes")
@Service
public class SysUsageService extends BaseService{

	@SuppressWarnings("unchecked")
	public ArrayList<SysUsageTable> getAllSysUsage() {
		ArrayList<SysUsageTable> sysUsageTables = (ArrayList<SysUsageTable>) this.getAllByString("SysUsageTable", "", null);
		return sysUsageTables;
	}
	
	@SuppressWarnings("unchecked")
	public void computeSysUsageTable(){
		this.deleteByString("SysUsageTable", "", null);
		ArrayList<UserTable> userTables = (ArrayList<UserTable>) this.getAllByString("UserTable", "enable=?", true);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(date);    
		calendar.add(Calendar.MONTH, -3);//当前时间前去一个月，即一个月前的时间    
		Date lastMonth = calendar.getTime();//获取一年前的时间，或者一个月前的时间    
		calendar.add(Calendar.MONTH, +6);
		Date nextMonth = calendar.getTime();
		System.out.println(lastMonth);
		for (UserTable userTable : userTables) {
			SysUsageTable sysUsageTable = new SysUsageTable();
			ArrayList<LocalTourTable> localTourTables = (ArrayList<LocalTourTable>) this.getAllByString("LocalTourTable", "userId=? and startTime between ? and ?", userTable.getId(),lastMonth,nextMonth);
			for (LocalTourTable localTourTable : localTourTables) {
				if(localTourTable.getStatus()==0){
					sysUsageTable.setCreateCount(sysUsageTable.getCreateCount()+1);
				}else if(localTourTable.getStatus()==1){
					sysUsageTable.setAuditingCount(sysUsageTable.getAuditingCount()+1);
				}else if(localTourTable.getStatus()==2){
					sysUsageTable.setFinanceCount(sysUsageTable.getFinanceCount()+1);
				}else if(localTourTable.getStatus()==3){
					sysUsageTable.setCanLendCount(sysUsageTable.getCanLendCount()+1);
				}else if(localTourTable.getStatus()==4){
					sysUsageTable.setOngoingCount(sysUsageTable.getOngoingCount()+1);
				}else if(localTourTable.getStatus()==5){
					sysUsageTable.setFinishCount(sysUsageTable.getFinishCount()+1);
				}else if(localTourTable.getStatus()==6){
					sysUsageTable.setBalanceCount(sysUsageTable.getBalanceCount()+1);
				}else if(localTourTable.getStatus()==7){
					sysUsageTable.setReimbursementCount(sysUsageTable.getReimbursementCount()+1);
				}else if(localTourTable.getStatus()==8){
					sysUsageTable.setVerificationCount(sysUsageTable.getVerificationCount()+1);
				}else if(localTourTable.getStatus()==9){
					sysUsageTable.setSettlementCount(sysUsageTable.getSettlementCount()+1);
				}
			}
			if(localTourTables!=null&&localTourTables.size()!=0){
				sysUsageTable.setUserRealName(userTable.getRealName());
				sysUsageTable.setTourCount(sysUsageTable.getCreateCount()+sysUsageTable.getAuditingCount()+sysUsageTable.getFinanceCount()+sysUsageTable.getCanLendCount()+sysUsageTable.getOngoingCount()+sysUsageTable.getFinishCount()+sysUsageTable.getBalanceCount()+sysUsageTable.getReimbursementCount()+sysUsageTable.getVerificationCount()+sysUsageTable.getSettlementCount());
				this.add(sysUsageTable);
			}
		}
	}

}
