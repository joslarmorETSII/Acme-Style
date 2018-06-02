<%--
 * layout.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<base
			href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<link rel="shortcut icon" href="favicon.ico"/>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


	<link rel="stylesheet" href="styles/common.css" type="text/css">
	<link rel="stylesheet" href="styles/jmenu.css" media="screen" type="text/css" />


	<title><tiles:insertAttribute name="title" ignore="true" /></title>

	<script type="text/javascript">
        $(document).ready(function() {
            $("#jMenu").jMenu();
        });

        function askSubmission(msg, form) {
            if (confirm(msg))
                form.submit();
        }

        function relativeRedir(loc) {
            var b = document.getElementsByTagName('base');
            if (b && b[0] && b[0].href) {
                if (b[0].href.substr(b[0].href.length - 1) == '/' && loc.charAt(0) == '/')
                    loc = loc.substr(1);
                loc = b[0].href + loc;
            }
            window.location.replace(loc);
        }
	</script>


</head>

<body>

<tiles:insertAttribute name="header" />

<div class="container">
	<h1 class="text-center"><tiles:insertAttribute name="title"/></h1>
</div>
<tiles:insertAttribute name="body" />
<jstl:if test="${message != null}">
	<br />
	<span class="message"><spring:message code="${message}" /></span>
</jstl:if>
<br>
<footer class="footer">
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<h6>Copyright &copy; 2018 Acme-Style</h6>
			</div>
			<div class="col-sm-4">
				<h6>About Us</h6>
				<p>Acme-style hackathon developed by group 8 D&T</p>
			</div>
			<div class="col-sm-2">
				<h6>Navigation</h6>
				<ul class="unstyled">
					<li><a href="welcome/index.do">Home</a></li>
				</ul>

			</div>
			<!--
			<div class="col-sm-2">
				<h6>Follow Us</h6>
				<ul class="unstyled">
					<li><a href="#">Twitter</a></li>
					<li><a href="#">Facebook</a></li>
					<li><a href="#">Instagram</a></li>
					<li><a href="#">Google Plus</a></li>
				</ul>
			</div>-->

			<div class="col-sm-2">
				<h6>Language</h6>
				<ul class="unstyled">
					<li><a href="?language=en">English</a></li>
					<li><a href="?language=es">Spanish</a></li>
				</ul>
			</div>

		</div>
	</div>
</footer>
</body>
</html>