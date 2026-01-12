package com.shop.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.service.GoodsService;
import com.shop.service.impl.GoodsServiceImpl;
import com.shop.bean.Goods;

import net.sf.json.JSONObject;

@WebServlet("/ShopIndex")
public class ShopIndex extends HttpServlet {

	private GoodsService goodsService=new GoodsServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/json"); 
		JSONObject json = new JSONObject();
		String count = request.getParameter("count");
		Integer limit=4;
		if (count!=null&&count!=""){
			limit=Integer.valueOf(count);
		}
		List<Goods> recGoodss = goodsService.goodsList(limit);
		json.put("recGoodss", recGoodss);
		List<Goods> newGoodss = goodsService.newGoodss(limit);
		json.put("newGoodss", newGoodss);
		
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.flush();
		
		
	}

}
