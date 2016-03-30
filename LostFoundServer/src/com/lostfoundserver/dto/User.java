package com.lostfoundserver.dto;

public class User {
	public static final String TABLE_NAME = "tb_user";

	public static final String USER_ID = "user_id";
	public static final String USER_NAME = "user_name";
	public static final String USER_PASSWORD = "user_password";
	public static final String USER_TELEPHONE = "user_telephone";
	public static final String USER_SEX = "user_sex";
	public static final String USER_HEAD_IMAGE = "user_head_image";

	private int userid;
	private String username;
	private String userpassword;
	private String usertelephone;
	private String usersex;
	private String userheadimage;

	public User() {
		super();
	}

	public User(int userid, String username, String userpassword, String usertelephone, String usersex,
			String userheadimage) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpassword = userpassword;
		this.usertelephone = usertelephone;
		this.usersex = usersex;
		this.userheadimage = userheadimage;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsertelephone() {
		return usertelephone;
	}

	public void setUsertelephone(String usertelephone) {
		this.usertelephone = usertelephone;
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	public String getUserheadimage() {
		return userheadimage;
	}

	public void setUserheadimage(String userheadimage) {
		this.userheadimage = userheadimage;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", userpassword=" + userpassword
				+ ", usertelephone=" + usertelephone + ", usersex=" + usersex + ", userheadimage=" + userheadimage
				+ "]";
	}

}
