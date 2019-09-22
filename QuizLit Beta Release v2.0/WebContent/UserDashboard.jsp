<%@ page import = "beans.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Quiz Lit User Dashboard</title>
		<link rel="stylesheet" type="text/css" href="css/DashboardStyling.css"/>
		<%
			User user = null;
			try{
				user = (User)session.getAttribute("User");
				
				
			} catch(Exception e){
				System.out.println("Failed to Display User Dashboard Quiz Table:");
				e.printStackTrace();
			}
		%>
	</head>
	<body>
		<div class="sideNav">
			<a href="UserDashboard.jsp">User Dashboard</a>
			<a href="AccountInfo.jsp">Account Details</a>
			<form action="LogoutController" method="post">
				<button>Logout</button>
			</form>
		</div>
		
		<div class="main">
			<div class="welcomeHeader">
				<h1>Welcome, <%=user.getfName() %>!</h1>
			</div>
			<div class="quizTable">
				<jsp:include page="QuizTableInsert.jsp"></jsp:include>
			</div>
		</div>
		
	</body>
</html>