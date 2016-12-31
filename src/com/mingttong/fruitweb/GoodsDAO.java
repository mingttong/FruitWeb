package com.mingttong.fruitweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsDAO extends BaseDAO {
	
	/**
	 * 根据title查找商品信息
	 * @param user_name
	 * @return 返回商品信息VO对象
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
				System.out.println("商品不存在！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return vo;
	}
	
	/**
	 * 查找商品信息是否存在
	 * @param title
	 * @return 是否存在
	 */
	public boolean checkExist(String title) {
		boolean f = false;
		
		if (findByTitle(title) != null) {
			f = true;
		}
		
		return f;
	}
	
	/**
	 * 向数据库添加商品信息
	 * @param vo
	 * @return 是否添加成功
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

				int count = ps.executeUpdate(); // 执行sql

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
				titleInDb = "没找到！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close(conn);
		}
		
		System.out.println(titleInDb);
		
	}

}
