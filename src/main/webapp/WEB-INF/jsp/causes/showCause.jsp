<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
    <spring:message code="cause" /> : <c:out value = "${cause.name}"> </c:out>
	<hr>
	<spring:message code="description" /> : <c:out value = "${cause.description}"> </c:out>
	<hr>
	<spring:message code="budgetTarget" /> : <c:out value = "${cause.budgetTarget}"> </c:out>
	<hr>
	<spring:message code="totalBudgetAchieved" /> : <c:out value = "${cause.totalBudgetAchived}"> </c:out>
	<hr>
    <table id="roomsTable" class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="donor" /></th>
            <th><spring:message code="date" /></th>
            <th><spring:message code="amount" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${donations}" var="donation">
            <tr>
                <td>
                    <c:out value="${donation.donorName}"/>
                </td>
                <td>
                    <c:out value="${donation.date}"/>
                </td>
                <td>
                    <c:out value="${donation.amount}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>
