<%--
  Created by IntelliJ IDEA.
  User: yuzi
  Date: 6/1/18
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>



<div class="container">
    <div class="row">
        <div class="col-md-12"><br><br><br>
            <div class="panel panel-default">
                <div class="panel-body">
                    <h3 class="text-center">${event.title}</h3>

                    <div class="thumbnail">
                        <img src="${event.image}" alt="..."  width="800" height="400">
                    </div>

                    <div class="caption">
                        <b><spring:message code="event.tipo" /></b><jstl:out value="${event.tipo}"/><br>

                        <b><spring:message code="event.description" /></b><jstl:out value="${event.description}"/><br>

                        <div class="pull-left">
                            <b><spring:message code="event.moment" /></b><jstl:out value="${event.moment}"/><br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

