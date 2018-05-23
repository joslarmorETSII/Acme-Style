<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
    <div class="col-md-6 col-md-offset-3">

<display:table name="events" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}" id="row">

    <security:authorize access="hasRole('MANAGER')">
        <display:column>
                <a href="event/manager/edit.do?eventId=${row.id}">
                    <spring:message code="general.edit" />
                </a>
        </display:column>
    </security:authorize>

    <spring:message code="event.title" var="headerTag" />
    <display:column property="title" title="${headerTag}"/>


    <spring:message code="event.description" var="headerTag" />
    <display:column property="description" title="${headerTag}"/>

    <spring:message code="event.celebrationDate" var="headerTag" />
    <display:column property="celebrationDate" title="${headerTag}"/>

    <spring:message code="event.tipo" var="headerTag" />
    <display:column property="tipo" title="${headerTag}"/>

    <spring:message code="event.price" var="headerTag" />
    <display:column property="price" title="${headerTag}"/>

    <spring:message code="event.location" var="headerTag" />
    <display:column property="location.name" title="${headerTag}"/>

    <security:authorize access="hasRole('USER')">
        <display:column>
            <c:if test="${notParticipated}">
                <a href="participate/user/participate.do?eventId=${row.id}">
                    <spring:message code="event.participate" />
                </a>
            </c:if>
        </display:column>
    </security:authorize>
</display:table>

    <security:authorize access="hasRole('MANAGER')">
        <acme:button code="general.create" url="event/manager/create.do"/>
    </security:authorize>
    <acme:button code="general.cancel" url="welcome/index.do"/>
    </div>
</div>