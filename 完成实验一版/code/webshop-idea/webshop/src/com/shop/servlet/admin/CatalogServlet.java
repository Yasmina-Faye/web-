 package com.shop.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.PageBean;
import com.shop.dao.CatalogDao;
import com.shop.dao.impl.CatalogDaoImpl;

import net.sf.json.JSONObject;

/**
 * 商品分类servlet
 */
@WebServlet("/CatalogServlet")
public class CatalogServlet extends HttpServlet {

	private static final String CATALOGLIST_PATH="jsp/admin/goodsManage/catalogList.jsp";
	private static final String CATALOGADD_PATH="jsp/admin/goodsManage/catalogAdd.jsp";
       


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if ("list".equals(action)) {
			catalogList(request, response);
		} else if ("add".equals(action)) {
			catalogAdd(request, response);
		} else if ("del".equals(action)) {
			catalogDel(request, response);
		} else if ("batDel".equals(action)) {
			catalogBatDel(request, response);
		} else if ("find".equals(action)) {
			catalogFind(request, response);
		}

	}
	
	
	private void catalogBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids=request.getParameter("ids");
		CatalogDao cd=new CatalogDaoImpl();
		
		if(cd.catalogBatDelById(ids)) {
			request.setAttribute("goodsMessage", "分类已批量删除");
			
		}else {
			request.setAttribute("goodsMessage", "分类删除失败");
		}
		//用户删除成功失败都跳转到用户列表页面
		catalogList(request, response);//通过servlet中listUser跳到用户列表
		
	}

	private void catalogDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int catalogId=Integer.parseInt(request.getParameter("id"));
		CatalogDao cd=new CatalogDaoImpl();
		if(cd.catalogDel(catalogId)) {
			request.setAttribute("catalogMessage", "该分类已删除");
		}else {
			request.setAttribute("catalogMessage", "该分类删除失败");
		}
		catalogList(request,response);
	}

	private void catalogAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String catalogName=request.getParameter("catalogName");
		CatalogDao cd =new CatalogDaoImpl();
		if(cd.catalogAdd(catalogName)) {
			request.setAttribute("catalogMessage", "增加分类成功");
			catalogList(request,response);
		}else {
			request.setAttribute("catalogMessage", "增加分类失败");
			request.getRequestDispatcher(CATALOGADD_PATH).forward(request,response);
		}
		
	}

	//获取分类列表
	private void catalogList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		CatalogDao cd = new CatalogDaoImpl();
		
		PageBean pb = new PageBean(curPage, maxSize, cd.catalogReadCount());
		
		request.setAttribute("pageBean", pb);
		
		request.setAttribute("catalogList", cd.catalogList(pb));
		request.getRequestDispatcher(CATALOGLIST_PATH).forward(request, response);
	}
	// ajax查找商品是否存在
		private void catalogFind(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String catalogName = request.getParameter("param");

			CatalogDao cd = new CatalogDaoImpl();
			// 这里实例化json对象需要导入6个jar包（
			// commons-lang-2.4.jar
			// ,commons-collections-3.2.1.jar,commons-beanutils-1.8.3.jar
			// json-lib-2.4-jdk15.jar ,ezmorph-1.0.6.jar ,commons-logging-1.1.3.jar）
			JSONObject json = new JSONObject();
			if (cd.findCatalogByCatalogName(catalogName)) {
				json.put("info", "该分类已存在");
				json.put("status", "n");
			} else {
				json.put("info", "输入正确");
				json.put("status", "y");
			}
			response.getWriter().write(json.toString());

		}

}
