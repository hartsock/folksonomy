<%@ page import="folksonomy.UserUri" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'userUri.label', default: 'UserUri')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-userUri" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-userUri" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="description"
                              title="${message(code: 'userUri.description.label', default: 'Description')}"/>

            <g:sortableColumn property="title" title="${message(code: 'userUri.title.label', default: 'Title')}"/>

            <th><g:message code="userUri.uri.label" default="Uri"/></th>

            <th><g:message code="userUri.user.label" default="User"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${userUriInstanceList}" status="i" var="userUriInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${userUriInstance.id}">${fieldValue(bean: userUriInstance, field: "description")}</g:link></td>

                <td>${fieldValue(bean: userUriInstance, field: "title")}</td>

                <td>${fieldValue(bean: userUriInstance, field: "uri")}</td>

                <td>${fieldValue(bean: userUriInstance, field: "user")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${userUriInstanceTotal}"/>
    </div>
</div>
</body>
</html>
