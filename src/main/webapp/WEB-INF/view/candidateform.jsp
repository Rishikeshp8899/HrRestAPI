<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- <link href="css/candidateform.css" rel="stylesheet"> -->


 <meta charset="ISO-8859-1"> 

<style type="text/css">
	*
{
	font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

.main_container
{
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	
	
}
.form_container
{
	padding: 10px;
	border: none;
	border-radius: 8px;
	background-color: #7569b649;
	width: 30%;
	margin: auto;
	
}
.form_container form input
{
	display: block;
	width: 98%;
	height: 1.8rem;
	border: none;
	border-radius: 5px;
	margin: 10px 0px;
}

select {
	
	display: block;
}
.apply
{
	text-align: center;
	font-weight: bolder;
}




</style>
<link href="css/main.css" rel="stylesheet">
</head>
<body>
	<%@ include file="nav.jsp" %>
		<div class="main_container">
			<div class="form_container">
				<h2 class="apply">Apply</h2>
				<f:form action="/savecandidate" modelAttribute="candidate"
					method="post">
					<label for="name">Name</label>
					<f:input path="name" id="name" />
	
					<label for="age">Age</label>
					<f:input path="age" id="name"  />
	
					<label for="experience">Experience</label>
					<f:input path="experience" id="" />
	
					<label for="email">Email</label>
					<f:input path="email" id="email" />
	
					<label for="contactNumber">Contact No</label>
					<f:input path="contactNumber" id="contactNumber" />
					
					<label for="primarySkill">Primary Skill</label>
					<f:select path="primarySkill">
						<option value="">select Skill</option>
						<option value="Spring boot">Spring Boot</option>
						<option value="core java">core java</option>
						<option value="Angular">Angular</option>
						<option value="JavaScript">JavaScript</option>
					</f:select>
	
					<label for="secondarySkill">Secondary Skill</label>
					<f:select path="secondarySkill">
						<option value="">select Skill</option>
						<option value="Spring boot">Spring Boot</option>
						<option value="core java">core java</option>
						<option value="Angular">Angular</option>
						<option value="JavaScript">JavaScript</option>
					</f:select>
	
					<label for="ternarySkill">Ternary Skill</label>
					<f:select path="ternarySkill">
						<option value="">select Skill</option>
						<option value="Spring boot">Spring Boot</option>
						<option value="core java">core java</option>
						<option value="Angular">Angular</option>
						<option value="JavaScript">JavaScript</option>
					</f:select>
	
					<button type="Submit">Submit</button>
				</f:form>
			</div>
		</div>
	<%@ include file="footer.jsp"%>
</body>
</html>