<%--
  Created by IntelliJ IDEA.
  User: yuzi
  Date: 5/21/18
  Time: 4:04 PM
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

            <form:form action="${requestURI}" modelAttribute="profile" class="form-horizontal" >
                <form:hidden path="id" />
                <form:hidden path="version" />
                <form:hidden path="actor" />
                <form:hidden path="galleries" />


                <div class="form-group">
                    <label  class="col-sm-3 control-label"><spring:message code="profile.fullName"/> </label>
                    <div class="col-sm-9">
                        <form:input path="fullName" class="form-control" />
                        <form:errors class="error" path="fullName"/>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-3 control-label"><spring:message code="profile.profilePhoto"/> </label>
                    <div class="col-sm-9">
                        <form:input path="profilePhoto" placeHolder="http://www.pinterest.com/pic.jpg" class="form-control"/>
                        <form:errors class="error" path="profilePhoto"/>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-3 control-label"><spring:message code="profile.education"/> </label>
                    <div class="col-sm-9">
                        <form:input path="education" class="form-control"/>
                        <form:errors class="error" path="education"/>
                    </div>
                </div>

                <div class="text-center">
                    <acme:submit code="general.save" name="save" />&nbsp;
                    <input type="button" name="cancel" class="btn btn-warning" value="<spring:message code="general.cancel" />"
                           onclick="javascript: relativeRedir('profile/actor/display.do');" />
                </div>

            </form:form>
        </div>
    </div>
</div>