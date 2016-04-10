package com.duan.lostandfound.analysis;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.duan.lostandfound.dto.Lost;
import com.duan.lostandfound.param.ResponseParam;

public class AnalysisGetLostInfoResponseParam extends ResponseParam {
	JSONArray content;

	public AnalysisGetLostInfoResponseParam(String responseJson) {
		super(responseJson);
		if (super.getResult() == ResponseParam.RESULT_SUCCESS) {
			try {
				content = super.jsonObject.getJSONArray(ResponseParam.CONTENT);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 解析服务器传来的数据
	 * 
	 * @return
	 */
	public List<Lost> getLostInfo() {
		List<Lost> listlosts = null;
		try {
			if (content != null) {

				listlosts = new ArrayList<Lost>();

				for (int i = 0; i < content.length(); i++) {
					JSONObject object = content.getJSONObject(i);

					System.out.println("i--->" + i);

					Lost lost = new Lost();
					// 从服务端获取的数据,设置到实体类中
					lost.setUserid(object.getInt(Lost.USER_ID));
					lost.setLostid(object.getInt(Lost.LOST_ID));
					lost.setUsername(object.getString(Lost.USER_NAME));
					lost.setLosttitle(object.getString(Lost.LOST_TITLE));
					lost.setLostcontent(object.getString(Lost.LOST_CONTENT));
					lost.setUsertelephone(object.getString(Lost.USER_TELEPHONE));
					lost.setLosttime(object.getString(Lost.LOST_TIME));
					listlosts.add(lost);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listlosts;
	}
}
