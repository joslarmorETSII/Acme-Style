<%--
  Created by IntelliJ IDEA.
  User: yuzi
  Date: 5/21/18
  Time: 1:19 AM
  To change this template use File | Settings | File Templates.
--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
        <form:form action="${requestURI}" modelAttribute="folder" class="form-horizontal" >

            <form:hidden path="id" />
            <form:hidden path="version" />
            <form:hidden path="system" />
            <form:hidden path="messages"/>
            <form:hidden path="actor"/>

            <acme:textbox code="messageFolder.name" path="name"/>
            <br />

            <acme:submit name="save" code="general.save"/>

            <acme:cancel url="folder/actor/list.do" code="general.cancel"/>

            <br />

        </form:form>
        </div>
    </div>
</div>