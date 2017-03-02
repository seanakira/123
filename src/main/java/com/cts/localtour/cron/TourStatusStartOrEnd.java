package com.cts.localtour.cron;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cts.localtour.service.LocalTourService;
import com.cts.localtour.service.SysUsageService;

@Component
public class TourStatusStartOrEnd {
	@Autowired
	private LocalTourService localTourService;
	@Autowired
	private SysUsageService sysUsageService;
    /** 
     * cron���ʽ��* * * * * *����6λ��ʹ�ÿո�������������£�  
     * cron���ʽ��*(��0-59) *(����0-59) *(Сʱ0-23) *(����1-31) *(�·�1-12����JAN-DEC) *(����1-7����SUN-SAT)  
     * ע�⣺ 30 * * * * * ��ʾÿ���ӵĵ�30��ִ�У�����*б��30����ʾÿ30��ִ�� 
     *  
     * */  
	@Scheduled(cron="0 0 0 * * ?" )  
	public void firstTask(){
		/*�����Ŷ�״̬*/
		localTourService.updateByString("LocalTourTable", "status=?", "startTime=? and status=?", 4, new Date() ,3);
		localTourService.updateByString("LocalTourTable", "status=?", "endTime=? and status=?", 5, new Date() ,4);
		
		/*����ϵͳʹ��״��*/
		sysUsageService.computeSysUsageTable();
	}
}
