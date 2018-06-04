<%--
  Created by IntelliJ IDEA.
  User: yuzi
  Date: 6/3/18
  Time: 12:25 AM
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
        <div class="col-md-10">

            <form:form  action="personalData/edit.do" modelAttribute="actorForm" class="form-horizontal">
                <div class="form-group">
                    <label for="inputName" class="col-sm-3 control-label"><spring:message code="artist.name"/></label>
                    <div class="col-sm-9">
                        <form:input path="name" class="form-control"  id="inputName" placeholder="Name"/>
                        <form:errors class="error" path="name" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputSurname" class="col-sm-3 control-label"><spring:message code="artist.surname"/></label>
                    <div class="col-sm-9">
                        <form:input path="surname" class="form-control"  id="inputSurname" placeholder="Surname" />
                        <form:errors class="error" path="surname" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-3 control-label"><spring:message code="artist.email"/></label>
                    <div class="col-sm-9">
                        <form:input path="email" class="form-control"  id="email" placeholder="Email"/>
                        <form:errors class="error" path="email" />
                    </div>
                </div>

                <div class="form-group">
                    <label for="phone" class="col-sm-3 control-label"><spring:message code="artist.phone"/> </label>
                    <div class="col-sm-9">
                        <form:input path="phone" class="form-control"  id="phone" placeholder="+XX XXXX [4,9]"/>
                        <form:errors class="error" path="phone"/>
                        <span class="help-block">Your phone number won't be disclosed anywhere </span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="postalAddresses" class="col-sm-3 control-label"><spring:message code="artist.postalAddresses"/> </label>
                    <div class="col-sm-9">
                        <form:input path="postalAddresses" class="form-control"  id="postalAddresses" />
                        <form:errors class="error" path="postalAddresses"/>
                    </div>
                </div>

                <div class="text-center">
                    <input type="submit" class="btn btn-primary"  name="save" id="saveButton" value="<spring:message code="general.save"/>"/>
                    <input type="button"   class="btn btn-warning" name="cancel" value="<spring:message code="general.cancel" />"
                       onclick="javascript: relativeRedir('welcome/index.do');" />
                </div>
            </form:form> <!-- /form -->
        </div>
    </div>
</div>
