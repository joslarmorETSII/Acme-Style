<%--
  Created by IntelliJ IDEA.
  User: Félix
  Date: 21/05/2018
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="jstt" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="row">
        <div class="col-md-10">
    <form:form action="${actionURI}" modelAttribute="panel" class="form-horizontal">

        <form:hidden path="id"/>
        <form:hidden path="version"/>

        <div class="form-group">
            <label class="col-sm-3 control-label"><spring:message code="panel.name"/></label>
            <div class="col-sm-9">
                <form:input path="name" class="form-control"  />
                <form:errors class="error" path="name" />
            </div>
        </div>

        <div class="text-center">

        <acme:submit name="save" code="panel.save"/>

        <jstl:if test="${panel.id != 0}" >
            <input type="submit" class="btn btn-danger"  name="delete" id="saveButton" value="<spring:message code="general.delete"/>"/>
        </jstl:if>

        <acme:cancel code="general.cancel" url="${cancelUri}"/>
        </div>

    </form:form>
        </div>
    </div>
</div>