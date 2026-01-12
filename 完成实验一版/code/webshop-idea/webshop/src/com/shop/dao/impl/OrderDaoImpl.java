package com.shop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shop.bean.Order;
import com.shop.bean.PageBean;
import com.shop.dao.OrderDao;
import com.shop.util.DbUtil;

public class OrderDaoImpl implements OrderDao {
	/**
	 * 向s_order插入一条订单记录
	 */
	@Override
	public boolean orderAdd(Order order) {
		String sql="insert into s_order(orderNum,userId,orderDate,orderStatus,money) values(?,?,?,?,?)";
		
		int i= DbUtil.excuteUpdate(sql,order.getOrderNum(),order.getUserId(),order.getOrderDate(),order.getOrderStatus(),order.getMoney());
		
		return i>0?true:false;	
	}
	/**
	 * by订单号查询订单编号
	 */
	@Override
	public int findOrderIdByOrderNum(String orderNum) {
		int orderId=0;
		String sql="select orderId from s_order where orderNum=?";
		List<Map<String, Object>> query = DbUtil.executeQuery(sql, orderNum);
		if(query.size()>0) {
			orderId=(int) query.get(0).get("orderId");
		}
		
		return orderId;
	}

	@Override
	public long orderReadCount(String ordernum,Integer userId) {
		StringBuilder sql = new StringBuilder("select count(*) as count from s_order");
		if (ordernum != null && !ordernum.isEmpty()) {
			sql.append(" where orderNum like '%").append(ordernum).append("%'");
		}
		List<Map<String, Object>> lm=null;
		if (userId != null) {
			if (ordernum == null || ordernum.isEmpty()) {
				sql.append(" where userId=?");
			} else {
				sql.append(" and userId=?");
			}
			lm = DbUtil.executeQuery(sql.toString(), userId);
		}else {
			lm = DbUtil.executeQuery(sql.toString());
		}


		return lm.size() > 0 ? (long) lm.get(0).get("count") : 0;
	}

	@Override
	public List<Order> orderList(PageBean pageBean, Integer userId, String ordernum) {
		List<Order> lo = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select * from s_order");
		if (userId != null) {
			sql.append(" where userId=?");
		}
		if (ordernum != null && !ordernum.isEmpty()) {
			if (userId == null) {
				sql.append(" where orderNum like '%").append(ordernum).append("%'");
			} else {
				sql.append(" and orderNum like '%").append(ordernum).append("%'");
			}
		}
		sql.append(" limit ?,?");
		List<Map<String, Object>> list = null;
		if (userId!=null){
			list = DbUtil.executeQuery(sql.toString(), userId, (pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());
		}else {
			list = DbUtil.executeQuery(sql.toString(), (pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());

		}

		if (list.size() > 0) {
			for (Map<String, Object> map : list) {
				Order order = new Order(map);
				lo.add(order);
			}
		}
		return lo;
	}

	
	@Override
	public Order findOrderByOrderId(int orderId) {
		Order order=null;
		String sql="select * from s_order where orderId=?";
		List<Map<String, Object>> query = DbUtil.executeQuery(sql, orderId);
		if(query.size()>0) {
			order=new Order(query.get(0));
		}
		
		return order;
	}

	@Override
	public long orderReadCountByStatus(int status, String ordernum) {
		StringBuilder sql = new StringBuilder("select count(*) as count from s_order where orderStatus=?");
		if (ordernum != null && !ordernum.isEmpty()) {
			sql.append(" and orderNum like '%").append(ordernum).append("%'");
		}
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql.toString(), status);
		return lm.size() > 0 ? (long) lm.get(0).get("count") : 0;
	}


	@Override
	public List<Order> orderListByStatus(PageBean pageBean, int status, String ordernum) {
		List<Order> lo = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select * from s_order where orderStatus=?");
		if (ordernum != null && !ordernum.isEmpty()) {
			sql.append(" and orderNum like '%").append(ordernum).append("%'");
		}
		sql.append(" limit ?,?");
		List<Map<String, Object>> list = DbUtil.executeQuery(sql.toString(), status, (pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());
		if (list.size() > 0) {
			for (Map<String, Object> map : list) {
				Order order = new Order(map);
				lo.add(order);
			}
		}
		return lo;
	}


	@Override
	public boolean orderStatus(int orderId,int status) {
		String sql="update s_order set orderStatus=? where orderId=?";
		int i = DbUtil.excuteUpdate(sql, status,orderId);
		return i>0?true:false;	
	}

	@Override
	public boolean deleteOrder(int orderid) {
		String sql = "delete from s_order where orderId=?";
		int i = DbUtil.excuteUpdate(sql, orderid);
		return i > 0 ? true : false;
	}
	
	@Override
	public boolean deleteOrderItem(int orderid) {
		String sql = "delete from s_orderitem where orderId=?";
		int i = DbUtil.excuteUpdate(sql, orderid);
		return i > 0 ? true : false;
	}
	
}
