<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="css/LoginStyling.css" />
		<title>Quiz Lit Login</title>
	</head>
	<body>
		<%
			
		%>
		<div class="login-page">
		  <div class="form">
		    <form class="login-form" action="LoginUserController" method="post">
		   	  <div class="login-header">Welcome to Quiz Lit!</div>
		      <input type="email" name = "email" placeholder="email" required/>
		      <input type="password" name = "password" placeholder="password" required/>
		      <button>login</button>
		      <p class="message">Not registered? <a href="RegisterPage.jsp">Register</a></p>
		      <p class="message">Forgot Password? <a href="RecoverPasswordPage.jsp">Recover Password</a></p>
		      <p class="message">Have a Quiz Access Code? <a href="GuestPage.jsp">Guest Sign In</a></p>
		    </form>
		  </div>
		</div>	
	</body>
</html>
