package com.duan.lostandfound.analysis;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.duan.lostandfound.dto.Found;
import com.duan.lostandfound.dto.Lost;
import com.duan.lostandfound.param.ResponseParam;

public class AnalysisGetFoundInfoResponseParam extends ResponseParam {
	JSONArray content;

	public AnalysisGetFoundInfoResponseParam(String responseJson) {
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
	public List<Found> getFoundInfo() {
		List<Found> listfounds = null;
		try {
			if (content != null) {

				listfounds = new ArrayList<Found>();

				for (int i = 0; i < content.length(); i++) {
					JSONObject object = content.getJSONObject(i);

					System.out.println("i--->" + i);

					Found found = new Found();
					// 从服务端获取的数据,设置到实体类中
					found.setUserid(object.getInt(Found.USER_ID));
					found.setFoundid(object.getInt(Found.FOUND_ID));
					found.setUsername(object.getString(Found.USER_NAME));
					found.setFoundtitle(object.getString(Found.FOUND_TITLE));
					found.setFoundcontent(object.getString(Found.FOUND_CONTENT));
					found.setUsertelephone(object
							.getString(Lost.USER_TELEPHONE));
					found.setFoundtime(object.getString(Found.FOUND_TIME));
					listfounds.add(found);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listfounds;
	}
}
