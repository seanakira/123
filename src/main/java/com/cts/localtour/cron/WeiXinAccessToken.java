package com.cts.localtour.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cts.localtour.util.WeiXinUtil;
@Component
public class WeiXinAccessToken {
	@Scheduled(fixedRate = 1000*7200)  
	public void weiXinAccessToken(){
		WeiXinUtil.makeAccessToken("wxbd28a656750d3724", "0Q0lU9XW4AqQqzrjgxerQtfdk1duoogDfxa6hkoMDiK-rkiF0WqhfE5P-db5zmwT");
		System.out.println(WeiXinUtil.getAccessToken());
	}
}
