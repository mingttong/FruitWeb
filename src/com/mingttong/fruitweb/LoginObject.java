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
	 * �ж��û��������Ƿ���ȷ
	 * @return boolean ���ؽ��
	 */
	public boolean checkLoginInfo () {
		boolean f = false;
		
		UserVO vo = null;
		UserDAO dao = new UserDAO();
		vo = dao.findByUsr(this.user_name);
		
		// �û�����
		if (vo != null) {
			// ƥ�������Ƿ���ȷ
			f = vo.getPassword().equals(password) ? true : false;
			
		// �û�������
		} else {
			
			System.out.println("�û������ڣ�");
			
		}
		
		return f;
	}
	
	public static void main(String[] args) {
		LoginObject loginObj = new LoginObject("test", "123");
		if (loginObj.checkLoginInfo()) {
			System.out.println("�ɹ���");
		} else {
			System.out.println("ʧ�ܣ�");
		}
	}
	
}
