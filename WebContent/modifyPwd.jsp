<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
</head>
<body>

<%
HttpSession se = request.getSession();
String errMsg = (String)se.getAttribute("ERR_MSG");
if (errMsg == null) {
	se.setAttribute("ERR_MSG", "");
	errMsg = "";
}
%>

<h1>修改密码</h1>
<hr/>

<form action="modifyPwd.do" method="post">
    原密码：<input type="password" name="oldPwd"/>
    <br/>
    新密码：<input type="password" name="newPwd"/>
    <br/>
    确认密码：<input type="password" name="newPwd2"/>
    <br/>
    <input type="submit" value="确认"/>
</form>
<em><%=errMsg %></em>

</body>
</html>