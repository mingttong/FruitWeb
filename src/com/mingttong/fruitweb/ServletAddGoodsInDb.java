package com.mingttong.fruitweb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletAddGoodsInDb extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletAddGoodsInDb() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String addPage_url = "addGoodsInDb.jsp";
		
		request.setCharacterEncoding("UTF-8"); // 对jsp传过来的值重新进行编码
		
		// 获取商品信息
		String title = request.getParameter("title");
		int price = Integer.parseInt(request.getParameter("price"));
		String img_url = request.getParameter("img_url");
//		String made_in = request.getParameter("made_in");
		
		GoodsVO vo = new GoodsVO(title, price, img_url);
		GoodsDAO dao = new GoodsDAO();
		
		boolean f = dao.add(vo);
		
		if (f) {
			// 添加成功...
			System.out.println("添加成功！");
		} else {
			// 添加失败...
			System.out.println("添加失败！");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(addPage_url);
		rd.forward(request, response);
		
	}

}
