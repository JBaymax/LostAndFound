package com.lostfoundserver.database;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatabaseManager {
	// MySql数据库连接驱动
	private String driverClass = "com.mysql.jdbc.Driver";
	// 连接数据库的url
	private String jdbcUrl = "jdbc:mysql://localhost:3306/db_lost?useUnicode=true&characterEncoding=utf-8";
	// 连接数据库的用户名及密码

	private String user = "root";
	private String password = "123456";

	private static DatabaseManager dbcPool = null;

	private static ComboPooledDataSource cpds = null;

	DatabaseManager() {
		cpds = new ComboPooledDataSource();
		cpds.setUser(user);
		cpds.setPassword(password);
		cpds.setJdbcUrl(jdbcUrl);
		try {
			cpds.setDriverClass(driverClass);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			System.out.println("设置数据库驱动出错");
		}
		cpds.setInitialPoolSize(20);
		cpds.setMaxPoolSize(1000);
		cpds.setMinPoolSize(10);

	}

	public synchronized static DatabaseManager getInstance() {
		if (dbcPool == null) {
			return dbcPool = new DatabaseManager();
		} else {
			return dbcPool;
		}
	}

	/**
	 * 获取数据库连接池中的连接
	 * 
	 * @return
	 */
	public synchronized Connection getConnection() {
		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param connection
	 */
	public synchronized static void CloseConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn = null;
	}
}
