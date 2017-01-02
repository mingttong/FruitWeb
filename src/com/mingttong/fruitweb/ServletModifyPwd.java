package com.mingttong.fruitweb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletModifyPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletModifyPwd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * 可改进...从session获取用户名，不需要用户输入用户名
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errMsg = "";
		String successUrl = "modifyPwd.jsp";
		String failUrl = "modifyPwd.jsp";
		
		// 获取修改密码信息
		String usr = request.getParameter("usr");
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		
		// 向数据库获取用户信息
		UserDAO dao = new UserDAO();
		UserVO vo = dao.findByUsr(usr);
		
		// 判断该用户是否存在
		// 通过session获取用户名则可以省去此步骤
		if (vo == null) {
			// 用户不存在
			
			errMsg = "密码有误！";
			System.out.println("用户名不存在！");
			request.getRequestDispatcher(failUrl).forward(request, response);
			
		} else {
			// 用户存在
			
			// 判断原密码是否正确
			if (oldPwd.equals(vo.getPassword())) {
				
				// 正确
				// 更改密码
				vo.setPassword(newPwd);
				dao.updatePwd(vo);
				
				if (dao.updatePwd(vo)) {
					
					// 修改密码成功，跳转页面
					errMsg="修改密码成功！";
					request.getRequestDispatcher(successUrl).forward(request, response);
					
				} else {
					
					// 修改密码失败，跳转页面
					errMsg = "修改密码失败...";
					request.getRequestDispatcher(failUrl).forward(request, response);			
				}
				
			} else {
				
				// 错误
				// 显示错误信息，跳转页面
				errMsg = "密码有误！";
				System.out.println("原密码错误！");
				request.getRequestDispatcher(failUrl).forward(request, response);			
			}
		}
		
	}

}
