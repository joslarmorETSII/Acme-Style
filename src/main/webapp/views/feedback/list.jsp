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
    <jstl:set value="{0,date,dd/MM/yyyy}" var="formatDate"/>
</jstl:if>

<jstl:if test="${pageContext.response.locale.language == 'en' }">
    <jstl:set value="{0,date,yyyy/MM/dd}" var="formatDate"/>
</jstl:if>

<div class="container">
    <div class="col-md-10 col-md-offset-1">

        <display:table name="feedbacks" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}" id="row">


            <spring:message code="feedback.points" var="headerTag" />
            <display:column property="points" title="${headerTag}"/>


            <spring:message code="feedback.text" var="headerTag" />
            <display:column property="text" title="${headerTag}"/>

            <spring:message var="moment" code="feedback.moment"/>
            <display:column property="moment" title="${moment}" format="${formatDate}" />

        </display:table>

            <acme:cancel code="general.cancel" url="welcome/index.do"/>
        </div>
    </div>
</div>