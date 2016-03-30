package com.lostfoundserver.request.handle;

import com.lostfoundserver.request.HandleRequest;
import com.lostfoundserver.request.RequestTypeCode;
import com.lostfoundserver.service.UserService;
import com.lostfoundserver.utils.Encrypt;

import net.sf.json.JSONArray;

public class ModifyPassword implements HandleRequest {

	private JSONArray responseContent = null;// Json数组

	@Override
	public int handleRequest(JSONArray params) {
		int id = params.getJSONObject(0).getInt("id");
		String password = params.getJSONObject(0).getString("password");
		System.out.println("ModifyName:id+password--->" + id + "," + password);

		UserService userService = UserService.getInstance();
		try {
			responseContent = userService.update(id, "user_password", Encrypt.md5Encrypt(password));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (responseContent != null) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public String getRequestType() {
		return RequestTypeCode.MODIFYPASSWORD;
	}

	@Override
	public JSONArray getResponseContent() {
		// TODO Auto-generated method stub
		return responseContent;
	}

	@Override
	public void setResponseContent(JSONArray responseContent) {
		this.responseContent = responseContent;

	}

}
