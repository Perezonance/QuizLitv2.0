<%@page import="beans.Category" import="beans.User" import="beans.QuizStructure" import="java.sql.ResultSet"%>
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
   		User user; 
    	String quizName = "ERROR";
    	int numQuestions = 0;
    	double time = 0.0;
       	try{
       		user = (User)session.getAttribute("User");
        	QuizStructure struct = (QuizStructure)session.getAttribute("Structure");
        	
        	
        	quizName = struct.getName();
    		numQuestions = struct.getQuestionCount();
    		time = struct.getTime();
       	}catch(Exception e){
       		System.out.println("QuizLandingPage Loading Failed:");
       		e.printStackTrace();
       	}
    %>
	
    <div class="login-form">
        <div class="form">
            <form class="quiz-form" action="GenerateNewQuizController" method="post">
                <h1><%=quizName%> Quiz</h1>
                
                <h3>Instructions</h3>
                <p align="left">Total Questions: <%=numQuestions %> Questions</p>
                <p align="left">Time Alloted: <%=time %> Minutes</p>
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