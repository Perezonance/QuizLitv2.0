<%@page import="beans.*" import="java.sql.ResultSet" import="java.util.*"%>
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
			List<Response> responses = (List<Response>)session.getAttribute("Responses");
			Question question = (Question)session.getAttribute("Question");
			Quiz quiz = (Quiz)session.getAttribute("Quiz");
			QuizStructure structure = (QuizStructure)session.getAttribute("Structure");
			int qCount = (int)session.getAttribute("qCount");
			
			String quizName = structure.getName();
			
			String questionText = question.getQuestion();
			
			String r1 = responses.get(0).getQuestion();
			String r2 = responses.get(1).getQuestion();
			String r3 = responses.get(2).getQuestion();
			String r4 = responses.get(3).getQuestion();
		%>
		<div id='container'>
			<div id='title'>
				<h1><%=quizName%> : Question #<%=qCount%></h1>
			</div>
   			<br/>
  			<div>
  				<form action='QuizTakingController' method='post'>
  					<p>
  						Question:<%=questionText%>
  					</p>
  					<br>
  					<p>
	  					<input type='radio' name='question' value='0' required><%= r1 %><br><br>
	  					<input type='radio' name='question' value='1'><%= r2 %><br><br>
	  					<input type='radio' name='question' value='2'><%= r3 %><br><br>
	  					<input type='radio' name='question' value='3'><%= r4 %><br><br>
  					</p>
  					<input type='submit' value='Next'>
  				</form>
  			</div>
  		</div>
	</body>
</html>
