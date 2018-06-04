<%--
  Created by IntelliJ IDEA.
  User: yuzi
  Date: 6/3/18
  Time: 1:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<fieldset>
    <div class="container">
        <div class="row">
            <div class="col-md-10">

                <form:form  action="userAccount/actor/change.do" modelAttribute="userAccountForm" class="form-horizontal">
                    <div class="form-group">
                        <label for="inputUsername" class="col-sm-3 control-label"><spring:message code="user.username"/></label>
                        <div class="col-sm-9">
                            <form:input path="username" class="form-control"  id="inputUsername" placeholder="Username" />
                            <form:errors class="error" path="username" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="oldPassword" class="col-sm-3 control-label"><spring:message code="user.oldPassword"/></label>
                        <div class="col-sm-9">
                            <form:password path="oldPassword" class="form-control"  id="oldPassword" placeholder="Old password"/>
                            <form:errors class="error" path="oldPassword" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newPassword" class="col-sm-3 control-label"><spring:message code="user.password"/></label>
                        <div class="col-sm-9">
                            <form:password path="newPassword" class="form-control"  id="newPassword" placeholder="New password"/>
                            <form:errors class="error" path="newPassword" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><spring:message code="user.repeatPassword"/></label>
                        <div class="col-sm-9">
                            <form:password path="repeatPassword" class="form-control"  id="repeatPassword" placeholder="repeatPassword"/>
                            <form:errors class="error" path="repeatPassword"/>
                        </div>
                    </div>

                    <div class="text-center">
                        <input type="submit" class="btn btn-primary"  name="save" id="saveButton" value="<spring:message code="general.save"/>"/>
                        <input type="button"   class="btn btn-warning" name="cancel" value="<spring:message code="general.cancel" />"
                               onclick="javascript: relativeRedir('welcome/index.do');" />
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</fieldset>
