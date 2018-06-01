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



<div class='container'>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="thumbnail">
                <img src="${event.image}" alt="..."  width="800" height="400">
                <div class="caption">
                    <h3 class="text-center">${event.title}</h3>
                    <p>${event.tipo}</p>
                    <p>
                        ${event.description}
                    </p>
                    <p class="pull-left">
                        ${event.moment}
                    </p>
                    <br>
                </div>
            </div>
        </div>
    </div>
</div>

