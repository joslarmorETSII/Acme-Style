<%--
 * followings.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="container">
    <div class="col-md-10 col-md-offset-1">

        <display:table id="row" name="followings" requestURI="${requestURI}" pagesize="10" class="table table-striped table-hover">

            <acme:column code="actor.name" value="${row.name}" />
            <acme:column code="actor.surname" value="${row.surname}" />
            <acme:column code="actor.email" value="${row.email}"/>
            <acme:column code="actor.phone" value="${row.phone}"/>
            <acme:column code="actor.postalAddresses" value="${row.postalAddresses}"/>
            <acme:columnButton url="/profile/actor/view.do?profileId=${row.profile.id}" codeButton="general.display"/>

        </display:table>

        <div class="text-center">
            <acme:cancel code="general.cancel" url="profile/actor/display.do"/>
        </div>

    </div>
</div>