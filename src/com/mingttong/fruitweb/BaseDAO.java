package com.mingttong.fruitweb;

import java.sql.Connection;

public class BaseDAO {
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	public void close(Connection conn) {
		if (conn == null) {
			return;
		}
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		Connection conn = null;
		
		try {
			// 1. 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获取指定数据库的连接对象
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}
