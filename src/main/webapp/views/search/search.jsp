<%--
 * action-1.jsp
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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<jstl:if test="${pageContext.response.locale.language == 'es' }">
    <jstl:set value="{0,date,dd/MM/yyyy HH:mm}" var="formatDate"/>
</jstl:if>

<jstl:if test="${pageContext.response.locale.language == 'en' }">
    <jstl:set value="{0,date,yyyy/MM/dd HH:mm}" var="formatDate"/>
</jstl:if>



<form action="search/search.do" method="get">
    <input type="text" name="keyword" value="${keyword}" />
    <input type="submit" value="<spring:message code="master.page.search"/>"/>
</form>
<br/>
<br/>
<fieldset>
    <legend><spring:message code="servise.listServises"/> </legend>
    <display:table name="servises" id="row" pagesize="5" class="displaytag" requestURI="${requestURI}">

        <acme:column code="servise.title" value="${row.title}" />

    </display:table>
</fieldset>
<br/>

<br/>
<fieldset>
    <legend><spring:message code="event.listEvents"/> </legend>
    <display:table name="events" id="event" pagesize="5" class="displaytag" requestURI="${requestURI}">

        <acme:column code="event.title" value="${event.title} " />

    </display:table>
</fieldset>
<br/>

<br/>
<fieldset>
    <legend><spring:message code="user.listUsers"/> </legend>
    <display:table name="users" id="user" pagesize="5" class="displaytag" requestURI="${requestURI}">

        <acme:column code="user.name" value="${user.name}" />

    </display:table>
</fieldset>
<br/>
<acme:cancel code="general.cancel" url="${cancelURI}"/>


