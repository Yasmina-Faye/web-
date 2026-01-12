package com.shop.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.Admin;
import com.shop.bean.PageBean;

import com.shop.service.AdminService;
import com.shop.service.impl.AdminServiceImpl;


import net.sf.json.JSONObject;


@WebServlet("/jsp/admin/AdminManageServlet")
public class AdminManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMINLIST_PATH="adminManage/adminList.jsp";//用户列表页面地址
	private static final String ADMINADD_PATH="adminManage/adminAdd.jsp";//用户增加页面地址
	private static final String ADMINEDIT_PATH="adminManage/adminEdit.jsp";//用户修改页面地址

	private AdminService adminService=new AdminServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if (action.equals("list")) {
			adminList(request, response);
		} else if (action.equals("add")) {
			adminAdd(request, response);
		} else if (action.equals("update")) {
			adminUpdate(request, response);
		} else if (action.equals("edit")) {
			adminEdit(request, response);
		} else if (action.equals("del")) {
			adminDel(request, response);
		} else if (action.equals("batDel")) {
			adminBatDel(request, response);
		} else if (action.equals("find")) {
			adminFind(request, response);
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//查询用户列表
	private void adminList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int curPage=1;
		String page=request.getParameter("page");
		if(page!=null){
			curPage=Integer.parseInt(page);
		}
		//获取xml中设置的每页显示大小参数
		int maxSize=Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		
		PageBean pageBean=new PageBean(curPage,maxSize,adminService.goodsReadCount());
		
		request.setAttribute("adminList", adminService.userList(pageBean));
		request.setAttribute("pageBean", pageBean);
		
		request.getRequestDispatcher(AdminManageServlet.ADMINLIST_PATH).forward(request, response);
		
	}
	
	//增加用户
	private void adminAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Admin admin=new Admin(request.getParameter("userName"),request.getParameter("passWord"),
				request.getParameter("name"));
		//添加之前判断用户名是否在库中存在
		if(adminService.findUser(admin.getUserName())){
			request.setAttribute("adminMessage", "用户添加失败！用户名已存在");
			request.getRequestDispatcher(AdminManageServlet.ADMINADD_PATH).forward(request, response);
		}else{
			//执行dao层添加操作
			if(adminService.addUser(admin)){
				request.setAttribute("adminMessage", "用户添加成功！");
				adminList(request, response);//通过servlet中listUser跳到用户列表
			}else{
				request.setAttribute("adminMessage", "用户添加失败！");
				request.getRequestDispatcher(AdminManageServlet.ADMINADD_PATH).forward(request, response);
			}
		}
		
	}
	//更新用户信息
	private void adminUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin=new Admin(Integer.parseInt(request.getParameter("id")),
				request.getParameter("passWord"),
				request.getParameter("name")
				);

		if(adminService.updateUser(admin)) {
			request.setAttribute("adminMessage", "用户更新成功");
			adminList(request, response);//通过servlet中listUser跳到用户列表
		}else {
			//更新失败跳转到修改页面
			request.setAttribute("adminMessage", "用户更新失败");
			request.setAttribute("adminInfo", adminService.findUser(Integer.valueOf(admin.getId())));//这里回去是Admin对象
			request.getRequestDispatcher(AdminManageServlet.ADMINEDIT_PATH).forward(request, response);
		}
		
	}

	//修改用户
	private void adminEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");

		request.setAttribute("adminInfo",adminService.findUser(Integer.valueOf(id)));//这里回去是Admin对象
		request.getRequestDispatcher(AdminManageServlet.ADMINEDIT_PATH).forward(request, response);
	}
	
	//删除用户
	private void adminDel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id=Integer.parseInt(request.getParameter("id"));

		if(adminService.delUser(id)) {
			request.setAttribute("adminMessage", "用户已删除");
		}else {
			request.setAttribute("adminMessage", "用户删除失败");
		}
		//用户删除成功失败都跳转到用户列表页面
		adminList(request, response);//通过servlet中listUser跳到用户列表
	}
	
	//批量删除
	private void adminBatDel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ids=request.getParameter("ids");

		
		if(adminService.batDelUser(ids)) {
			request.setAttribute("adminMessage", "用户已批量删除");
		}else {
			request.setAttribute("adminMessage", "用户批量删除失败");
		}
		//用户删除成功失败都跳转到用户列表页面
		adminList(request, response);//通过servlet中listUser跳到用户列表
		
	}
	
	//ajax判断用户名(param为接收的用户名）
	private void adminFind(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userName=request.getParameter("param");
		

		//这里实例化json对象需要导入6个jar包（
		//commons-lang-2.4.jar ,commons-collections-3.2.1.jar,commons-beanutils-1.8.3.jar 
		//json-lib-2.4-jdk15.jar ,ezmorph-1.0.6.jar ,commons-logging-1.1.3.jar）
		JSONObject json=new JSONObject();
		if(adminService.findUser(userName)){
			json.put("info", "用户名已存在");
			json.put("status", "n");
		}else{
			json.put("info", "用户名可以使用");
			json.put("status", "y");
		}
		response.getWriter().write(json.toString());
	}
	
}
