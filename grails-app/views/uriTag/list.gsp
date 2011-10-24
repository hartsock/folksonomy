
<%@ page import="folksonomy.UriTag" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'uriTag.label', default: 'UriTag')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-uriTag" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-uriTag" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="uriTag.tag.label" default="Tag" /></th>
					
						<th><g:message code="uriTag.uri.label" default="Uri" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${uriTagInstanceList}" status="i" var="uriTagInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${uriTagInstance.id}">${fieldValue(bean: uriTagInstance, field: "tag")}</g:link></td>
					
						<td>${fieldValue(bean: uriTagInstance, field: "uri")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${uriTagInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
