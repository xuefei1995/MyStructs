package com.xuefei.framework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuefei.framework.bean.Action;
import com.xuefei.framework.bean.Result;


public class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ConfigAction con; 
	
	@Override
	public void init() throws ServletException {
		con=new ConfigAction();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, Action> actions = con.getActions();
			//获得对应的action请求
			String uri = request.getRequestURI();
			String pathname=uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf("."));
			
			if(!actions.containsKey(pathname)){
				throw new RuntimeException("没用对应的Action对象");
			}
			//获得对应的action对象
			Action action = actions.get(pathname);
			String name = action.getPath();
			//构造对应的对象
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName(name);
			Object obj = clazz.newInstance();
			//调用对应的method
			String method = action.getMethod();
			@SuppressWarnings("unchecked")
			Method declaredMethod = clazz.getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			String resulttag = (String)declaredMethod.invoke(obj, request,response);
			//根据method返回结果获得对应的result对象
			Map<String, Result> results = action.getMap();
			Result rs = results.get(resulttag);
			//获得对应的类型和应该去到的页面
			String type = rs.getType();
			String page = rs.getUri();
			if("redirect".equals(type)){
				response.sendRedirect(request.getContextPath()+page);
			}
			if("dispacher".equals(type)){
				request.getRequestDispatcher(page).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
