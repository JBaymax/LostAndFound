package com.duan.lostandfound.dto;

public class Lost {
	public static final String LOST_ID = "lostid";
	public static final String USER_ID = "userid";
	public static final String USER_NAME = "name";
	public static final String USER_TELEPHONE = "telephone";
	public static final String LOST_TITLE = "title";
	public static final String LOST_CONTENT = "content";
	public static final String LOST_TIME = "time";
	public static final String LOST_IMAGE = "image";

	private int lostid;
	private int userid;
	private String username;
	private String usertelephone;
	private String losttitle;
	private String lostcontent;
	private String losttime;
	private String lostimage;

	public Lost() {
		super();
	}

	public Lost(int lostid, int userid, String username, String usertelephone,
			String losttitle, String lostcontent, String losttime,
			String lostimage) {
		super();
		this.lostid = lostid;
		this.userid = userid;
		this.username = username;
		this.usertelephone = usertelephone;
		this.losttitle = losttitle;
		this.lostcontent = lostcontent;
		this.losttime = losttime;
		this.lostimage = lostimage;
	}

	public int getLostid() {
		return lostid;
	}

	public void setLostid(int lostid) {
		this.lostid = lostid;
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

	public String getUsertelephone() {
		return usertelephone;
	}

	public void setUsertelephone(String usertelephone) {
		this.usertelephone = usertelephone;
	}

	public String getLosttitle() {
		return losttitle;
	}

	public void setLosttitle(String losttitle) {
		this.losttitle = losttitle;
	}

	public String getLostcontent() {
		return lostcontent;
	}

	public void setLostcontent(String lostcontent) {
		this.lostcontent = lostcontent;
	}

	public String getLosttime() {
		return losttime;
	}

	public void setLosttime(String losttime) {
		this.losttime = losttime;
	}

	public String getLostimage() {
		return lostimage;
	}

	public void setLostimage(String lostimage) {
		this.lostimage = lostimage;
	}

	@Override
	public String toString() {
		return "Lost [lostid=" + lostid + ", userid=" + userid + ", username="
				+ username + ", usertelephone=" + usertelephone
				+ ", losttitle=" + losttitle + ", lostcontent=" + lostcontent
				+ ", losttime=" + losttime + ", lostimage=" + lostimage + "]";
	}

}
