package com.shop.servlet.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.Catalog;
import com.shop.dao.CatalogDao;
import com.shop.dao.impl.CatalogDaoImpl;
import com.shop.service.GoodsService;
import com.shop.service.impl.GoodsServiceImpl;

import net.sf.json.JSONObject;


@WebServlet("/GetCatalog")
public class GetCatalog extends HttpServlet {

	private GoodsService goodsService=new GoodsServiceImpl();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json"); 
		JSONObject json = new JSONObject();
		CatalogDao cd=new CatalogDaoImpl();

		List<Catalog> catalog = cd.getCatalog();
		
		//这里返回查询每个分类的数量
		for(int i=0;i<catalog.size();i++) {
			Catalog c = catalog.get(i);
			long size=goodsService.goodsReadCount(null,c.getCatalogId());
			c.setCatalogSize(size);
		}
		json.put("catalog", catalog);
		response.getWriter().append(json.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
