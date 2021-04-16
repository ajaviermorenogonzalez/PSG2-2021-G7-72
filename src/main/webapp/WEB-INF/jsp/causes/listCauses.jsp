<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="petclinic" tagdir="/WEB-INF/tags" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %>


<petclinic:layout pageName="causes">
	<h2>Causes</h2>

	<table id="roomsTable" class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Budget Target</th>
				<th>Total Budget Achieved</th>
				<th>Make a Donation</th>
				<th>See Details</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${causes}" var="cause">
				<tr>
					<td><c:out value="${cause.name}" /></td>
					<td><c:out value="${cause.budgetTarget}" /></td>
					<td><c:out value="${cause.totalBudgetAchived}" /></td>
					<td><c:choose>
							<c:when test="${cause.isClosed}">
								<c:out value="¡ Hemos alcanzado la meta !">
								</c:out>
							</c:when>
							<c:otherwise>

								<spring:url value="/donations/new/{causeId}" var="donationUrl">
									<spring:param name="causeId" value="${cause.id}" />
								</spring:url>

								<a href="${fn:escapeXml(donationUrl)}"><c:out value="Donate" /></a>

							</c:otherwise>
						</c:choose></td>
					<td><spring:url value="/causes/{causeId}"
							var="causeDetailsUrl">
							<spring:param name="causeId" value="${cause.id}" />
						</spring:url> <a href="${fn:escapeXml(causeDetailsUrl)}"><c:out value="DETAILS" /></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />

	<a class="btn btn-default"
		href='<spring:url value="/causes/new" htmlEscape="true"/>'>Create
		Cause</a>


</petclinic:layout>