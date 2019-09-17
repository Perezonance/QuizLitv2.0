<%@ page import="java.text.DecimalFormat" import="beans.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Guest Results Page</title>
</head>
<body>
	<% 
		double correct = Double.valueOf((Integer)session.getAttribute("CorrectAnswers"));
		
		QuizStructure struct  = (QuizStructure)session.getAttribute("Structure");
		double numQuestions = Double.valueOf(struct.getQuestionCount());
		System.out.println(numQuestions);
		System.out.println(numQuestions);
		
		DecimalFormat df = new DecimalFormat("##.##");
		String grade = df.format((correct / numQuestions) * 100.00);
		session.invalidate();
		
		
	%>
	<h1>You Scored: <%=grade%></h1>
	<a href="GuestPage.jsp"><button>Back to Guest Access Page</button></a>
</body>
</html>