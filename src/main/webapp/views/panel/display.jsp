<%--
  Created by IntelliJ IDEA.
  User: Félix
  Date: 22/05/2018
  Time: 18:29
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

<center>
    <h4><jstl:out value="${panel.name}"/></h4>
</center>

<jstl:forEach var="row" items="${photos}">

    <div class="container">
        <div class="row">
            <div class="col-md-12"><br><br><br>
                <div class="panel panel-default">
                    <div class="panel-body">

                        <center>
                            <img src="${row.url}" width="500px" height="100%" class="img-responsive" /></br>

                            <b><spring:message code="photo.descripcion"/></b> <jstl:out value="${row.description}" />
                            <hr>
                        </center>



                        <acme:button url="photo/user/delete.do?photoId=${row.id}" code="general.delete" />

                    </div>
                    
                </div>
            </div>


        </div>

    </div>
    </div>
    </body>


</jstl:forEach>
    <center>
    <div class="col-md-10 col-md-offset-1">
        <button type="button" class="btn btn-info" onclick="javascript: relativeRedir('${redirURI}')" >
            <spring:message code="post.user" />
        </button>

        <button type="button" class="btn btn-warning" onclick="javascript: relativeRedir('${cancelURI}')" >
            <spring:message code="general.cancel" />
        </button>
    </div>
    </center>
<br/>





