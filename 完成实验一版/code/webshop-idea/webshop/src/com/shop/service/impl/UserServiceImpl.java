package com.shop.service.impl;

import com.shop.bean.PageBean;
import com.shop.bean.User;
import com.shop.dao.UserDao;
import com.shop.dao.impl.UserDaoImpl;
import com.shop.service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService {

	private UserDao userDao=new UserDaoImpl();

	@Override
	public long goodsReadCount(String username) {
		return userDao.goodsReadCount(username);
	}

	@Override
	public List<User> userList(PageBean pageBean, String username) {
		return userDao.userList(pageBean, username);
	}

	@Override
	public boolean findUser(String userName) {
		return userDao.findUser(userName);
	}

	@Override
	public User findUser(Integer id) {
		return userDao.findUser(id);
	}

	@Override
	public boolean addUser(User user) {
		return userDao.userAdd(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.userUpdate(user);
	}

	@Override
	public boolean delUser(int id) {
		return userDao.delUser(id);
	}

	@Override
	public boolean batDelUser(String ids) {
		return userDao.batDelUser(ids);
	}

	@Override
	public User userLogin(User user) {
		return userDao.userLogin(user);
	}
}
