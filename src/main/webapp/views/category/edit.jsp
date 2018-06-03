<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<div class="container">
	<div class="row">
		<div class="col-md-10">
			<form:form action="category/administrator/edit.do" modelAttribute="category" class="form-horizontal">

				<form:hidden path="id"/>
				<form:hidden path="version"/>

				<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message code="category.name"/></label>
					<div class="col-sm-9">
						<form:input path="name" class="form-control"  />
						<form:errors class="error" path="name" />
					</div>
				</div>

				<div class="text-center">

					<security:authorize access="hasRole('ADMINISTRATOR')">
						<acme:submit name="save" code="general.save"/>
					</security:authorize>

					<acme:cancel code="general.cancel" url="${cancelURI}"/>

				</div>
			</form:form>
		</div>
	</div>
</div>


