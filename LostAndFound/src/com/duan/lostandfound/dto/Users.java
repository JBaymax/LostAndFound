package com.duan.lostandfound.dto;

public class Users {
	public static final String ID = "user_id"; // int(11)
	public static final String NAME = "user_name"; // varchar(20) 用户名
	public static final String TELEPHONE = "user_telephone"; // varchar(11) 电话
	public static final String PASSWORD_MD5 = "user_password"; // varchar(20) 密码
	public static final String SEX = "user_sex";// char(4) 性别
	public static final String HEAD_IMAGE = "user_head_image"; // varchar(100)
																// 头像
	int id; // int(11)
	String name;// varchar(20) 用户名
	String telephone;// varchar(20) 电话
	String password_md5;// varchar(20) 密码
	String headImage;// varchar(100) 头像
	String sex;// char(4) 性别

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword_md5() {
		return password_md5;
	}

	public void setPassword_md5(String password_md5) {
		this.password_md5 = password_md5;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
