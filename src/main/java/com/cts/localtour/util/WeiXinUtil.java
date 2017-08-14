package com.cts.localtour.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 公众平台通用接口工具类
 * 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeiXinUtil {
	private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
	
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=SECRECT";
	public final static String send_massage_url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
	public final static String create_user_url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";
	public final static String update_user_url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";
	public final static String code_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	public final static String userId_url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
	private static String accessToken = null;
	/**
	 * 获取access_token
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static void makeAccessToken(String corpid, String sercret) {
		String requestUrl = access_token_url.replace("CORPID", corpid).replace("SECRECT", sercret);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = jsonObject.getString("access_token");
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
	}

	public static String getAccessToken() {
		return accessToken;
	}
	

	/*发送文本消息*/
	public static boolean sendTextMessage(String touser, String url, String txt, String safe){
		if(touser==null||"".equals(touser)){
			return false;
		}else{
			String requestUrl = send_massage_url.replace("ACCESS_TOKEN", accessToken);
			JSONObject message = new JSONObject();
			message.element("touser", touser);
			message.element("msgtype", "text");
			message.element("agentid", "11");/*应用id*/
			JSONObject text = new JSONObject();
			String content = "";
			if(null==url||"".equals(url)){
				content = txt;
			}else{
				content = "<a href=\""+url+"\">"+txt+"</a>";
			}
			text.element("content", content);
			message.element("text", text);
			message.element("safe", safe);
			JSONObject error = httpsRequest(requestUrl, "POST", message.toString());
			System.out.println("发送至："+touser);
			System.out.println("url："+url);
			System.out.println("内容："+txt);
			System.out.println("结果："+error.getString("errmsg"));
			if(!error.getString("errmsg").equals("ok")){
				return false;
			}
			return true;
		}
	}
	
	/*组装URL*/
	/*获取域名，如：http://f0rb.iteye.com/
	StringBuffer url = request.getRequestURL();  
	String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString(); 
	获取带部署环境上下文的域名，如： http://www.iteye.com/admin/
	Java代码  收藏代码
	StringBuffer url = request.getRequestURL();  
	String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();  */
	public static String makeUrl(HttpServletRequest request, String controller, JSONObject json){
		StringBuffer urlBuffer = request.getRequestURL();  
		StringBuffer tempContextUrl = urlBuffer.delete(urlBuffer.length() - request.getRequestURI().length(), urlBuffer.length()).append(request.getServletContext().getContextPath()).append("/");
		try {
			tempContextUrl.append("weixin?controller=").append(URLEncoder.encode(controller, "utf-8")).append("&parm=").append(URLEncoder.encode(json.toString(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempContextUrl.toString();
	}
	/*添加通讯录*/
	/*public static JSONObject addUser(String userid, String name, String department, String mobile){
		String requestUrl = create_user_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject user = new JSONObject();
		user.element("userid", userid);
		user.element("name", name);
		user.element("department", department);
		user.element("mobile", mobile);
		JSONObject error = httpRequest(requestUrl, "POST", user.toString());
		return error;
	}*/
	
	/*更新通讯录*/
	public static JSONObject updateUser(String userid, String name, String position, String mobile, String email) {
		String requestUrl = update_user_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject user = new JSONObject();
		user.element("userid", userid);
		user.element("name", name);
		user.element("position", position);
		user.element("mobile", mobile);
		user.element("email", email);
		/*user.element("weixinid", weixinid);*/
		JSONObject error = httpsRequest(requestUrl, "POST", user.toString());
		return error;
	}
	
	/*更改通讯录状态*/
	public static JSONObject changEnable(String userid, String enable) {
		String requestUrl = update_user_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject user = new JSONObject();
		user.element("userid", userid);
		user.element("enable", enable);
		JSONObject error = httpsRequest(requestUrl, "POST", user.toString());
		return error;
	}
	
	/*获取userId*/
	public static String getUserId(String code){
		String requestUrl = userId_url.replace("ACCESS_TOKEN", accessToken).replace("CODE", code);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		String userId = jsonObject.getString("UserId");
		if(userId==null||userId.equals("")){
			return null;
		}else{
			return userId;
		}
	}
	
	/*获取code*/
	public static void getCode(HttpServletResponse response, String redirect, String parm){
		String redirect_uri = null;
		String state = null;
		try {
			/*要跳转的URL，跳转到redirect方法*/
			redirect_uri = URLEncoder.encode(redirect,"utf-8");
			/*一个可以自己备用的参数*/
			state = URLEncoder.encode(parm,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String requestUrl = code_url.replace("CORPID", "wxbd28a656750d3724").replace("REDIRECT_URI", redirect_uri).replace("STATE", state);*/
		String requestUrl = code_url.replace("CORPID", "wxc0469422cfd3e1a7").replace("REDIRECT_URI", redirect_uri).replace("STATE", state);
		try {
			response.sendRedirect(requestUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 从上述SSLContext对象中得到SSLSocketFactory对象

			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			try {
				jsonObject = JSONObject.fromObject(buffer.toString());
			} catch (Exception e) {
				jsonObject = new JSONObject();
				jsonObject.put("html", buffer.toString());
			}
			
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
}