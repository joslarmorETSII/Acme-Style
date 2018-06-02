<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="container">
	<div class="row">
		<div class="col-md-10">

			<form:form action="configuration/administrator/edit.do" modelAttribute="configuration">

				<form:hidden path="id"/>
				<form:hidden path="version"/>

				<form:label path="englishWelcome"><spring:message code="configuration.englishWelcome"/></form:label>
				<form:input path="englishWelcome"/>
				<form:errors path="englishWelcome" cssClass="error"/>
				<br/>

				<form:label path="spanishWelcome"><spring:message code="configuration.spanishWelcome"/></form:label>
				<form:textarea path="spanishWelcome"/>
				<form:errors path="spanishWelcome" cssClass="error"/>
				<br/>

				<form:label path="tabooWords"><spring:message code="configuration.tabooWords"/></form:label>
				<form:textarea path="tabooWords" />
				<form:errors path="tabooWords" cssClass="error"/>
				<br/>

				<b><spring:message code="configuration.enterPhotos" /></b> <spring:message code="configuration.enterPhotos2" />
				<br/>
				<b><spring:message code="configuration.sizePhoto" /></b> <spring:message code="configuration.sizePhoto2" />
				<br/>
				<form:label path="photos"><spring:message code="configuration.photos"/></form:label>
				<form:textarea path="photos" />
				<form:errors path="photos" cssClass="error"/>
				<br/>

				<input type="submit" name="save"  class="btn btn-primary" value="<spring:message code="general.save" />" />
				<input type="button" name="cancel" class="btn btn-warning" value="<spring:message code="general.cancel" />"
						onclick="javascript: relativeRedir('welcome/index.do');" />
			</form:form>

		</div>
	</div>
</div>