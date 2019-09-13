<%@page import="beans.*" import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><!DOCTYPE html>
<html>
	<head>
		<title>Dynamic Quiz Project</title>
		<link type='text/css' rel='stylesheet' href='css/QuizStyling.css'/>
		<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open Sans"/>
	</head>
	<body>
	
		<%
			Quiz quiz = (Quiz)session.getAttribute("Quiz");
			QuizStructure structure = (QuizStructure)session.getAttribute("Structure");
			String quizName = structure.getName();
		%>
		<div id='container'>
			<div id='title'>
				<h1><%=quizName%> : Question #</h1>
			</div>
   			<br/>
  			<div>
  				<form action='GenerateNewQuizController' method='post'>
  					<p>
  						Question:<%= (String)request.getAttribute("q") %>
  					</p>
  					<br>
  					<p>
	  					<input type='radio' name='question' value='q1' required><%= request.getAttribute("q1") %><br><br>
	  					<input type='radio' name='question' value='q2'><%= request.getAttribute("q2") %><br><br>
	  					<input type='radio' name='question' value='q3'><%= request.getAttribute("q3") %><br><br>
	  					<input type='radio' name='question' value='q4'><%= request.getAttribute("q4") %><br><br>
  					</p>
  					<input type='submit' value='Next'>
  				</form>
  			</div>
  		</div>
	</body>
</html>
