package com.shop.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.service.impl.AdminServiceImpl;
import com.shop.bean.Admin;

import com.shop.service.AdminService;


@WebServlet("/jsp/admin/LoginServlet")
public class LoginServlet extends HttpServlet {

	private AdminService adminService=new AdminServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mainPath="index.jsp";
		String loginPath="login.jsp";
		
		String userName=request.getParameter("userName");
		String passWord=request.getParameter("passWord");
		Admin admin=new Admin(userName, passWord);

		
		List<String> list=new ArrayList<String>();
		if(userName==null) {
			list.add("用户名不能为空");
		}
		if(passWord==null) {
			list.add("密码不能为空");
		}
		if(list.size()==0) {
			if(adminService.userLogin(admin)) {
				request.getSession().setAttribute("adminUser",admin );
				response.sendRedirect(mainPath);
				return;
			}else {
				list.add("用户名或密码错误!请重新输入");	
			}
		}
		request.setAttribute("infoList", list);
		request.getRequestDispatcher(loginPath).forward(request, response);
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
