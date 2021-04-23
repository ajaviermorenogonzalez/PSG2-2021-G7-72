<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="application">
    <h2><spring:message code = "adoptionApplication"/>
    </h2>
    <spring:url value="/adoptions/application/pet/{petId}/new" var="applicationUrl">
                        <spring:param name="petId" value="${petId}"/>
    </spring:url>
    <form:form modelAttribute="application" class="form-horizontal" action="${applicationUrl}">
        <input type="hidden" name="id" value="${application.id}"/>
        <input type="hidden" name="state" value="${application.state}"/>
        <label for="description">Description</label>
        <div class="control-group">
        <input name="description" value="${description}"/>
        </div>
        
        <div class="control-group">
        <label for="selectOwner">Owner</label>
        <form:select id="selectOwner" class="form-control" path="owner" label= "Owner " size="5">
        <c:forEach items="${owner}" var="o">
        	<c:set var="nameO" value="${o.firstName}"/>
           <form:option name="owner" value="${o}"> <c:out value="${nameO}"></c:out> </form:option>
         </c:forEach>
        </form:select>
        </div>
                 
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">Submit application</button>
            </div>
        </div>
    </form:form>
    <c:if test="${!room['new']}">
     </c:if>
</petclinic:layout>
