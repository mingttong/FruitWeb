package com.mingttong.fruitweb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogin extends HttpServlet {
	
	private String loginKey = "LOGIN_RES";
	
	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}

	private static final long serialVersionUID = 1L;
       
    public ServletLogin() {
        super();
    }
    
    /**
     * 判断用户名和密码是否正确
     * @param user_name
     * @param password
     * @return
     */
	public boolean checkLoginInfo (String user_name, String password) {
		boolean f = false;
		
		UserVO vo = null;
		UserDAO dao = new UserDAO();
		vo = dao.findByUsr(user_name);
		
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String success_url = "index.jsp"; // 成功则跳转网站首页
		String fail_url = "login.jsp"; // 失败跳转回登录页面
		
		// 接收登录信息
		String user_name = request.getParameter("usr");
		String password = request.getParameter("pwd");
		
		// 获取session
		HttpSession session = request.getSession();
		
		if (checkLoginInfo(user_name, password)) {
			// 账号密码正确
			
			// session缓存用户信息
			session.setAttribute(loginKey, user_name);
			
			// 跳转网站首页
			request.getRequestDispatcher(success_url).forward(request, response);
			
		} else {
			// 账号密码错误，跳转回登录页面
			request.getRequestDispatcher(fail_url).forward(request, response);

			System.out.println("登录失败！");
		}
	}

}
