package com.mingttong.fruitweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				usrInDb = rs.getString("usr_name");
				pwdInDb = rs.getString("pwd");
				vo = new UserVO(usrInDb, pwdInDb);
				
			} else {
				System.out.println("用户名不存在！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return vo;
	}
	
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		Connection conn = dao.getConn();
		
	}

}
