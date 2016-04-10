package com.lostfoundserver.request.handle;

import com.lostfoundserver.request.HandleRequest;
import com.lostfoundserver.request.RequestTypeCode;
import com.lostfoundserver.service.LostService;

import net.sf.json.JSONArray;

public class GetLost implements HandleRequest {
	private JSONArray responseContent = null;

	@Override
	public int handleRequest(JSONArray params) {
		LostService lostService = LostService.getInstance();
		responseContent = lostService.getLost();

		if (responseContent != null) {
			return 0;
		}
		return -1;
	}

	@Override
	public String getRequestType() {
		// TODO Auto-generated method stub
		return RequestTypeCode.GETLOST;
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
