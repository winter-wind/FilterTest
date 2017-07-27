<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<%
	//获取用户的登录信息
	String username = request.getParameter("username");
	
	//若登录信息完整即不为空和不为“”，则把登录信息放到httpSession
	if(username != null && !username.trim().equals("")){
		
		session.setAttribute(application.getInitParameter("userSessionKey"), username);
		//session.setAttribute("user", username);
		System.out.println("jsp11:"+application.getInitParameter("userSessionKey"));
		System.out.println("jsp:"+username);
		//重定向到list.jsp
		response.sendRedirect("list.jsp");
	}else{
		response.sendRedirect("login.jsp");
	}

%>

</body>
</html>