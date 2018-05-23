<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 21/05/2018
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 04/04/2018
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${actionURI}" modelAttribute="servise">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


    <acme:textbox path="title" code="servise.title"/>
    <acme:textarea path="description" code="servise.description" />
    <acme:textbox path="picture" code="servise.picture"/>
    <acme:textbox path="discount" code="servise.discount"/>
    <acme:textbox path="price" code="servise.price"/>


    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST')">
        <acme:submit name="save" code="general.save"/>
    </security:authorize>

    <security:authorize access="hasRole('ADMINISTRATOR')">
        <jstl:if test="${ servise.taboo ne false }">
            <acme:submit name="delete" code="general.delete"/>
        </jstl:if>
    </security:authorize>

    <security:authorize access="hasRole('USER')">
        <jstl:if test="${servise.id !=0}">
            <acme:submit name="delete" code="newsPaper.delete"/>
        </jstl:if>
    </security:authorize>

    <input type="button" name="cancel" value="<spring:message code="general.cancel" />" onclick="javascript: relativeRedir('${cancelURI}');" />

</form:form>



