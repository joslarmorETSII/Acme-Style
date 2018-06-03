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

    <jstl:if test="${pageContext.response.locale.language == 'es' }">
        <jstl:set value="{0,date,dd/MM/yyyy HH:mm}" var="formatDate"/>
    </jstl:if>

    <jstl:if test="${pageContext.response.locale.language == 'en' }">
        <jstl:set value="{0,date,yyyy/MM/dd HH:mm}" var="formatDate"/>
    </jstl:if>

<div class="container">
    <div class="col-md-10 col-md-offset-1">

        <display:table name="events" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}" id="row">

            <security:authorize access="hasRole('MANAGER')">
                <display:column>
                    <acme:button url="event/manager/edit.do?eventId=${row.id}" code="general.edit"/>
                </display:column>
            </security:authorize>

            <spring:message code="event.creator" var="titleTag" />
            <display:column title="${titleTag}">
                        <jstl:out value="${row.manager.name}"/>
            </display:column>


            <spring:message code="event.title" var="titleTag" />
            <display:column title="${titleTag}">
                <a href="event/display.do?eventId=${row.id}">
                    <jstl:out value="${row.title}"/>
                </a>
            </display:column>


            <spring:message code="event.description" var="headerTag" />
            <display:column property="description" title="${headerTag}"/>

            <spring:message code="event.celebrationDate" var="headerTag" />
            <display:column property="celebrationDate" title="${headerTag}" format="${formatDate}" />

            <spring:message code="event.tipo" var="headerTag" />
            <display:column property="tipo" title="${headerTag}"/>

            <spring:message code="event.image" var="pic"/>
            <display:column title="${pic}"><img src="${row.image}" alt="no image" width="130" height="100"></display:column>

            <spring:message code="event.price" var="headerTag" />
            <display:column property="price" title="${headerTag}" />

            <spring:message code="event.location" var="headerTag" />
            <display:column property="location.name" title="${headerTag}"/>

            <security:authorize access="hasRole('USER')">
                <display:column>
                    <c:if test="${notParticipated}">
                        <acme:button url="participate/user/participate.do?eventId=${row.id}" code="event.participate"/>
                    </c:if>
                    <c:if test="${!notParticipated }">
                        <acme:button url="participate/user/unparticipate.do?eventId=${row.id}" code="event.unparticipate"/>
                    </c:if>
                </display:column>
            </security:authorize>

            <spring:message code="event.store" var="titleTag" />
            <display:column title="${titleTag}">
                <a href="store/display.do?storeId=${row.store.id}">
                    <jstl:out value="${row.store.title}"/>
                </a>
            </display:column>
        </display:table>


        <div class="text-center">
            <security:authorize access="hasRole('MANAGER')">
                <acme:button code="general.create" url="event/manager/create.do"/>
            </security:authorize>
            <acme:cancel code="general.cancel" url="welcome/index.do"/>
        </div>
    </div>
</div>