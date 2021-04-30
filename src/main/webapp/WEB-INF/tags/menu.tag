<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div>
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="gslyphicon glyphicon-home" aria-hidden="true"></span>
					<span><spring:message code = "home"></spring:message></span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'owners'}" url="/owners/find"
					title="find owners">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span><spring:message code = "findOwners"></spring:message></span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
					title="veterinarians">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span><spring:message code = "veterinarians"></spring:message></span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'rooms'}" url="/rooms"
					title="pet hotel">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Pet hotel</span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'adoptions'}" url="/adoptions/pets"
					title="pet hotel">
					<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
					<span><spring:message code = "adoptAPet"></spring:message></span>
         </petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'causes'}" url="/causes"
					title="causes">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Causes</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'error'}" url="/oups"
					title="trigger a RuntimeException to see how it is handled">
					<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
					<span><spring:message code = "error"></spring:message></span>
				</petclinic:menuItem>
				
					<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />"><spring:message code = "login"></spring:message></a></li>
					<li><a href="<c:url value="/users/new" />"><spring:message code = "register"></spring:message></a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<sec:authentication property="name" /> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
						</ul></li>
				</sec:authorize>
			</ul>

		</div>

	</div>
</nav>