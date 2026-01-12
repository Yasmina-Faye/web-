package com.shop.servlet.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.service.GoodsService;
import com.shop.service.impl.GoodsServiceImpl;
import com.shop.bean.Goods;
import com.shop.bean.Cart;
import com.shop.bean.CartItem;


import net.sf.json.JSONObject;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	private GoodsService goodsService=new GoodsServiceImpl();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if (action.equals("add")) {
			addTOCart(request, response);
		} else if (action.equals("changeIn")) {
			changeIn(request, response); //更改购物车商品数量
		} else if (action.equals("delItem")) {
			delItem(request, response);
		} else if (action.equals("delAll")) {
			delAll(request, response);
		}

	}


	private void delAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("shopCart");
		response.sendRedirect("jsp/front/cart.jsp");

	}

	//购物项删除
	private void delItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int goodsId=Integer.parseInt(request.getParameter("id"));
		Cart shopCart = (Cart) request.getSession().getAttribute("shopCart");
		if(shopCart.getMap().containsKey(goodsId)) {
			shopCart.getMap().remove(goodsId);
		}
		response.sendRedirect("jsp/front/cart.jsp");
	}

	//更改购物项数量
	private void changeIn(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		Cart shopCart = (Cart) request.getSession().getAttribute("shopCart");
		CartItem item = shopCart.getMap().get(goodsId);
		item.setQuantity(quantity);
		JSONObject json=new JSONObject();
		json.put("subtotal", item.getSubtotal());
		json.put("totPrice", shopCart.getTotPrice());
		json.put("totQuan", shopCart.getTotQuan());
		json.put("quantity", item.getQuantity());
		response.getWriter().print(json.toString());


	}

	private void addTOCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String goodsId=request.getParameter("goodsId");

		Goods goods = goodsService.findGoodsById(Integer.parseInt(goodsId));

		Cart shopCart = (Cart) request.getSession().getAttribute("shopCart");

		if(shopCart==null) {
			shopCart=new Cart();
			request.getSession().setAttribute("shopCart", shopCart);
		}
		shopCart.addGoods(goods);
		response.getWriter().print(shopCart.getTotQuan());//返回现在购物车实时数量
	}

}
 