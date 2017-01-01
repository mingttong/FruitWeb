package com.mingttong.fruitweb;

import java.sql.Connection;
import java.sql.DriverManager;

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
	
	/**
	 * 获取数据库连接
	 * @return 返回数据库连接
	 */
	public Connection getConn() {
		Connection conn = null;
		
		try {
			// 1. 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获取指定数据库的连接对象
			String url = "jdbc:mysql://127.0.0.1:3306/fruit?useUnicode=true&characterEncoding=UTF-8";
			conn = DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void main(String[] args) {
		BaseDAO dao = new BaseDAO();
		Connection conn = dao.getConn();
		
		
	}

}
