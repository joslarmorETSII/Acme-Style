<%--
  Created by IntelliJ IDEA.
  User: F?lix
  Date: 22/02/2018
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>


<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<!-- Queries level c -->

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.avgMinMaxServicesPerPhotographer"/></b>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMinMaxServicesPerPhotographer[0]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMinMaxServicesPerPhotographer[1]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMinMaxServicesPerPhotographer[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.avgMinMaxServicesPerMakeupArtist"/></b>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMinMaxServicesPerMakeupArtist[0]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMinMaxServicesPerMakeupArtist[1]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMinMaxServicesPerMakeupArtist[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.avgMinMaxServicesPerStylist"/></b>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMinMaxServicesPerStylist[0]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMinMaxServicesPerStylist[1]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMinMaxServicesPerStylist[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend><b><spring:message code="dash.numberServiseWithDiscountOverFive"/></b></legend>

        <jstl:out value="${numberServiseWithDiscountOverFive}"/><br>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.serviseBestSuscription"/></b>
        <br/>
        <h4><jstl:out value=" NAME: "/><jstl:out value="${serviseBestSuscription[0]}"/> <br/></h4>
        <h4><jstl:out value=" COUNT: "/><jstl:out value="${serviseBestSuscription[1]}"/> <br/></h4>
    </div>
</fieldset>
<br/>


<fieldset>
    <b><spring:message code="dash.topFiveServiseWithSubscriptions"/></b>
    <display:table name="topFiveServiseWithSubscriptions" id="row" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">

        <acme:column code="servise.title" value="${row.title} " />
        <acme:column code="servise.description" value="${row.description}"/>
        <spring:message var="publicationDate" code="servise.publicationDate"/>
        <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" sortable="true" />
        <spring:message var="formatDate" code="event.format.date"/>
        <acme:column code="servise.taboo" value="${row.taboo}"/>
        <spring:message code="servise.picture" var="pic"/>
        <display:column title="${pic}"><img src="${row.picture}" alt="no image" width="130" height="100"></display:column>

        <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" sortable="true" />
        <acme:column code="servise.price" value="${row.price}"/>
        <acme:column code="servise.discount" value="${row.discount}"/>

    </display:table>
</fieldset>


        <fieldset>
            <b><spring:message code="dash.topTenServiseWithSubscriptions"/></b>
            <display:table name="topTenServiseWithSubscriptions" id="row" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">

                <acme:column code="servise.title" value="${row.title} " />
                <acme:column code="servise.description" value="${row.description}"/>
                <spring:message var="publicationDate" code="servise.publicationDate"/>
                <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" sortable="true" />
                <acme:column code="servise.taboo" value="${row.taboo}"/>
                <spring:message code="servise.picture" var="pic"/>
                <display:column title="${pic}"><img src="${row.picture}" alt="no image" width="130" height="100"></display:column>


                <spring:message var="formatDate" code="event.format.date"/>
                <acme:column code="servise.price" value="${row.price}"/>
                <acme:column code="servise.discount" value="${row.discount}"/>

            </display:table>
        </fieldset>

<br/>

<fieldset>
    <div class="panel-body">
        <legend><b><spring:message code="dash.avgQuestionPerServiseForAllPhotographer"/></b></legend>

        <jstl:out value="${avgQuestionPerServiseForAllPhotographer}"/><br>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.avgAndSqrtCommentsPerUser"/></b>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgAndSqrtCommentsPerUser[0]}"/> <br/></h4>
        <h4><jstl:out value=" SQRT: "/><jstl:out value="${avgAndSqrtCommentsPerUser[1]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend><b><spring:message code="dash.servicesMoreAnsThan60PercentAboutQuestiosn"/></b></legend>

        <jstl:out value="${servicesMoreAnsThan60PercentAboutQuestiosn}"/><br>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.avgMinMaxStoresPerService"/></b>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMinMaxStoresPerService[0]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMinMaxStoresPerService[1]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMinMaxStoresPerService[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.avgSqrtEventsPerManager"/></b>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgSqrtEventsPerManager[0]}"/> <br/></h4>
        <h4><jstl:out value=" SQRT: "/><jstl:out value="${avgSqrtEventsPerManager[1]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.avgSqrtParticipatersParticipateAEventStore"/></b>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgSqrtParticipatersParticipateAEventStore[0]}"/> <br/></h4>
        <h4><jstl:out value=" SQRT: "/><jstl:out value="${avgSqrtParticipatersParticipateAEventStore[1]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend><b><spring:message code="dash.ratioEventsStoreVSEvents"/></b></legend>

        <jstl:out value="${ratioEventsStoreVSEvents}"/><br>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.numberOfLikePostPerCategory"/></b>
        <br/>
        <h4><jstl:out value=" LIKES: "/><jstl:out value="${numberOfLikePostPerCategory[0]}"/> <br/></h4>
        <h4><jstl:out value=" NAME: "/><jstl:out value="${numberOfLikePostPerCategory[1]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

        <fieldset>
            <b><spring:message code="dash.listingPostByMomentOfCreation"/></b>
            <display:table name="listingPostByMomentOfCreation" id="row" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">

                <acme:column code="post.title" value="${row.title} " />
                <acme:column code="post.description" value="${row.description}"/>
                <spring:message var="moment" code="post.moment"/>
                <display:column property="moment" title="${publicationDate}" format="${formatDate}" sortable="true" />
                <spring:message var="endDate" code="post.endDate"/>
                <display:column property="endDate" title="${publicationDate}" format="${formatDate}" sortable="true" />
                <spring:message code="post.picture" var="pic"/>
                <display:column title="${pic}"><img src="${row.picture}" alt="no image" width="130" height="100"></display:column>


                <acme:column code="post.price" value="${row.price}"/>
                <acme:column code="post.discount" value="${row.reward}"/>

                <acme:column code="post.finalMode" value="${row.finalMode}"/>
                <acme:column code="post.hasWinner" value="${row.hasWinner}"/>
                <acme:column code="post.reward" value="${row.reward} " />

            </display:table>
        </fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.numberOfHeartPostPerCategory"/></b>
        <br/>
        <h4><jstl:out value=" SUM: "/><jstl:out value="${numberOfHeartPostPerCategory[0]}"/> <br/></h4>
        <h4><jstl:out value=" NAME: "/><jstl:out value="${numberOfHeartPostPerCategory[1]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.topTenPostsWithLikesLoves"/></b>
        <br/>
        <h4><jstl:out value=" TITLE: "/><jstl:out value="${topTenPostsWithLikesLoves[0]}"/> <br/></h4>
        <h4><jstl:out value=" LIKES: "/><jstl:out value="${topTenPostsWithLikesLoves[1]}"/> <br/></h4>
        <h4><jstl:out value=" LOVES: "/><jstl:out value="${topTenPostsWithLikesLoves[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.avgMaxMinSqrtPhotosPerPanel"/></b>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMaxMinSqrtPhotosPerPanel[0]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMaxMinSqrtPhotosPerPanel[1]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMaxMinSqrtPhotosPerPanel[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <b><spring:message code="dash.avgMaxMinSqrtPanelsPerUser"/></b>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMaxMinSqrtPanelsPerUser[0]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMaxMinSqrtPanelsPerUser[1]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMaxMinSqrtPanelsPerUser[2]}"/> <br/></h4>
        <h4><jstl:out value=" SQRT: "/><jstl:out value="${avgMaxMinSqrtPanelsPerUser[3]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend><b><spring:message code="dash.ratioPostsWithRaffles"/></b></legend>

        <jstl:out value="${ratioPostsWithRaffles}"/><br>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend><b><spring:message code="dash.avgPostWithReward"/></b></legend>

        <jstl:out value="${avgPostWithReward}"/><br>
    </div>
</fieldset>
<br/>

        <fieldset>
            <b><spring:message code="dash.listingPostByEndDate"/></b>
            <display:table name="listingPostByEndDate" id="row" pagesize="5" class="table table-striped table-hover" requestURI="${requestURI}">

                <acme:column code="post.title" value="${row.title} " />
                <acme:column code="post.description" value="${row.description}"/>
                <spring:message var="moment" code="post.moment"/>
                <display:column property="moment" title="${publicationDate}" format="${formatDate}" sortable="true" />
                <spring:message var="endDate" code="post.endDate"/>
                <display:column property="endDate" title="${publicationDate}" format="${formatDate}" sortable="true" />
                <spring:message code="post.picture" var="pic"/>
                <display:column title="${pic}"><img src="${row.picture}" alt="no image" width="130" height="100"></display:column>


                <acme:column code="post.price" value="${row.price}"/>
                <acme:column code="post.discount" value="${row.reward}"/>

                <acme:column code="post.finalMode" value="${row.finalMode}"/>
                <acme:column code="post.hasWinner" value="${row.hasWinner}"/>
                <acme:column code="post.reward" value="${row.reward} " />

            </display:table>
        </fieldset>

<br/>

