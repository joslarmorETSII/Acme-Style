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

				</security:authorize>

				<security:authorize access="isAuthenticated()">

				</security:authorize>

				<security:authorize access="hasRole('USER')">
				<li class="dorpdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" ><spring:message code="master.page.user" /> <span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="j_spring_security_logout"><span class="glyphicon glyphicon-log-in"/> <spring:message code="master.page.logout"/></a></li>
					</ul>
					</security:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="user/search.do?keyword="><span class="glyphicon glyphicon-search"/></a></li>
			</ul>
		</div>
	</div>
</nav>
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

