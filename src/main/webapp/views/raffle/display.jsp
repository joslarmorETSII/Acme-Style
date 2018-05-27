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
        <div class="row">
            <div class="col-md-12"><br><br><br>
                <div class="panel panel-default">
                    <div class="panel-body">

                        <center>
                            <h4><jstl:out value="${row.title}"/></h4>
                        </center>
                        <p><jstl:out value="${row.description}"/><br><br>

                        <p><jstl:out value="${row.reward}"/><br><br>

                        <p><jstl:out value="${row.endDate}"/><br><br>

                        <div class="pull-right">

                            <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST', 'USER', 'MANAGER')" >
                                <jstl:if test="${row.post.actor eq actor}" >
                                <acme:button url="raffle/actor/edit.do?raffleId=${row.id}" code="general.edit" />
                                </jstl:if>
                            </security:authorize>

                            <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST', 'USER', 'MANAGER')" >
                                <jstl:if test="${row.post.actor eq actor}" >
                                <acme:button url="raffle/actor/edit.do?raffleId=${row.id}" code="general.delete" />
                                </jstl:if>
                            </security:authorize>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<br/>
<div class="text-center">

    <acme:cancel code="general.cancel" url="${cancelURI}"/>
</div>
