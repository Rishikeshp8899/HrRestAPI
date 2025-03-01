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
	
	<c:if test="${falidtoremove}">
		<div class="remove"></div>
	</c:if>
	<c:if test="${falidtoadd}">
		<div class="accept"></div>
	</c:if>

	<c:if test="${add}">
		<div class="sucessfullyadd"></div>
	</c:if>
	<c:if test="${remove}">
		<div class="sucessfullyremove"></div>
	</c:if>

	<h1 class="heading">Project Details</h1>
	<div class="container">
		<table>
			<tr>
				<th>Name</th>
				<th>Description</th>
				<th>Budget</th>
				<th>Status</th>
				<th>Available Funds</th>
			</tr>
			<tr>
				<td>${project.projectname}</td>
				<td>${project.describsion}</td>
				<td>${project.budget}</td>
				<td>${project.status}</td>
				<td>${project.availableFund}</td>
			</tr>

		</table>
	</div>


	<section>
		<h3 class="heading">New Requests</h3>

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
					<th>Accept</th>
					<th>Reject</th>
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
						<td><a
							href="/mindgate/projectmanager/newrequest/${list.jobDescriptionId}"><button>Accept</button></a></td>
						<td><a
							href="/mindgate/projectmanager/newrequest/reject/${list.jobDescriptionId}"><button>Reject</button></a></td>

					</tr>

				</c:forEach>
			</table>
		</div>
	</section>

	<%@ include file="footer.jsp"%>
	<script type="text/javascript" src="/script/pm.js"></script>
</body>
</html>