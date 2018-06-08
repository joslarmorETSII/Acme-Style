
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<div class="container">
    <div class="col-md-10 col-md-offset-1">
        <fieldset>
        <b><spring:message code="category.associated"/></b>

        <display:table id="category" name="categoriesAssociated" class="table table-striped table-hover" requestURI="${requestURI}"
                       pagesize="5">

            <acme:column code="category.name" value="${category.name} " />

            <security:authorize access="hasRole('ADMINISTRATOR')">
                <display:column>
                        <acme:button url="category/administrator/edit.do?categoryId=${category.id}" code="general.edit"/>
                </display:column>
            </security:authorize>

        </display:table>
        </fieldset>

    </div>
</div>

<div class="container">
    <div class="col-md-10 col-md-offset-1">
        <fieldset>
        <b><spring:message code="category.notAssociated"/></b>

        <display:table id="row" name="categories" class="table table-striped table-hover" requestURI="${requestURI}"
                       pagesize="5">

            <acme:column code="category.name" value="${row.name} " />

            <security:authorize access="hasRole('ADMINISTRATOR')">
                <display:column>
                    <acme:button url="category/administrator/edit.do?categoryId=${row.id}" code="general.edit"/>
                </display:column>
            </security:authorize>

            <security:authorize access="hasRole('ADMINISTRATOR')">
                <display:column>
                    <a href="category/administrator/delete.do?categoryId=${row.id}">
                        <input type="submit" class="btn btn-danger" name="delete"
                               value="<spring:message code="general.delete" />"
                               onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
                    </a>
                </display:column>
            </security:authorize>

        </display:table>
        </fieldset>

    </div>
</div>

<div class="text-center">

    <security:authorize access="hasRole('ADMINISTRATOR')">
        <acme:button code="general.create" url="category/administrator/create.do"/>
    </security:authorize>

    <acme:cancel code="general.cancel" url="${cancelURI}"/>
</div>