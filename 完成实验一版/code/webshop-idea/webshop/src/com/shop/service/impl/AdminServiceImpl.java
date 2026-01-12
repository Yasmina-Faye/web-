package com.shop.service.impl;

import com.shop.bean.Admin;
import com.shop.bean.PageBean;
import com.shop.dao.AdminDao;
import com.shop.dao.impl.AdminDaoImpl;
import com.shop.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao=new AdminDaoImpl();

	@Override
	public boolean userLogin(Admin admin) {
		return adminDao.userLogin(admin);
	}

	@Override
	public long goodsReadCount() {
		return adminDao.goodsReadCount();
	}

	@Override
	public List<Admin> userList(PageBean pageBean) {
		return adminDao.userList(pageBean);
	}

	@Override
	public boolean addUser(Admin admin) {
		return adminDao.userAdd(admin);
	}

	@Override
	public boolean updateUser(Admin admin) {
		return adminDao.userUpdate(admin);
	}

	@Override
	public Admin findUser(Integer id) {
		return adminDao.findUser(id);
	}

	@Override
	public boolean findUser(String username) {
		return adminDao.findUser(username);
	}

	@Override
	public boolean delUser(int id) {
		return adminDao.delUser(id);
	}

	@Override
	public boolean batDelUser(String ids) {
		return adminDao.batDelUser(ids);
	}
}
