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
		User user = null;
		try{
			user = (User)session.getAttribute("User");
			RequestDispatcher rd = request.getRequestDispatcher("CategoryTableController");
			rd.include(request, response);
		} catch(Exception e){
			System.out.println("Failed to Display User Dashboard Quiz Table:");
			e.printStackTrace();
		}
	%>
	<h1>Welcome, <%=user.getfName() %>!</h1>
	<%
		
	%>
</body>
</html>