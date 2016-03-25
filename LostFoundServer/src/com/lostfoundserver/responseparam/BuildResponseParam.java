package com.lostfoundserver.responseparam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BuildResponseParam {
	private static BuildResponseParam BUILD_RESPONSE_PARAM = new BuildResponseParam();

	public static BuildResponseParam getInstance() {

		return BuildResponseParam.BUILD_RESPONSE_PARAM;

	}

	public String buildResponseParam(int result, String requestType,JSONArray content) {
		JSONObject param = new JSONObject();
		if(content == null || content.toString().equals("")){
			content = new JSONArray();
		}
		
		param.put("result", result);
		param.put("requestType", requestType);
		param.put("content", content);
		
		return param.toString();
	}
}
