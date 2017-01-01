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
		
		// 接收登录信息
		String user_name = request.getParameter("usr");
		String password = request.getParameter("pwd");
		
		// 初始化登录对象，检查登录信息
		LoginObject loginObj = new LoginObject(user_name, password);
		
		if (loginObj.checkLoginInfo()) {
			// 账号密码正确，跳转网站首页
			RequestDispatcher rd = request.getRequestDispatcher(index_url);
			rd.forward(request, response);
			
		} else {
			// 账号密码错误，跳转回登录页面
			RequestDispatcher rd = request.getRequestDispatcher(login_url);
			rd.forward(request, response);
			System.out.println("登录失败！");
		}
	}

}
