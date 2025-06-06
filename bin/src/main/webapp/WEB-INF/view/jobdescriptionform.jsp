<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<%@ include file="tlnavigation.jsp"%>
	<%@ include file="portfolio.jsp"%>
	
	<div class="main_container">
		<div class="form_group">
			<f:form action="/mindgate/teamlead/jobrequest/addrequirement" modelAttribute="jobrequestobject">

				<h2 class="heading">Job Description</h2>

				<label for="role">Role</label>
				<f:input type="text" id="role" path="role" />

				<label for="location">Location</label>
				<f:input type="text" id="location" path="location" />

				<div class="skill">
					<label for="primarySkill">Primary Skill : </label>
					<f:select path="primarySkill" class="select">
						<option value="">select Skill</option>
						<option value="Spring boot">Spring Boot</option>
						<option value="java">Java</option>
						<option value="Angular">Angular</option>
						<option value="JavaScript">JavaScript</option>
						<option value="sql">Sql</option>
						<option value="plsql">Plsql</option>
						<option value="JavaScript">Hibernate</option>
						<option value="webtech">Web Technology</option>
						<option value="Automation">Automation testing</option>
						<option value="manual_testing">Manual testing</option>
					</f:select>
				</div>
				<div class="skill">
					<label for="secondarySkill">Secondary Skill : </label>
					<f:select path="secondarySkill" class="select">
						<option value="">select Skill</option>
						<option value="Spring boot">Spring Boot</option>
						<option value="java">Java</option>
						<option value="Angular">Angular</option>
						<option value="JavaScript">JavaScript</option>
						<option value="sql">Sql</option>
						<option value="plsql">Plsql</option>
						<option value="JavaScript">Hibernate</option>
						<option value="webtech">Web Technology</option>
						<option value="Automation">Automation testing</option>
						<option value="manual_testing">Manual testing</option>
					</f:select>
				</div>
				<div class="skill">
					<label for="ternarySkill">Ternary Skill : </label>
					<f:select path="ternarySkill" class="select">
						<option value="">select Skill</option>
						<option value="Spring boot">Spring Boot</option>
						<option value="java">Java</option>
						<option value="Angular">Angular</option>
						<option value="JavaScript">JavaScript</option>
						<option value="sql">Sql</option>
						<option value="plsql">Plsql</option>
						<option value="JavaScript">Hibernate</option>
						<option value="webtech">Web Technology</option>
						<option value="Automation">Automation testing</option>
						<option value="manual_testing">Manual testing</option>
					</f:select>
				</div>


				<label for="experience">Experience</label>
				<f:input type="text" id="experience" path="experience" />

				<label for="about">About</label>
				<f:input type="text" id="about" path="about" />

				<label for="about">Required candidate</label>
				<f:input type="text" id="about" path="requiredCandidate" />

				<button type="submit" value="submit" id="submit" >Send</button>

			</f:form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>