<%@ page import = "beans.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Quiz Lit User Dashboard</title>
		<link rel="stylesheet" type="text/css" href="css/DashboardStyling.css"/>
		
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/w/dt/dt-1.10.18/datatables.min.css"/>
		<script type="text/javascript" src="https://cdn.datatables.net/w/dt/dt-1.10.18/datatables.min.js"></script>
		<%
			User user = null;
			String firstName = "NameNotFound";
			try{
				user = (User)session.getAttribute("User");
				firstName = user.getfName();
				
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
				<h1>Welcome, <%=firstName%>!</h1>
			</div>
			<div class="quizTable">
				<jsp:include page="QuizTableInsert.jsp"></jsp:include>
			</div>
		</div>
		
	</body>
</html>