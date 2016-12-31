package com.mingttong.fruitweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsDAO extends BaseDAO {
	
	/**
	 * ����title������Ʒ��Ϣ
	 * @param user_name
	 * @return ������Ʒ��ϢVO����
	 */
	public GoodsVO findByTitle(String title) {
		GoodsVO vo = null;
		
		String titleInDb = "";
		int priceInDb = 0;
		String imgUrlInDb = "";
		
		Connection conn = getConn();
		String sql = "select title, price, img_url where title=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				titleInDb = rs.getString("title");
				priceInDb = rs.getInt("price");
				imgUrlInDb = rs.getString("img_url");
				vo = new GoodsVO(titleInDb, priceInDb, imgUrlInDb);
				
			} else {
				System.out.println("��Ʒ�����ڣ�");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return vo;
	}
	
	/**
	 * ������Ʒ��Ϣ�Ƿ����
	 * @param title
	 * @return �Ƿ����
	 */
	public boolean checkExist(String title) {
		boolean f = false;
		
		if (findByTitle(title) != null) {
			f = true;
		}
		
		return f;
	}
	
	/**
	 * �����ݿ������Ʒ��Ϣ
	 * @param vo
	 * @return �Ƿ���ӳɹ�
	 */
	public boolean add(GoodsVO vo) {
		boolean f = false;
		
		String title = vo.getTitle();
		int price = vo.getPrice();
		String imgUrl = vo.getImgUrl();
//		String madeIn = vo.getMadeIn();
		
		if (! checkExist(title)) {
			Connection conn = getConn();
			String sql = "insert into goods (title, price, img_url) values (?, ?, ?)";

			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, title);
				ps.setInt(2, price);
				ps.setString(3, imgUrl);
//				ps.setString(4, madeIn);

				int count = ps.executeUpdate(); // ִ��sql

				if (count > 0) {
					f = true;
					System.out.println("insert ok!");
				} else {
					System.out.println("insert failed...");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn);
			}
		}
		
		return f;
	}
	
	public static void main(String[] args) {
		String titleInDb = "";
		
		GoodsDAO dao = new GoodsDAO();
		Connection conn = dao.getConn();
		String sql = "select title from goods where id=1003";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				titleInDb = rs.getString("title");
			} else {
				titleInDb = "û�ҵ���";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close(conn);
		}
		
		System.out.println(titleInDb);
		
	}

}
