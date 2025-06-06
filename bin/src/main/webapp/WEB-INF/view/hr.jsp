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
	<c:if test="${failedtopost }"> <div class="failedtopost"></div> </c:if>
	<c:if test="${datenotvalid }"> <div class="datenotvalid"></div> </c:if>
	<c:if test="${jobposted }"> <div class="jobposted"></div> </c:if>
	<c:if test="${assigned }"> <div class="assigned"></div> </c:if>
	<h2 class="heading">Request</h2>
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
				<th>post the job</th>
				<th>previously Apply Candidate</th>
				<th>New Apply Candidate</th>
				<th>Selected Candidate</th>
			</tr>
			<c:forEach var="list" items="${jobrequestList}">
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
					<td>
					<c:if test="${list.postJobDescription eq 'notPost'}">
						<a href="/mindgate/hr/home/postjob/${list.jobDescriptionId}"> <button>post</button> </a>
					</c:if>
					
					<c:if test="${list.postJobDescription eq 'Post'}">
						 posted
					</c:if>
					</td>
					
					<td>
						<a href="/mindgate/hr/home/previouscandidate/${list.primarySkill}/${list.jobDescriptionId}"> <button>View</button> </a> 
					</td>
					
					<td>
						<c:if test="${list.postJobDescription eq 'Post'}">
						 <a href="/mindgate/hr/home/newapplycandidate/${list.jobDescriptionId}"> <button>View</button> </a> 
						</c:if>
						
						<c:if test="${list.postJobDescription eq 'notPost'}">
							Not posted
						</c:if>
					</td>
					<td><a href="/mindgate/hr/selectedcandidate/${list.jobDescriptionId}">
						<button>selected</button>
					</a> </td>
				</tr>

			</c:forEach>
		</table>
	</div>



	<%@ include file="footer.jsp"%>
	<script type="text/javascript" src="/script/hr.js"></script>
</body>
</html>