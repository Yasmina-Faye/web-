package com.shop.bean;

import java.util.HashMap;
import java.util.Map;

import com.shop.util.MathUtils;

public class Cart {
	private Map<Integer,CartItem> map=new HashMap<>();
	private double totPrice;
	private int totQuan;
	
	
	public Cart() {
		super();
	}
	public Cart(Map<Integer, CartItem> map, double totPrice) {
		super();
		this.map = map;
		this.totPrice = totPrice;
	}
	public Map<Integer, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}
	public double getTotPrice() {
		double sum=0;
		for(CartItem cartItem:map.values()) {
			double d=cartItem.getSubtotal();
			sum+=d;
		}
		this.totPrice= MathUtils.getTwoDouble(sum);
		return totPrice;
	}
	public void setTotPrice(double totPrice) {
		this.totPrice = totPrice;
	}
	
	
	public int getTotQuan() {
		int sum=0;
		for(CartItem cartItem:map.values()) {
			sum+=cartItem.getQuantity();
		}
		this.totQuan=sum;
		return totQuan;
	}
	public void setTotQuan(int totQuan) {
		this.totQuan = totQuan;
	}
	public void addGoods(Goods goods) {
		int goodsId = goods.getGoodsId();
		if(map.containsKey(goodsId)) {
			CartItem item = map.get(goodsId);
			item.setQuantity(item.getQuantity()+1);
		}else {
			map.put(goodsId, new CartItem(goods,1));
		}
		
	}
	@Override
	public String toString() {
		return "Cart [map=" + map + ", totPrice=" + totPrice + "]";
	}
	
	
	
}
