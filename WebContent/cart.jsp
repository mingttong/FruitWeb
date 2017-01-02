<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.mingttong.fruitweb.ItemVO" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
Map<Integer, ItemVO> cart = (HashMap)session.getAttribute("GOODS_IN_CART");
%>

<div class="cart">
    <div class="item-list">
    <%for (int goodsID : cart.keySet()) {
    	ItemVO item = cart.get(goodsID);
    %>
        <div class="item-item">
            <div class="goods">
                <div class="img">
                    <img src="<%=item.getImgUrl() %>" alt="<%=item.getImgUrl() %>"/>
                </div>
                <div class="title">
                    <em><%=item.getTitle() %></em>
                </div>
            </div>
            <div class="price">
                <strong><%=item.getPrice() %></strong>
            </div>
            <div class="quantity">
                <a href="javascript:void(0)">-</a>
                <input type="text" value="<%=item.getNum() %>"/>
                <a href="javascript:void(0)">+</a>
            </div>
            <div class="options">
                <a data-sku="<%=goodsID %>" href="javascript:void(0)">删除</a>
            </div>
        </div>
    <%} %>
    </div>
</div>

</body>
</html>