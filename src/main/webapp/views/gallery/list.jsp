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

<jstl:set var="x" value="1"/>
<div class="container">
<section>
    <div class="container gal-container">
        <div class="col-md-8 col-sm-12 co-xs-12 gal-item">
            <div class="box">
                <a href="#" data-toggle="modal" data-target="#1">
                    <img src=${profile.profilePhoto}>
                </a>
                <div class="modal fade" id="1" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <div class="modal-body">
                                <img src=${profile.profilePhoto}>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jstl:forEach var="gallery" items="${galleries}" >
        <jstl:set var="x" value="${x +1}"/>
            <div class="col-md-4 col-sm-6 co-xs-12 gal-item">

                <div class="box">
                    <a href="#" data-toggle="modal" data-target="#${x}">
                        <img src="${gallery.picture}">
                    </a>
                    <div class="modal fade" id="${x}" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                <div class="modal-body">
                                    <img src="${gallery.picture}">
                                </div>
                                <div class="col-md-12 description">
                                    <h4><jstl:if test="${owner}">
                                        <a href="gallery/actor/delete.do?galleryId=${gallery.id}">
                                            <input type="submit" class="btn btn-danger" name="delete"
                                                   value="<spring:message code="general.delete" />"
                                                   onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
                                        </a>
                                    </jstl:if>
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </jstl:forEach>
    </div>
</section>
</div>
<div class="text-center" >
    <jstl:if test="${owner}">
        <acme:button code="general.create" url="gallery/actor/create.do"/>
    </jstl:if>
    <acme:cancel url="${cancelURI}" code="general.cancel"/>
</div>