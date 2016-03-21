package com.lostfoundserver.requst.handle;

import com.lostfoundserver.request.HandleRequest;

import net.sf.json.JSONArray;

public class Login implements HandleRequest {

	@Override
	public int handleRequest(JSONArray params) {
		// 处理请求的主要方法
		return 0;
	}

	@Override
	public String getRequestType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray getResponseContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResponseContent(JSONArray responseContent) {
		// TODO Auto-generated method stub

	}

}
