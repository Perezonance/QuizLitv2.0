<%@page import="beans.Category" import="beans.User" import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Quiz Lit!</title>
<link rel="stylesheet" type="text/css" href="css/LoginStyling.css" />
</head>
<body>
    <%
       	User user = (User)session.getAttribute("User");
    	Category category = (Category)session.getAttribute("Category");
    	String catName = category.getName();
		
      
    %>
	
    <div class="login-form">
        <div class="form">
            <form class="quiz-form" action="GenerateNewQuizController" method="post">
                <h1><%=catName%> Quiz</h1>
                
                <h3>Instructions</h3>
                <p align="left">Total Questions: 10 Questions</p>
                <p align="left">Time Alloted: 10 Minutes</p>
                <p align="left">Total Score will be displayed once you finish
                    the Quiz</p>
                 <p align="left">You cannot go back to a previously answered question</p>
                <p align="left">Click on Start button to begin the Quiz</p>
                <button>Start</button>
            </form>
        </div>
    </div>
</body>
</html>