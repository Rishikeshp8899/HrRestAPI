<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/hrnav.css">
<link rel="stylesheet" href="/css/main.css">
<link href="/css/candidateform.css" rel="stylesheet"> 
</head>
<body>
	<%@ include file="interviewernav.jsp"%>
	<%@ include file="portfolio.jsp"%>
	<c:if test="${assesmentsaved }"> <div class="assesmentsaved"></div> </c:if>
	<c:if test="${ failedtosave}"> <div class="failedtosave"></div> </c:if>
	
	<div class="main_container">
			<div class="form_container">
				<h2 class="apply">Apply</h2>
				<f:form action="/mindgate/interviewer/home/assementform/save" modelAttribute="assesment"
					method="post">
				
					<label for="">Name</label>
					<input id=""  value="${candidate.firstName}" readonly="readonly"/>
					
					<div class="skill">
					<label for="">Technical Skill : </label>
					<f:select path="techSkill" class="select">
						<option value="">select</option>
						<option value="good">Good</option>
						<option value="average">Average</option>
						<option value="bad">Bad</option>
					</f:select>
					</div>
					
					<div class="skill">
					<label for="secondarySkill">Communication : </label>
					<f:select path="communication" class="select">
						<option value="">select</option>
						<option value="good">Good</option>
						<option value="average">Average</option>
						<option value="bad">Bad</option>
					</f:select>
					</div>
					
					<div class="skill">
					<label for="ternarySkill">Soft Skill</label>
					<f:select path="softSkills" class="select">
						<option value="">select</option>
						<option value="good">Good</option>
						<option value="average">Average</option>
						<option value="bad">Bad</option>
					</f:select>
					</div>
					
					<div class="skill">
					<label for="ternarySkill">Status</label>
					<f:select path="status" class="select">
						<option value="selected">Selected</option>
						<option value="notSelected">Not Selected</option>
					</f:select>
					</div>
					
					
					
					<button type="Submit" class="submit">Submit</button>
				</f:form>
			</div>
		</div>
	
	<%@ include file="footer.jsp" %>
	<script type="text/javascript" src="/script/assesment.js"></script>
</body>
</html>