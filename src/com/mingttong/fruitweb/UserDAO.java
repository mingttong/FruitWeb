package com.mingttong.fruitweb;

import java.sql.Connection;

public class UserDAO extends BaseDAO {
	
	/**
	 * 根据用户名查找用户信息
	 * @param user_name
	 * @return 返回用户信息
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
