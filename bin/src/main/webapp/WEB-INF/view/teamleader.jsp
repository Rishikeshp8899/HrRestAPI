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
	<%@ include file="tlnavigation.jsp"%>
	<%@ include file="portfolio.jsp"%>
	<h1 class="heading">Project Details</h1>
	<div class="container">
		<c:if test="${alertMessage}">
			<%-- <div class="alert alert-${alertMessage.type}">
				${alertMessage.message}</div> --%>
			<div class="alert"></div>
		</c:if>

		<c:if test="${failedtodend}">
			<div class="failedtodend"></div>
		</c:if>
		<table>
			<tr>
				<th>Name</th>
				<th>Description</th>
				<th>Budget</th>
				<th>Status</th>
				<th>Available Funds</th>
				<th>Add Job Requirement</th>
			</tr>
			<tr>
				<td>${project.projectname}</td>
				<td>${project.describsion}</td>
				<td>${project.budget}</td>
				<td>${project.status}</td>
				<td>${project.availableFund}</td>
				<td><a
					href="/mindgate/teamlead/jobrequest/${project.projectId}"
					class="add">
						<button>Add</button>
				</a></td>
			</tr>

		</table>

	</div>
	<section></section>
	<%@ include file="footer.jsp"%>
	<script type="text/javascript" src="/script/tl.js"></script>
</body>
</html>