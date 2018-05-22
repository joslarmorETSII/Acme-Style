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

<form:form action="${actionURI}" modelAttribute="panel" >

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <acme:textbox path="name" code="panel.name"/>

    <acme:submit name="save" code="panel.save"/>

    <jstl:if test="${panel.id != 0}" >
        <acme:submit name="delete" code="panel.delete"/>
    </jstl:if>

    <input type="button" name="cancel" value="<spring:message code="general.cancel" />"
           onclick="javascript: relativeRedir('${cancelUri}');" />
</form:form>
