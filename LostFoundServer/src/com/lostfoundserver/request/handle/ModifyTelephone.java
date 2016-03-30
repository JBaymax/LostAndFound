package com.lostfoundserver.request.handle;

import com.lostfoundserver.request.HandleRequest;
import com.lostfoundserver.request.RequestTypeCode;
import com.lostfoundserver.service.UserService;

import net.sf.json.JSONArray;

public class ModifyTelephone implements HandleRequest {
	private JSONArray responseContent = null;// Json数组

	@Override
	public int handleRequest(JSONArray params) {
		int id = params.getJSONObject(0).getInt("id");
		String telephone = params.getJSONObject(0).getString("telephone");
		System.out.println("ModifyName:id+telephone--->" + id + "," + telephone);

		UserService userService = UserService.getInstance();
		responseContent = userService.update(id, "user_telephone", telephone);
		if (responseContent != null) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public String getRequestType() {
		// TODO Auto-generated method stub
		return RequestTypeCode.MODIFYTELEPHONE;
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
