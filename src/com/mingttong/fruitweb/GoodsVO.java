package com.mingttong.fruitweb;

public class GoodsVO {
	
	private int goodsId = 0;
	private String title = "";
	private int price = 0;
	private String imgUrl = ""; // 商品图片地址
	
	/*
	 * getter/setter
	 * ***********start***********
	 */
	
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
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
	
	public GoodsVO(int goodsId, String title, int price, String imgUrl) {
		super();
		this.goodsId = goodsId;
		this.title = title;
		this.price = price;
		this.imgUrl = imgUrl;
	}

}
