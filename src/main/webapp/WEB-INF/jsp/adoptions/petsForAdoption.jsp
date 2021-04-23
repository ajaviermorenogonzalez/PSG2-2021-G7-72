<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Pets for adoption">
    <h2><spring:message code = "petsForAdoption"/></h2>

    <table id="petsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;"><spring:message code = "name"/></th>
            <th style="width: 200px;"><spring:message code = "birthDate"/></th>
            <th><spring:message code = "type"/></th>
            <th style="width: 120px"><spring:message code = "owner"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="pet">
            <tr>
                <td>
                    <spring:url value="application/pet/{petId}/new" var="applicationUrl">
                        <spring:param name="petId" value="${pet.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(applicationUrl)}"><c:out value="${pet.name}"/></a>
                </td>
                <td>
                    <c:out value="${pet.birthDate}"/>
                </td>
                <td>
                    <c:out value="${pet.type}"/>
                </td>
                <td>
                    <c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
