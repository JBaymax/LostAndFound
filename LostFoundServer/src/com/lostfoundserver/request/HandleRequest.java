package com.lostfoundserver.request;

import net.sf.json.JSONArray;

public interface HandleRequest {
	/**
	 * 处理请求的主要方法
	 * 
	 * @param param
	 */
	public int handleRequest(JSONArray params);

	public String getRequestType(); // 获取请求类型

	public JSONArray getResponseContent(); // 获取处理结果原因

	public void setResponseContent(JSONArray responseContent); // 设置返回的响应数据
}
