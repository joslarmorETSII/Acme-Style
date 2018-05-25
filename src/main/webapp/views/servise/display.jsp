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
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h3><b><spring:message code="servise.title"/>:&nbsp; </b><jstl:out value="${servise.title}"/></h3>


<b><spring:message code="servise.description"/>:&nbsp; </b><jstl:out value="${servise.description}"/>
<br/>

<b><spring:message code="servise.creator.name"/>:&nbsp; </b><jstl:out value="${servise.creator.name}"/>
<br/>

<b><spring:message code="servise.price"/>:&nbsp; </b><jstl:out value="${servise.price}"/>
<br/>

<b><spring:message code="servise.discount"/>:&nbsp; </b><jstl:out value="${servise.discount}"/>
<br/>





<b><spring:message code="servise.finalPrice"/>:&nbsp; </b><jstl:out value="${finalPrice}"/>
<br/>

<br/>

<img src="${servise.picture}" width="500px" height="100%" />
<br/>

<b><spring:message code="servise.feedbacks"/>:&nbsp; </b><jstl:out value="${servise.feedbacks}"/>
<br/>

<input type="button" class="btn btn-warning" name="cancel" value="<spring:message code="general.cancel" />"
       onclick="javascript: relativeRedir('${cancelURI}');" />
