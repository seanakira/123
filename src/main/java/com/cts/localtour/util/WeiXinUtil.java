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
 * ����ƽ̨ͨ�ýӿڹ�����
 * 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeiXinUtil {
	private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
	
	// ��ȡaccess_token�Ľӿڵ�ַ��GET�� ��200����/�죩
	public final static String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=SECRECT";
	public final static String send_massage_url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
	public final static String create_user_url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";
	public final static String update_user_url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";
	public final static String code_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	public final static String userId_url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
	private static String accessToken = null;
	/**
	 * ��ȡaccess_token
	 * 
	 * @param appid ƾ֤
	 * @param appsecret ��Կ
	 * @return
	 */
	public static void makeAccessToken(String corpid, String sercret) {
		String requestUrl = access_token_url.replace("CORPID", corpid).replace("SECRECT", sercret);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		// �������ɹ�
		if (null != jsonObject) {
			try {
				accessToken = jsonObject.getString("access_token");
			} catch (JSONException e) {
				accessToken = null;
				// ��ȡtokenʧ��
				log.error("��ȡtokenʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
	}

	public static String getAccessToken() {
		return accessToken;
	}
	

	/*�����ı���Ϣ*/
	public static boolean sendTextMessage(String touser, String url, String txt, String safe){
		if(touser==null||"".equals(touser)){
			return false;
		}else{
			String requestUrl = send_massage_url.replace("ACCESS_TOKEN", accessToken);
			JSONObject message = new JSONObject();
			message.element("touser", touser);
			message.element("msgtype", "text");
			message.element("agentid", "11");/*Ӧ��id*/
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
			System.out.println("��������"+touser);
			System.out.println("url��"+url);
			System.out.println("���ݣ�"+txt);
			System.out.println("�����"+error.getString("errmsg"));
			if(!error.getString("errmsg").equals("ok")){
				return false;
			}
			return true;
		}
	}
	
	/*��װURL*/
	/*��ȡ�������磺http://f0rb.iteye.com/
	StringBuffer url = request.getRequestURL();  
	String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString(); 
	��ȡ�����𻷾������ĵ��������磺 http://www.iteye.com/admin/
	Java����  �ղش���
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
	/*���ͨѶ¼*/
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
	
	/*����ͨѶ¼*/
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
	
	/*����ͨѶ¼״̬*/
	public static JSONObject changEnable(String userid, String enable) {
		String requestUrl = update_user_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject user = new JSONObject();
		user.element("userid", userid);
		user.element("enable", enable);
		JSONObject error = httpsRequest(requestUrl, "POST", user.toString());
		return error;
	}
	
	/*��ȡuserId*/
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
	
	/*��ȡcode*/
	public static void getCode(HttpServletResponse response, String redirect, String parm){
		String redirect_uri = null;
		String state = null;
		try {
			/*Ҫ��ת��URL����ת��redirect����*/
			redirect_uri = URLEncoder.encode(redirect,"utf-8");
			/*һ�������Լ����õĲ���*/
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
	 * ����https���󲢻�ȡ���
	 * 
	 * @param requestUrl �����ַ
	 * @param requestMethod ����ʽ��GET��POST��
	 * @param outputStr �ύ������
	 * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// ������SSLContext�����еõ�SSLSocketFactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// ��������ʽ��GET/POST��
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// ����������Ҫ�ύʱ
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// ע������ʽ����ֹ��������
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// �����ص�������ת�����ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
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
			// ������SSLContext�����еõ�SSLSocketFactory����

			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// ��������ʽ��GET/POST��
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// ����������Ҫ�ύʱ
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// ע������ʽ����ֹ��������
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// �����ص�������ת�����ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
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