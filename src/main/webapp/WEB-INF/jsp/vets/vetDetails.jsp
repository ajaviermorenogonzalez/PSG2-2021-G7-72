<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">

    <h2>Vet Information</h2>


    <table class="table table-striped">
        <tr>
            <th class="table_header_light_grey" >Name</th>
            <td><b><c:out value="${vet.firstName} ${vet.lastName}"/></b></td>
        </tr>
    </table>

    <spring:url value="{vetId}/edit" var="editUrl">
        <spring:param name="vetId" value="${vet.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit vet</a>
    
      <spring:url value="{vetId}/delete" var="deleteUrl">
        	<spring:param name="vetId" value="${vet.id}"/>
    	</spring:url>
    	<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete vet</a>

</petclinic:layout>
