<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">

    <h2><spring:message code = "ownerInformation"/></h2>
    <table class="table table-striped">
        <tr>
            <th class="table_header_light_grey" ><spring:message code = "firstName"/></th>
            <td><b><c:out value="${owner.firstName} ${owner.lastName}"/></b></td>
        </tr>
        <tr>
            <th class="table_header_light_grey" ><spring:message code = "address"/></th>
            <td><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th class="table_header_light_grey" ><spring:message code = "city"/></th>
            <td><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th class="table_header_light_grey" ><spring:message code = "telephone"/></th>
            <td><c:out value="${owner.telephone}"/></td>
        </tr>
    </table>
    
    <spring:url value="{ownerId}/edit" var="editUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
 <a href="${fn:escapeXml(editUrl)}" class="btn btn-default"><spring:message code = "editOwner"/></a>
    
     <spring:url value="{ownerId}/delete" var="deleteUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete Owner</a>
    


    <spring:url value="{ownerId}/pets/new" var="addUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default"><spring:message code = "addNewPet"/></a>

    <br/>
    <br/>
    <br/>
    <h2><spring:message code = "petsAndVisits"/></h2>

    <table class="table table-striped">
        <c:forEach var="pet" items="${owner.pets}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt><spring:message code = "firstName"/></dt>
                        <dd><c:out value="${pet.name}"/></dd>
                        <dt><spring:message code = "birthDate"/></dt>
                        <dd><petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt><spring:message code = "type"/></dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead class="table_header_light_grey">
                        <tr>
                            <th><spring:message code = "visitDate"/></th>
                            <th><spring:message code = "description"/></th>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${pet.visits}">
                            <tr>  
                            <td><petclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                             <td><c:out value="${visit.description}"/></td>
                             
                            <spring:url value="/owners/{ownerId}/pets/{petId}/visits/{visitId}/delete" var="visitDetailsUrl">
                             <spring:param name="ownerId" value="${owner.id}"/>
                             <spring:param name="petId" value="${pet.id}"/>
                        	<spring:param name="visitId" value="${visit.id}"/>
                   			 </spring:url>
                   		<td>
                   		 <a href="${fn:escapeXml(visitDetailsUrl)}">Delete Visit</a>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="petUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>

                                <a href="${fn:escapeXml(petUrl)}"><spring:message code = "editPet"/></a>
                                
                                 <spring:url value="/owners/{ownerId}/pets/{petId}/delete" var="deletepetUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(deletepetUrl)}">Delete Pet</a>

                            </td>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/new" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>

                                <a href="${fn:escapeXml(visitUrl)}"><spring:message code = "addVisit"/></a>

                            </td>
                            <td>
                                <spring:url value="/adoptions/request/new/{petId}" var="adoptionUrl">
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(adoptionUrl)}"><spring:message code = "addAdoptionRequest"/></a>
                            </td>
                            <td>
                                <spring:url value="/adoptions/request/{petId}" var="viewAdoptionRequest">
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(viewAdoptionRequest)}">View Adoption Requests</a>
                            </td>
                        </tr>
                    </table>
                </td>
        	</tr>
        </c:forEach>
                       <tr>
                <td valign="top">
                	<h2>Your adoption request</h2>
                	<table id="yourAdoptionTable" class="table table-striped">
        				<thead>
        					<tr>
            					<th>Pet Name</th>
            					<th>Description</th>
            					<th>State</th>
        					</tr>
        				</thead>
        				<tbody>
        					<c:forEach items="${adoptionApplication}" var="adoptionPet">
           						<tr>
                					<td>
                    					<c:out value="${adoptionPet.owner.firstName}  ${adoptionPet.owner.lastName}"/>
               						</td>

                					<td>
                    					<c:out value="${adoptionPet.description}"/>
                					</td>
                					<td>
                     					<c:out value="${adoptionPet.state}"/>           
                					</td>
           						</tr>
       						</c:forEach>
        				</tbody>
    				</table>
               	</td>
            </tr>
    </table>

</petclinic:layout>
