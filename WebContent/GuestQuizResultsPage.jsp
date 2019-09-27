<%@ page import="java.text.DecimalFormat" import="beans.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Guest Results Page</title>
<link rel="stylesheet" type="text/css" href="css/ResultStyling.css" />
</head>
<body>
	<% 
		double correct = Double.valueOf((Integer)session.getAttribute("CorrectAnswers"));
		
		QuizStructure struct  = (QuizStructure)session.getAttribute("Structure");
		double numQuestions = Double.valueOf(struct.getQuestionCount());
		
		String code = struct.getAccessCode();
		
		DecimalFormat df = new DecimalFormat("##.##");
		String grade = df.format((correct / numQuestions) * 100.00);
		session.invalidate();
		
		
	%>
	<div class="outer-container">
		<div class="inner-container">
			<h1><%=struct.getName()%> Results</h1>
			<h2>Guest Access</h2>
			<div class="gradeContainer">
				<h2>You Scored: <%=grade%>% <%=code%></h2>
			</div>
			<div class="result-container">
				
			</div>
			<form method="post" action="GuestPage.jsp"><button type="submit">Back to Guest Access</button></form>
			<br>
			<form method="post" action="EmailResultsController">
				<input type="email" placeholder="email" required>
				<button type="submit">Email My Results!</button>
			</form>
		</div>
	</div>
</body>
</html>