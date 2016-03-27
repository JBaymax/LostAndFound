package com.lostfoundserver.request.handle;

import com.lostfoundserver.request.HandleRequest;
import com.lostfoundserver.request.RequestTypeCode;
import com.lostfoundserver.service.UserService;

import net.sf.json.JSONArray;

public class Register implements HandleRequest {
	private JSONArray responseContent = null;// Json数组

	@Override
	public int handleRequest(JSONArray params) {
		String telephone = params.getJSONObject(0).getString("telephone");
		String name = params.getJSONObject(0).getString("name");
		String password = params.getJSONObject(0).getString("password");

		UserService userService = UserService.getInstance();
		if (!userService.userIsExist(telephone)) {

			responseContent = userService.Register(telephone, name, password);
			if (responseContent != null) {
				return 0;
			} else {
				return -1;
			}
		} else {
			return -2;
		}
	}

	@Override
	public String getRequestType() {
		// TODO Auto-generated method stub
		return RequestTypeCode.REGISTER;
	}

	@Override
	public JSONArray getResponseContent() {
		// TODO Auto-generated method stub
		return responseContent;
	}

	@Override
	public void setResponseContent(JSONArray responseContent) {
		// TODO Auto-generated method stub
		this.responseContent = responseContent;
	}

}
