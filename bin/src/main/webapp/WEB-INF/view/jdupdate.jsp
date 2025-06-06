<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/hrnav.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/jdform.css">
</head>
<body>
	<%@ include file="Pmnav.jsp"%>
	<%@ include file="portfolio.jsp"%>
	<div class="main_container">
		<div class="form_group">

			<f:form action="/mindgate/projectmanager/newrequest/accept"
				modelAttribute="jobrequest">

				<h2 class="heading">Job Description</h2>

				<label for="role">Role</label>
				<f:input type="text" id="role" path="role" readonly="true" />

				<label for="location">Location</label>
				<f:input type="text" id="location" path="location"
					readonly="true" />

				<div class="skill">
					<label for="primarySkill">Primary Skill : </label>
					<input type="text" value="${jobrequest.primarySkill}" readonly="readonly">
					<f:hidden path="primarySkill"/>
				</div>
				<div class="skill">
					<label for="secondarySkill">Secondary Skill : </label>
					<input type="text" value="${jobrequest.secondarySkill}" readonly="readonly">
					<f:hidden path="secondarySkill"/>
				</div>
				<div class="skill">
					<label for="ternarySkill">Ternary Skill : </label>
					<input type="text" value="${jobrequest.ternarySkill}" readonly="readonly">
					<f:hidden path="ternarySkill"/>
					<f:hidden path="jobDescriptionId"/>
				</div>

				<label for=salary>Salary </label>
				<f:input type="text" id="salary" path="salary" />

				<label for="experience">Experience</label>
				<f:input type="text" id="experience" path="experience"
					readonly="true" />

				<label for="about">About</label>
				<f:input type="text" id="about" path="about" readonly="true" />

				<label for="about">Required candidate</label>
				<f:input type="text" id="about" path="requiredCandidate" readonly="true"  />

				<button type="submit" value="submit" id="submit">Send</button>

			</f:form>
		</div>
	</div>


	<%@ include file="footer.jsp"%>
</body>
</html>