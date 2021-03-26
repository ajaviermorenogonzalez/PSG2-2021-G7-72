<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="rooms">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#firstDate").datepicker({dateFormat: 'yy/mm/dd'});
                $("#lastDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    
    <jsp:body>
    
    <h2>
        <c:if test="${room['new']}">Booking </c:if> Details
    </h2>
    
    <form:form modelAttribute="room"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${room.id}"/>
                <petclinic:inputField label="First Date" name="firstDate"/>
                <petclinic:inputField label="Last Date" name="lastDate"/>
                
                 <div class="control-group">
                 <label for="selectOwner">Choose an Owner</label>
                 <form:select id="selectOwner" class="form-control" path="owner" label= "Owner " size="5">
                 <c:forEach items="${owner}" var="own">
                 	<c:set var="name" value="${own.firstName}"/>
                    <form:option name="owner" value="${own}"> <c:out value="${name}"></c:out> </form:option>
                  </c:forEach>
                 </form:select>
                </div>
                
                <br/>
                
                <div class="control-group">
                 <label for="selectPet">Choose a Pet</label>
                 <form:select id="selectPet" class="form-control" path="pet" label= "Pet " size="5">
                 <c:forEach items="${pet}" var="p">
                 	<c:set var="nameP" value="${p.name}"/>
                    <form:option name="pet" value="${p}"> <c:out value="${nameP}"></c:out> </form:option>
                  </c:forEach>
                 </form:select>
                </div>
                
                
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${room['new']}">
                            <button class="btn btn-default" type="submit">Book Room</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Booking</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!room['new']}">
        </c:if>
    
    
    </jsp:body>
</petclinic:layout>