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
<link rel="stylesheet" href="/css/bigtable.css">
</head>
<body>
	<%@ include file="hrnav.jsp"%>
	<%@ include file="portfolio.jsp"%>

	<c:if test="${datenotvalid}">
		<p>Enter valid date</p>
	</c:if>
	<h3 class="heading">Candidate</h3>

	<div class="container">
		<table style="width: 100%">
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
				<th>Add Interviewer</th>
			</tr>
			<c:if test="${empty previouscandidate}">
				<tr>
					<td colspan="10"> No Previous Candidate Available</td>
				</tr>
			</c:if>

			<c:forEach var="list" items="${previouscandidate}">
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
					<td>
						<form
							action="/mindgate/hr/home/candidate/asigninterviewer/${list.candidateId}"
							method="post">
							<select name="interviewer">
								<option>Select</option>
								<c:forEach var="item" items="${interviewer}">
									<option value="${item.employeeId}">${item.firstname}</option>
								</c:forEach>
							</select> <input type="date" placeholder="select date" name="date">
							<button type="submit">Add</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>