 <%--
 * login.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

 <div class="container">
	 <div class="row">
		 <div class="col-md-6 col-md-offset-3">
			 <div class="panel panel-default">
				 <div class="panel-heading">
					 <span class="glyphicon glyphicon-lock"></span> Login</div>
				 <div class="panel-body">
					 <form:form action="j_spring_security_check" modelAttribute="credentials" class="form-horizontal" >

						 <div class="form-group">
							 <label for="inputUsername" class="col-sm-3 control-label"><spring:message code="security.username" /></label>
							 <div class="col-sm-9">
								 <form:input path="username" class="form-control"  id="inputUsername" placeholder="username"/>
								 <form:errors class="error" path="username" />
							 </div>
						 </div>
						 <div class="form-group">
							 <label for="inputPassword3" class="col-sm-3 control-label"><spring:message code="security.password" /></label>
							 <div class="col-sm-9">
								 <form:password path="password" class="form-control" id="inputPassword3" placeholder="Password"/>
								 <form:errors class="error" path="password" />
							 </div>
						 </div>
						 <div class="form-group last">
							 <div class="col-sm-offset-3 col-sm-9">
								 <input type="submit" class="btn btn-success btn-sm" value="<spring:message code="security.login" />"/>
							 </div>
						 </div>
					 </form:form>
				 </div>
				 <div class="panel-footer">
					 <jstl:if test="${showError == true}">
						 <div class="error">
							 <spring:message code="security.login.failed" />
						 </div>
					 </jstl:if>
				 </div>
			 </div>
		 </div>
	 </div>
 </div>
