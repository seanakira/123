package com.cts.localtour.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cts.localtour.util.WeiXinUtil;
@Component
public class WeiXinAccessToken {
	@Scheduled(fixedRate = 1000*7200)  
	public void weiXinAccessToken(){
		/*测试环境*/
		/*WeiXinUtil.makeAccessToken("wxbd28a656750d3724", "0Q0lU9XW4AqQqzrjgxerQtfdk1duoogDfxa6hkoMDiK-rkiF0WqhfE5P-db5zmwT");*/
		
		/*正式环境*/
		WeiXinUtil.makeAccessToken("wxc0469422cfd3e1a7", "z6VGHv1UtG548JR7FhiIFtvg-N5LdrXDQXc5MzFnOIKjrXg0hbl2wu7eLL61ZZ7v");
		/*System.out.println(WeiXinUtil.getAccessToken());*/
	}
}
