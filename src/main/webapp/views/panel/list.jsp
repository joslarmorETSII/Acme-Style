<%--
  Created by IntelliJ IDEA.
  User: Félix
  Date: 21/05/2018
  Time: 15:44
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


<div class="container">
    <div class="col-md-10 col-md-offset-1">

        <display:table name="panels" id="row" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">

            <acme:column code="panel.name" value="${row.name}" />

            <display:column>

                <acme:button url="panel/user/display.do?panelId=${row.id}" code="panel.display" />

            </display:column>
            
        </display:table>


    <acme:button code="panel.create" url="panel/user/create.do"/>
    </div>
</div>
