package com.mingttong.fruitweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 删除购物车中的商品
 * @author lenovo
 *
 */
public class ServletDeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletDeleteItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 获取商品ID
		int goodsID = Integer.parseInt(request.getParameter("goodsID"));
		HttpSession session = request.getSession();
		
		// 获取购物车
		Map<Integer, ItemVO> cart = (HashMap)session.getAttribute("GOODS_IN_CART");
		
		// 将购物车中对应的商品删除
		// 判断是否删除成功？
		cart.remove(goodsID);
		
		// 跳转回购物车页面
//		request.getRequestDispatcher("cart.jsp").forward(request, response);
		
		// 返回特殊的数目表示删除
		String resString = "0,0";
		
		PrintWriter out = response.getWriter();
		out.println(resString);
		out.flush();
		out.close();
	}

}
