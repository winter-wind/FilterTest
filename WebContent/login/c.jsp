<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<%--
	//检验用户是否登录，若没有登录，则直接重定向到login.jsp
	Object user = session.getAttribute("user");

	if(user == null){
		response.sendRedirect("login.jsp");
	}
--%>

<h4>This is CCC</h4>
<br>
<a href="list.jsp">return...</a>
</body>
</html>