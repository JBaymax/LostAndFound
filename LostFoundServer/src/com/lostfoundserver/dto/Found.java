package com.lostfoundserver.dto;

public class Found {
	public static final String FOUND_ID = "found_id";
	public static final String USER_ID = "user_id";
	public static final String FOUND_TITLE = "found_title";
	public static final String FOUND_CONTENT = "found_content";
	public static final String FOUND_TIME = "found_time";
	public static final String FOUND_IMAGE = "found_image";

	private int found;
	private int userid;
	private String foundtitle;
	private String foundcontent;
	private String foundtime;
	private String foundimage;

	public Found() {
		super();
	}

	public Found(int found, int userid, String foundtitle, String foundcontent, String foundtime, String foundimage) {
		super();
		this.found = found;
		this.userid = userid;
		this.foundtitle = foundtitle;
		this.foundcontent = foundcontent;
		this.foundtime = foundtime;
		this.foundimage = foundimage;
	}

	public int getFound() {
		return found;
	}

	public void setFound(int found) {
		this.found = found;
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
		return "Found [found=" + found + ", userid=" + userid + ", foundtitle=" + foundtitle + ", foundcontent="
				+ foundcontent + ", foundtime=" + foundtime + ", foundimage=" + foundimage + "]";
	}

}
