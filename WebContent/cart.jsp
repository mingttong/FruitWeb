<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.mingttong.fruitweb.ItemVO" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<link rel="stylesheet" href="css/cart.css"/>
</head>
<body>

<header>
    <div class="grid">
        <ul>
            <li class="usr-name">
                <strong>mingttong</strong>
            </li>
            <li class="log-off">
                <a href="logoff.do">退出</a>
            </li>
            <li class="log-in">
                <a class="link-login" href="login.jsp">请登录</a>
            </li>
            <li class="cart-icon">
                <i class="cart-img"></i>
            </li>
            <li class="cart-link">
                <a target="_blank" href="cart.jsp">我的购物车</a>
            </li>
        </ul>
    </div>
</header>

<%
if (session.getAttribute("GOODS_IN_CART") != null) {
Map<Integer, ItemVO> cart = (HashMap)session.getAttribute("GOODS_IN_CART");
%>

<div class="cart">
    <div class="grid">
        <div class="item-list clearfix">
    	<%for (int goodsID : cart.keySet()) {
    		ItemVO item = cart.get(goodsID);
    	%>
	        <div id="<%=goodsID %>" class="item-item clearfix">
	            <div class="cell p-goods">
	                <div class="p-img">
	                    <img src="<%=item.getImgUrl() %>" alt="<%=item.getImgUrl() %>"/>
	                </div>
	                <div class="p-title">
	                    <em><%=item.getTitle() %></em>
	                </div>
	            </div>
	            <div class="cell p-price">
	                <strong><%=item.getPrice() %></strong>
	            </div>
	            <div class="cell p-quantity">
	                <a class="decrement <%if (item.getNum() == 1) { %> disabled <%} %>" data-sku="<%=goodsID %>" href="javascript:void(0)" onclick="decreaseItem(this)">-</a>
	                <input data-sku="<%=goodsID %>" type="text" value="<%=item.getNum() %>"/>
	                <a class="increment" data-sku="<%=goodsID %>" href="javascript:void(0)" onclick="increaseItem(this)">+</a>
	            </div>
	            <div class="cell p-sum">
	                <strong><%=item.getPrice() * item.getNum() %></strong>
	            </div>
	            <div class="cell p-ops">
	                <a data-sku="<%=goodsID %>" href="javascript:void(0)" onclick="deleteItem(this)">删除</a>
	            </div>
	        </div>
    	<%} %>
    </div>
    </div>
</div>
<%} %>

<script src="js/cart.js"></script>
</body>
</html>