<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<%@ include file="Pmnav.jsp"%>
	<%@ include file="portfolio.jsp"%>
	<c:if test="${reuirementfull}"> <div class="reuirementfull"></div> </c:if>
	<c:if test="${sendedtohr}"> <div class="sendedtohr"></div> </c:if>
	<c:if test="${failedtosend}"> <div class="failedtosend"></div> </c:if>
		<section>
		<h3 class="heading">Approved Requests</h3>

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
					<th>Add from Work Bench</th>
					<th>Send to hr</th>
				</tr>
				<c:forEach var="list" items="${AcceptedRequest}">
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
						<td><a href="/mindgate/projectmanager/approvedrequests/workbench/${list.jobDescriptionId}"> <button> Add</button> </a></td>
						<td><a href="/mindgate/projectmanager/approvedrequests/sendtohr/${list.jobDescriptionId}"> <button> Send</button> </a></td>
					</tr>

				</c:forEach>
			</table>
		</div>
	</section>
		
	
	<%@ include file="footer.jsp"%>
	<script type="text/javascript" src="/script/approved.js"></script>
</body>
</html>