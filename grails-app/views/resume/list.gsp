
<%@ page import="grailswork2.Resume" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'resume.label', default: 'Resume')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-resume" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-resume" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="purpose" title="${message(code: 'resume.purpose.label', default: 'Purpose')}" />
					
						<g:sortableColumn property="edu" title="${message(code: 'resume.edu.label', default: 'Edu')}" />
					
						<g:sortableColumn property="exp" title="${message(code: 'resume.exp.label', default: 'Exp')}" />

                        <th>Mail button</th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${resumeInstanceList}" status="i" var="resumeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${resumeInstance.id}">${fieldValue(bean: resumeInstance, field: "purpose")}</g:link></td>
					
						<td>${fieldValue(bean: resumeInstance, field: "edu")}</td>
					
						<td>${fieldValue(bean: resumeInstance, field: "exp")}</td>

                        <td><g:link id="${resumeInstance?.id}" action="mailWorker">Mail him</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${resumeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
