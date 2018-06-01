<%--
  Created by IntelliJ IDEA.
  User: yuzi
  Date: 6/1/18
  Time: 5:17 PM
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

                        <center>
                            <h4><jstl:out value="${store.title}"/></h4>
                        
                            <img src="${store.banner}" width="500px" height="100%" class="img-responsive" /> <hr>
                        </center>
                    </div>
                    <div class="panel-footer">
                        <jstl:out value="${store.gpsCoordinates.name}"/>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="col-md-10 col-md-offset-1">
        <button type="button" class="btn btn-warning" onclick="javascript: relativeRedir('${cancelURI}')" >
            <spring:message code="general.cancel" />
        </button>
    </div>

