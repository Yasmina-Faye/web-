package com.shop.service.impl;

import com.shop.bean.Order;
import com.shop.bean.PageBean;
import com.shop.dao.OrderDao;
import com.shop.dao.impl.OrderDaoImpl;
import com.shop.service.OrderService;

import java.util.List;

/** 

*/
public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao=new OrderDaoImpl();


	@Override
	public boolean addOrder(Order order) {
		return orderDao.orderAdd(order);
	}

	@Override
	public int findOrderIdByOrderNum(String orderNum) {
		return orderDao.findOrderIdByOrderNum(orderNum);
	}

	@Override
	public long orderReadCount(String ordernum,Integer userId) {
		return orderDao.orderReadCount(ordernum,userId);
	}





	@Override
	public long orderReadCountByStatus(Integer status, String ordernum) {
		return orderDao.orderReadCountByStatus(status,ordernum);
	}

	@Override
	public List<Order> orderList(PageBean pageBean, Integer userId, String ordernum) {
		return orderDao.orderList(pageBean, userId, ordernum);
	}





	@Override
	public List<Order> orderListByStatus(PageBean pb, int status, String ordernum) {
		return orderDao.orderListByStatus(pb, status, ordernum);
	}

	@Override
	public Order findOrderByOrderId(int orderId) {
		return orderDao.findOrderByOrderId(orderId);
	}

	@Override
	public boolean orderStatus(int orderId, int status) {
		return orderDao.orderStatus(orderId,status);
	}

	@Override
	public boolean deleteOrder(int orderid) {
		return orderDao.deleteOrder(orderid);
	}

	@Override
	public boolean deleteOrderItem(int orderid) {
		return orderDao.deleteOrderItem(orderid);
	}
}
