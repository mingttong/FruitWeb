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
 * 增加购物车中商品的数量
 * @author lenovo
 *
 */
public class ServletIncreaseItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletIncreaseItem() {
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
		
		// 获取商品
		ItemVO item = cart.get(goodsID);
		
		// 增加商品的数量
		int oldNum = item.getNum();
		item.setNum(oldNum + 1);
		
		// 跳转回页面
//		request.getRequestDispatcher("cart.jsp").forward(request, response);
		
		PrintWriter out = response.getWriter();
		out.println("111111");
		out.flush();
		out.close();
	}

}
