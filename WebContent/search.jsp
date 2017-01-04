<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索-京东一下</title>
</head>
<body>

<h1>京东一下，你就知道</h1>
<hr/>

<form action="searchGoods.do" method="post">
    <input type="text" name="keyword"/>
    <input type="submit" value="京东一下"/>
    <br/>
    <input type="hidden" name="page" value="1"/>
</form>

</body>
</html>