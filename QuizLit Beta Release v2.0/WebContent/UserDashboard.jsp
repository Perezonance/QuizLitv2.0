<%@ page import = "beans.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		User user = (User)session.getAttribute("User");
	%>
	<h1>Welcome, <%=user.getfName() %>!</h1>
	<%
		RequestDispatcher rd = request.getRequestDispatcher("CategoryTableController");
		rd.include(request, response);
	%>
</body>
</html>