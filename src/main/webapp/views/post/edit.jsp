<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 21/05/2018
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 04/04/2018
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="row">
        <div class="col-md-10">
<form:form action="${actionURI}" modelAttribute="post" class="form-horizontal">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <div class="form-group">
        <label class="col-sm-3 control-label"><spring:message code="message.category" /></label>
        <div class="col-sm-9">
            <form:select class="form-control" path="categories" items="${allCategories}" itemLabel="name"/>
            <form:errors class="error" path="categories" />

        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label"><spring:message code="post.title"/></label>
        <div class="col-sm-9">
            <form:input path="title" class="form-control"  />
            <form:errors class="error" path="title" />
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label"><spring:message code="post.description"/></label>
        <div class="col-sm-9">
            <form:input path="description" class="form-control"  />
            <form:errors class="error" path="description" />
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label"><spring:message code="post.picture"/></label>
        <div class="col-sm-9">
            <form:input path="picture" class="form-control"  />
            <form:errors class="error" path="picture" />
        </div>
    </div>

    <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST','USER', 'MANAGER')">

    <div class="thumbnail">
        <div class="form-group">
            <label class="col-sm-3 control-label"><spring:message code="post.raffle"/></label>
            <div class="col-sm-9">
                <form:checkbox id="checkRaffle" onclick="comprobar();" path="raffle" class="form-control"/>
                <form:errors class="error" path="raffle" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label"><spring:message code="post.reward"/></label>
            <div class="col-sm-9">
                <form:input path="reward" class="form-control" id="checkReward"/>
                <form:errors class="error" path="reward" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label"><spring:message code="post.endDate"/></label>
            <div class="col-sm-9">
                <form:input path="endDate" class="form-control" id="checkEndDate" />
                <form:errors class="error" path="endDate" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label"><spring:message code="post.finalMode"/></label>
            <div class="col-sm-9">
                <form:checkbox path="finalMode" class="form-control" id="checkFinalMode" />
                <form:errors class="error" path="finalMode" />
            </div>
    </div>
    </security:authorize>

    </div>

    <div class="text-center">

        <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST','USER', 'MANAGER')">
            <jstl:if test="${post.raffle eq true && post.finalMode eq false || post.id == 0 }" >
                <acme:submit name="save" code="general.save"/>
            </jstl:if>
        </security:authorize>

        <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST','USER', 'MANAGER')">
            <jstl:if test="${post.id !=0 && post.raffle eq false}">
                <input type="submit" class="btn btn-danger"  name="delete" id="saveButton" value="<spring:message code="general.delete"/>"/>
            </jstl:if>
        </security:authorize>

        <security:authorize access="hasAnyRole('STYLIST','PHOTOGRAPHER','MAKEUPARTIST','USER', 'MANAGER')">
            <jstl:if test="${post.id !=0 && post.raffle eq true && empty post.comments || post.hasWinner eq true}">
                <input type="submit" class="btn btn-danger"  name="delete" id="saveButton2" value="<spring:message code="general.delete"/>"/>
            </jstl:if>
        </security:authorize>

        <acme:cancel code="general.cancel" url="${cancelURI}"/>
    </div>

</form:form>

        </div>
    </div>
</div>

<jstl:if test="${post.raffle eq false}">
<script>
    document.getElementById("checkRaffle").checked = false;
    document.getElementById("checkReward").disabled = true;
    document.getElementById("checkEndDate").disabled = true;
    document.getElementById("checkFinalMode").disabled = true;

    function comprobar() {

        var aux = document.getElementById("checkRaffle").checked;

        if(aux == true) {
            document.getElementById("checkReward").disabled = false;
            document.getElementById("checkEndDate").disabled = false;
            document.getElementById("checkFinalMode").disabled = false;
        }else{
            document.getElementById("checkReward").disabled = true;
            document.getElementById("checkEndDate").disabled = true;
            document.getElementById("checkFinalMode").disabled = true;
        }
    }
</script>
</jstl:if>

<jstl:if test="${post.raffle eq true}">
    <script>
        document.getElementById("checkRaffle").checked = true;
        document.getElementById("checkReward").disabled = false;
        document.getElementById("checkEndDate").disabled = false;
        document.getElementById("checkFinalMode").disabled = false;

        function comprobar() {

            var aux = document.getElementById("checkRaffle").checked;

            if(aux == true) {
                document.getElementById("checkReward").disabled = false;
                document.getElementById("checkEndDate").disabled = false;
                document.getElementById("checkFinalMode").disabled = false;
            }else{
                document.getElementById("checkReward").disabled = true;
                document.getElementById("checkEndDate").disabled = true;
                document.getElementById("checkFinalMode").disabled = true;
            }
        }
    </script>
</jstl:if>

