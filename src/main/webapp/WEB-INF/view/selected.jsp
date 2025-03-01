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
	<%@ include file="hrnav.jsp"%>
	<%@ include file="portfolio.jsp"%>
	<c:if test="${failedtosend }"> <div class="failedtosend"></div> </c:if>
	<c:if test="${offerlettersend }"> <div class="offerlettersend"></div> </c:if>
	<c:if test="${failedtoupdate }"> <div class="failedtoupdate"></div> </c:if>
	<c:if test="${requiredMentisFull}"> <div class="requiredMentisFull"></div> </c:if>
	<h1 class="heading">Selected Candidate</h1>
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
				<th>Send Offer Letter </th>
				<th>Delete</th>
				
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
					<td><a href="/mindgate/hr/selectedcandidate/sendofferletter/${list.candidateId}"><button>Send Offer Letter</button></a></td>
					<td>
						<a href="/mindgate/hr/selectedcandidate/delete/${list.candidateId}"> <button>Delete</button> </a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@ include file="footer.jsp"%>
	<script type="text/javascript" src="/script/selected.js"></script>
</body>
</html>