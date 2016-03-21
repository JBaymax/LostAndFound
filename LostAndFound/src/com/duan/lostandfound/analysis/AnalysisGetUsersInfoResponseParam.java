package com.duan.lostandfound.analysis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.duan.lostandfound.dto.Users;
import com.duan.lostandfound.param.ResponseParam;
import com.duan.lostandfound.utils.Encrypt;

/**
 * 解析用户相应参数
 * 
 * @author Duan
 * 
 */
public class AnalysisGetUsersInfoResponseParam extends ResponseParam {

	JSONArray content;

	public AnalysisGetUsersInfoResponseParam(String responseJson) {
		super(responseJson);
		if (getResult() == ResponseParam.RESULT_SUCCESS) {
			try {
				content = super.jsonObject.getJSONArray(ResponseParam.CONTENT);
				System.out
						.println("----Duan:AnalysisGetUsersInfoResponseParam.conmtent--->"
								+ content);
				System.out
						.println("----Duan:AnalysisGetUsersInfoResponseParam.telephone--->"
								+ content.getJSONObject(0).getString(
										"telephone"));
				System.out.println("----Duan:content.getJSONObject(0)--->"
						+ content.getJSONObject(0));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 返回的值
	 * 
	 * @return
	 */
	public Users getUsersInfo() {
		Users users = null;
		try {
			if (content != null) {

				System.out.println("----Duan:content != null--->");
				System.out.println("----Duan:getUsersInfo.content--->"
						+ content);
				users = new Users();

				JSONObject jsonObject = content.getJSONObject(0);
				System.out
						.println("----Duan:getUsersInfo.content.getJSONObject(0)--->"
								+ content.getJSONObject(0));

				users.setId(jsonObject.getInt(Users.ID));
				users.setName(jsonObject.getString(Users.NAME));
				users.setTelephone(jsonObject.getString(Users.TELEPHONE));
				users.setPassword_md5((Encrypt.md5Encrypt(jsonObject
						.getString(Users.PASSWORD_MD5))));

				System.out
						.println("----Duan:getUsersInfo.content.jsonObject.getInt(Users.ID)--->"
								+ jsonObject.getString(Users.PASSWORD_MD5));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;

	}
}
