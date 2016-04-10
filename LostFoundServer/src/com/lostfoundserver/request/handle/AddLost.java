package com.lostfoundserver.request.handle;

import com.lostfoundserver.request.HandleRequest;
import com.lostfoundserver.request.RequestTypeCode;
import com.lostfoundserver.service.LostService;

import net.sf.json.JSONArray;

public class AddLost implements HandleRequest {
	private JSONArray responseContent = null;// Json数组

	@Override
	public int handleRequest(JSONArray params) {
		int id = params.getJSONObject(0).getInt("id");
		String name = params.getJSONObject(0).getString("name");
		String title = params.getJSONObject(0).getString("title");
		String telephone = params.getJSONObject(0).getString("telephone");
		String content = params.getJSONObject(0).getString("content");

		LostService lostService = LostService.getInstance();
		if (!lostService.lostIsExist(telephone, title, content)) {

			responseContent = lostService.AddLost(id, name, telephone, title, content,
					Long.toString(System.currentTimeMillis()));
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
		return RequestTypeCode.ADDLOST;
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
