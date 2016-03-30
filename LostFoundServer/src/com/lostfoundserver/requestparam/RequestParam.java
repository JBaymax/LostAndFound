package com.lostfoundserver.requestparam;

import net.sf.json.JSONArray;

public class RequestParam {
	private String requestType;
	private JSONArray params;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public JSONArray getParams() {
		return params;
	}

	public void setParams(JSONArray params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "RequestParam [requestType=" + requestType + ", params="
				+ params + "]";
	}

}
