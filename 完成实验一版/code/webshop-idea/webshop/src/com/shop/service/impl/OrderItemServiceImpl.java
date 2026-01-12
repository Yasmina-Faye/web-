package com.shop.service.impl;

import com.shop.bean.OrderItem;
import com.shop.dao.OrderItemDao;
import com.shop.dao.impl.OrderItemDaoImpl;
import com.shop.service.OrderItemService;

import java.util.List;

/**
*/
public class OrderItemServiceImpl implements OrderItemService {

	private OrderItemDao orderItemDao=new OrderItemDaoImpl();

	@Override
	public boolean addOrder(OrderItem orderItem) {
		return orderItemDao.orderAdd(orderItem);
	}

	@Override
	public List<OrderItem> findItemByOrderId(int orderId) {
		return  orderItemDao.findItemByOrderId(orderId);
	}
}
