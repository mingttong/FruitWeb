<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请登录</title>
</head>
<body>
<h1>登录</h1>
<hr />

<form action="login.do" method="post">
    用户名：<input type="text" name="usr" />
    <br />
    密码：<input type="password" name="pwd" />
    <br />
    <input type="submit" value="登录" />
    <a href="reg.jsp"><input type="button" value="注册"/></a>
</form>

</body>
</html>