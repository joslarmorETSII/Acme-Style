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
                 <form:form  action="store/manager/edit.do" modelAttribute="store" class="form-horizontal">
                     <form:hidden path="id"/>
                     <form:hidden path="version"/>
                     <form:hidden path="manager"/>


                    <div class="form-group">
                        <label for="title" class="col-sm-3 control-label"><spring:message code="store.title"/>*</label>
                        <div class="col-sm-9">
                            <form:input path="title" class="form-control"  id="title"/>
                            <form:errors class="error" path="title" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="banner" class="col-sm-3 control-label"><spring:message code="store.banner"/></label>
                        <div class="col-sm-9">
                            <form:input path="banner" placeHolder="http://www.google.com/pic.jpg" class="form-control"  id="banner"/>
                            <form:errors class="error" path="banner" />
                        </div>
                    </div>


                     <div class="form-group">
                         <label class="col-sm-3 control-label"><spring:message code="store.servise" /></label>
                         <div class="col-sm-9">
                             <form:select path="servises" class="form-control" multiple="true">
                                 <form:options class="form-control" items="${servises}" itemLabel="title"/>
                             </form:select>
                             <form:errors path="servises" cssClass="error" />
                         </div>
                     </div>


                    <div class="form-group">
                        <label class="col-sm-3 control-label"><spring:message code="event.location"/>*</label>
                        <div class="col-sm-9">
                            <form:input path="gpsCoordinates.name" placeHolder="Sevilla" class="form-control" />
                            <form:errors class="error" path="gpsCoordinates.name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label  class="col-sm-3 control-label"><spring:message code="location.latitude"/> </label>
                        <div class="col-sm-9">
                            <form:input path="gpsCoordinates.latitude" placeHolder="[-90.0, 90.0]" class="form-control"  />
                            <form:errors class="error" path="gpsCoordinates.latitude"/>
                        </div>
                    </div>

                     <div class="form-group">
                         <label class="col-sm-3 control-label"><spring:message code="location.longitude"/> </label>
                         <div class="col-sm-9">
                             <form:input path="gpsCoordinates.longitude" placeHolder="[-180.0, 180.0]" class="form-control"  />
                             <form:errors class="error" path="gpsCoordinates.longitude"/>
                         </div>
                     </div>

                     <div class="form-group">
                             <label class="col-sm-3 control-label"><spring:message code="user.holder"/> </label>
                             <div class="col-sm-9">
                                 <form:input path="creditCard.holder" class="form-control"  />
                                 <form:errors class="error" path="creditCard.holder"/>
                             </div>
                     </div>

                     <div class="form-group">
                         <label class="col-sm-3 control-label"><spring:message code="user.brand"/> </label>
                         <div class="col-sm-9">
                             <form:input path="creditCard.brand" class="form-control"  />
                             <form:errors class="error" path="creditCard.brand"/>
                         </div>
                     </div>

                     <div class="form-group">
                         <label class="col-sm-3 control-label"><spring:message code="user.number"/> </label>
                         <div class="col-sm-9">
                             <form:input path="creditCard.number" class="form-control"  />
                             <form:errors class="error" path="creditCard.number"/>
                         </div>
                     </div>

                     <div class="form-group">
                         <label class="col-sm-3 control-label"><spring:message code="user.expirationMonth"/> </label>
                         <div class="col-sm-9">
                             <form:input path="creditCard.expirationMonth" placeHolder="MM" class="form-control"  />
                             <form:errors class="error" path="creditCard.expirationMonth"/>
                         </div>
                     </div>

                     <div class="form-group">
                         <label class="col-sm-3 control-label"><spring:message code="user.expirationYear"/> </label>
                         <div class="col-sm-9">
                             <form:input path="creditCard.expirationYear" placeHolder="yyyy" class="form-control"  />
                             <form:errors class="error" path="creditCard.expirationYear"/>
                         </div>
                     </div>

                     <div class="form-group">
                         <label class="col-sm-3 control-label"><spring:message code="user.cvv"/> </label>
                         <div class="col-sm-9">
                             <form:input path="creditCard.cvv" placeHolder="XXX" class="form-control"  />
                             <form:errors class="error" path="creditCard.cvv"/>
                         </div>
                     </div>

                     <div class="text-center">
                        <input type="submit" class="btn btn-primary"  name="save" id="saveButton" value="<spring:message code="general.save"/>"/>
                         <jstl:if test="${store.id != 0}">
                         <input type="submit" class="btn btn-danger"  name="delete" id="saveButton" value="<spring:message code="general.delete"/>"/>
                         </jstl:if>
                         <acme:cancel url="store/manager/list.do" code="general.cancel"/>
                     </div>

                 </form:form>
        </div>
    </div>
</div>

