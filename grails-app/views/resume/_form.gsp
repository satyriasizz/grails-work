<%@ page import="grailswork2.Resume" %>



<div class="fieldcontain ${hasErrors(bean: resumeInstance, field: 'purpose', 'error')} ">
	<label for="purpose">
		<g:message code="resume.purpose.label" default="Purpose" />
		
	</label>
	<g:textField name="purpose" value="${resumeInstance?.purpose}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resumeInstance, field: 'edu', 'error')} ">
	<label for="edu">
		<g:message code="resume.edu.label" default="Edu" />
		
	</label>
	<g:textField name="edu" value="${resumeInstance?.edu}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resumeInstance, field: 'exp', 'error')} ">
	<label for="exp">
		<g:message code="resume.exp.label" default="Exp" />
		
	</label>
	<g:textField name="exp" value="${resumeInstance?.exp}"/>
</div>

