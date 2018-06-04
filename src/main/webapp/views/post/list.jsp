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
<%@ taglib prefix="fnt" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <input id="myInput" type="text" placeholder="Search..">
</div>

<br><br>
<jstl:forEach var="row" items="${posts}">


    <div class="container">
        <div class="row">
            <div class="col-md-12"><br><br><br>
                <div class="panel panel-default">
                    <div class="panel-body">
                            <div class="pull-right">
                            <jstl:if test="${row.raffle}">
                                <img src="http://1.bp.blogspot.com/-bkJooiCloYQ/UrsYojHuF3I/AAAAAAAAIGY/aWFiQRGWzGE/s1600/Imagen-animada-Regalos-de-navidad-64.gif" width="150px" height="100%" class="img-responsive" />
                            </jstl:if>
                            </div>
                        <center>
                            <h4><jstl:out value="${row.title}"/></h4>
                        </center>
                        <p><jstl:out value="${row.description}"/><br><p>
                        <div class="thumbnail">
                            <img src="${row.picture}" width="500px" height="100%" class="img-responsive" />

                        <security:authorize access="hasRole('USER')">
                        <div class="container">
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#${row.id}">Add to Panel</button>
                            <!-- Modal -->
                            <div class="modal fade" id="${row.id}" role="dialog">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Select a Panel</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p>My panels</p>
                                            <form action="panel/user/add.do"  method="get" role="add">
                                                <div class="input-group">
                                                    <jstl:if test="${not empty myPanels}">
                                                        <select name="panelId">
                                                                <jstl:forEach var="panel" items="${myPanels}">
                                                                    <option value="${panel.id}" >${panel.name}</option>
                                                                </jstl:forEach>

                                                        </select>
                                                    </jstl:if>
                                                    <input  name="postId" value="${row.id}" hidden="true">
                                                    <jstl:if test="${not empty myPanels}">
                                                        <div class="input-group-btn"><button class="btn btn-success" type="submit">Add</button></div>
                                                    </jstl:if>

                                                </div>
                                            </form>
                                        </div>
                                        <jstl:if test="${ empty myPanels}">
                                            <spring:message code="panels.add"/>
                                        </jstl:if>

                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </security:authorize>
                    </div>

                    <jstl:if test="${row.raffle}">
                        <b><spring:message code="post.raffle"/>:&nbsp;</b> <spring:message code="post.thisIsARaffle"/>
                        <br/>
                    </jstl:if>

                    <spring:message var="patternDate" code="event.pattern.date" />
                    <b><spring:message code="post.moment"/>:&nbsp;</b> <fmt:formatDate value="${row.moment}" pattern="${patternDate}"/><br/>

                    <b><spring:message code="post.catrgories"/>:&nbsp;</b>
                    <div class="well">
                        <for:forEach var="category" items="${row.categories}">
                            <p>#<jstl:out value="${category.name}"/>, <p>
                        </for:forEach>
                    </div>

            <br><br>
                        <div class="pull-left">
                            <jstl:out value="${row.lik}"/><a href="post/actor/lik.do?postId=${row.id}"><button type="button" class="btn btn-outline"> <span class="btn-label"><i class="glyphicon glyphicon-thumbs-up"></i></span></button></a>
                            <jstl:out value="${row.dislike}"/><a href="post/actor/dislike.do?postId=${row.id}"><button type="button" class="btn btn-outline"> <span class="btn-label"><i class="glyphicon glyphicon-thumbs-down"></i></span></button></a>
                            <jstl:out value="${row.heart}"/><a href="post/actor/heart.do?postId=${row.id}"><button type="button" class="btn btn-outline"> <span class="btn-label"><i class="glyphicon glyphicon-heart"></i></span></button></a>
                        </div>

                        <div class="pull-right">

                            <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST','USER', 'MANAGER')">
                                <jstl:if test="${row.finalMode eq true}">
                                    <acme:button code="general.create.comment" url="comment/actor/create.do?postId=${row.id}"/>
                                </jstl:if>
                            </security:authorize>

                            <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST', 'USER', 'MANAGER')" >
                                <jstl:if test="${row.finalMode eq false}">
                                    <acme:button url="post/actor/edit.do?postId=${row.id}" code="general.edit" />
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

    </jstl:forEach>

<br/>
<div class="text-center">
    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST','USER', 'MANAGER')">
        <acme:button code="general.create" url="post/actor/create.do"/>
    </security:authorize>

    <acme:cancel code="general.cancel" url="${cancelUri}"/>
</div>

<script>
    $(document).ready(function(){
        $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $(".panel").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>