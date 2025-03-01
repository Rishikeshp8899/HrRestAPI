<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/carrier.css">
<link rel="stylesheet" href="/css/main.css">
</head>
<body>
	<%@ include file="nav.jsp" %>
	
	<c:if test="${addded}">
		<div class="added"></div>
	</c:if>
	
	<c:if test="${notadded}"> 
	<div class="notadded"></div> 
	</c:if>
    <main>
        <section id="product-overview">
            <h1>Careers</h1>
            <hr class="line">
            <h2>Join us, in delivering Innovations.</h2>
            <p>At Mindgate we firmly believe in Delivering Innovations. Our teams have always delivered tailor-made solutions which address the needs of today’s top businesses globally. Our professionals work collaboratively to design, integrate, manage & evolve all business applications with high quality, greater speed and operational efficiency.</p>
        </section>
        <section id="${plans}">
            <c:if test="empty">
            	<h1>Not Requirement posted</h1>
            </c:if>
            <div class="card-group">
            	<c:forEach var="jd" items="${jdList}">
		            	<div class="card">
		                    <div class="title">
		                        <h3>${jd.role}</h3>
		                    </div>
		                    <div class="columns">
		
		                        <div class="leftColumn">
		
		                            <div class="inner_row">
		                                <h4>Location :  ${jd.location}</h4>
		                            </div>
		                            <div class="inner_row">
		                                <h4>Expirence :  ${jd.experience} Year</h4>
		                            </div>
		                        </div>
		
		                        <div class="rightColumn">
		                            <div class="inner_row">
		                                <h4>Skills :  ${jd.primarySkill}</h4>
		                            </div>
		                            <div class="inner_row">
		                                <h4>Salary :  ${jd.salary }LPA</h4>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="about">
		                       
		                        <h6>${jd.about}</h6>
		
		                    </div>
		                
		                
		                    <div class="button">
		                    	
		                       	<a href="/mindgate/career/candidatedetails/${jd.jobDescriptionId}" class="submit"><button>Apply Now</button></a> 
		                    </div>
	            	</div>
	            </c:forEach>
                                
                
            </div>
           
        </section>
        
    </main>
    
    <%@ include file="footer.jsp" %> 
    <script type="text/javascript" src="/script/carrier.js"></script>
</body>

</html>