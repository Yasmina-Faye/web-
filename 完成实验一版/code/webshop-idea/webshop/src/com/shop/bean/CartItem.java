package com.shop.bean;

import com.shop.util.MathUtils;

public class CartItem {
	private Goods goods;
	private int quantity;//数量
	private double subtotal;//小计
	
	public CartItem() {}
	
	
	
	public CartItem(Goods goods, int quantity) {
		super();
		this.setGoods(goods);
		this.setQuantity(quantity);
	}

	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.subtotal = MathUtils.getTwoDouble(quantity*goods.getPrice());
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	

	@Override
	public String toString() {
		return "CartItem [front=" + goods + ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}
	
	
	
}
