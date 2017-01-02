package com.mingttong.fruitweb;

public class GoodsVO {
	
	private int goodsID = 0;
	private String title = "";
	private int price = 0;
	private String imgUrl = ""; // 商品图片地址
	
	/*
	 * getter/setter
	 * ***********start***********
	 */
	
	public int getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/*
	 * **************end***************
	 */
	
	public GoodsVO(String title, int price, String imgUrl) {
		super();
		this.title = title;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public GoodsVO(int goodsID, String title, int price, String imgUrl) {
		super();
		this.goodsID = goodsID;
		this.title = title;
		this.price = price;
		this.imgUrl = imgUrl;
	}

}
