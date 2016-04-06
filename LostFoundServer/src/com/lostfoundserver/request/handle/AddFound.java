package com.lostfoundserver.request.handle;

import com.lostfoundserver.request.HandleRequest;
import com.lostfoundserver.request.RequestTypeCode;
import com.lostfoundserver.service.FoundService;

import net.sf.json.JSONArray;

public class AddFound implements HandleRequest {
	private JSONArray responseContent = null;// Json数组

	@Override
	public int handleRequest(JSONArray params) {
		int id = params.getJSONObject(0).getInt("id");
		String title = params.getJSONObject(0).getString("title");
		String telephone = params.getJSONObject(0).getString("telephone");
		String content = params.getJSONObject(0).getString("content");

		FoundService foundService = FoundService.getInstance();
		if (!foundService.foundIsExist(telephone, title, content)) {

			responseContent = foundService.AddFound(id, telephone, title, content,
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
		return RequestTypeCode.ADDFOUND;
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
