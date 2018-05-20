<%--
 * register.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-lock"></span> <spring:message code="general.register"/></div>
                <div class="panel-body">
                 <form:form  action="artist/register.do" modelAttribute="userForm" class="form-horizontal">

                    <div class="form-group">
                        <label for="inputName" class="col-sm-3 control-label"><spring:message code="user.name"/>*</label>
                        <div class="col-sm-9">
                            <form:input path="name" class="form-control"  id="inputName" placeholder="Name"/>
                            <form:errors class="error" path="name" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputSurname" class="col-sm-3 control-label"><spring:message code="user.surname"/>*</label>
                        <div class="col-sm-9">
                            <form:input path="surname" class="form-control"  id="inputSurname" placeholder="Surname" />
                            <form:errors class="error" path="surname" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label"><spring:message code="user.email"/>* </label>
                        <div class="col-sm-9">
                            <form:input path="email" class="form-control"  id="email" placeholder="Email"/>
                            <form:errors class="error" path="email" />
                        </div>
                    </div>
                     <div class="form-group">
                         <label for="inputUsername" class="col-sm-3 control-label"><spring:message code="user.username"/>*</label>
                         <div class="col-sm-9">
                             <form:input path="username" class="form-control"  id="inputUsername" placeholder="Username" />
                             <form:errors class="error" path="username" />
                         </div>
                     </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label"><spring:message code="user.password"/>*</label>
                        <div class="col-sm-9">
                            <form:password path="password" class="form-control"  id="password" placeholder="Password"/>
                            <form:errors class="error" path="password" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><spring:message code="user.repeatPassword"/>*</label>
                        <div class="col-sm-9">
                            <form:password path="repeatPassword" class="form-control"  id="repeatPassword" placeholder="Password"/>
                            <form:errors class="error" path="repeatPassword"/>
                        </div>
                    </div>

                     <div class="form-group">
                         <label for="sel1" class="col-sm-3 control-label">Select a Role</label>
                         <div class="col-sm-9">
                         <form:select path="role" class="form-control" id="sel1">
                             <option>PHOTOGRAPHER</option>
                             <option>MAKEUPARTIST</option>
                             <option>STYLIST</option>
                         </form:select>
                         </div>
                     </div>

                    <div class="form-group">
                        <label for="phone" class="col-sm-3 control-label"><spring:message code="user.phone"/> </label>
                        <div class="col-sm-9">
                            <form:input path="phone" class="form-control"  id="phone" placeholder="Phone"/>
                            <form:errors class="error" path="phone"/>
                            <span class="help-block">Your phone number won't be disclosed anywhere </span>
                        </div>
                    </div>

                     <div class="form-group">
                     <label class="col-sm-3 control-label ">Terms of use</label>
                        <div class="col-sm-9">
                            <form:checkbox id="checkTerm" onclick="comprobar();" path="check"/>
                                <a href="termAndCondition/termAndCondition.do"><spring:message code="general.lopd"/></a>
                        </div>
                     </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <span class="help-block">*<spring:message code="general.required"/></span>
                        </div>
                    </div>

                    <input type="submit" class="btn btn-primary btn-block"  name="save" id="saveButton" value="<spring:message code="general.register"/>"/>
                    <input type="button"   class="btn btn-warning btn-block" name="cancel" value="<spring:message code="general.cancel" />"
                           onclick="javascript: relativeRedir('welcome/index.do');" />
                 </form:form> <!-- /form -->
                </div>
            </div>
        </div>
    </div>
</div> <!-- ./container -->

<script>

    document.getElementById("saveButton").disabled = true;
    document.getElementById("checkTerm").checked = false;

    function comprobar() {

        var aux = document.getElementById("checkTerm").checked;

        if(aux == true){
            document.getElementById("saveButton").disabled = false;
        }
        else{
            document.getElementById("saveButton").disabled = true;
        }
    }
</script>