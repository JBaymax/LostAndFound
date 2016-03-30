package com.lostfoundserver.responseparam;

import net.sf.json.JSONArray;

public class ResponseParam {
	private int result;
	private String requestType;
	private JSONArray content;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public JSONArray getContent() {
		return content;
	}

	public void setContent(JSONArray content) {
		this.content = content;
	}

}
