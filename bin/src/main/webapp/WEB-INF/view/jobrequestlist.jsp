<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/hrnav.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/teamlead.css">
</head>
<body>
<%@ include file="tlnavigation.jsp" %>
<%@ include file="portfolio.jsp" %>

<h1 class="heading">Job Request List</h1>
<div class="container">

<table>
	<tr>
		<th>Role</th>
		<th>Salary</th>
		<th>Location</th>
		<th>Primary Skill</th>
		<th>Secondary Skill</th>
		<th>ternary Skill</th>
		<th>Experience</th>
		<th>About</th>
		<th>Required Candidate</th>
		<th>Status</th>
		
	</tr>
	<c:forEach var="list" items="${jobDescriptionList}">
		<tr>
			<td>${list.role}</td>
			<td>${list.salary}</td>
			<td>${list.location}</td>
			<td>${list.primarySkill}</td>
			<td>${list.secondarySkill}</td>
			<td>${list.ternarySkill}</td>
			<td>${list.experience}</td>
			<td>${list.about}</td>
			<td>${list.requiredCandidate}</td>
			<td>${list.status}</td>
			<!-- <td><a href="" class="add"> <button>Add</button> </a></td> -->
		</tr>
		
	</c:forEach>
</table>

</div>


<%@ include file="footer.jsp" %>
</body>
</html>