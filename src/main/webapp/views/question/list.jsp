<%--
  Created by IntelliJ IDEA.
  User: yuzi
  Date: 5/26/18
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<jstl:if test="${pageContext.response.locale.language == 'es' }">
    <jstl:set value="{0,date,dd/MM/yyyy HH:mm}" var="formatDate"/>
</jstl:if>

<jstl:if test="${pageContext.response.locale.language == 'en' }">
    <jstl:set value="{0,date,yyyy/MM/dd HH:mm}" var="formatDate"/>
</jstl:if>


<div class="container">
    <div class="col-md-10 col-md-offset-1">
        <display:table name="questions" id="row" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">

            <acme:column code="question.servise" value="${row.servise.title} " />
            <acme:column code="question.text" value="${row.text}"/>


            <spring:message var="moment" code="question.moment"/>
            <display:column property="moment" title="${moment}" format="${formatDate}" sortable="true" />

            <display:column >
                <acme:button url="answer/actor/list.do?questionId=${row.id}" code="question.answers"/>
            </display:column>

            <security:authorize access="hasRole('ADMINISTRATOR')">
            <display:column>
                <a href="question/administrator/edit.do?questionId=${row.id}">
                    <input type="submit" class="btn btn-danger" name="delete"
                           value="<spring:message code="general.delete" />"
                           onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
                </a>
            </display:column>
            </security:authorize>
        </display:table>
        <div class="text-center">
            <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST','USER', 'MANAGER')">
                <acme:button url="/question/actor/add.do?serviseId=${servise.id}" code="question.add" />
            </security:authorize>
            <security:authorize access="hasRole('MANAGER')">
                <acme:cancel code="general.cancel" url="/servise/listServisesPublished.do"/>
            </security:authorize>

            <security:authorize access="hasRole('ADMINISTRATOR')">
                <acme:cancel code="general.cancel" url="servise/administrator/list.do"/>
            </security:authorize>

            <security:authorize access="!hasAnyRole('ADMINISTRATOR','MANAGER')">
                <acme:cancel code="general.cancel" url="welcome/index.do"/>
            </security:authorize>


        </div>
    </div>
</div>
