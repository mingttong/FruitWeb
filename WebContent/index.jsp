<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mingttong.fruitweb.GoodsDAO" %>
<%@ page import="com.mingttong.fruitweb.GoodsVO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>京东西南北</title>
</head>
<body>

<h1>网站首页</h1>
<hr/>

<h1>欢迎！</h1>

<header>
    <form action="logoff.do" method="post">
        <input type="submit" value="退出"/>
    </form>
</header>

<%
GoodsDAO dao = new GoodsDAO();
List<GoodsVO> goodsList = dao.getGoodsList(); // 获取商品列表
%>

<div id="goodsList">
    <ul class="gl-wrap clearfix">
    <%for (GoodsVO vo : goodsList) { %>
        <li id="<%=vo.getGoodsID() %>" class="gl-item">
            <div class="gl-i-wrap">
                <div class="p-img">
                    <a target="_blank" href="#" title="<%=vo.getTitle() %>">
                        <img src="<%=vo.getImgUrl() %>" alt="<%=vo.getTitle() %>" class="err-product"/>
                    </a>
                </div>
                <div class="p-price">
                    <strong>
                        <em>￥</em>
                        <i><%=vo.getPrice() %></i>
                    </strong>
                </div>
                <div class="p-name">
                    <a target="_blank" title="<%=vo.getTitle() %>"  href="#">
                        <em><%=vo.getTitle() %></em>
                    </a>
                </div>
                <div class="p-operate">
                    <form action="" method="post">
                        <input type="submit" value="加入购物车"/>
                    </form>
                </div>
            </div>
        </li>
    <%} %>
    </ul>
</div>

<footer></footer>

</body>
</html>