package com.xuefei.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuefei.entity.User;
import com.xuefei.service.UserService;
import com.xuefei.service.UserServiceImpl;

public class RegisterAction {
	
	public String register(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		UserService  service = new UserServiceImpl();
		service.registerUser(user);
		return "success";
	}
}
