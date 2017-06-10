<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>   
    <title>注册页面</title>   
  </head>
  <body>
    <form action="${pageContext.request.contextPath }/register.action" method="post">
    	用户名：<input name="name" type="text"><br>
    	密码：<input name="password" type="password"><br>
    	<input type="submit" value="注册">
    </form>
  </body>
</html>
