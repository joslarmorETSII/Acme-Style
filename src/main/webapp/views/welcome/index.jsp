<%--
 * index.jsp
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


<div class="well">
<jstl:forEach var="event" items="${events}">
    <div class='container'>
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="thumbnail">
                    <img src="${event.image}" alt="..."  width="800" height="400">
                    <div class="caption">
                        <h3 class="text-center">${event.title}</h3>
                        <p>${event.tipo}</p>
                        <p>
                            ${event.description}
                        </p>
                        <p class="pull-right">
                                ${event.moment}
                    </div>
                </div>
            </div>
        </div>
    </div>
</jstl:forEach>





<jstl:if test="${pageContext.response.locale.language == 'es'}">
    <p><spring:message code="welcome.greeting.prefix" /><jstl:out value="${spanishWelcome}"></jstl:out><spring:message code="welcome.greeting.suffix" /></p>
    <p><b><spring:message code="welcome.greeting.current.time" />:&nbsp;</b>${momentEs} </p>
</jstl:if>

<jstl:if test="${pageContext.response.locale.language == 'en'}">
    <p><spring:message code="welcome.greeting.prefix" /><jstl:out value="${englishWelcome}"></jstl:out><spring:message code="welcome.greeting.suffix" /></p>
    <p><b><spring:message code="welcome.greeting.current.time" />:&nbsp;</b>${momentEn} </p>
</jstl:if>
</div>