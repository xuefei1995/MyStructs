package com.xuefei.dao;

import com.xuefei.entity.User;

public interface UserDao {
	public abstract void addUser(User u);
	public abstract User findUser(User u);
}
