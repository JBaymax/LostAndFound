package com.lostfoundserver.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {
	
	// 数据库连接对象
	protected static Connection conn = null;

	/**
	 * 设置数据库连接
	 * @param conn
	 */
	public void setConnection(Connection conn) {
		BaseDAO.conn = conn;
	}

	/**
	 * 关闭执行数据库语句的对象
	 * @param stmt
	 */
	public static void closeStatement(Statement statement) {
		try {
			statement.close();
			statement = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭数据库结果集
	 * @param rs
	 */
	public static void closeResultSet(ResultSet resultSet) {

		try {
			resultSet.close();
			resultSet = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
