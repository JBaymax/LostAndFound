package com.duan.lostandfound.param;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

/**
 * 请求服务器的参数
 * 
 * @author Administrator
 * 
 */
public class RequestParam {

	// 请求操作类型

	public static final String LOGIN = "Login"; // 获取登录请求

	public static final String REGISTER = "Register"; // 获取注册请求

	public static final String MODIFYNAME = "ModifyName"; // 修改昵称的请求

	public static final String MODIFYTELEPHONE = "ModifyTelephone"; // 修改昵称的请求
	
	public static final String MODIFYPASSWORD = "ModifyPassword"; // 修改昵称的请求
	
	public static final String MODIFYSEX = "ModifySex"; // 修改性别的请求
	
	public static final String ADDLOST = "AddLost"; // 增加失物招领信息
	public static final String ADDFOUND = "AddFound"; // 增加失物招领信息
	// 请求参数名
	public static final String TELEPHONE = "telephone";
	public static final String TOKEN = "token";
	public static final String REQUEST_TYPE = "requestType";
	public static final String PARAMS = "params";

	public static final String STATUS = "loginStatus";

	public static final int ONLINE = 0;
	public static final int OFFLINE = 1;

	private String telephone;

	private String token;

	private String requestType;

	private JSONObject params[];

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public JSONObject[] getParams() {
		return params;
	}

	public void setParams(JSONObject[] params) {
		this.params = params;
	}

	public String getJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put(this.TELEPHONE, this.telephone);
			json.put(this.TOKEN, this.token);
			json.put(this.REQUEST_TYPE, this.requestType);

			JSONArray jsonArray = new JSONArray();

			for (JSONObject param : params) {
				jsonArray.put(param);
			}

			json.put(this.PARAMS, jsonArray);
			System.out.println("请求参数" + json.toString());
			return json.toString();
		} catch (Exception e) {
			Log.e("RequestParam", "构建发送请求参数出错", e);
			return "";
		}
	}
}
