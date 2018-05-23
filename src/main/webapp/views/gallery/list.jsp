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

<div class="container">
    <div class="col-md-6 col-md-offset-3">

<display:table name="galleries" pagesize="10" class="table table-striped table-hover" requestURI="${requestURI}" id="row">


    <spring:message code="gallery.picture" var="pic"/>
    <display:column title="${pic}"><img src="${row.picture}" alt="no image" width="130" height="100"></display:column>


</display:table>


        <acme:button code="general.create" url="gallery/actor/create.do"/>
        <acme:cancel url="${cancelURI}" code="general.cancel"/>

    </div>
</div>