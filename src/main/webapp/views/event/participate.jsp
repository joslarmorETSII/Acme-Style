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
            <label  class="col-sm-3 control-label"><spring:message code="user.holder"/>* </label>
            <div class="col-sm-9">
                <form:input path="holder" class="form-control"  />
                <form:errors class="error" path="holder" />
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-3 control-label"><spring:message code="user.brand"/>* </label>
            <div class="col-sm-9">
                <form:input path="brand" class="form-control"  />
                <form:errors class="error" path="brand" />
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-3 control-label"><spring:message code="user.number"/>* </label>
            <div class="col-sm-9">
                <form:input path="number" class="form-control"  />
                <form:errors class="error" path="number" />
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-3 control-label"><spring:message code="user.expirationMonth"/>* </label>
            <div class="col-sm-9">
                <form:input path="expirationMonth" class="form-control"  />
                <form:errors class="error" path="expirationMonth" />
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-3 control-label"><spring:message code="user.expirationYear"/>* </label>
            <div class="col-sm-9">
                <form:input path="expirationYear" class="form-control"  />
                <form:errors class="error" path="expirationYear" />
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-3 control-label"><spring:message code="user.cvv"/>* </label>
            <div class="col-sm-9">
                <form:input path="cvv" class="form-control"  />
                <form:errors class="error" path="cvv" />
            </div>
        </div>

        <div class="text-center">
            <input type="submit" class="btn btn-primary"  name="save" value="<spring:message code="general.save"/>" />
            <input type="button" class="btn btn-warning"  name="cancel" value="<spring:message code="general.cancel" />"
                   onclick="javascript: relativeRedir('event/user/list.do');" />
        </div>
    </form:form>
        </div>
    </div>
</div>