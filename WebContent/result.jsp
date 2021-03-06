<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mingttong.fruitweb.GoodsVO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果-京东一下</title>
<style>
.s-err {
    color: red;
    font-size: larger;
}
</style>
</head>
<body>

<%!List<GoodsVO> result; %>
<%!int curPgNum; %>
<%!int totalPgNum; %>
<%!String keyword; %>
<%
result = (List<GoodsVO>)request.getAttribute("QUERY_RES");
Integer o = (Integer)request.getAttribute("curPgNum");
curPgNum = o.intValue(); // 当前页码
Integer o2 = (Integer)request.getAttribute("totalPgNum");
totalPgNum = o2.intValue(); // 页码总数

keyword = (String)request.getAttribute("keyword");

HttpSession se = request.getSession();
String searchErr = (String)se.getAttribute("SEARCH_ERR");
if (searchErr == null) {
	se.setAttribute("SEARCH_ERR", "");
	searchErr = "";
}

%>

<h1>搜索结果</h1>
<a href="index.jsp">首页</a>
<a href="cart.jsp">我的购物车</a>
<hr/>

<table>
    <tr>
        <th>商品图片</th>
        <th>标题</th>
        <th>价格</th>
    </tr>
    <% for (GoodsVO vo : result) { %>
    <tr>
        <td>
            <img src="<%=vo.getImgUrl() %>" alt="<%=vo.getImgUrl() %>"/>
        </td>
        <td>
            <em><%=vo.getTitle() %></em>
        </td>
        <td>
            <strong><%=vo.getPrice() %></strong>
        </td>
        <td>
            <a class="addcart" data-sku="<%=vo.getGoodsID() %>" href="javascript:void(0)" onclick="addCart(this)">加入购物车</a>
        </td>
    </tr>
    <% } %>
</table>

<em class="s-err"><%=searchErr %></em>
<br />

当前第<%=curPgNum %>页，共<%=totalPgNum %>页
<%if (curPgNum != 1) { %>
<a href="searchGoods.do?keyword=&page=1">首页</a>
<a href="searchGoods.do?keyword=&page=">上一页</a>
<%} %>
<%if (curPgNum != totalPgNum) { %>
<a href="searchGoods.do?keyword=&page=">下一页</a>
<a href="searchGoods.do?keyword=&page=">末页</a>
<%} %>

<script src="js/index.js"></script>
</body>
</html>