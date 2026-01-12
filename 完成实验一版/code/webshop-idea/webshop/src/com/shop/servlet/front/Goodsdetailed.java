package com.shop.servlet.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shop.service.GoodsService;
import com.shop.service.impl.GoodsServiceImpl;


@WebServlet("/goodsdetail")
public class Goodsdetailed extends HttpServlet {

	private GoodsService goodsService=new GoodsServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int goodsId = Integer.parseInt(request.getParameter("goodsId"));

		request.setAttribute("goodsInfo", goodsService.findGoodsById(goodsId));
		request.getRequestDispatcher("jsp/front/goodsdetails.jsp").forward(request, response);

	}

}
