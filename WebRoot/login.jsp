<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>登陆页面</title>   
  </head>
  <body>
        <form action="${pageContext.request.contextPath }/login.action" method="post">
    	用户名：<input name="name" type="text"><br>
    	密码：<input name="password" type="password"><br>
    	<input type="submit" value="登陆">
    </form>
  </body>
</html>
