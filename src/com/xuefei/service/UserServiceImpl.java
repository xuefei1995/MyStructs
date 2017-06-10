package com.xuefei.service;

import com.xuefei.dao.UserDao;
import com.xuefei.dao.UserDaoImpl;
import com.xuefei.entity.User;

public class UserServiceImpl implements UserService {
	UserDao dao=new UserDaoImpl();
	
	@Override
	public void registerUser(User u) {
		dao.addUser(u);
	}

	@Override
	public User checkUser(User u) {
		User user = dao.findUser(u);
		return user;
	}

}
