<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
    
<petclinic:layout pageName="rooms">
    <h2>Pet Hotel</h2>
    
    <table id="roomsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Number</th>
            <th>Start Date</th>
            <th>Finish Date</th>
            <th>Pet</th>
            <th>Owner</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rooms}" var="room">
            <tr>
                <td>
                    <c:out value="${room.id}"/>
                </td>
                <td>
                    <c:out value="${room.firstDate}"/>
                </td>
                <td>
                    <c:out value="${room.lastDate}"/>
                </td>
                <td>
                    <c:out value="${room.pet}"/>
                </td>
                <td>
                    <c:out value="${room.owner.firstName} ${room.owner.lastName}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <br/> 
    
    <a class="btn btn-default" href='<spring:url value="/rooms/new" htmlEscape="true"/>'>Book Room</a>
    
    
    
</petclinic:layout>