<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登录|菜缘网</title>
</head>
<body>
	<h3>登录</h3>

	<form action="/caiyuan/validateLogin" method="POST">
		账号：<input type="text" name="account" /><br /> 
		密码：<input type="password" name="password" /><br /> 
		<input type="submit" value="登录" />
	</form>
	
	<form action="/caiyuan/validateLoginForAPP" method="POST">
		账号：<input type="text" name="account" /><br /> 
		密码：<input type="password" name="password" /><br /> 
		<input type="submit" value="登录" />
	</form>

</body>
</html>