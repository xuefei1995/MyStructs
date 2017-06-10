package com.xuefei.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuefei.entity.User;
import com.xuefei.service.UserService;
import com.xuefei.service.UserServiceImpl;

public class LoginAction {
	
	public String login(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			
			UserService  service = new UserServiceImpl();
			User checkUser = service.checkUser(user);
			if(checkUser==null){
				return "error";
			}else{
				return "success";
			}
	}
}
