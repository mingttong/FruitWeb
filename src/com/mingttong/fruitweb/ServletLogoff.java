package com.mingttong.fruitweb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogoff extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ServletLogoff() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "login.jsp"; // 退出登录后，跳转回登录页面
		
		// 获取该浏览器对应的session，并将其删除
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 跳转页面
		request.getRequestDispatcher(url).forward(request, response);
	}

}
