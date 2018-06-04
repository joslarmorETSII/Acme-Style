<%--
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


<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <ul class="nav nav-pills nav-justified">
                <li class="active"><a data-toggle="pill" href="#home"><spring:message code="master.page.servise"/> </a></li>
                <li><a data-toggle="pill" href="#menu1"><spring:message code="master.page.event"/> </a></li>
                <li><a data-toggle="pill" href="#menu2"><spring:message code="actor.listActors"/></a></li>
            </ul>

            <div class="tab-content">
                <div id="home" class="tab-pane fade in active">
                    <p></p>
                    <form action="search/search.do"  method="get" role="search" target="vufind" name="searchForm">
                        <div class="input-group lrcInputs">
                            <input class="form-control" type="text" placeHolder="Search by title" name="keyword" value="${keyword}" >
                            <div class="input-group-btn"><button class="btn btn-success lrcSearchButton" type="submit"><i class="glyphicon glyphicon-search"></i></button></div>
                        </div>
                    </form>
                    <p></p>
                    <fieldset>
                        <legend><spring:message code="servise.listServises"/> </legend>
                        <display:table name="servises" id="row" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">

                            <acme:column code="servise.creator" value="${row.creator.name} " />
                            <acme:column code="servise.title" value="${row.title}"/>
                            <acme:column code="servise.description" value="${row.description}"/>
                            <acme:column code="servise.price" value="${row.price}"/>
                            <spring:message code="servise.picture" var="pic"/>
                            <display:column title="${pic}"><img src="${row.picture}" alt="no image" width="130" height="100"></display:column>

                            <spring:message var="publicationDate" code="servise.publicationDate"/>
                            <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" />


                        </display:table>
                    </fieldset>

                </div>
                <div id="menu1" class="tab-pane fade active" >
                    <p></p>
                    <form action="search/search.do"  method="get" role="search" target="vufind" name="searchForm">
                        <div class="input-group lrcInputs">
                            <input class="form-control" type="text" placeHolder="Search by title" name="keyword" value="${keyword}" >
                            <div class="input-group-btn"><button class="btn btn-success lrcSearchButton" type="submit"><i class="glyphicon glyphicon-search"></i></button></div>
                        </div>
                    </form>
                    <p></p>
                    <fieldset>
                        <legend><spring:message code="event.listEvents"/> </legend>
                        <display:table name="events" id="event" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">
                            <spring:message code="event.title" var="headerTag" />
                            <display:column property="title" title="${headerTag}"/>

                            <spring:message code="event.description" var="headerTag" />
                            <display:column property="description" title="${headerTag}"/>

                            <spring:message code="event.celebrationDate" var="headerTag" />
                            <display:column property="celebrationDate" title="${headerTag}" format="${formatDate}" />

                            <spring:message code="event.tipo" var="headerTag" />
                            <display:column property="tipo" title="${headerTag}"/>

                            <spring:message code="event.image" var="pic"/>
                            <display:column title="${pic}"><img src="${event.image}" alt="no image" width="130" height="100"></display:column>

                            <spring:message code="event.price" var="headerTag" />
                            <display:column property="price" title="${headerTag}"/>

                            <spring:message code="event.location" var="headerTag" />
                            <display:column property="location.name" title="${headerTag}"/>
                        </display:table>
                    </fieldset>

                </div>
                <div id="menu2" class="tab-pane fade">
                    <p></p>
                    <form action="search/search.do"  method="get" role="search" target="vufind" name="searchForm">
                        <div class="input-group lrcInputs">
                            <input class="form-control" type="text" placeHolder="Search by name, surname or username" name="keyword" value="${keyword}" >
                            <div class="input-group-btn"><button class="btn btn-success lrcSearchButton" type="submit"><i class="glyphicon glyphicon-search"></i></button></div>
                        </div>
                    </form>
                    <p></p>
                    <fieldset>
                        <legend><spring:message code="actor.listActors"/> </legend>
                        <display:table name="actors" id="actor" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">

                            <acme:column code="actor.name" value="${actor.name}" />
                            <acme:column code="actor.surname" value="${actor.email}" />
                            <acme:column code="actor.username" value="${actor.userAccount.username}" />

                            <security:authorize access="isAuthenticated()">
                                <display:column>
                                    <a href="profile/actor/view.do?profileId=${actor.profile.id}">
                                        <button class="btn btn-info"><spring:message code="master.page.profile"/></button>
                                    </a>
                                </display:column>
                            </security:authorize>
                        </display:table>
                    </fieldset>

                </div>
                <acme:cancel code="general.cancel" url="${cancelURI}"/>

            </div>
        </div>
    </div>
</div>

