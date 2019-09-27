<%@ page import="java.text.DecimalFormat" import="beans.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Quiz Results Page</title>
<link rel="stylesheet" type="text/css" href="css/ResultStyling.css" />
</head>
<body>
	<% 
		double correct = Double.valueOf((Integer)session.getAttribute("CorrectAnswers"));
	
		User user = (User)session.getAttribute("User");
		
		QuizStructure struct  = (QuizStructure)session.getAttribute("Structure");
		double numQuestions = Double.valueOf(struct.getQuestionCount());
		String code = struct.getAccessCode();
		
		DecimalFormat df = new DecimalFormat("##.##");
		String grade = df.format((correct / numQuestions) * 100.00);
		
		
	%>
	<div class="outer-container">
		<div class="inner-container">
			<h1><%=struct.getName()%> Results</h1>
			<h2><%= user.getfName()%></h2>
			<div class="gradeContainer">
				<div class="grade-text"><h2>Grade: <%=grade%>%</h2></div>
				<div class="code-text"><h2>Code: <%=code%></h2></div>
			</div>
			<div class="result-container">
				
			</div>
			<form method="post" action="UserDashboard.jsp"><button type="submit">Back to Dashboard</button></form>
			<br>
			<form method="post" action="EmailResultsController"><button type="submit">Email My Results!</button></form>
		</div>
	</div>
</body>
</html>