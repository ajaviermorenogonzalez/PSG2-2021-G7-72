<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#date").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><spring:message code = "newVisit"/></h2>

        <b><spring:message code = "pet"/></b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th><spring:message code = "firstName"/></th>
                <th><spring:message code = "birthDate"/></th>
                <th><spring:message code = "type"/></th>
                <th><spring:message code = "owner"/></th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${visit.pet.name}"/></td>
                <td><petclinic:localDate date="${visit.pet.birthDate}" pattern="yyyy/MM/dd"/></td>
                <td><c:out value="${visit.pet.type.name}"/></td>
                <td><c:out value="${visit.pet.owner.firstName} ${visit.pet.owner.lastName}"/></td>
            </tr>
        </table>

        <form:form modelAttribute="visit" class="form-horizontal">
            <div class="form-group has-feedback">
                <h3><form:label path="date"><spring:message code = "date"/>&nbsp;</form:label>
           		<form:input path="date"/></h3>
                <h3><form:label path="description"><spring:message code = "description"/>&nbsp;</form:label>
           		<form:input path="description"/></h3>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${visit.pet.id}"/>
                    <button class="btn btn-default" type="submit"><spring:message code = "addVisit"/></button>
                </div>
            </div>
        </form:form>

        <br/>
        <b><spring:message code = "previousVisit"/></b>
        <table class="table table-striped">
            <tr>
                <th><spring:message code = "date"/></th>
                <th><spring:message code = "description"/></th>
            </tr>
            <c:forEach var="visit" items="${visit.pet.visits}">
                <c:if test="${!visit['new']}">
                    <tr>
                        <td><petclinic:localDate date="${visit.date}" pattern="yyyy/MM/dd"/></td>
                        <td><c:out value="${visit.description}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </jsp:body>

</petclinic:layout>
