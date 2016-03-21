package com.duan.lostandfound.finaldata;

public class ResponseStatusCode {
	
	/**
	 * 自定义返回状态码
	 * 1XXX:指示信息(表示请求已接受、继续处理);
	 * 2XXX:成功(表示请求已经被成功接收、理解、接受);
	 * 3XXX:重定向(要完成请求必须进行更进一步的操作);
	 * 4XXX:客户端错误(请求有语法错误或请求无法实现);
	 * 5XXX:服务器端错误(服务器未能实现合法的请求).
	 */
	
	public static final int REQUEST_ERROR = 400;
	
	public static final int ILLEGAL_USER = 401;
	
	public static final int ILLEGAL_USER_ERROR = 402;
	/**
	 * 注册相关的返回状态码
	 */
	public static final int USER_NOT_EXIST = 411; //用户未注册
	
	public static final int REGISTER_USER_EXIST = 412; //注册用户已存在
	
	
}
