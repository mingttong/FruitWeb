package com.mingttong.fruitweb;

import java.sql.Connection;

public class UserDAO extends BaseDAO {
	
	/**
	 * �����û��������û���Ϣ
	 * @param user_name
	 * @return �����û���Ϣ
	 */
	public UserVO findByUsr(String user_name) {
		UserVO vo = null;
		
		String usrInDb = "", pwdInDb = "";
		
		Connection conn = getConn();
		String sql = "select usr_name, pwd from usr_info where usr_name = ?";
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return vo;
	}

}
