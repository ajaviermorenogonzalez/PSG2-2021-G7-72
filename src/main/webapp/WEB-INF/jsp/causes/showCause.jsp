<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

    <h2>Cause Details</h2>

	<h3>Name</h3>
	<c:out value = "${cause.name}"> </c:out>
	
	<h3>Description</h3>
	<c:out value = "${cause.description}"> </c:out>
	
	<h3>Total Budget</h3>
	<c:out value = "${cause.budgetTarget}"> </c:out>
	
	<h3>Total Achieved</h3>
	<c:out value = "${cause.totalBudgetAchived}"> </c:out>
	
    <table id="roomsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Donor</th>
            <th>Date</th>
            <th>Amount</th>
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
