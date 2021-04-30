<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            
        </h2>
        <form:form modelAttribute="pet"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${pet.id}"/>
            <div class="form-group has-feedback">
                <div class="form-group">
                <h3><spring:message code = "owner"/>&nbsp;
                <c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/></h3>
                    
            	<h3><form:label path="name"><spring:message code = "firstName"/>&nbsp;</form:label>
           		<form:input path="name" /></h3>
           		<h3><form:label path="birthDate"><spring:message code = "birthDate"/>&nbsp;</form:label>
           		<form:input path="birthDate"/></h3>
           
           		<h3><form:label path="type"><spring:message code = "type"/>&nbsp;</form:label>
           		<select name="type">
  					<option value="bird"><spring:message code = "bird"/></option>
  					<option value="cat"><spring:message code = "cat"/></option>
  					<option value="dog"><spring:message code = "dog"/></option>
  					<option value="hamster"><spring:message code = "hamster"/></option>
  					<option value="lizard"><spring:message code = "lizard"/></option>
  					<option value="snake"><spring:message code = "snake"/></option>
				</select></h3>
            	</div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${pet['new']}">
                            <button class="btn btn-default" type="submit"><spring:message code = "addPet"/></button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit"><spring:message code = "updatePet"/></button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!pet['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>
