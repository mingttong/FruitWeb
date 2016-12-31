package com.mingttong.fruitweb;

import java.sql.Connection;

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
	
	public Connection getConn() {
		Connection conn = null;
		
		try {
			// 1. ��������
			Class.forName("com.mysql.jdbc.Driver");
			// 2. ��ȡָ�����ݿ�����Ӷ���
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}
