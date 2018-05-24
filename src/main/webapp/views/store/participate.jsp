<%--
  Created by IntelliJ IDEA.
  User: yuzi
  Date: 5/23/18
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
    <form:form  action="participate/user/participate.do" modelAttribute="participateToEventForm" class="form-horizontal">
        <form:hidden path="event"/>

        <div class="form-group">
            <acme:textbox code="user.holder" path="holder" />
            <br/>

            <div class="form-group">
                <acme:textbox code="user.brand" path="brand" />
            </div>

            <div class="form-group">
                <acme:textbox code="user.number" path="number" />
            </div>
            <div class="form-group">
                <acme:textbox code="user.expirationMonth" path="expirationMonth" />
            </div>
            <div class="form-group">
                <acme:textbox code="user.expirationYear" path="expirationYear"/>
            </div>
            <div class="form-group">
                <acme:textbox code="user.cvv" path="cvv"/>
            </div>


            <input type="submit" name="save" value="<spring:message code="general.save"/>" />

            <input type="button" name="cancel" value="<spring:message code="general.cancel" />"
                   onclick="javascript: relativeRedir('event/list.do');" />
        </div>
    </form:form>
        </div>
    </div>
</div>