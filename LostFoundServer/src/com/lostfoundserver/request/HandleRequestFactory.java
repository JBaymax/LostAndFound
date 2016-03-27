package com.lostfoundserver.request;

/**
 * 通过requestType将请求分发给不同的类去处理
 * 
 * @author Duan
 *
 */
public class HandleRequestFactory {
	public static HandleRequest getInstance(String requestType) {
		HandleRequest handleRequest = null;
		try {
			handleRequest = (HandleRequest) Class.forName("com.lostfoundserver.request.handle." + requestType)
					.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return handleRequest;
	}
}
