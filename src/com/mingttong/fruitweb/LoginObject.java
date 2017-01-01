package com.mingttong.fruitweb;

public class LoginObject {

	private String user_name = "";
	private String password = "";
	
	public LoginObject (String usr, String pwd) {
		this.user_name = usr;
		this.password = pwd;
	}
	
	
	
	public String getUser_name() {
		return user_name;
	}



	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
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
			f = vo.getPassword().equals(password) ? true : false;
			
		// 用户不存在
		} else {
			
			System.out.println("用户不存在！");
			
		}
		
		return f;
	}
	
	public static void main(String[] args) {
		LoginObject loginObj = new LoginObject("test", "123");
		if (loginObj.checkLoginInfo()) {
			System.out.println("成功！");
		} else {
			System.out.println("失败！");
		}
	}
	
}
