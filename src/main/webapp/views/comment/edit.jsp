<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 17/02/2018
  Time: 12:14
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

            <form:form action="${actionURI}" modelAttribute="comment">

                <form:hidden path="id"/>
                <form:hidden path="version"/>
                <form:hidden path="post"/>

                <center>
                <br/>
                <acme:textbox path="text" code="comment.text"/>

                <br/>
                <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST','USER', 'MANAGER')">
                    <acme:submit name="save" code="general.save"/>
                </security:authorize>

                <acme:cancel code="general.cancel" url="${cancelURI}"/>
                </center>
            </form:form>

        </div>
    </div>
</div>

