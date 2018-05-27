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


<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <ul class="nav nav-pills nav-justified">
                <li class="active"><a data-toggle="pill" href="#home"><spring:message code="master.page.servise"/> </a></li>
                <li><a data-toggle="pill" href="#menu1"><spring:message code="master.page.event"/> </a></li>
                <li><a data-toggle="pill" href="#menu2"><spring:message code="user.listUsers"/></a></li>
            </ul>

            <div class="tab-content">
                <div id="home" class="tab-pane fade in active">
                    <p></p>
                    <form action="search/search.do"  method="get" role="search" target="vufind" name="searchForm">
                        <div class="input-group lrcInputs">
                            <input class="form-control" type="text" name="keyword" value="${keyword}" >
                            <div class="input-group-btn"><button class="btn btn-success lrcSearchButton" type="submit"><i class="glyphicon glyphicon-search"></i></button></div>
                        </div>
                    </form>
                    <p></p>
                    <fieldset>
                        <legend><spring:message code="servise.listServises"/> </legend>
                        <display:table name="servises" id="row" pagesize="5" class="displaytag" requestURI="${requestURI}">

                            <acme:column code="servise.title" value="${row.title}" />

                        </display:table>
                    </fieldset>

                </div>
                <div id="menu1" class="tab-pane fade">
                    <p></p>
                    <form action="search/search.do"  method="get" role="search" target="vufind" name="searchForm">
                        <div class="input-group lrcInputs">
                            <input class="form-control" type="text" name="keyword" value="${keyword}" >
                            <div class="input-group-btn"><button class="btn btn-success lrcSearchButton" type="submit"><i class="glyphicon glyphicon-search"></i></button></div>
                        </div>
                    </form>
                    <p></p>
                    <fieldset>
                        <legend><spring:message code="event.listEvents"/> </legend>
                        <display:table name="events" id="event" pagesize="5" class="displaytag" requestURI="${requestURI}">

                            <acme:column code="event.title" value="${event.title} " />

                        </display:table>
                    </fieldset>

                </div>
                <div id="menu2" class="tab-pane fade">
                    <p></p>
                    <form action="search/search.do"  method="get" role="search" target="vufind" name="searchForm">
                        <div class="input-group lrcInputs">
                            <input class="form-control" type="text" name="keyword" value="${keyword}" >
                            <div class="input-group-btn"><button class="btn btn-success lrcSearchButton" type="submit"><i class="glyphicon glyphicon-search"></i></button></div>
                        </div>
                    </form>
                    <p></p>
                    <fieldset>
                        <legend><spring:message code="user.listUsers"/> </legend>
                        <display:table name="users" id="user" pagesize="5" class="displaytag" requestURI="${requestURI}">

                            <acme:column code="user.name" value="${user.name}" />

                        </display:table>
                    </fieldset>

                </div>
            </div>
                    <acme:cancel code="general.cancel" url="${cancelURI}"/>
        </div>
    </div>
</div>
