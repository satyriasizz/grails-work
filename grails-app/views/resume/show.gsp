
<%@ page import="grailswork2.Resume" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'resume.label', default: 'Resume')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-resume" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-resume" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list resume">
			
				<g:if test="${resumeInstance?.purpose}">
				<li class="fieldcontain">
					<span id="purpose-label" class="property-label"><g:message code="resume.purpose.label" default="Purpose" /></span>
					
						<span class="property-value" aria-labelledby="purpose-label"><g:fieldValue bean="${resumeInstance}" field="purpose"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resumeInstance?.edu}">
				<li class="fieldcontain">
					<span id="edu-label" class="property-label"><g:message code="resume.edu.label" default="Edu" /></span>
					
						<span class="property-value" aria-labelledby="edu-label"><g:fieldValue bean="${resumeInstance}" field="edu"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resumeInstance?.exp}">
				<li class="fieldcontain">
					<span id="exp-label" class="property-label"><g:message code="resume.exp.label" default="Exp" /></span>
					
						<span class="property-value" aria-labelledby="exp-label"><g:fieldValue bean="${resumeInstance}" field="exp"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${resumeInstance?.id}" />
					<g:link class="edit" action="edit" id="${resumeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
