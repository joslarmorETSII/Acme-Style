<%--
  Created by IntelliJ IDEA.
  User: Félix
  Date: 07/04/2018
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="row">
        <div class="col-md-10">
    <form:form  action="servise/user/subscribe.do" modelAttribute="subscribeServiseForm" class="form-horizontal">

    <form:hidden path="servise"/>

        <div class="form-group">
            <label for="holder" class="col-sm-3 control-label"><spring:message code="user.holder"/>*</label>
            <div class="col-sm-9">
                <form:input path="holder" class="form-control"  id="holder"/>
                <form:errors class="error" path="holder" />
            </div>
        </div>

        <div class="form-group">
            <label for="brand" class="col-sm-3 control-label"><spring:message code="user.brand"/>*</label>
            <div class="col-sm-9">
                <form:input path="brand" class="form-control"  id="brand"/>
                <form:errors class="error" path="brand" />
            </div>
        </div>

        <div class="form-group">
            <label for="number" class="col-sm-3 control-label"><spring:message code="user.number"/>*</label>
            <div class="col-sm-9">
                <form:input path="number" class="form-control"  id="number"/>
                <form:errors class="error" path="number" />
            </div>
        </div>

        <div class="form-group">
            <label for="expirationMonth" class="col-sm-3 control-label"><spring:message code="user.expirationMonth"/>*</label>
            <div class="col-sm-9">
                <form:input path="expirationMonth" placeHolder="MM" class="form-control"  id="expirationMonth"/>
                <form:errors class="error" path="expirationMonth" />
            </div>
        </div>

        <div class="form-group">
            <label for="expirationYear" class="col-sm-3 control-label"><spring:message code="user.expirationYear"/>*</label>
            <div class="col-sm-9">
                <form:input path="expirationYear" placeHolder="yyyy" class="form-control"  id="expirationYear"/>
                <form:errors class="error" path="expirationYear" />
            </div>
        </div>

        <div class="form-group">
            <label for="cvv" class="col-sm-3 control-label"><spring:message code="user.cvv"/>*</label>
            <div class="col-sm-9">
                <form:input path="cvv" placeHolder="XXX" class="form-control"  id="cvv"/>
                <form:errors class="error" path="cvv" />
            </div>
        </div>

        <div class="text-center">

            <input type="submit" class="btn btn-primary"  name="save" id="saveButton" value="<spring:message code="general.save"/>"/>
            <acme:cancel url="servise/user/listServisesToSubscribe.do" code="general.cancel"/>
        </div>

        </form:form>
        </div>
    </div>
</div>

