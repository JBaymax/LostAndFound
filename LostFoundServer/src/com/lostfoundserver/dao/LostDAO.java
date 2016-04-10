package com.lostfoundserver.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lostfoundserver.dto.Lost;

public class LostDAO extends BaseDAO {
	private static LostDAO lostDAO;

	public static LostDAO getInstance() {
		if (lostDAO == null) {
			lostDAO = new LostDAO();
		}
		return lostDAO;
	}

	/**
	 * 查询失物招领信息
	 * 
	 * @param telephone
	 * @param title
	 * @param content
	 * @return
	 */
	public Lost getLost(String telephone, String title, String content) {
		Lost lost = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select * from tb_lost where user_telephone=? and lost_title=? and lost_content=?");
			System.out.println("Duan:查询失物招领的SQL语句--->" + "select * from tb_lost where user_telephone=" + telephone
					+ "and lost_title=" + title + " and lost_content=" + content);
			ps.setString(1, telephone);
			ps.setString(2, title);
			ps.setString(3, content);
			rs = ps.executeQuery();
			if (rs.next()) {
				lost = new Lost();
				lost.setLostid(rs.getInt("lost_id"));
				lost.setUserid(rs.getInt("user_id"));
				lost.setUsertelephone(rs.getString("user_telephone"));
				lost.setLosttitle(rs.getString("lost_title"));
				lost.setLostcontent(rs.getString("lost_content"));
				lost.setLosttime(rs.getString("lost_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(ps);
			closeResultSet(rs);
		}
		return lost;
	}

	/**
	 * 添加失物招领
	 * 
	 * @param lost
	 * @return
	 */
	public boolean insert(Lost lost) {
		PreparedStatement ps = null;

		boolean result = false;
		try {
			ps = conn.prepareStatement(
					"insert into tb_lost(user_id,user_name,user_telephone,lost_title,lost_content,lost_time) values(?,?,?,?,?,?)");

			int i = 1;
			ps.setInt(i++, lost.getUserid());
			ps.setString(i++, lost.getUsername());
			ps.setString(i++, lost.getUsertelephone());
			ps.setString(i++, lost.getLosttitle());
			ps.setString(i++, lost.getLostcontent());
			ps.setString(i++, lost.getLosttime());

			int updateNum = ps.executeUpdate();
			if (updateNum > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(ps);
		}
		return result;
	}

	/**
	 * 查询所有的失物招领信息
	 * 
	 * @return
	 */
	public List<Lost> getLosts() {
		List<Lost> losts = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement("select * from tb_lost order by lost_id DESC");

			rs = ps.executeQuery();
			if (rs != null) {
				losts = new ArrayList<Lost>();
				Lost lost = null;
				while (rs.next()) {
					lost = new Lost();
					lost.setUserid(rs.getInt("user_id"));
					lost.setLostid(rs.getInt("lost_id"));
					lost.setUsername(rs.getString("user_name"));
					lost.setLosttitle(rs.getString("lost_title"));
					lost.setLostcontent(rs.getString("lost_content"));
					lost.setUsertelephone(rs.getString("user_telephone"));
					lost.setLosttime(rs.getString("lost_time"));

					losts.add(lost);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement(ps);
			closeResultSet(rs);
		}
		return losts;
	}
}
