package com.shop.service;

import com.shop.bean.Order;
import com.shop.bean.PageBean;

import java.util.List;

/** 

*/
public interface OrderService {
	
	//增加一个订单记录
	boolean addOrder(Order order);
	//查找订单编号通过订单号
	int findOrderIdByOrderNum(String orderNum);
	//根据订单号统计总订单数
	long orderReadCount(String ordernum,Integer userId);


	//统计总订单数(by orderStatus and ordernum)
	long orderReadCountByStatus(Integer status,String ordernum);
	//获得订单列表（分页）,条件用户id
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
