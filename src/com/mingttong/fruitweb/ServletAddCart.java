package com.mingttong.fruitweb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 将商品加入购物车
 * @author zhouwunan
 *
 */
public class ServletAddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAddCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 获取商品ID
		int goodsID = Integer.parseInt(request.getParameter("goodsID"));
		HttpSession session = request.getSession();
		
		// 从数据库中获取该商品信息
		GoodsDAO dao = new GoodsDAO();
		GoodsVO vo = dao.getGoodsByID(goodsID);
		
		// 检查"GOODS_IN_CART"是否创建
		if (session.getAttribute("GOODS_IN_CART") == null) {
			
			// 初始化该商品，其数目为1
			ItemVO item = new ItemVO(vo.getTitle(), vo.getPrice(), vo.getImgUrl(), 1);
			
			// 将商品放入购物车中
			Map<Integer, ItemVO> cart = new HashMap<Integer, ItemVO>();
			cart.put(goodsID, item);
			
			// 将购物车保存在session中
			session.setAttribute("GOODS_IN_CART", cart);
			
		} else {
			
			// 已创建对应session则只需将商品信息加入
			Map<Integer, ItemVO> cart = (HashMap)session.getAttribute("GOODS_IN_CART");
			
			// 检查原来是否已加入该商品
			if (cart.get(goodsID) != null) {
				// 原先已有该商品
				ItemVO item = cart.get(goodsID);
				
				// 只需将对应数量+1
				int oldNum = item.getNum();
				item.setNum(oldNum + 1);
				
			} else {
				// 原先无此商品
				
				// 初始化该商品，数目为1
				ItemVO item = new ItemVO(vo.getTitle(), vo.getPrice(), vo.getImgUrl(), 1);
				
				// 将该商品加入购物车中
				cart.put(goodsID, item);
				
			}
			
			// 更新session中的信息
			session.setAttribute("GOODS_IN_CART", cart);
			
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
