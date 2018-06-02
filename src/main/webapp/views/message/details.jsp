<%--
  Created by IntelliJ IDEA.
  User: yuzi
  Date: 5/21/18
  Time: 1:28 AM
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

<!-- todo: Cambiar el display-->
<div class="container">
        <div class="col-md-6 col-md-offset-5">
                <display:table name="msg.actorReceivers" pagesize="${msg.actorReceivers.size()}" class="displaytag" requestURI="message/actor/details.do" id="row">
                    <spring:message code="message.recipient" var="title"/>
                    <display:column property="name" title="${title}"/>
                </display:table>
                <acme:textbox code="message.sender" path="msg.actorSender.name" readonly="true"/>
                <acme:textbox code="message.priority" path="msg.priority" readonly="true"/>
                <acme:textbox code="message.subject" path="msg.subject" readonly="true"/>
                <acme:textbox code="message.momentSent" path="msg.moment" readonly="true"/>
                <acme:textarea code="message.body" path="msg.body" readonly="true"/>
            <!-- boton para volver atrás -->
            <input type="button" class="btn btn-warning" name="volver" onclick="history.back()"
                   value="<spring:message code="message.volver"/>" />

            <div>
                <%-- 		<acme:back code="message.cancel"/> --%>
            </div>

        </div>
</div>
