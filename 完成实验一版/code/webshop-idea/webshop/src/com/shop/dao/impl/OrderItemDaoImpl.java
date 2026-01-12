package com.shop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shop.bean.OrderItem;
import com.shop.dao.OrderItemDao;
import com.shop.util.DbUtil;

public class OrderItemDaoImpl implements OrderItemDao {

	@Override
	public boolean orderAdd(OrderItem orderItem) {
		String sql="insert into s_orderItem(goodsId,orderId,quantity) values(?,?,?)";
		
		int i= DbUtil.excuteUpdate(sql,orderItem.getGoodsId(),orderItem.getOrderId(),orderItem.getQuantity());
		
		return i>0?true:false;
	}

	@Override
	public List<OrderItem> findItemByOrderId(int orderId) {
		List<OrderItem> lo=new ArrayList<>();
		String sql="select * from s_orderItem where orderId=?";
		List<Map<String, Object>> query = DbUtil.executeQuery(sql, orderId);
		if(query.size()>0) {
			for(Map<String,Object> map:query) {
				OrderItem oItem=new OrderItem(map);
				lo.add(oItem);
			}
			
		}
		return lo;
	}

}
