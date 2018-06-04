<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 04/04/2018
  Time: 18:14
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<div class="container">
    <div class="row">
        <div class="col-md-12"><br><br><br>
            <div class="panel panel-default">
                <div class="panel-body">

                    <center>
                        <h4><jstl:out value="${servise.title}"/></h4>
                    </center>

                    <div class="thumbnail">
                        <img src="${servise.picture}" width="500px" height="100%" class="img-responsive" />
                    </div>

                    <b><spring:message code="servise.description" /></b><jstl:out value="${servise.description}"/><br>

                    <b><spring:message code="servise.creator.name" /></b> <jstl:out value="${servise.creator.name}"/><br>

                    <b><spring:message code="servise.price" /> </b> <jstl:out value="${servise.price}"/><br>

                    <b><spring:message code="servise.discount" /></b> <jstl:out value="${servise.discount}"/><br>

                    <b><spring:message code="servise.finalPrice" /></b> <jstl:out value="${finalPrice}"/><br>

                    <br><br>

                </div>
            </div>
        </div>
    </div>
</div>

    <center>
        <legend><spring:message code="servise.feedback" /> </legend>
    </center>
    <jstl:forEach var="feedback" items="${servise.feedbacks}">
    <div class="container">
        <div class="comment">
            <div class="col-md-12"><br><br><br>
                <div class="panel panel-default">
                    <div class="panel-body">

                        <center>
                            <h4><jstl:out value="${feedback.user.name}"/></h4>
                        </center>
                        <b><spring:message code="feedback.points" /></b> <jstl:out value="${feedback.points}"/><br>

                        <b><spring:message code="feedback.text" /></b> <jstl:out value="${feedback.text}"/><br>

                        <security:authorize access="hasRole('ADMINISTRATOR')" >
                            <acme:button url="feedback/administrator/edit.do?feedbackId=${feedback.id}" code="general.delete" />
                        </security:authorize>

                    </div>
                </div>
            </div>
        </div>
    </div>
</jstl:forEach>
<div class="well">

    <jstl:forEach var="question" items="${servise.questions}">
        <div class="container">
            <div class="comment">
                <div class="col-md-12"><br><br><br>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <center>
                                <legend><spring:message code="servise.questions" /> </legend>
                            </center>
                            <b><spring:message code="question.text" /></b> <jstl:out value="${question.text}"/><br>

                            <spring:message var="patternDate" code="event.pattern.date" />
                            <b><spring:message code="question.moment"/>:&nbsp;</b> <fmt:formatDate value="${question.moment}" pattern="${patternDate}"/>
                            <center>
                                <legend><spring:message code="question.answers" /> </legend>
                            </center>
                             <jstl:forEach var="answer" items="${question.answers}">
                                <div class="panel-body">
                                    <b><spring:message code="answer.text" /></b> <jstl:out value="${answer.text}"/><br>

                                    <spring:message var="patternDate" code="event.pattern.date" />
                                    <b><spring:message code="answer.moment"/>:&nbsp;</b> <fmt:formatDate value="${answer.moment}" pattern="${patternDate}"/>
                                </div>
                             </jstl:forEach>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jstl:forEach>


</div>
<center>
<input type="button" class="btn btn-warning" name="cancel" value="<spring:message code="general.cancel" />"
       onclick="javascript: relativeRedir('${cancelURI}');" />
</center>