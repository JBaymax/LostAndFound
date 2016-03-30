package com.lostfoundserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lostfoundserver.request.HandleRequest;
import com.lostfoundserver.request.HandleRequestFactory;
import com.lostfoundserver.requestparam.RequestParam;
import com.lostfoundserver.requestparam.RequestParamAnalysis;
import com.lostfoundserver.responseparam.BuildResponseParam;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ServletFactory
 */
@WebServlet("/ServletFactory")
public class ServletFactory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletFactory() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得打http:~:端口号/LostFoundServer/ServletFactory/requestParam
		String json = request.getParameter("requestParam");
		System.out.println("LostAndFound接收到客户端请求：requestParam = " + json);

		if (json != null) {
			// 将请求参数转成UTF-8编码格式
			json = new String(json.getBytes("ISO8859_1"), "UTF-8");
			System.out.println("LostAndFound编码格式转化UTF-8之后：json = " + json);
			// 通过RequestParam类中的get/set方法，得到/设置requestType和params
			// RequestParamAnalysis解析requestType和params
			RequestParam requestParam = RequestParamAnalysis.getInstance().analysisRequestParam(json);
			String requestType = requestParam.getRequestType();
			JSONArray params = requestParam.getParams();// Json数组

			System.out.println("解析请求：requestParam = " + requestParam.toString());

			// 关于response响应
			String responseParam = null;
			// 通过HandleRequestFactory中的包名方法，找到相应的类
			HandleRequest handle = HandleRequestFactory.getInstance(requestType);
			// 通过HandleRequest定义的handleRequest(params)方法处理请求
			int result = handle.handleRequest(params);
			// 将请求结果，请求类型，请求返回的内容打包成Json
			responseParam = BuildResponseParam.getInstance().buildResponseParam(result, requestType,
					handle.getResponseContent());
			// 响应的编码格式
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");

			System.out.println("服务器返回响应内容：ResponseParam = " + responseParam);
			// 传送返回的响应
			PrintWriter out = response.getWriter();
			out.println(responseParam);
			out.close();
		}

	}
}
