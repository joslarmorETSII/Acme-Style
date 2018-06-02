<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 21/05/2018
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 04/04/2018
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="row">
        <div class="col-md-10">
            <form:form action="${actionURI}" modelAttribute="servise" class="form-horizontal" >

                <form:hidden path="id"/>
                <form:hidden path="version"/>


                <div class="form-group">
                    <label  class="col-sm-3 control-label"><spring:message code="servise.title"/> </label>
                    <div class="col-sm-9">
                        <form:input path="title" class="form-control" />
                        <form:errors class="error" path="title"/>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-3 control-label"><spring:message code="servise.description"/> </label>
                    <div class="col-sm-9">
                        <form:input path="description" class="form-control" />
                        <form:errors class="error" path="description"/>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-3 control-label"><spring:message code="servise.picture"/> </label>
                    <div class="col-sm-9">
                        <form:input path="picture"  placeHolder="http://www.pinteres.com/pic.jpg" class="form-control" />
                        <form:errors class="error" path="picture"/>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-3 control-label"><spring:message code="servise.discount"/> </label>
                    <div class="col-sm-9">
                        <form:input path="discount" placeHolder="Enter a discount [0,100]" class="form-control" />
                        <form:errors class="error" path="discount"/>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-3 control-label"><spring:message code="servise.price"/> </label>
                    <div class="col-sm-9">
                        <form:input path="price" class="form-control" />
                        <form:errors class="error" path="price"/>
                    </div>
                </div>

                <div class="text-center">
                    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST')">
                        <acme:submit name="save" code="general.save"/>
                    </security:authorize>

                    <security:authorize access="hasRole('ADMINISTRATOR')">
                        <jstl:if test="${ servise.taboo ne false }">
                            <acme:submit name="delete" code="general.delete"/>
                        </jstl:if>
                    </security:authorize>

                    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST')">
                        <jstl:if test="${servise.id !=0}">
                            <input type="submit" class="btn btn-danger"  name="delete" id="saveButton" value="<spring:message code="general.delete"/>"/>
                        </jstl:if>
                    </security:authorize>

                    <acme:cancel code="general.cancel" url="${cancelURI}"/>
                </div>
            </form:form>
        </div>
    </div>
</div>


