<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><spring:message code = "welcomeMessage"/></h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/gatoLogo.jpg" htmlEscape="true" var="petsImage"/>
            <img width="800" class="img-responsive" src="${petsImage}"/>
        </div>
    </div>
    <div style="padding: 10px;">
    	<a href="?lang=es">Spanish</a>
    	<a href="?lang=en">English</a>
	</div>
</petclinic:layout>
