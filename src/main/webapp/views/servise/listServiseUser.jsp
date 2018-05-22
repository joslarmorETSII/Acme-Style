
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<jstl:if test="${pageContext.response.locale.language == 'es' }">
    <jstl:set value="{0,date,dd/MM/yyyy}" var="formatDate"/>
</jstl:if>

<jstl:if test="${pageContext.response.locale.language == 'en' }">
    <jstl:set value="{0,date,yyyy/MM/dd}" var="formatDate"/>
</jstl:if>


    <display:table id="servise" name="servises" requestURI="${requestURI}"
                   pagesize="5">


        <acme:column code="servise.publisher" value="${servise.creator.name} " />
        <acme:column code="servise.title" value="${servise.title}"/>
        <acme:column code="servise.description" value="${servise.description}"/>

        <spring:message code="servise.picture" var="pic"/>
        <display:column title="${pic}"><img src="${servise.picture}" alt="no image" width="130" height="100"></display:column>

        <spring:message var="publicationDate" code="servise.publicationDate"/>
        <spring:message var="formatDate" code="event.format.date"/>
        <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" sortable="true" />

        <security:authorize access="hasRole('USER')" >
            <display:column >
                <acme:button url="servise/user/display.do?serviseId=${servise.id}" code="servise.display"/>
            </display:column>
        </security:authorize>

        <security:authorize access="hasRole('USER')">
            <display:column>
                    <acme:button url="servise/user/unsubscribe.do?serviseId=${servise.id}" code="servise.unsubscribe"/>
            </display:column>
        </security:authorize>

    </display:table>

<input type="button" name="cancel" value="<spring:message code="general.cancel" />" onclick="javascript: relativeRedir('${cancelURI}');" />
