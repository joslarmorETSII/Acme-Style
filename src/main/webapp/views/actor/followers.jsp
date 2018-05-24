<%--
 * followers.jsp
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


<display:table id="row" name="followers" requestURI="${requestURI}" pagesize="5">

    <acme:column code="user.name" value="${row.name}" />
    <acme:column code="user.surname" value="${row.surname}" />
    <acme:column code="user.email" value="${row.email}"/>
    <acme:column code="user.phone" value="${row.phone}"/>
    <acme:column code="user.postalAddresses" value="${row.postalAddresses}"/>
    <acme:columnButton url="user/display.do?userId=${row.id}" codeButton="general.display"/>

</display:table>

<acme:cancel code="general.cancel" url="profile/actor/display.do"/>
