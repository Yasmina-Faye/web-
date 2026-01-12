package com.shop.bean;

import java.util.Date;
import java.util.Map;

public class Goods {

	private int goodsId; // 商品编号
	private String goodsName; // 商品名称
	private double price; // 价格
	private String description; // 描述信息
	private String origin; // 产地
	private String supplier; // 供应商
	private int catalogId; // 商品分类id
	private int imgId; // 图片id
	private Date addTime;//上架时间

	private Catalog catalog = new Catalog(); // 商品分类类属性
	private UpLoadImg upLoadImg = new UpLoadImg(); // 图片类属性

	public Goods() {
	}

	// 这里是从数据库获取时集合转对象
	public Goods(Map<String, Object> map) {
		this.goodsId = (int) map.get("goodsId");
		this.goodsName = (String) map.get("goodsName");
		this.price = (double) map.get("price");
		this.description = (String) map.get("description");
		this.origin = (String) map.get("origin");
		this.supplier = (String) map.get("supplier");
		this.addTime=(Date) map.get("addTime");
		this.catalog = new Catalog(map);
		this.upLoadImg = new com.shop.bean.UpLoadImg(map);
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public int getCatalogId() {
		this.catalogId = this.catalog.getCatalogId();
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalog.setCatalogId(catalogId);
	}

	public int getImgId() {
		this.imgId = this.upLoadImg.getImgId();
		return imgId;
	}

	public void setImgId(int imgId) {
		this.upLoadImg.setImgId(imgId);
		;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public com.shop.bean.UpLoadImg getUpLoadImg() {
		return upLoadImg;
	}

	public void setUpLoadImg(com.shop.bean.UpLoadImg upLoadImg) {
		this.upLoadImg = upLoadImg;
	}
	
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName + ", price=" + price + ", description=" + description
				+ ", origin=" + origin + ", supplier=" + supplier + ", catalogId=" + catalogId + ", imgId=" + imgId
				+ ", catalog=" + catalog + ", upLoadImg=" + upLoadImg + "]";
	}

}
