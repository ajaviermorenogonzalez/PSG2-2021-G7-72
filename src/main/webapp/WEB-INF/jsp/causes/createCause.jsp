<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %> <%@
taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
	<h2>
		<spring:message code="causes" />
	</h2>
	<form:form modelAttribute="cause" class="form-horizontal"
		id="add-cause-form">
		<div class="form-group has-feedback">
			<h3>
				<form:label path="name">
					<spring:message code="name" />&nbsp;</form:label>
				<form:input path="name" />
			</h3>
			<h3>
				<form:label path="description">
					<spring:message code="description" />&nbsp;</form:label>
				<form:input path="description" />
			</h3>
			<h3>
				<form:label path="budgetTarget">
					<spring:message code="budgetTarget" />&nbsp;</form:label>
				<form:input path="budgetTarget" />
			</h3>
			<h3>
				<form:label path="organization">
					<spring:message code="organization" />&nbsp;</form:label>
				<form:input path="organization" />
			</h3>
		</div>

		<button class="btn btn-default" type="submit">Add Cause</button>

	</form:form>
</petclinic:layout>
