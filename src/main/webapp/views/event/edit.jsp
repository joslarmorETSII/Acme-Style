<%--
 * register.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="container">
    <div class="row">
        <div class="col-md-10">
                 <form:form  action="event/manager/edit.do" modelAttribute="event" class="form-horizontal">
                     <form:hidden path="id"/>
                     <form:hidden path="version"/>
                     <form:hidden path="manager"/>
                     <form:hidden path="participates"/>
                     <form:hidden path="artists"/>


                    <div class="form-group">
                        <label for="title" class="col-sm-3 control-label"><spring:message code="event.title"/>*</label>
                        <div class="col-sm-9">
                            <form:input path="title" class="form-control"  id="title"/>
                            <form:errors class="error" path="title" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-3 control-label"><spring:message code="event.description"/>*</label>
                        <div class="col-sm-9">
                            <form:input path="description" class="form-control"  id="description"/>
                            <form:errors class="error" path="description" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="celebrationDate" class="col-sm-3 control-label"><spring:message code="event.celebrationDate"/>* </label>
                        <div class="col-sm-9">
                            <form:input path="celebrationDate" class="form-control"  placeholder="dd/mm/yyyy HH:mm" id="celebrationDate"/>
                            <form:errors class="error" path="celebrationDate" />
                        </div>
                    </div>

                     <div class="form-group">
                         <label class="col-sm-3 control-label"><spring:message code="event.tipo" /></label>
                         <div class="col-sm-9">
                             <form:select class="form-control" id="tipo" path="tipo">
                                 <spring:message code="event.tipo.meetup" var="meetup"/><form:option value="MEETUP" label="${meetup}" />
                                 <spring:message code="event.tipo.masterclass" var="masterclass"/><form:option value="MASTERCLASS" label="${masterclass}" />
                                 <spring:message code="event.tipo.fashionShow" var="fashionShow"/><form:option value="FASHION_SHOW" label="${fashionShow}" />
                                 <spring:message code="event.tipo.exposition" var="exposition"/><form:option value="EXPOSITION" label="${exposition}" />
                             </form:select>
                             <form:errors path="tipo" cssClass="error" />
                         </div>
                     </div>

                     <div class="form-group">
                         <label for="tipo" class="col-sm-3 control-label"><spring:message code="event.image"/>*</label>
                         <div class="col-sm-9">
                             <form:input path="image" class="form-control"  id="tipo" />
                             <form:errors class="error" path="image" />
                         </div>
                     </div>
                    <div class="form-group">
                        <label for="price" class="col-sm-3 control-label"><spring:message code="event.price"/>*</label>
                        <div class="col-sm-9">
                            <form:input path="price" class="form-control"  id="price" />
                            <form:errors class="error" path="price" />
                        </div>
                    </div>

                     <div class="form-group">
                         <label class="col-sm-3 control-label"><spring:message code="event.store" /></label>
                         <div class="col-sm-9">
                             <form:select path="store">
                             <form:option label="----" value="0"/>
                             <form:options class="form-control" items="${stores}" itemLabel="title"/>
                             </form:select>
                             <form:errors path="store" cssClass="error" />
                         </div>
                     </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label"><spring:message code="event.location"/>*</label>
                        <div class="col-sm-9">
                            <form:input path="location.name" class="form-control" />
                            <form:errors class="error" path="location.name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label  class="col-sm-3 control-label"><spring:message code="location.latitude"/> </label>
                        <div class="col-sm-9">
                            <form:input path="location.latitude" class="form-control"  />
                            <form:errors class="error" path="location.latitude"/>
                        </div>
                    </div>

                     <div class="form-group">
                         <label class="col-sm-3 control-label"><spring:message code="location.longitude"/> </label>
                         <div class="col-sm-9">
                             <form:input path="location.longitude" class="form-control"  />
                             <form:errors class="error" path="location.longitude"/>
                         </div>
                     </div>

                     <div class="text-center">
                        <input type="submit" class="btn btn-primary"  name="save" id="saveButton" value="<spring:message code="general.save"/>"/>
                         <jstl:if test="${event.id != 0}">
                         <input type="submit" class="btn btn-danger"  name="delete" id="saveButton" value="<spring:message code="general.delete"/>"/>
                         </jstl:if>
                         <acme:cancel url="event/manager/list.do" code="general.cancel"/>
                     </div>

                 </form:form>
        </div>
    </div>
</div>

