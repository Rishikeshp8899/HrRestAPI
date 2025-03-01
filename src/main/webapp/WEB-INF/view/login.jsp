<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="/css/main.css">
</head>
<body>
	
	<%@ include file="nav.jsp" %>

	<div class="main">
		<div class="container">
		<c:if test="${invalidId}">
			<p class="err">Enter Valid User name</p>
		</c:if>
		<c:if test="${invalidCredentials}">
			<p class="err">Invalid Credentials</p>
		</c:if>
		<c:if test="${notAuthoried}">
			<p class="err">You are Not Authorized User </p>
		</c:if>
			<h1>Login</h1>
			<Form action="/mindgate/login/validate" method="post">
				<label for="username">User name</label> 
				<input type="text"name="username" id="username"> 
				<label for="password">Password</label>
				<input type="password" name="password" id="password">
				<button type="submit" class="submit">submit</button>
			</Form>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
	<script type="text/javascript" src="/script/login.js"></script> 
</body>
</html>