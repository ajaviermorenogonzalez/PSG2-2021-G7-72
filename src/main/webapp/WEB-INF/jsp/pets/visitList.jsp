<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="visits">





	<h2>Visit Information</h2>


    <table class="table table-striped">
        <tr>
            <th class="table_header_light_grey" >Descripcion</th>
            <td><b><c:out value="${visit.description} "/></b></td>
        </tr>
    </table>

    
      <spring:url value="{visitId}/delete" var="deleteUrl">
        	<spring:param name="visitId" value="${visit.id}"/>
    	</spring:url>
    	<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete visit</a>




</petclinic:layout>
















