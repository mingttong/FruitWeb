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
<link rel="stylesheet" href="css/normalize.css"/>
<link rel="stylesheet" href="css/index.css"/>
</head>
<body>

<%
HttpSession se = request.getSession();
Object loginMsg = se.getAttribute("LOGGED_IN_USER");
%>

<header>
    <div class="grid">
        <ul>
        <% if (loginMsg != null) { %>
            <li class="usr-name">
                <strong><%=loginMsg %></strong>
            </li>
            <li class="modify-pwd">
                <a href="modifyPwd.jsp">修改密码</a>
            </li>
            <li class="log-off">
                <a href="logoff.do">退出</a>
            </li>
        <% } else { %>
            <li class="log-in">
                <a class="link-login" href="login.jsp">请登录</a>
            </li>
        <% } %>
            <li class="cart-icon">
                <i class="cart-img"></i>
            </li>
            <li class="cart-link">
                <a target="_blank" href="cart.jsp">我的购物车</a>
            </li>
        </ul>
    </div>
</header>

<div class="search-item">
    <div class="grid">
        <form action="searchGoods.do" method="post">
            <input class="search-value" type="text" name="keyword"/>
            <input type="submit" value="京东一下"/>
            <br/>
            <input type="hidden" name="page" value="1"/>
        </form>
    </div>
</div>

<%
GoodsDAO dao = new GoodsDAO();
List<GoodsVO> goodsList = dao.getGoodsList(); // 获取商品列表
%>

<div class="goods-list">
    <div class="grid">
    	<ul class="gl-wrap clearfix">
    	<%for (GoodsVO vo : goodsList) { %>
	        <li class="gl-item">
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
	                    <a class="addcart" data-sku="<%=vo.getGoodsID() %>" href="javascript:void(0)" onclick="addCart(this)">加入购物车</a>
	                </div>
	            </div>
	        </li>
    	<%} %>
    	</ul>
    </div>
</div>

<footer>
    <div class="grid">
        <p>This page is powered by zhouwunan.</p>

        <p>Contact: zhouwunan@qq.com</p>
    </div>
</footer>

<script src="js/index.js"></script>
</body>
</html>