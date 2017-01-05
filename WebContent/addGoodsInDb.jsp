<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品信息</title>
<link rel="stylesheet" href="css/addGoodsInDb.css"/>
</head>
<body>

<h1>添加商品信息</h1>
<hr/>

<form action="addGoodsInDb.do" method="post">
    商品标题：<input class="ipt-txt" type="text" name="title"/>
    <br/>
    商品价格：<input class="ipt-txt" type="text" name="price"/>
    <br/>
    图片地址：<input class="ipt-txt" type="text" name="img_url"/>
    <br/>
    <input type="submit" value="添加"/>
</form>

</body>
</html>