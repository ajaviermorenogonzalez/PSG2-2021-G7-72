<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %> <%@
taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="donations">
	<h2>
		<spring:message code="donations" />
	</h2>
	
	<spring:url value="/donations/new/{causeId}" var="url">
                        <spring:param name="causeId" value="${causeId}"/>
    </spring:url>
	
	<form:form action="${url}" modelAttribute="donation" class="form-horizontal"
		id="add-donation-form" method = "POST">
		<div class="form-group has-feedback">
		
		<input id="causeId" name="causeId" type="hidden" value="${causeId}">		
		
			<h3>
				<form:label path="donorName">
					<spring:message code="donorName" />&nbsp;</form:label>
				<form:input path="donorName" />
			</h3>
			<h3>
				<form:label path="amount">
					<spring:message code="amount" />&nbsp;</form:label>
				<form:input path="amount" />
			</h3>
			
		</div>

		<button class="btn btn-default" type="submit">Make a Donation</button>

	</form:form>
</petclinic:layout>
