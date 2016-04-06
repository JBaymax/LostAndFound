package com.lostfoundserver.dto;

public class Lost {
	public static final String LOST_ID = "lost_id";
	public static final String USER_ID = "user_id";
	public static final String USER_TELEPHONE = "user_telephone";
	public static final String LOST_TITLE = "lost_title";
	public static final String LOST_CONTENT = "lost_content";
	public static final String LOST_TIME = "lost_time";
	public static final String LOST_IMAGE = "lost_image";

	private int lostid;
	private int userid;
	private String usertelephone;
	private String losttitle;
	private String lostcontent;
	private String losttime;
	private String lostimage;

	public Lost() {
		super();
	}

	public String getUsertelephone() {
		return usertelephone;
	}

	public void setUsertelephone(String usertelephone) {
		this.usertelephone = usertelephone;
	}

	public Lost(int lostid, int userid, String usertelephone, String losttitle, String lostcontent, String losttime,
			String lostimage) {
		super();
		this.lostid = lostid;
		this.userid = userid;
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
		return "Lost [lostid=" + lostid + ", userid=" + userid + ", usertelephone=" + usertelephone + ", losttitle="
				+ losttitle + ", lostcontent=" + lostcontent + ", losttime=" + losttime + ", lostimage=" + lostimage
				+ "]";
	}

}
