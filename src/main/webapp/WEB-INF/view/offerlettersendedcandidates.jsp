<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
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
	<c:if test="${requiredmentFull }"> <div class="requiredmentFull"></div> </c:if>
	<c:if test="${failedtodelete }"> <div class="failedtodelete"></div> </c:if>
	
	<h1 class="heading">Selected Candidate</h1>
	<div class="container">
		<table style="width: 100%">
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Age</th>
				<th>Email</th>
				<th>Experience</th>
				<th>Contact Number</th>
				<th>Primary Skill</th>
				<th>Secondary Skill</th>
				<th>ternary Skill</th>
				<th>Add to Employee</th>
				<th>Delete</th>

			</tr>
			<c:if test="${empty candidates}">
				<tr>
					<td colspan="10">No  Candidate Available</td>
				</tr>
			</c:if>

			<c:forEach var="candidate" items="${candidates}">
				<tr>
					<tr>
					<td>${candidate.firstName}</td>
					<td>${candidate.lastName}</td>
					<td>${candidate.age}</td>
					<td>${candidate.email}</td>
					<td>${candidate.experience}</td>
					<td>${candidate.contactNumber}</td>
					<td>${candidate.primarySkill}</td>
					<td>${candidate.secondarySkill}</td>
					<td>${candidate.ternarySkill}</td>
						<td><a href="/mindgate/hr/offerlettersendedcandidate/addtoemployee/${candidate.candidateId }">
								<button>Add</button>
						</a></td>
						<td><a href="/mindgate/hr/offerlettersendedcandidate/delete/${candidate.candidateId }">
								<button>Delete</button>
						</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%@ include file="footer.jsp"%>
	<script type="text/javascript" src="/script/offerlettr.js"></script>
</body>
</html>