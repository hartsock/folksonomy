<%@ page import="folksonomy.UserUriTag" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'userUriTag.label', default: 'UserUriTag')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-userUriTag" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-userUriTag" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list userUriTag">

        <g:if test="${userUriTagInstance?.uriTag}">
            <li class="fieldcontain">
                <span id="uriTag-label" class="property-label"><g:message code="userUriTag.uriTag.label"
                                                                          default="Uri Tag"/></span>

                <span class="property-value" aria-labelledby="uriTag-label"><g:link controller="uriTag" action="show"
                                                                                    id="${userUriTagInstance?.uriTag?.id}">${userUriTagInstance?.uriTag?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${userUriTagInstance?.user}">
            <li class="fieldcontain">
                <span id="user-label" class="property-label"><g:message code="userUriTag.user.label"
                                                                        default="User"/></span>

                <span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show"
                                                                                  id="${userUriTagInstance?.user?.id}">${userUriTagInstance?.user?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${userUriTagInstance?.id}"/>
            <g:link class="edit" action="edit" id="${userUriTagInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
