package com.mingttong.fruitweb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String index_url = "index.jsp";
		String login_url = "login.jsp";
		
		// ���յ�¼��Ϣ
		String user_name = request.getParameter("usr");
		String password = request.getParameter("pwd");
		
		// ��ʼ����¼���󣬼���¼��Ϣ
		LoginObject loginObj = new LoginObject(user_name, password);
		
		if (loginObj.checkLoginInfo()) {
			// �˺�������ȷ����ת��վ��ҳ
			RequestDispatcher rd = request.getRequestDispatcher(index_url);
			rd.forward(request, response);
			
		} else {
			// �˺����������ת�ص�¼ҳ��
			RequestDispatcher rd = request.getRequestDispatcher(login_url);
			rd.forward(request, response);
			System.out.println("��¼ʧ�ܣ�");
		}
	}

}
