package com.mingttong.fruitweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoodsDAO extends BaseDAO {

	public static void main(String[] args) {

	}
	
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
		String sql = "select usr_name, pwd from usr_info where usr_name = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				usrInDb = rs.getString("usr_name");
				pwdInDb = rs.getString("pwd");
				vo = new GoodsVO(usrInDb, pwdInDb);
				
			} else {
				System.out.println("�û��������ڣ�");
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
		String madeIn = vo.getMadeIn();
		
		if (! checkExist(title)) {
			Connection conn = getConn();
			String sql = "insert into goods (title, price, img_url, made_in) values (?, ?, ?, ?)";

			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, title);
				ps.setInt(2, price);
				ps.setString(3, imgUrl);
				ps.setString(4, madeIn);

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

}
