<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/hrnav.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/bigtable.css">
</head>
<body>
<%@ include file="interviewernav.jsp" %>
<%@ include file="portfolio.jsp" %>
<h1 class="heading"> CandidateS</h1>
	<div class="container">
		<table style="">
			<tr>
				<th>First Name</th>
				<th>Last name</th>
				<th>Age</th>
				<th>Email</th>
				<th>Experience</th>
				<th>Contact Number</th>
				<th>Primary Skill</th>
				<th>Secondary Skill</th>
				<th>ternary Skill</th>
				<th>Submit Assesment Status</th>
				
			</tr>
			<c:if test="${empty candidates}">
				<tr>
					<td colspan="10"> No Selected Candidate Available</td>
				</tr>
			</c:if>

			<c:forEach var="list" items="${candidates}">
				<tr>
					<td>${list.firstName}</td>
					<td>${list.lastName}</td>
					<td>${list.age}</td>
					<td>${list.email}</td>
					<td>${list.experience}</td>
					<td>${list.contactNumber}</td>
					<td>${list.primarySkill}</td>
					<td>${list.secondarySkill}</td>
					<td>${list.ternarySkill}</td>
					<td><a href="/mindgate/interviewer/home/assementform/${list.candidateId}"><button>Submit</button></a></td>
				
				</tr>
			</c:forEach>
		</table>
	</div>
<%@ include file="footer.jsp" %>

</body>
</html>