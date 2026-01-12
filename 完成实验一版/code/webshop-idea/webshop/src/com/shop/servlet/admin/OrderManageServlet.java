package com.shop.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shop.service.GoodsService;
import com.shop.service.OrderItemService;
import com.shop.service.OrderService;
import com.shop.service.UserService;
import com.shop.service.impl.GoodsServiceImpl;
import com.shop.bean.Order;
import com.shop.bean.OrderItem;
import com.shop.bean.PageBean;

import com.shop.service.impl.OrderItemServiceImpl;
import com.shop.service.impl.OrderServiceImpl;
import com.shop.service.impl.UserServiceImpl;


@WebServlet("/jsp/admin/OrderManageServlet")
public class OrderManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ORDERLIST_PATH="orderManage/orderlist.jsp";
	private static final String ORDERDETAIL_PATH="orderManage/orderDetail.jsp";
	private static final String ORDEROP_PATH="orderManage/orderOp.jsp";

	private GoodsService goodsService=new GoodsServiceImpl();
	private UserService userServic=new UserServiceImpl();
	private OrderService orderService=new OrderServiceImpl();
	private OrderItemService orderItemService=new OrderItemServiceImpl();
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if (action.equals("list")) {
			orderList(request, response);
		} else if (action.equals("detail")) {
			orderDetail(request, response);
		} else if (action.equals("processing")) {
			orderProcessing(request, response);
		} else if (action.equals("ship")) {
			orderShip(request, response);
		} else if (action.equals("seach")) {
			seachOrder(request, response);
		} else if (action.equals("seach1")) {
			seachOrder1(request, response);
		} else if (action.equals("delete")) {
			deleteOrder(request, response);
		}


	}
	
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String orderid=request.getParameter("id");

		if(orderid != null && orderid != "") {
			orderService.deleteOrderItem(Integer.valueOf(orderid));
			orderService.deleteOrder(Integer.valueOf(orderid));
		}
		orderList(request, response);
	}

	private void seachOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		String ordername = request.getParameter("ordername");

		PageBean pb = null;
		if(ordername != null && ordername != "") {
			pb = new PageBean(curPage, maxSize, orderService.orderReadCount(ordername,null));
			request.setAttribute("orderList", orderService.orderList(pb,null,ordername));
		}else {
			pb = new PageBean(curPage, maxSize, orderService.orderReadCount(null,null));
			request.setAttribute("orderList", orderService.orderList(pb,null,null));
		}
		
		request.setAttribute("pageBean", pb);
		request.getRequestDispatcher(ORDERLIST_PATH).forward(request, response);
	}
	
	private void seachOrder1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		String ordername = request.getParameter("ordername");

		PageBean pb = null;
		if(ordername != null && ordername != "") {
			pb = new PageBean(curPage, maxSize, orderService.orderReadCountByStatus(1,ordername));
			request.setAttribute("orderList", orderService.orderListByStatus(pb,1,ordername));
		}else {
			pb = new PageBean(curPage, maxSize, orderService.orderReadCountByStatus(1,null));
			request.setAttribute("orderList", orderService.orderListByStatus(pb,1,null));
		}
		
		request.setAttribute("pageBean", pb);
		request.getRequestDispatcher(ORDEROP_PATH).forward(request, response);
	}

	private void orderShip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId=request.getParameter("id");

		
		if(orderService.orderStatus(Integer.parseInt(orderId),2)) {
			request.setAttribute("orderMessage", "一个订单操作成功");
		}else {
			request.setAttribute("orderMessage", "一个订单操作失败");
		}
		orderProcessing(request,response);
		
		
	}

	private void orderProcessing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));

		PageBean pb = new PageBean(curPage, maxSize, orderService.orderReadCountByStatus(1,null));
		
		request.setAttribute("pageBean", pb);
		request.setAttribute("orderList", orderService.orderListByStatus(pb,1,null));
		request.getRequestDispatcher(ORDEROP_PATH).forward(request, response);
		
		
		
	}

	private void orderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId=Integer.parseInt(request.getParameter("id"));




		
		Order order = orderService.findOrderByOrderId(orderId);
		order.setUser(userServic.findUser(order.getUserId()));
		order.setoItem(orderItemService.findItemByOrderId(order.getOrderId()));
		for ( OrderItem oItem : order.getoItem()) {
			//通过商品id获取商品对象
			oItem.setGoods(goodsService.findGoodsById(oItem.getGoodsId()));
		}
		request.setAttribute("order", order);
		request.getRequestDispatcher(ORDERDETAIL_PATH).forward(request, response);
		
		
	}

	private void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));

		PageBean pb = new PageBean(curPage, maxSize, orderService.orderReadCount(null,null));
		
		request.setAttribute("pageBean", pb);
		request.setAttribute("orderList", orderService.orderList(pb,null,null));
		request.getRequestDispatcher(ORDERLIST_PATH).forward(request, response);
		
	}

}
