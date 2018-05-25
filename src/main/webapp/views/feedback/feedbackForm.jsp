<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 24/05/2018
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <form:form  action="feedback/user/evaluate.do" modelAttribute="feedbackForm" class="form-horizontal">

                <form:hidden path="servise"/>

                <div class="form-group">
                    <label for="text" class="col-sm-3 control-label"><spring:message code="feedback.text"/>*</label>
                    <div class="col-sm-9">
                        <form:input path="text" class="form-control"  id="text"/>
                        <form:errors class="error" path="text" />
                    </div>
                </div>

                <div class="form-group">
                    <label for="points" class="col-sm-3 control-label"><spring:message code="feedback.points"/>*</label></label>
                    <div class="col-sm-9">
                        <form:select path="points" class="form-control" id="points">
                            <option>0</option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </form:select>
                        <form:errors class="error" path="points" />
                    </div>
                </div>


                <div class="text-center">

                    <input type="submit" class="btn btn-primary"  name="save" id="saveButton" value="<spring:message code="general.save"/>"/>
                    <acme:cancel url="servise/user/list.do" code="general.cancel"/>
                </div>

            </form:form>
        </div>
    </div>
</div>


