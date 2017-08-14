package com.cts.localtour.util;

import net.sf.json.JSONObject;

public class BackgroundImg {
	private static String img = "";
	private static String imgInfo = "";
	public static String getBackgroundImg(){
		if("".equals(img)){
			JSONObject jsonObject = WeiXinUtil.httpRequest("http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1", "GET", null);
			img = "http://www.bing.com"+((JSONObject)jsonObject.getJSONArray("images").get(0)).getString("url");
		}
		return img;
	}
	public static String getBackgroundImgInfo(){
		if("".equals(imgInfo)){
			JSONObject html = WeiXinUtil.httpRequest("http://cn.bing.com/cnhp/life", "GET", null);
			imgInfo = html.getString("html");
		}
		return imgInfo;
	}
	public static void clear(){
		img = "";
		imgInfo = "";
	}
}
