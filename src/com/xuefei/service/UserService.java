package com.xuefei.service;

import com.xuefei.entity.User;

public interface UserService {
	public abstract void registerUser(User u);
	public abstract User checkUser(User u);
}
