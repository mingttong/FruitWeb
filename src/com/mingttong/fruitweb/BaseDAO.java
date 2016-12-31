package com.mingttong.fruitweb;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
	
	/**
	 * �ر����ݿ�����
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
	 * ��ȡ���ݿ�����
	 * @return �������ݿ�����
	 */
	public Connection getConn() {
		Connection conn = null;
		
		try {
			// 1. ��������
			Class.forName("com.mysql.jdbc.Driver");
			// 2. ��ȡָ�����ݿ�����Ӷ���
			String url = "jdbc:mysql://127.0.0.1:3306/fruit";
			conn = DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}
