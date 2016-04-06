package com.lostfoundserver.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lostfoundserver.dto.Found;

public class FoundDAO extends BaseDAO {
	private static FoundDAO foundDAO;

	public static FoundDAO getInstance() {
		if (foundDAO == null) {
			foundDAO = new FoundDAO();
		}
		return foundDAO;
	}

	/**
	 * 查询寻物启事信息
	 * 
	 * @param telephone
	 * @param title
	 * @param content
	 * @return
	 */
	public Found getFound(String telephone, String title, String content) {
		Found found = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select * from tb_found where user_telephone=? and found_title=? and found_content=?");
			System.out.println("Duan:查询失物招领的SQL语句--->" + "select * from tb_found where user_telephone=" + telephone
					+ "and found_title=" + title + " and found_content=" + content);
			ps.setString(1, telephone);
			ps.setString(2, title);
			ps.setString(3, content);
			rs = ps.executeQuery();
			if (rs.next()) {
				found = new Found();
				found.setFoundid(rs.getInt("found_id"));
				found.setUserid(rs.getInt("user_id"));
				found.setUsertelephone(rs.getString("user_telephone"));
				found.setFoundtitle(rs.getString("found_title"));
				found.setFoundcontent(rs.getString("found_content"));
				found.setFoundtime(rs.getString("found_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(ps);
			closeResultSet(rs);
		}
		return found;
	}

	/**
	 * 添加寻物启事
	 * 
	 * @param found
	 * @return
	 */
	public boolean insert(Found found) {
		PreparedStatement ps = null;

		boolean result = false;
		try {
			ps = conn.prepareStatement(
					"insert into tb_found(user_id,user_telephone,found_title,found_content,found_time) values(?,?,?,?,?)");

			int i = 1;
			ps.setInt(i++, found.getUserid());
			ps.setString(i++, found.getUsertelephone());
			ps.setString(i++, found.getFoundtitle());
			ps.setString(i++, found.getFoundcontent());
			ps.setString(i++, found.getFoundtime());

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
}
