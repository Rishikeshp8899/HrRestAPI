<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="/css/main.css">
<body>
	<%@ include file="nav.jsp"%>
	<div class="main">
		<div class="container">
			<form action="/mindgate/career/savecandidate/verify/verifyotp "method="post">
			<c:if test="${invalidotp}"> <div style="color: red;">Invalid otp</div> </c:if>
				<label>Enter Otp </label> <input type="number"
					placeholder="Enter otp" name="otp">
				<button type="submit" class="">verify</button>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>