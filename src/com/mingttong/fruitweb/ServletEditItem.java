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

public class ServletEditItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletEditItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 获取商品ID
		int goodsID = Integer.parseInt(request.getParameter("goodsID"));
		int newNum = Integer.parseInt(request.getParameter("num"));
		HttpSession session = request.getSession();
		
		// 获取购物车
		Map<Integer, ItemVO> cart = (HashMap)session.getAttribute("GOODS_IN_CART");
		
		// 获取商品
		ItemVO item = cart.get(goodsID);
		
		// 编辑商品数量
		item.setNum(newNum);
		
		// 返回（数目，总价）
		int resNum = item.getNum();
		int resSum = resNum * item.getPrice();
		
		String resString = resNum + "," + resSum;
		
		PrintWriter out = response.getWriter();
		out.println(resString);
		out.flush();
		out.close();
	}

}
