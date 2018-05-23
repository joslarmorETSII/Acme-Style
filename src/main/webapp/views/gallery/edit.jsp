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
        <div class="col-md-6 col-md-offset-3">

            <form:form action="${requestURI}" modelAttribute="gallery" class="form-horizontal" >
                <form:hidden path="id" />
                <form:hidden path="version" />
                <form:hidden path="profile" />



                <div class="form-group">
                    <label  class="col-sm-3 control-label"><spring:message code="gallery.picture"/> </label>
                    <div class="col-sm-9">
                        <form:input path="picture" class="form-control" />
                        <form:errors class="error" path="picture"/>
                    </div>
                </div>

                <div class="form-group last">
                    <div class="col-sm-offset-3 col-sm-9">
                        <acme:submit name="save" code="general.save"/>

                        <acme:cancel url="${cancelURI}" code="general.cancel"/>
                    </div>
                </div>


            </form:form>
        </div>
    </div>
</div>