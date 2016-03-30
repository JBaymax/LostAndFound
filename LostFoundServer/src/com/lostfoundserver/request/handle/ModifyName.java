package com.lostfoundserver.request.handle;

import com.lostfoundserver.request.HandleRequest;
import com.lostfoundserver.request.RequestTypeCode;
import com.lostfoundserver.service.UserService;

import net.sf.json.JSONArray;

public class ModifyName implements HandleRequest {

	private JSONArray responseContent = null;// Json数组

	@Override
	public int handleRequest(JSONArray params) {

		int id = params.getJSONObject(0).getInt("id");
		String name = params.getJSONObject(0).getString("name");
		System.out.println("ModifyName:id+name--->" + id + "," + name);

		UserService userService = UserService.getInstance();
		responseContent = userService.update(id, "user_name", name);
		if (responseContent != null) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public String getRequestType() {
		return RequestTypeCode.MODIFYNAME;
	}

	@Override
	public JSONArray getResponseContent() {
		return responseContent;
	}

	@Override
	public void setResponseContent(JSONArray responseContent) {
		this.responseContent = responseContent;
	}

}
