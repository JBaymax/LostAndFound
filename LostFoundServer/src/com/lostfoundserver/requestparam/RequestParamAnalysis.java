package com.lostfoundserver.requestparam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RequestParamAnalysis {
	private static RequestParamAnalysis REQUEST_PARAM_ANALYSIS = new RequestParamAnalysis();
	private RequestParam requestParam;

	public RequestParamAnalysis() {
		requestParam = new RequestParam();
	}

	public static RequestParamAnalysis getInstance() {
		return RequestParamAnalysis.REQUEST_PARAM_ANALYSIS;
	}

	public RequestParam analysisRequestParam(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		requestParam.setRequestType(jsonObject.getString("requestType"));
		
		/*
		 * JSONArray里是JSONOnject
		 */
		JSONArray jsonArray = jsonObject.getJSONArray("params");
        requestParam.setParams(jsonArray);
		return requestParam;

	}
}
