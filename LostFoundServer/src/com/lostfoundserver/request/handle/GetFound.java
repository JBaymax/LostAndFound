package com.lostfoundserver.request.handle;

import com.lostfoundserver.request.HandleRequest;
import com.lostfoundserver.request.RequestTypeCode;
import com.lostfoundserver.service.FoundService;

import net.sf.json.JSONArray;

public class GetFound implements HandleRequest {
	private JSONArray responseContent = null;

	@Override
	public int handleRequest(JSONArray params) {
		FoundService foundService = FoundService.getInstance();
		responseContent = foundService.getFound();

		if (responseContent != null) {
			return 0;
		}
		return -1;
	}

	@Override
	public String getRequestType() {
		return RequestTypeCode.GETFOUND;
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
