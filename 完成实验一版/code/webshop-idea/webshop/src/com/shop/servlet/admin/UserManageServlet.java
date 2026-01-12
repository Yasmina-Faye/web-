package com.shop.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shop.bean.PageBean;
import com.shop.bean.User;


import com.shop.dao.impl.AdminDaoImpl;


import com.shop.service.UserService;
import com.shop.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;


@WebServlet("/jsp/admin/UserManageServlet")
public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERLIST_PATH="userManage/userList.jsp";//用户列表页面地址
	private static final String USERADD_PATH="userManage/userAdd.jsp";//用户增加页面地址
	private static final String USEREDIT_PATH="userManage/userEdit.jsp";//用户修改页面地址
	private static final String USERDETAIL_PATH="userManage/userDetail.jsp";//用户修改页面地址

	private UserService userService=new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action==null) {
			action="list";
		}
		if (action.equals("list")) {
			userList(request, response);
		} else if (action.equals("add")) {
			userAdd(request, response);
		} else if (action.equals("update")) {
			userUpdate(request, response);
		} else if (action.equals("edit")) {
			userEdit(request, response);
		} else if (action.equals("del")) {
			userDel(request, response);
		} else if (action.equals("batDel")) {
			userBatDel(request, response);
		} else if (action.equals("find")) {
			adminFind(request, response);
		} else if (action.equals("detail")) {
			datail(request, response);
		} else if (action.equals("seach")) {
			seachUser(request, response);
		}

	}

	private void seachUser(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

		int curPage=1;
		String page=request.getParameter("page");
		if(page!=null){
			curPage=Integer.parseInt(page);
		}
		//获取xml中设置的每页显示大小参数
		int maxSize=Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		String username = request.getParameter("username");
		PageBean pageBean=null;
		if(username != null && username != "") {
			pageBean=new PageBean(curPage,maxSize,userService.goodsReadCount(username));
			request.setAttribute("userList", userService.userList(pageBean,username));
		}else {
			pageBean=new PageBean(curPage,maxSize,userService.goodsReadCount(null));
			request.setAttribute("userList", userService.userList(pageBean,null));
		}
		request.setAttribute("pageBean", pageBean);
		
		request.getRequestDispatcher(USERLIST_PATH).forward(request, response);
		
	}

	private void datail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");

		request.setAttribute("userInfo",userService.findUser(Integer.valueOf(id)));//这里回去是User对象
		request.getRequestDispatcher(USERDETAIL_PATH).forward(request, response);
		
	}

	private void userBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids=request.getParameter("ids");

		
		if(userService.batDelUser(ids)) {
			request.setAttribute("userMessage", "用户已批量删除");
		}else {
			request.setAttribute("userMessage", "用户批量删除失败");
		}
		//用户删除成功失败都跳转到用户列表页面
		userList(request, response);//通过servlet中listUser跳到用户列表
		
	}

	private void userDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		if(userService.delUser(id)) {
			request.setAttribute("userMessage", "用户已删除");
		}else {
			request.setAttribute("userMessage", "用户删除失败");
		}
		//用户删除成功失败都跳转到用户列表页面
		userList(request, response);//通过servlet中listUser跳到用户列表
		
	}

	private void userUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User(
				Integer.parseInt(request.getParameter("userId")),
				request.getParameter("passWord"),
				request.getParameter("name"),
				request.getParameter("sex"),
				Integer.parseInt(request.getParameter("age")),
				request.getParameter("tell"),
				request.getParameter("address"),
				request.getParameter("enabled"));

		if(userService.updateUser(user)) {
			request.setAttribute("userMessage", "用户更新成功");
			userList(request, response);//通过servlet中listUser跳到用户列表
		}else {
			//更新失败跳转到修改页面
			request.setAttribute("userMessage", "用户更新失败");
			request.setAttribute("userInfo", userService.findUser(Integer.valueOf(user.getUserId())));//这里回去是Admin对象
			request.getRequestDispatcher(USEREDIT_PATH).forward(request, response);
		}
		
	}

	private void userEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");

		request.setAttribute("userInfo",userService.findUser(Integer.valueOf(id)));//这里回去是User对象
		request.getRequestDispatcher(USEREDIT_PATH).forward(request, response);
		
	}

	private void adminFind(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userName=request.getParameter("param");

		JSONObject json=new JSONObject();
		if(userService.findUser(userName)){
			json.put("info", "用户名已存在");
			json.put("status", "n");
		}else{
			json.put("info", "用户名可以使用");
			json.put("status", "y");
		}
		response.getWriter().write(json.toString());
		
	}

	private void userAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user=new User(
				request.getParameter("userName"),
				request.getParameter("passWord"),
				request.getParameter("name"),
				request.getParameter("sex"),
				Integer.parseInt(request.getParameter("age")),
				request.getParameter("tell"),
				request.getParameter("address"));
		user.setEnabled("y");//默认添加的用户启用
		//添加之前判断用户名是否在库中存在
		if(new AdminDaoImpl().findUser(user.getUserName())){
			request.setAttribute("userMessage", "用户添加失败！用户名已存在");
			request.getRequestDispatcher(USERADD_PATH).forward(request, response);
		}else{
			//执行dao层添加操作
			if(userService.addUser(user)){
				request.setAttribute("userMessage", "用户添加成功！");
				userList(request, response);//通过servlet中listUser跳到用户列表
			}else{
				request.setAttribute("userMessage", "用户添加失败！");
				request.getRequestDispatcher(USERADD_PATH).forward(request, response);
			}
		}
	}

	private void userList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int curPage=1;
		String page=request.getParameter("page");
		if(page!=null){
			curPage=Integer.parseInt(page);
		}
		//获取xml中设置的每页显示大小参数
		int maxSize=Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		
		PageBean pageBean=new PageBean(curPage,maxSize,userService.goodsReadCount(null));
		
		request.setAttribute("userList", userService.userList(pageBean,null));
		request.setAttribute("pageBean", pageBean);
		
		request.getRequestDispatcher(USERLIST_PATH).forward(request, response);
		
	}

}
