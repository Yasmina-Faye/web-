package com.shop.servlet.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.service.GoodsService;
import com.shop.service.impl.GoodsServiceImpl;
import com.shop.bean.Goods;
import com.shop.bean.PageBean;


@WebServlet("/GoodsList")
public class GoodsList extends HttpServlet {

	private static final int MAX_LIST_SIZE = 12;
	private static final String BOOKLIST_PATH="jsp/front/goodslist.jsp";
	private GoodsService goodsService=new GoodsServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		goodsList(request,response);
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	private void goodsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		String catalogIdStr = request.getParameter("catalogId");//获取有没有分类id，没有就是查全部
		String seachname = request.getParameter("seachname");
		Integer catalogId = null; // Default to null
		if (catalogIdStr != null && !catalogIdStr.isEmpty()) {
			catalogId = Integer.parseInt(catalogIdStr);
		}

		PageBean pb = new PageBean(curPage, MAX_LIST_SIZE, goodsService.goodsReadCount(seachname, catalogId));
		List<Goods> goodsList = goodsService.goodsList(pb, catalogId, seachname);

		if(catalogIdStr!=null) {
			if(goodsList.size()>0) {
				request.setAttribute("title", goodsList.get(0).getCatalog().getCatalogName());//从返回的分类集合中第一个获取数据的分类
			}
		}else {
			request.setAttribute("title", "所有商品");
		}
		request.setAttribute("pageBean", pb);
		request.setAttribute("goodsList", goodsList);

		request.getRequestDispatcher(BOOKLIST_PATH).forward(request, response);
	}

}
