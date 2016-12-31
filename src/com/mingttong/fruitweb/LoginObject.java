package com.mingttong.fruitweb;

public class LoginObject {

	private String user_name = "";
	private String password = "";
	
	public LoginObject (String usr, String pwd) {
		this.user_name = usr;
		this.password = pwd;
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
			f = vo.getPwd().equals(password) ? true : false;
			
		// �û�������
		} else {
			
			System.out.println("�û������ڣ�");
			
		}
		
		return f;
	}
	
}
