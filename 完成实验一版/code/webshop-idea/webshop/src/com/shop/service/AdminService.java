package com.shop.service;

import com.shop.bean.Admin;
import com.shop.bean.PageBean;

import java.util.List;

public interface AdminService {
	
	//用户登录
	boolean userLogin(Admin admin);
	//获取总记录数
	long goodsReadCount();
	//获取用户列表（分页显示）
	List<Admin> userList(PageBean pageBean);
	//增加用户
	boolean addUser(Admin admin);
	//更新用户
	boolean updateUser(Admin admin);
	//根据id获取一个用户的信息
	Admin findUser(Integer id);
	//查找用户名是否存在
	boolean findUser(String username);
	//根据id删除一个用户
	boolean delUser(int id);
	//根据一组id字符串批量删除用户
	boolean batDelUser(String ids);
}
