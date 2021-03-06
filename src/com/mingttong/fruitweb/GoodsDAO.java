package com.mingttong.fruitweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO extends BaseDAO {
	
	public List<GoodsVO> searchByKeyword(String keyword, int iPage) {
		List<GoodsVO> result = new ArrayList<GoodsVO>();
		int numPerPage = 10; // 每页10条记录
		
		Connection conn = getConn();
		int startIndex = numPerPage * (iPage - 1) ;
		String sql = "select goods_id, title, price, img_url from goods where title like ? limit ?, ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setInt(2, startIndex);
			ps.setInt(3, numPerPage);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int goodsID = rs.getInt("goods_id");
				String title = rs.getString("title");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				GoodsVO vo = new GoodsVO(goodsID, title, price, imgUrl);
				result.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 获取搜索结果数目
	 * @param keyword
	 * @return 返回搜索结果数目
	 */
	public int countByKeyword(String keyword) {
		int count = 0;
		
		String sql = "select count(*) as totalCount from goods where title like ?";
		Connection conn = getConn();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("totalCount");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	/**
	 * 根据商品ID获取商品信息
	 * @param goodsID
	 * @return 返回GoodsVO类商品信息
	 * 
	 * 是否可以和findByTitle合并？
	 */
	public GoodsVO getGoodsByID(int goodsID) {
		GoodsVO vo = null;
		
		int goodsIDInDb = 0;
		String titleInDb = "";
		int priceInDb = 0;
		String imgUrlInDb = "";
		
		Connection conn = getConn();
		String sql = "select goods_id, title, price, img_url from goods where goods_id=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, goodsID);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				goodsIDInDb = rs.getInt("goods_id");
				titleInDb = rs.getString("title");
				priceInDb = rs.getInt("price");
				imgUrlInDb = rs.getString("img_url");
				vo = new GoodsVO(goodsIDInDb, titleInDb, priceInDb, imgUrlInDb);
				
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
	 * 获取数据库中的所有商品信息
	 * @return
	 */
	public List<GoodsVO> getGoodsList() {
		List<GoodsVO> goodsList = new ArrayList<GoodsVO>();
		Connection conn = getConn();
		
		String sql = "select goods_id, title, price, img_url from goods";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ResultSet rs = ps.executeQuery();
			GoodsVO vo = null; // 用于缓存商品信息
			
			while (rs.next()) {
				
				int goodsID = rs.getInt("goods_id");
				String title = rs.getString("title");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				vo = new GoodsVO(goodsID, title, price, imgUrl);
				goodsList.add(vo); // 将商品信息加入到商品列表中
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return goodsList;
	}
	
	//**************测试getGoodList()方法
//	public static void main(String[] args) {
//		GoodsDAO dao = new GoodsDAO();
//		List<GoodsVO> goodsList = dao.getGoodsList();
//		
//	}
	
	/**
	 * 根据title查找商品信息
	 * @param user_name
	 * @return 返回商品信息VO对象
	 */
	public GoodsVO findByTitle(String title) {
		GoodsVO vo = null;
		
		int goodsIDInDb = 0;
		String titleInDb = "";
		int priceInDb = 0;
		String imgUrlInDb = "";
		
		Connection conn = getConn();
		String sql = "select goods_id, title, price, img_url from goods where title=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				goodsIDInDb = rs.getInt("goods_id");
				titleInDb = rs.getString("title");
				priceInDb = rs.getInt("price");
				imgUrlInDb = rs.getString("img_url");
				vo = new GoodsVO(goodsIDInDb, titleInDb, priceInDb, imgUrlInDb);
				
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
	 * 
	 * error: 如果出现了错误，则返回false，但逻辑上应该是不对的。
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
	
	//***************测试getConn()方法
//	public static void main(String[] args) {
//		String titleInDb = "";
//		
//		GoodsDAO dao = new GoodsDAO();
//		Connection conn = dao.getConn();
//		String sql = "select title from goods where goods_id=1005";
//		
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			
//			if (rs.next()) {
//				titleInDb = rs.getString("title");
//			} else {
//				titleInDb = "没找到！";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			dao.close(conn);
//		}
//		
//		System.out.println(titleInDb);
//		
//	}

}
