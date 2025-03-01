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
	
	<c:if test="${notadd}">
		<div class="notadd"></div>
	</c:if>
	
	<c:if test="${added}">
		<div class="added"></div>
	</c:if>
	
	<h1 class="heading">Work Bench</h1>
	<section>

		<div class="container">

			<table>
				<tr>
					<th>First name</th>
					<th>Last name</th>
					<th>Age</th>
					<th>Role</th>
					<th>Designation</th>
					<th>Primary Skill</th>
					<th>Secondary Skill</th>
					<th>Ternary Skill</th>
					<th>Add to Project</th>

				</tr>
				<c:forEach var="e" items="${employeelist}">
					<tr>
						<td>${e.firstname}</td>
						<td>${e.lastname}</td>
						<td>${e.age}</td>
						<td>${e.role}</td>
						<td>${e.designation}</td>
						<td>${e.primarySkill}</td>
						<td>${e.secondarySkill}</td>
						<td>${e.ternarySkill}</td>
						<td><a href="/mindgate/projectmanager/approvedrequests/addemptoproject/${e.employeeId}">
								<button>Add</button>
						</a></td>
					</tr>

				</c:forEach>
			</table>

		</div>

	</section>

	<%@ include file="footer.jsp"%>
	<script type="text/javascript" src="/script/workbench.js"></script>
</body>
</html>