<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <h2><spring:message code = "owner"/>
    </h2>
    <form:form modelAttribute="owner" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
           	<h3><form:label path="firstName"><spring:message code = "firstName"/>&nbsp;</form:label>
           	<form:input path="firstName"/></h3>
           	<h3><form:label path="lastName"><spring:message code = "lastName"/>&nbsp;</form:label>
           	<form:input path="lastName"/></h3>
           	<h3><form:label path="address"><spring:message code = "address"/>&nbsp;</form:label>
           	<form:input path="address"/></h3>
           	<h3><form:label path="city"><spring:message code = "city"/>&nbsp;</form:label>
           	<form:input path="city"/></h3>
           	<h3><form:label path="telephone"><spring:message code = "telephone"/>&nbsp;</form:label>
           	<form:input path="telephone"/></h3>
           	<h3><form:label path="user.username"><spring:message code = "username"/>&nbsp;</form:label>
           	<form:input path="user.username"/></h3>
           	<h3><form:label path="user.password"><spring:message code = "password"/>&nbsp;</form:label>
           	<form:input path="user.password"/></h3>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${owner['new']}">
                        <button class="btn btn-default" type="submit">Add Owner</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit"><spring:message code = "editOwner"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
