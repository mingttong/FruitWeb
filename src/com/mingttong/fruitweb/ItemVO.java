package com.mingttong.fruitweb;

/**
 * 存储购物车中的商品信息。
 * @author zhouwunan
 *
 */
public class ItemVO {
	
//	private int goodsID;
	private String title;
	private int price;
	private String imgUrl;
	private int num;
	
//	public int getGoodsID() {
//		return goodsID;
//	}
//	public void setGoodsID(int goodsID) {
//		this.goodsID = goodsID;
//	}
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public ItemVO(String title, int price, String imgUrl, int num) {
		super();
		this.title = title;
		this.price = price;
		this.imgUrl = imgUrl;
		this.num = num;
	}
	
//	public ItemVO(int goodsID, String title, int price, String imgUrl, int num) {
//		super();
//		this.goodsID = goodsID;
//		this.title = title;
//		this.price = price;
//		this.imgUrl = imgUrl;
//		this.num = num;
//	}
	
}
