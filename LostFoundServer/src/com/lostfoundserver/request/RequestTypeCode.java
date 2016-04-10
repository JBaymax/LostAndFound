package com.lostfoundserver.request;

/**
 * 请求参数的类型码
 * 
 * @author Duan
 *
 */
public class RequestTypeCode {
	public static final String REGISTER = "Register"; // 用户注册
	public static final String LOGIN = "Login"; // 用户登录
	public static final String MODIFYNAME = "ModifyName"; // 修改昵称
	public static final String MODIFYTELEPHONE = "ModifyTelephone"; // 修改手机号
	public static final String MODIFYPASSWORD = "ModifyPassword"; // 修改密码
	public static final String MODIFYSEX = "ModifySex"; // 修改性别
	public static final String ADDFOUND = "AddFound"; // 增加寻物启事
	public static final String ADDLOST = "AddLost"; // 增加失物招领
	public static final String GETLOST = "GetLost"; // 获取失物招领
	public static final String GETFOUND = "GetFound"; // 获取寻物启事
}
