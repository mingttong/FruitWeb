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

<h1>购物车</h1>
<hr/>

<%
if (session.getAttribute("GOODS_IN_CART") != null) {
Map<Integer, ItemVO> cart = (HashMap)session.getAttribute("GOODS_IN_CART");
%>

<div class="cart">
    <div class="item-list">
    <%for (int goodsID : cart.keySet()) {
    	ItemVO item = cart.get(goodsID);
    %>
        <div id="<%=goodsID %>" class="item-item">
            <div class="p-goods">
                <div class="img">
                    <img src="<%=item.getImgUrl() %>" alt="<%=item.getImgUrl() %>"/>
                </div>
                <div class="title">
                    <em><%=item.getTitle() %></em>
                </div>
            </div>
            <div class="p-price">
                <strong><%=item.getPrice() %></strong>
            </div>
            <div class="p-quantity">
                <a class="decrement <%if (item.getNum() == 1) { %> disabled <%} %>" data-sku="<%=goodsID %>" href="javascript:void(0)" onclick="decreaseItem(this)">-</a>
                <input type="text" value="<%=item.getNum() %>"/>
                <a class="increment" data-sku="<%=goodsID %>" href="javascript:void(0)" onclick="increaseItem(this)">+</a>
            </div>
            <div class="p-sum">
                <strong><%=item.getPrice() * item.getNum() %></strong>
            </div>
            <div class="p-ops">
                <a data-sku="<%=goodsID %>" href="javascript:void(0)" onclick="deleteItem(this)">删除</a>
            </div>
        </div>
    <%} %>
    </div>
</div>
<%} %>

<script src="js/cart.js"></script>
</body>
</html>