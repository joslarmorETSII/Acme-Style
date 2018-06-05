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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>

<jstl:if test="${pageContext.response.locale.language == 'es' }">
    <jstl:set value="{0,date,dd/MM/yyyy HH:mm}" var="formatDate"/>
</jstl:if>

<jstl:if test="${pageContext.response.locale.language == 'en' }">
    <jstl:set value="{0,date,yyyy/MM/dd HH:mm}" var="formatDate"/>
</jstl:if>
<!-- Queries level c -->

<div class="container">
    <div class="col-md-10 col-md-offset-1">

<fieldset>
    <div class="panel-body">

         <legend class="text-center"> <b><spring:message code="dash.avgMinMaxServicesPerPhotographer"/></b></legend>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMinMaxServicesPerPhotographer[0]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMinMaxServicesPerPhotographer[1]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMinMaxServicesPerPhotographer[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>


<fieldset>
    <div class="panel-body">
        <legend class="text-center"> <b><spring:message code="dash.avgMinMaxServicesPerMakeupArtist"/></b></legend>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMinMaxServicesPerMakeupArtist[0]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMinMaxServicesPerMakeupArtist[1]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMinMaxServicesPerMakeupArtist[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.avgMinMaxServicesPerStylist"/></b></legend>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMinMaxServicesPerStylist[0]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMinMaxServicesPerStylist[1]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMinMaxServicesPerStylist[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.numberServiseWithDiscountOverFive"/></b></legend>

        <h4><jstl:out value="${numberServiseWithDiscountOverFive}"/><br></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.serviseBestSuscription"/></b></legend>
        <br/>

        <display:table name="serviseBestSuscription" id="row10" pagesize="5" class="table table-striped table-hover" requestURI="dashboard/administrator/dashboard.do">

            <acme:column code="servise.title" value="${row10.title} " />
            <acme:column code="servise.description" value="${row10.description}"/>
            <spring:message var="publicationDate" code="servise.publicationDate"/>
            <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" sortable="true" />
            <acme:column code="servise.taboo" value="${row10.taboo}"/>
            <spring:message code="servise.picture" var="pic"/>
            <display:column title="${pic}"><img src="${row10.picture}" alt="no image" width="130" height="100"></display:column>

            <spring:message var="formatDate" code="event.format.date"/>
            <acme:column code="servise.price" value="${row10.price}"/>

        </display:table>
    </div>
</fieldset>
<br/>


<fieldset>
    <legend class="text-center"><b><spring:message code="dash.topFiveServiseWithSubscriptions"/></b></legend>
    <display:table name="topFiveServiseWithSubscriptions" id="row1" pagesize="5" class="table table-striped table-hover" requestURI="dashboard/administrator/dashboard.do">

        <acme:column code="servise.title" value="${row1.title} " />
        <acme:column code="servise.description" value="${row1.description}"/>
        <spring:message var="publicationDate" code="servise.publicationDate"/>
        <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" sortable="true" />
        <spring:message var="formatDate" code="event.format.date"/>
        <acme:column code="servise.taboo" value="${row1.taboo}"/>
        <spring:message code="servise.picture" var="pic"/>
        <display:column title="${pic}"><img src="${row1.picture}" alt="no image" width="130" height="100"></display:column>

        <acme:column code="servise.price" value="${row1.price}"/>
        <acme:column code="servise.discount" value="${row1.discount}"/>

    </display:table>
</fieldset>


        <fieldset>
            <legend class="text-center"><b><spring:message code="dash.topTenServiseWithSubscriptions"/></b></legend>
            <display:table name="topTenServiseWithSubscriptions" id="row2" pagesize="5" class="table table-striped table-hover" requestURI="dashboard/administrator/dashboard.do">

                <acme:column code="servise.title" value="${row2.title} " />
                <acme:column code="servise.description" value="${row2.description}"/>
                <spring:message var="publicationDate" code="servise.publicationDate"/>
                <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" sortable="true" />
                <acme:column code="servise.taboo" value="${row2.taboo}"/>
                <spring:message code="servise.picture" var="pic"/>
                <display:column title="${pic}"><img src="${row2.picture}" alt="no image" width="130" height="100"></display:column>


                <spring:message var="formatDate" code="event.format.date"/>
                <acme:column code="servise.price" value="${row2.price}"/>
                <acme:column code="servise.discount" value="${row2.discount}"/>

            </display:table>
        </fieldset>

<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.avgQuestionPerServiseForAllPhotographer"/></b></legend>

        <h4><jstl:out value="${avgQuestionPerServiseForAllPhotographer}"/><br></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.ratioQuestionsServicesByStylists"/></b></legend>

       <h4> <jstl:out value="${ratioQuestionsServicesByStylists}"/><br></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.avgAndSqrtCommentsPerUser"/></b></legend>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgAndSqrtCommentsPerUser[0]}"/> <br/></h4>
        <h4><jstl:out value=" SQRT: "/><jstl:out value="${avgAndSqrtCommentsPerUser[1]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.servicesMoreAnsThan60PercentAboutQuestiosn"/></b></legend>
        <br/>
        <display:table id="row0" name="servicesMoreAnsThan60PercentAboutQuestiosn" class="table table-striped table-hover" requestURI="${requestURI}"
                       pagesize="5">

            <acme:column code="servise.creator" value="${row0.creator.name} " />
            <acme:column code="servise.title" value="${row0.title}"/>
            <acme:column code="servise.description" value="${row0.description}"/>
            <spring:message code="servise.picture" var="pic"/>
            <display:column title="${pic}"><img src="${row0.picture}" alt="no image" width="130" height="100"></display:column>
            <spring:message var="publicationDate" code="servise.publicationDate"/>
            <display:column property="publicationDate" title="${publicationDate}" format="${formatDate}" />

        </display:table>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.avgMinMaxStoresPerService"/></b></legend>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMinMaxStoresPerService[0]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMinMaxStoresPerService[1]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMinMaxStoresPerService[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.avgSqrtEventsPerManager"/></b></legend>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgSqrtEventsPerManager[0]}"/> <br/></h4>
        <h4><jstl:out value=" SQRT: "/><jstl:out value="${avgSqrtEventsPerManager[1]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.avgSqrtParticipatersParticipateAEventStore"/></b></legend>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgSqrtParticipatersParticipateAEventStore[0]}"/> <br/></h4>
        <h4><jstl:out value=" SQRT: "/><jstl:out value="${avgSqrtParticipatersParticipateAEventStore[1]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.ratioEventsStoreVSEvents"/></b></legend>

       <h4> <jstl:out value="${ratioEventsStoreVSEvents}"/><br></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.numberOfLikePostPerCategory"/></b></legend>
        <br/>
        <for:forEach items="${numberOfLikePostPerCategory}" var="row12">
            <h4><jstl:out value=" LIKES: "/><jstl:out value="${row12[0]}"/> </h4>
            <h4><jstl:out value=" NAME: "/><jstl:out value="${row12[1]}"/> <br/></h4>
        </for:forEach>
    </div>
</fieldset>
<br/>

        <fieldset>
            <legend class="text-center"><b><spring:message code="dash.listingPostByMomentOfCreation"/></b></legend>
            <display:table name="listingPostByMomentOfCreation" id="post" pagesize="5" class="table table-striped table-hover" requestURI="dashboard/administrator/dashboard.do">

                <spring:message code="post.title" var="titleTag" />
                <display:column property="title" title="${titleTag}" />


                <spring:message code="post.description" var="descriptionTag" />
                <display:column property="description" title="${descriptionTag}" />

                <spring:message code="post.moment" var="momentTag" />
                <display:column property="moment" title="${momentTag}" format="${formatDate}" />

                <spring:message code="post.endDate" var="endDateTag" />
                <display:column property="endDate" title="${endDateTag}" format="${formatDate}" />

                <spring:message code="post.finalMode" var="finalModeTag" />
                <display:column property="finalMode" title="${finalModeTag}" />

                <spring:message code="post.hasWinner" var="hasWinnerTag" />
                <display:column property="hasWinner" title="${hasWinnerTag}" />

                <spring:message code="post.reward" var="rewardTag" />
                <display:column property="reward" title="${rewardTag}" />

            </display:table>
        </fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.numberOfHeartPostPerCategory"/></b></legend>
        <br/>
        <for:forEach items="${numberOfHeartPostPerCategory}" var="row11">
        <h4><jstl:out value=" SUM: "/><jstl:out value="${row11[0]}"/> <br/></h4>
        <h4><jstl:out value=" NAME: "/><jstl:out value="${row11[1]}"/> <br/></h4>
        </for:forEach>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.topTenPostsWithLikesLoves"/></b></legend>
        <br/>
        <display:table name="topTenPostsWithLikesLoves" id="row30" pagesize="5" class="table table-striped table-hover" requestURI="dashboard/administrator/dashboard.do">

            <acme:column code="post.title" value="${row30.title} " />
            <acme:column code="post.description" value="${row30.description}"/>
            <spring:message var="moment" code="post.moment"/>
            <display:column property="moment" title="${publicationDate}" format="${formatDate}" sortable="true" />
            <spring:message var="endDate" code="post.endDate"/>
            <display:column property="endDate" title="${publicationDate}" format="${formatDate}" sortable="true" />
            <spring:message code="post.picture" var="pic"/>
            <display:column title="${pic}"><img src="${row30.picture}" alt="no image" width="130" height="100"></display:column>
            <acme:column code="post.finalMode" value="${row30.finalMode}"/>
            <acme:column code="post.hasWinner" value="${row30.hasWinner}"/>
            <acme:column code="post.reward" value="${row30.reward} " />

        </display:table>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.avgMaxMinSqrtPhotosPerPanel"/></b></legend>
        <br/>
        <h4><jstl:out value=" AVG: "/><jstl:out value="${avgMaxMinSqrtPhotosPerPanel[0]}"/> <br/></h4>
        <h4><jstl:out value=" MAX: "/><jstl:out value="${avgMaxMinSqrtPhotosPerPanel[1]}"/> <br/></h4>
        <h4><jstl:out value=" MIN: "/><jstl:out value="${avgMaxMinSqrtPhotosPerPanel[2]}"/> <br/></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.avgMaxMinSqrtPanelsPerUser"/></b></legend>
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
        <legend class="text-center"><b><spring:message code="dash.ratioPostsWithRaffles"/></b></legend>

       <h4> <jstl:out value="${ratioPostsWithRaffles}"/><br></h4>
    </div>
</fieldset>
<br/>

<fieldset>
    <div class="panel-body">
        <legend class="text-center"><b><spring:message code="dash.avgPostWithReward"/></b></legend>

       <h4> <jstl:out value="${avgPostWithReward}"/><br></h4>
    </div>
</fieldset>
<br/>

        <fieldset>
            <legend class="text-center"><b><spring:message code="dash.listingPostByEndDate"/></b></legend>
            <display:table name="listingPostByEndDate" id="row3" pagesize="5" class="table table-striped table-hover" requestURI="dashboard/administrator/dashboard.do">

                <acme:column code="post.title" value="${row3.title} " />
                <acme:column code="post.description" value="${row3.description}"/>
                <spring:message var="moment" code="post.moment"/>
                <display:column property="moment" title="${publicationDate}" format="${formatDate}" sortable="true" />
                <spring:message var="endDate" code="post.endDate"/>
                <display:column property="endDate" title="${publicationDate}" format="${formatDate}" sortable="true" />
                <spring:message code="post.picture" var="pic"/>
                <display:column title="${pic}"><img src="${row3.picture}" alt="no image" width="130" height="100"></display:column>
                <acme:column code="post.finalMode" value="${row3.finalMode}"/>
                <acme:column code="post.hasWinner" value="${row3.hasWinner}"/>
                <acme:column code="post.reward" value="${row3.reward} " />

            </display:table>
        </fieldset>

<br/>

    </div>
</div>