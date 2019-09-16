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
			Question question = (Question)session.getAttribute("Question");
			Quiz quiz = (Quiz)session.getAttribute("Quiz");
			QuizStructure structure = (QuizStructure)session.getAttribute("Structure");
			String quizName = structure.getName();
			
			String questionText = question.getQuestion();
			
			String r1 = question.getCorrectAnswer();
			String r2 = question.getWrongAnswer1();
			String r3 = question.getWrongAnswer2();
			String r4 = question.getWrongAnswer3();
		%>
		<div id='container'>
			<div id='title'>
				<h1><%=quizName%> : Question #</h1>
			</div>
   			<br/>
  			<div>
  				<form action='QuizTakingController' method='post'>
  					<p>
  						Question:<%=questionText%>
  					</p>
  					<br>
  					<p>
	  					<input type='radio' name='question' value='q1' required><%= r1 %><br><br>
	  					<input type='radio' name='question' value='q2'><%= r2 %><br><br>
	  					<input type='radio' name='question' value='q3'><%= r3 %><br><br>
	  					<input type='radio' name='question' value='q4'><%= r4 %><br><br>
  					</p>
  					<input type='submit' value='Next'>
  				</form>
  			</div>
  		</div>
	</body>
</html>
