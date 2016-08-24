package com.cts.localtour.cron;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cts.localtour.service.LocalTourService;

@Component
public class TourStatusStartOrEnd {
	@Autowired
	private LocalTourService localTourService;
    /** 
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下）  
     * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)  
     * 注意： 30 * * * * * 表示每分钟的第30秒执行，而（*斜杠30）表示每30秒执行 
     *  
     * */  
	@Scheduled(cron="0 0 0 * * *")  
	public void firstTask(){  
//		System.out.println("==============it is first task!时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		localTourService.updateByParam("LocalTourTable", "status=?", "startTime=? and status=?", 4, new Date() ,3);
		localTourService.updateByParam("LocalTourTable", "status=?", "endTime=? and status=?", 5, new Date() ,4);
	}
}
