package com.mingttong.fruitweb;

import java.io.IOException;
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
		
		// ��ȡ��Ʒ��Ϣ
		String title = request.getParameter("title");
		int price = Integer.parseInt(request.getParameter("price"));
		String img_url = request.getParameter("img_url");
//		String made_in = request.getParameter("made_in");
		
		GoodsVO vo = new GoodsVO(title, price, img_url);
		GoodsDAO dao = new GoodsDAO();
		
		boolean f = dao.add(vo);
		
		if (f) {
			// ��ӳɹ�...
		} else {
			// ���ʧ��...
		}
		
	}

}
