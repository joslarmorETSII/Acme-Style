<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>



<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed dd" data-toggle="collapse" data-targer="#navegacion-fm"> </button>
			<a href="welcome/index.do" class="navbar-brand"><span class="glyphicon glyphicon-home"></span></a>
		</div>
		<!-- iniciar Menu -->
		<div class="collapse navbar-collapse" id="navegacion-fm">
			<ul class="nav navbar-nav">
				<security:authorize access="isAnonymous()">
					<li class="active"><a href="security/login.do"><spring:message code="master.page.login" /></a></li>
					<li class="dorpdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" >
							<spring:message code="general.register" /> <span class="caret"/></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="user/register.do"><spring:message code="master.page.user" /></a> </li>
							<li><a href="artist/register.do"><spring:message code="master.page.artist" /></a> </li>
							<li><a href="managr/register.do"><spring:message code="master.page.manager" /></a> </li>
						</ul>
					</li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li class="dorpdown">
						<security:authorize access="hasRole('USER')">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" >
								<spring:message code="master.page.user" /> <span class="caret"/>
							</a>
						</security:authorize>
						<security:authorize access="hasRole('MANAGER')">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" >
								<spring:message code="master.page.manager" /> <span class="caret"/>
							</a>
						</security:authorize>
						<security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST')">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" >
								<spring:message code="master.page.artist" /> <span class="caret"/>
							</a>
						</security:authorize>
						<security:authorize access="hasRole('ADMINISTRATOR')">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" >
								<spring:message code="master.page.administrator" /> <span class="caret"/>
							</a>
						</security:authorize>
						<ul class="dropdown-menu" role="menu">
							<li><a href="profile/actor/display.do"><span class="glyphicon glyphicon-user"/> <spring:message code="master.page.profile" /></a> </li>
							<li><a href="folder/actor/list.do"><span class="glyphicon glyphicon-envelope"/> <spring:message code="master.page.mail" /></a> </li>
							<li><a href="post/actor/list.do"><span class="glyphicon glyphicon-pushpin"/> <spring:message code="master.page.posts" /></a> </li>
							<li><a href="user/search.do?keyword="><span class="glyphicon glyphicon-search"/> <spring:message code="master.page.search" /></a></li>
							<li class="divider"></li>
							<li><a href="j_spring_security_logout"><span class="glyphicon glyphicon-off"/> <spring:message code="master.page.logout"/></a></li>
						</ul>
					</li>
				</security:authorize>

					<!-- USER -->
				<security:authorize access="hasRole('USER')">
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.servise" /> </a>
						<ul class="dropdown-menu">
							<li><a href="servise/user/listServisesToSubscribe.do"><spring:message code="master.page.listServisesToSubscribe" /></a></li>
							<li><a href="servise/user/list.do"><spring:message code="master.page.list" /></a></li>
						</ul>
					</li>

					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.event" /> </a>
						<ul class="dropdown-menu">
							<li><a href="event/user/list.do"><spring:message code="master.page.event.toSubscribe" /></a></li>
							<li><a href="event/user/listParticipated.do"><spring:message code="master.page.event.my" /></a></li>
						</ul>
					</li>
				</security:authorize>

				<!-- Artist -->
				<security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST')">

					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.servise" /> </a>
						<ul class="dropdown-menu">
							<li><a href="servise/artist/list.do"><spring:message code="master.page.servise.my" /></a></li>
							<li><a href="servise/listServisesPublished.do"><spring:message code="master.page.serviseAll" /></a></li>
						</ul>
					</li>
					<li><a href="event/list.do"><spring:message code="master.page.event" /></a></li>


				</security:authorize>

				<!-- Manager -->
				<security:authorize access="hasRole('MANAGER')">
					<li><a href="servise/listServisesPublished.do"><spring:message code="master.page.serviseAll" /></a></li>
					<li><a href="store/manager/list.do"><spring:message code="master.page.stores" /></a></li>
					<li><a href="event/manager/list.do"><spring:message code="master.page.event" /></a></li>


				</security:authorize>

				<!-- Admin -->
				<security:authorize access="hasRole('ADMINISTRATOR')">
					<li><a href="servise/listServisesPublished.do"><spring:message code="master.page.serviseAll" /></a></li>
				</security:authorize>

				<!-- Admin -->
				<security:authorize access="hasRole('ADMINISTRATOR')">
					<li><a href="servise/listServisesPublished.do"><spring:message code="master.page.serviseAll" /></a></li>
				</security:authorize>
			</ul>



		</div>
	</div>
</nav>



<!-- Carousel -->
<jstl:if test="${isIndex}">
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>

		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="http://cdn.bukisa.com/wp-content/uploads/2017/09/F-13.jpg" alt="...">
				<div class="carousel-caption">
					<h3>Acme Style</h3>
					<p>Stay fashionable </p>
				</div>
			</div>

			<div class="item">
				<img src="http://www.stylewati.com/wp-content/uploads/2018/02/7-combinations-will-never-run-style.jpg" alt="Image">
				<div class="carousel-caption">
					<h3>Acme Style</h3>
					<p></p>
				</div>
			</div>

			<div class="item">
				<img src="https://fashionista.com/.image/t_share/MTUyODU0NTkxODI0NzMzNDY3/hp-milan-fashion-week-mens-fall-2018-street-style.jpg" alt="Image">
				<div class="carousel-caption">
					<h3>Acme Style</h3>
					<p></p>
				</div>
			</div>
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
</jstl:if>

