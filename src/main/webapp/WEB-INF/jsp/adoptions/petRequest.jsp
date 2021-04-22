<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="petRequest">
    <h2>AdoptionRequest</h2>

    <table id="adoptionsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${adoptions}" var="adoption">
            <tr>
                <td>
                    <c:out value="${adoption.owner.firstName}  ${adoption.owner.lastName}"/>
                </td>

                <td>
                    <c:out value="${adoption.description}"/>
                </td>
                <td>
                	<spring:url value="/adoptions/request/true/{adoptionId}" var="petAdoptionTrue">
                    	<spring:param name="adoptionId" value="${adoption.id}"/>
                 	</spring:url>
                 	<a href="${fn:escapeXml(petAdoptionTrue)}">Aceptar</a>   
                 
                	<spring:url value="/adoptions/request/false/{adoptionId}" var="petAdoptionFalse">
                    	<spring:param name="adoptionId" value="${adoption.id}"/>
                 	</spring:url>
                 	<a href="${fn:escapeXml(petAdoptionFalse)}">Rechazar</a>            
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <h2>Previous adoption requests</h2>    
    
    <table id="RejectedadoptionsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>State</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${adoptionsRejected}" var="adoptionsRejected">
            <tr>
                <td>
                    <c:out value="${adoptionsRejected.owner.firstName}  ${adoptionsRejected.owner.lastName}"/>
                </td>

                <td>
                    <c:out value="${adoptionsRejected.description}"/>
                </td>
                <td>
                     <c:out value="${adoptionsRejected.state}"/>           
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
