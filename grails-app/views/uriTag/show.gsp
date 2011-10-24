
<%@ page import="folksonomy.UriTag" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'uriTag.label', default: 'UriTag')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-uriTag" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-uriTag" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list uriTag">
			
				<g:if test="${uriTagInstance?.tag}">
				<li class="fieldcontain">
					<span id="tag-label" class="property-label"><g:message code="uriTag.tag.label" default="Tag" /></span>
					
						<span class="property-value" aria-labelledby="tag-label"><g:link controller="tag" action="show" id="${uriTagInstance?.tag?.id}">${uriTagInstance?.tag?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${uriTagInstance?.uri}">
				<li class="fieldcontain">
					<span id="uri-label" class="property-label"><g:message code="uriTag.uri.label" default="Uri" /></span>
					
						<span class="property-value" aria-labelledby="uri-label"><g:link controller="uri" action="show" id="${uriTagInstance?.uri?.id}">${uriTagInstance?.uri?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${uriTagInstance?.id}" />
					<g:link class="edit" action="edit" id="${uriTagInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
