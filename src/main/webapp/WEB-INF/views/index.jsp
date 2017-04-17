<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页|菜缘网</title>
</head>
<body>
	<!-- 
	<h3>首页</h3>
	<br/>
	<a href="/caiyuan/login">登录</a>
	<br>
	<a href="/caiyuan/myHomepage">个人主页</a>
	<br>
	<a href="/caiyuan/scanQuestionNew">最新问题</a>
	 -->

	<form action="/caiyuan/app/validateLogin" method="POST">
		账号：<input type="text" name="account" /><br /> 密码：<input
			type="password" name="password" /><br /> <input type="submit"
			value="登录" />
	</form>
	<hr>
	<form action="/caiyuan/app/modifyPersonalMessage" method="post">
		<input type="hidden" name="id" value="1" /><br> 
		<input type="text" name="name" value="helly" /><br> 
		<input type="radio" name="gender" value="男" checked>男 
		<input type="radio" name="gender" value="女">女<br> 
		<input type="date" name="formatBirthday" value="1996-07-05" /><br> 
		<input type="text" name="phone" value="18483661669" /><br> 
		<input type="text" name="email" value="hellyuestc@gmail.com" /><br> 
		<input type="text" name="address" value="电子科技大学沙河校区欣苑5栋719" /><br> 
		<input type="text" name="job" value="大学僧" /><br> 
		<input type="text" name="introduction" value="临渊羡鱼，不如退而结网。" /><br> 
		<input type="submit" value="提交">
	</form>
	<hr>
	<a href="/caiyuan/app/getAvatar/helly">请求头像</a>
	<hr>
	<a href="/caiyuan/app/topic/getNextPage/1">下一页话题</a>
	<hr>
	<a href="/caiyuan/app/question/new/getPrePage/2016-04-12 20:44:56">刷新问题（最新）</a>
	<a href="/caiyuan/app/question/new/getNextPage/2017-04-14 20:44:54">下一页问题（最新）</a>
	<a href="/caiyuan/app/question/hot/getNextPage/1">下一页问题（最热）</a>

</body>
</html>