<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息</title>
</head>
<body>
修改个人信息<br/>
	<form action="/caiyuan/modifyAvatar">
		<input type="file">
		<input type="submit" value="更换头像">
	</form>

	<form action="/caiyuan/excuteModifyPersonalMessage" method="post">
		<input type="text" name="name" value="${currentUser.name}"/><br>
		<input type="radio" name="gender" value="男" checked>男
		<input type="radio" name="gender" value="女">女<br>
		<input type="date" name="formatBirthday" value="${currentUser.getFormatBirthday()}"/><br>
		<input type="text" name="phone" value="${currentUser.phone}"/><br>
		<input type="text" name="email" value="${currentUser.email}"/><br>
		<input type="text" name="address" value="${currentUser.address}"/><br>
		<input type="text" name="job" value="${currentUser.job}"/><br>
		<input type="text" name="introduction" value="${currentUser.introduction}"/><br>
		<input type="submit" value="提交">
	</form>

</body>
</html>