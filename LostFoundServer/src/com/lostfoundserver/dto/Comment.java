package com.lostfoundserver.dto;

public class Comment {
	public static final String USER_ID = "user_id";
	public static final String COMMENT_ID = "comment_id";
	public static final String COMMENT_TIME = "comment_time";
	public static final String COMMENT_CONTENT = "comment_content";

	private int userid;
	private int commentid;
	private String commenttime;
	private String commentcontent;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public String getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}

	public String getCommentcontent() {
		return commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

}
