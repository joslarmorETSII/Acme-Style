<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 04/04/2018
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="jstt" uri="http://java.sun.com/jsp/jstl/core" %>

<jstl:if test="${pageContext.response.locale.language == 'es' }">
    <jstl:set value="{0,date,dd/MM/yyyy HH:mm}" var="formatDate"/>
</jstl:if>

<jstl:if test="${pageContext.response.locale.language == 'en' }">
    <jstl:set value="{0,date,yyyy/MM/dd HH:mm}" var="formatDate"/>
</jstl:if>

<div class="container">
    <div class="col-md-10 col-md-offset-1">

<fieldset>
    <security:authorize access="hasRole('ADMINISTRATOR')" >
    <b><spring:message code="servise.allTaboo"/></b>
    </security:authorize>

        <display:table name="servises" id="row" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">

            <display:column>
                <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST')" >
                    <jstl:if test="${isPublished eq true and empty row.subscriptions}">
                         <acme:button url="servise/artist/edit.do?serviseId=${row.id}" code="general.edit" />
                    </jstl:if>
                 </security:authorize>
            </display:column>

            <spring:message code="servise.creator" var="titleTag" />
            <display:column title="${titleTag}">
                <security:authorize access="isAnonymous()">
                    <a href="profile/view.do?profileId=${row.creator.profile.id}">
                        <jstl:out value="${row.creator.name}"/>
                    </a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <a href="profile/actor/view.do?profileId=${row.creator.profile.id}">
                        <jstl:out value="${row.creator.name}"/>
                    </a>
                </security:authorize>
            </display:column>
            <acme:column code="servise.title" value="${row.title}"/>
            <acme:column code="servise.description" value="${row.description}"/>
            <acme:column code="servise.price" value="${row.price}"/>
            <spring:message code="servise.picture" var="pic"/>
            <display:column title="${pic}"><img src="${row.picture}" alt="no image" width="130" height="100"></display:column>

            <spring:message var="publicationDate" code="servise.publicationDate"/>
            <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" />

            <security:authorize access="hasRole('USER')">
                <display:column>
                    <acme:button url="servise/user/subscribe.do?serviseId=${row.id}" code="servise.subscribe"/>
                </display:column>
            </security:authorize>


            <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST')" >
            <display:column >
                    <acme:button url="servise/artist/display.do?serviseId=${row.id}" code="general.display"/>
            </display:column>
            </security:authorize>

            <security:authorize access="hasRole('USER')" >
                <display:column>
                        <acme:button url="servise/user/display.do?serviseId=${row.id}" code="general.display"/>
                </display:column>
            </security:authorize>

            <security:authorize access="hasRole('MANAGER')" >
                <display:column>
                    <acme:button url="servise/actor/display.do?serviseId=${row.id}" code="general.display"/>
                </display:column>
            </security:authorize>

            <security:authorize access="hasRole('ADMINISTRATOR')">
                <display:column >
                    <acme:button url="servise/administrator/display.do?serviseId=${row.id}" code="general.display"/>
                </display:column>
            </security:authorize>

            <security:authorize access="hasRole('ADMINISTRATOR')" >
                <display:column>
                    <a href="servise/administrator/edit.do?serviseId=${row.id}">
                        <input type="submit" class="btn btn-danger" name="delete"
                               value="<spring:message code="general.delete" />"
                               onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
                    </a>
                </display:column>
            </security:authorize>

            <security:authorize access="isAuthenticated()" >
                <display:column>
                    <acme:button url="/question/actor/list.do?serviseId=${row.id}" code="servise.questions" />
                </display:column>
            </security:authorize>

</display:table>
</fieldset>
    </div>
</div>
<br/>

<div class="container">
    <div class="col-md-10 col-md-offset-1">
        <security:authorize access="hasRole('ADMINISTRATOR')">
        <fieldset>
            <b><spring:message code="servise.all"/></b>
            <display:table name="allServises" id="row" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">

                <acme:column code="servise.creator" value="${row.creator.name} " />
                <acme:column code="servise.title" value="${row.title}"/>
                <acme:column code="servise.description" value="${row.description}"/>
                <acme:column code="servise.price" value="${row.price}"/>
                <spring:message code="servise.picture" var="pic"/>
                <display:column title="${pic}"><img src="${row.picture}" alt="no image" width="130" height="100"></display:column>

                <spring:message var="publicationDate" code="servise.publicationDate"/>
                <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" sortable="true" />

                <display:column >
                    <acme:button url="servise/administrator/display.do?serviseId=${row.id}" code="general.display"/>
                </display:column>

                <display:column>
                    <a href="servise/administrator/edit.do?serviseId=${row.id}">
                        <input type="submit" class="btn btn-danger" name="delete"
                               value="<spring:message code="general.delete" />"
                               onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
                    </a>
                </display:column>

                <display:column>
                    <acme:button url="/question/actor/list.do?serviseId=${row.id}" code="servise.questions" />
                </display:column>

            </display:table>
        </fieldset>

        </security:authorize>
    </div>
</div>

<div class="text-center">
    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST')">
        <acme:button code="general.create" url="servise/artist/create.do"/>
    </security:authorize>
    <acme:cancel code="general.cancel" url="${cancelURI}"/>
</div>