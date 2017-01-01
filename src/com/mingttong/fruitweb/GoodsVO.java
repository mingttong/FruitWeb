package com.mingttong.fruitweb;

public class GoodsVO {
	
	private String title = "";
	private int price = 0;
	private String imgUrl = ""; // 商品图片地址
//	private String madeIn = ""; // 商品产地
	
	/*
	 * getter/setter
	 * ***********start***********
	 */
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
//	public String getMadeIn() {
//		return madeIn;
//	}
//	public void setMadeIn(String madeIn) {
//		this.madeIn = madeIn;
//	}
	/*
	 * **************end***************
	 */
	
	public GoodsVO(String title, int price, String imgUrl) {
		super();
		this.title = title;
		this.price = price;
		this.imgUrl = imgUrl;
//		this.madeIn = madeIn;
	}
	
	
	
	

}
