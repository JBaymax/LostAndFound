package com.lostfoundserver.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTools {

	/**
	 * 释放rs、preparedStatement和conn三个资源
	 * 
	 * @param rs
	 * @param preparedStatement
	 * @param conn
	 */
	public static void releaseSource(ResultSet rs, PreparedStatement preparedStatement, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取连接数据库的配置文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Properties properties = new Properties();
		InputStream in = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");

		properties.load(in);
		String driverClass = properties.getProperty("driver");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String JDBCUrl = properties.getProperty("jdbcUrl");
		Class.forName(driverClass);
		// 3.通过DriverManager的getConnection()方法获取数据库连接
		Connection connection = DriverManager.getConnection(JDBCUrl, user, password);
		return connection;
	}
}
