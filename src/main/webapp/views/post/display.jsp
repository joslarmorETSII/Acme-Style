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

                                <p><jstl:out value="${row.description}"/><br><p>

                                <div class="thumbnail">
                                    <img src="${row.picture}" width="500px" height="100%" class="img-responsive" />
                                </div>

                                <jstl:out value="${row.moment}"/>

                                <br><br>

                                <div class="pull-left">
                                    <jstl:out value="${row.lik}"/><a href="post/actor/lik.do?postId=${row.id}"><button type="button" class="btn btn-outline"> <span class="btn-label"><i class="glyphicon glyphicon-thumbs-up"></i></span></button></a>
                                    <jstl:out value="${row.dislike}"/><a href="post/actor/dislike.do?postId=${row.id}"><button type="button" class="btn btn-outline"> <span class="btn-label"><i class="glyphicon glyphicon-thumbs-down"></i></span></button></a>
                                    <jstl:out value="${row.heart}"/><a href="post/actor/heart.do?postId=${row.id}"><button type="button" class="btn btn-outline"> <span class="btn-label"><i class="glyphicon glyphicon-heart"></i></span></button></a>
                                </div>

                                <div class="pull-right">
                                    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST','USER', 'MANAGER')">
                                        <acme:button code="general.create.comment" url="comment/actor/create.do?postId=${row.id}"/>
                                    </security:authorize>

                                    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST', 'USER', 'MANAGER')" >
                                        <jstl:if test="${row.finalMode eq false}">
                                            <acme:button url="post/actor/edit.do?postId=${row.id}" code="general.edit" />
                                        </jstl:if>
                                    </security:authorize>

                                    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST', 'USER', 'MANAGER')" >
                                        <jstl:if test="${row.raffle eq true && notParticipates eq false}">
                                            <acme:button url="post/actor/edit.do?postId=${row.id}" code="general.delete" />
                                        </jstl:if>
                                    </security:authorize>

                                    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST', 'USER', 'MANAGER')" >
                                        <jstl:if test="${row.raffle eq false}">
                                            <acme:button url="post/actor/edit.do?postId=${row.id}" code="general.delete" />
                                        </jstl:if>
                                    </security:authorize>

                                    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST','USER', 'MANAGER')">
                                        <acme:button code="general.create.display" url="post/actor/display.do?postId=${row.id}"/>
                                    </security:authorize>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>

            <jstl:forEach var="comment" items="${comments}">
                <div class="container">
                    <div class="comment">
                        <div class="col-md-12"><br><br><br>
                            <div class="panel panel-default">
                                <div class="panel-body">

                                    <center>
                                        <h4><jstl:out value="${comment.actor.name}"/></h4>
                                    </center>
                                    <p><jstl:out value="${comment.text}"/><br><br>

                                    <p><jstl:out value="${row.moment}"/><br><br>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </jstl:forEach>

<br/>
<div class="text-center">

    <acme:cancel code="general.cancel" url="${cancelURI}"/>
</div>
