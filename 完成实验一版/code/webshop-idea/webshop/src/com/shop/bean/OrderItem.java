package com.shop.bean;

import java.util.Map;

/**
 * 订单项
 *
 */
public class OrderItem {

    private int itemId;          //订单项编号
    private int goodsId;         //商品编号
    private int orderId;        //订单编号
    private int quantity;       //数量
    
    private Goods goods;
    
    public OrderItem() {
    }

   
    public OrderItem(Map<String, Object> map) {
		this.setOrderId((int) map.get("orderId"));
		this.setGoodsId((int) map.get("goodsId"));
		this.setItemId((int) map.get("itemId"));
		this.setQuantity((int) map.get("quantity"));
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


	public Goods getGoods() {
		return goods;
	}


	public void setGoods(Goods goods) {
		this.goods = goods;
	}
    
}
