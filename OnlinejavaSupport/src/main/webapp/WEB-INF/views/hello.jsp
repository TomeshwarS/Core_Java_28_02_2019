<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>WELCOME TO JAVA ONLINE SUPPORT</h1>

	
${question_opn.queSerialNo} . 	${question_opn.queName}  
	<br>
	<br>
<form action="next">
  <input type="radio" name="optionId" value="${question_opn.optionId1}" >  ${question_opn.optionName1}<br>
  <input type="radio" name="optionId" value="${question_opn.optionId2}" >  ${question_opn.optionName2}<br>
  <input type="radio" name="optionId" value="${question_opn.optionId3}" >  ${question_opn.optionName3}<br>
  <input type="radio" name="optionId" value="${question_opn.optionId4}" >  ${question_opn.optionName4}<br>
<input type="submit" value="Next">
</form>
</body>
</html>