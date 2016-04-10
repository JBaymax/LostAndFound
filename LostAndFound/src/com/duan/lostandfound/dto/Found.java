package com.duan.lostandfound.dto;

public class Found {
	public static final String FOUND_ID = "foundid";
	public static final String USER_ID = "userid";
	public static final String USER_NAME = "name";
	public static final String USER_TELEPHONE = "telephone";
	public static final String FOUND_TITLE = "title";
	public static final String FOUND_CONTENT = "content";
	public static final String FOUND_TIME = "time";
	public static final String FOUND_IMAGE = "image";

	private int foundid;
	private int userid;
	private String username;
	private String usertelephone;
	private String foundtitle;
	private String foundcontent;
	private String foundtime;
	private String foundimage;

	public Found() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Found(int foundid, int userid, String username,
			String usertelephone, String foundtitle, String foundcontent,
			String foundtime, String foundimage) {
		super();
		this.foundid = foundid;
		this.userid = userid;
		this.username = username;
		this.usertelephone = usertelephone;
		this.foundtitle = foundtitle;
		this.foundcontent = foundcontent;
		this.foundtime = foundtime;
		this.foundimage = foundimage;
	}

	public String getUsertelephone() {
		return usertelephone;
	}

	public void setUsertelephone(String usertelephone) {
		this.usertelephone = usertelephone;
	}

	public int getFoundid() {
		return foundid;
	}

	public void setFoundid(int foundid) {
		this.foundid = foundid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFoundtitle() {
		return foundtitle;
	}

	public void setFoundtitle(String foundtitle) {
		this.foundtitle = foundtitle;
	}

	public String getFoundcontent() {
		return foundcontent;
	}

	public void setFoundcontent(String foundcontent) {
		this.foundcontent = foundcontent;
	}

	public String getFoundtime() {
		return foundtime;
	}

	public void setFoundtime(String foundtime) {
		this.foundtime = foundtime;
	}

	public String getFoundimage() {
		return foundimage;
	}

	public void setFoundimage(String foundimage) {
		this.foundimage = foundimage;
	}

	@Override
	public String toString() {
		return "Found [foundid=" + foundid + ", userid=" + userid
				+ ", username=" + username + ", usertelephone=" + usertelephone
				+ ", foundtitle=" + foundtitle + ", foundcontent="
				+ foundcontent + ", foundtime=" + foundtime + ", foundimage="
				+ foundimage + "]";
	}

}
