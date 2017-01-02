package com.mingttong.fruitweb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletReg() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fail_url = "reg.jsp";
		String success_url = "index.jsp";
//		String loginKey = "LOGIN_RES";
		
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");
		
		UserVO vo = new UserVO(usr, pwd);
		UserDAO dao = new UserDAO();
		
		boolean f = false;
		
		f = dao.add(vo);
		
		if (f) {
			System.out.println("注册成功！");
			
			// 在session中存储登录信息
			HttpSession session = request.getSession();
			session.setAttribute("LOGGED_IN_USER", usr);
			
			// 跳转至welcome.jsp并显示提示
			request.getRequestDispatcher(success_url).forward(request, response);
		} else {
			System.out.println("注册失败！");
			
			// 在session中存储注册提示信息
			HttpSession session = request.getSession();
			session.setAttribute("REG_RES", "该用户名已经注册了！");
			
			// 跳转至reg.jsp并显示提示
			request.getRequestDispatcher(fail_url).forward(request, response);
		}
	}

}
