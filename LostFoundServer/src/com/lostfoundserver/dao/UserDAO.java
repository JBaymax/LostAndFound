package com.lostfoundserver.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lostfoundserver.dto.User;

/**
 * 数据传输对象-负责与数据库进行直接交互，即执行SQL语句
 * 
 * @author Duan
 *
 */
public class UserDAO extends BaseDAO {
	private static UserDAO userDAO;

	public static UserDAO getInstance() {
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}

	public boolean insert(User user) {
		PreparedStatement ps = null;

		boolean result = false;
		try {
			ps = conn.prepareStatement("insert into tb_user(user_name,user_telephone,user_password) values(?,?,?)");

			int i = 1;
			ps.setString(i++, user.getUsername());
			ps.setString(i++, user.getUsertelephone());
			ps.setString(i++, user.getUserpassword());

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
	 * 登录
	 * 
	 * @param telephone
	 * @return
	 */
	public User getUser(String telephone) {
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from tb_user where user_telephone=?");
			System.out.println("Duan:登录时的SQL语句--->" + "select * from tb_user where " + "user_telephone=" + telephone);
			ps.setString(1, telephone);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserid(rs.getInt("user_id"));
				user.setUsername(rs.getString("user_name"));
				user.setUsertelephone(rs.getString("user_telephone"));
				user.setUserpassword(rs.getString("user_password"));
				user.setUsersex(rs.getString("user_sex"));
				user.setUserheadimage(rs.getString("user_head_image"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(ps);
			closeResultSet(rs);
		}
		return user;
	}

	/**
	 * 更新昵称
	 */
	public boolean update(int userid, String updateName, String updateValue) {
		boolean result = false;
		User user = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE tb_user SET user_name=?  where user_id= ?");

			ps.setString(1, updateValue);
			ps.setInt(2, userid);

			int updateResult = ps.executeUpdate();

			// 判断是否更新成功
			if (updateResult > 0) {
				result = true;

			} else {

				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement(ps);
		}
		return result;

	}
}
