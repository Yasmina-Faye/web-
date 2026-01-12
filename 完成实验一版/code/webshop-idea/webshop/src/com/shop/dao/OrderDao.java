package com.shop.dao;

import java.util.List;

import com.shop.bean.Order;
import com.shop.bean.PageBean;


public interface OrderDao {
	
	//增加一个订单记录
	boolean orderAdd(Order order);
	//查找订单编号通过订单号
	int findOrderIdByOrderNum(String orderNum);
	//根据订单号统计总订单数
	long orderReadCount(String ordernum,Integer userId);


	//统计总订单数(by orderStatus and ordernum)
	long orderReadCountByStatus(int status,String ordernum);

	//获得订单列表（分页）
	List<Order> orderList(PageBean pageBean, Integer userId, String ordernum);

	//获得订单列表（分页）,条件orderStatus  ordernum
	List<Order> orderListByStatus(PageBean pb, int status,String ordernum);
	//查找订单编号通过订单号
	Order findOrderByOrderId(int orderId);
	
	//更改订单状态
	boolean orderStatus(int orderId,int status);
	
	boolean deleteOrder(int orderid);
	boolean deleteOrderItem(int orderid);

	
	
}
