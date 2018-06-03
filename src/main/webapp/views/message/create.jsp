<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">

    <form:form action="${requestURI}" modelAttribute="sendMessage" class="form-horizontal" >
        <form:hidden path="id" />
        <form:hidden path="version" />
        <form:hidden path="actorSender" />
        <form:hidden path="moment" />
        <form:hidden path="folder" />

            <div class="form-group">
                <label class="col-sm-3 control-label"><spring:message code="message.priority" /></label>
                <div class="col-sm-9">
            <form:select class="form-control" id="priority" path="priority">
                <spring:message code="message.priority.low" var="lowHeader"/><form:option value="LOW" label="${lowHeader }" />
                <spring:message code="message.priority.neutral" var="neutralHeader"/><form:option value="NEUTRAL" label="${neutralHeader }" />
                <spring:message code="message.priority.high" var="highHeader"/><form:option value="HIGH" label="${highHeader }" />
            </form:select>
            <form:errors path="priority" cssClass="error" />
                </div>
            </div>

            <div class="form-group">
                <label  class="col-sm-3 control-label"><spring:message code="message.subject"/> </label>
                <div class="col-sm-9">
                    <form:input path="subject" class="form-control" placeholder="Subject"/>
                    <form:errors class="error" path="subject"/>
                </div>
            </div>
        <div class="form-group">
            <label  class="col-sm-3 control-label"><spring:message code="message.body"/> </label>
            <div class="col-sm-9">
                <form:input path="body" class="form-control"/>
                <form:errors class="error" path="body"/>
            </div>
        </div>

        <div class="form-group">
            <jstl:if test="${isBroadcast ne true}">
                <label class="col-sm-3 control-label"><spring:message code="message.recipient" /></label>
                <div class="col-sm-9">
                <form:select class="form-control" path="actorReceivers" items="${recipients}" itemLabel="name"/>
                <form:errors path="actorReceivers" cssClass="error" />
            </jstl:if>
                    </div>
        </div>

        <div class="center">
        <div class="form-group last">
            <div class="col-sm-offset-3 col-sm-9">
                <acme:submit code="message.send" name="send" />&nbsp;
                <input type="button" name="cancel" class="btn btn-warning" value="<spring:message code="general.cancel" />"
                       onclick="javascript: relativeRedir('folder/actor/list.do');" />

            </div>
        </div>
        </div>
    </form:form>
        </div>
    </div>
</div>