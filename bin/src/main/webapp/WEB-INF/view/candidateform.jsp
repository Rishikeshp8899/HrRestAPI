<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="ISO-8859-1"> 

<link href="/css/candidateform.css" rel="stylesheet"> 
<link href="/css/main.css" rel="stylesheet"> 

</head>
<body>
	<c:if test="${invalidemail}"> <div class="invalidemail"></div> </c:if>
	
	 <%@ include file="nav.jsp" %>
		<div class="main_container">
			<div class="form_container">
				<h2 class="apply">Apply</h2>
				<f:form action="/mindgate/career/savecandidate/otpsend" modelAttribute="candidate"
					method="post">
					<label for="name">First Name</label>
					<f:input path="firstName" id="name" />
					
					<label for="name">Last Name</label>
					<f:input path="lastName" id="name" />
	
					<label for="age">Age</label>
					<f:input path="age" id="name"  />
	
					<label for="experience">Experience</label>
					<f:input path="experience" id="" />
	
					<label for="email">Email</label>
					<f:input path="email" id="email" />
	
					<label for="contactNumber">Contact No</label>
					<f:input path="contactNumber" id="contactNumber" />
					
					<div class="skill">
					<label for="primarySkill">Primary Skill : </label>
					<f:select path="primarySkill" class="select">
						<option value="">select Skill</option>
						<option value="Spring boot">Spring Boot</option>
						<option value="java">java</option>
						<option value="Angular">Angular</option>
						<option value="JavaScript">JavaScript</option>
						<option value="sql">Sql</option>
						<option value="plsql">Plsql</option>
						<option value="JavaScript">Hibernate</option>
						<option value="webtech">Web Technology</option>
					</f:select>
					</div>
					<div class="skill">
					<label for="secondarySkill">Secondary Skill : </label>
					<f:select path="secondarySkill" class="select">
						<option value="">select Skill</option>
						<option value="Spring boot">Spring Boot</option>
						<option value="java">java</option>
						<option value="Angular">Angular</option>
						<option value="JavaScript">JavaScript</option>
						<option value="sql">Sql</option>
						<option value="plsql">Plsql</option>
						<option value="JavaScript">Hibernate</option>
						<option value="webtech">Web Technology</option>
					</f:select>
					</div>
					<div class="skill">
					<label for="ternarySkill">Ternary Skill : </label>
					<f:select path="ternarySkill" class="select">
						<option value="">select Skill</option>
						<option value="Spring boot">Spring Boot</option>
						<option value="java">java</option>
						<option value="Angular">Angular</option>
						<option value="JavaScript">JavaScript</option>
						<option value="sql">Sql</option>
						<option value="plsql">Plsql</option>
						<option value="JavaScript">Hibernate</option>
						<option value="webtech">Web Technology</option>
					</f:select>
					</div>
					<button type="Submit" class="submit">Submit</button>
				</f:form>
			</div>
		</div>
	<%@ include file="footer.jsp"%>
	<script type="text/javascript" src="/script/candidate.js"></script>
</body>
</html>