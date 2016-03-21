package com.duan.lostandfound.analysis;

import org.json.JSONArray;
import org.json.JSONException;

import com.duan.lostandfound.dto.Users;
import com.duan.lostandfound.param.ResponseParam;

public class AnalysisGetRegisterInfoResponseParam extends ResponseParam {
	JSONArray content;

	public AnalysisGetRegisterInfoResponseParam(String responseJson) {
		super(responseJson);
		// TODO Auto-generated constructor stub
		if (getResult() == ResponseParam.RESULT_SUCCESS) {
			try {
				content = super.jsonObject.getJSONArray(ResponseParam.CONTENT);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Users getUsersInfo() {
		Users users = null;
		return users;

	}

}
