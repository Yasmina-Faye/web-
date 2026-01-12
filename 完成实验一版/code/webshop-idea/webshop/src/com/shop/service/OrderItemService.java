package com.shop.service;

import com.shop.bean.OrderItem;

import java.util.List;

/**
*/
public interface OrderItemService {
	//增加一个订单项记录
	boolean addOrder(OrderItem orderItem);
	//通过订单编号查找订单项
	List<OrderItem> findItemByOrderId(int orderId);
}
