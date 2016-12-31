package com.mingttong.fruitweb;

public class LoginObject {

	private String user_name = "";
	private String password = "";
	
	public LoginObject (String usr, String pwd) {
		this.user_name = usr;
		this.password = pwd;
	}
	
	/**
	 * 判断用户名密码是否正确
	 * @return boolean 返回结果
	 */
	public boolean checkLoginInfo () {
		boolean f = false;
		
		UserVO vo = null;
		UserDAO dao = new UserDAO();
		vo = dao.findByUsr(this.user_name);
		
		// 用户存在
		if (vo != null) {
			// 匹配密码是否正确
			f = vo.getPwd().equals(password) ? true : false;
			
		// 用户不存在
		} else {
			
			System.out.println("用户不存在！");
			
		}
		
		return f;
	}
	
}
