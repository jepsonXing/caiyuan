<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜缘测试页</title>
</head>
<body>
	<!-- 账号密码登录 -->
	<form action="/caiyuan/sessions" method="POST">
		账号：<input type="text" name="account" /><br /> 密码：<input
			type="password" name="password" /><br /> <input type="submit"
			value="登录" />
	</form>

	<!-- 更换用户头像 -->
	<form action="/caiyuan/users/1?field=avatarUrl" method="post" enctype="multipart/form-data">
		<div>
			<span></span> 
			<input type="file" name="userAvatarImage">
		</div>
		<div>
			<button type="submit">更换用户头像</button>
		</div>
	</form>

</body>
</html>
