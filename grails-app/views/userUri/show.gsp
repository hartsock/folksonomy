<%@ page import="folksonomy.UserUri" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'userUri.label', default: 'UserUri')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-userUri" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-userUri" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list userUri">

        <g:if test="${userUriInstance?.description}">
            <li class="fieldcontain">
                <span id="description-label" class="property-label"><g:message code="userUri.description.label"
                                                                               default="Description"/></span>

                <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${userUriInstance}"
                                                                                               field="description"/></span>

            </li>
        </g:if>

        <g:if test="${userUriInstance?.tags}">
            <li class="fieldcontain">
                <span id="tags-label" class="property-label"><g:message code="userUri.tags.label"
                                                                        default="Tags"/></span>

                <g:each in="${userUriInstance.tags}" var="t">
                    <span class="property-value" aria-labelledby="tags-label"><g:link controller="tag" action="show"
                                                                                      id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${userUriInstance?.title}">
            <li class="fieldcontain">
                <span id="title-label" class="property-label"><g:message code="userUri.title.label"
                                                                         default="Title"/></span>

                <span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${userUriInstance}"
                                                                                         field="title"/></span>

            </li>
        </g:if>

        <g:if test="${userUriInstance?.uri}">
            <li class="fieldcontain">
                <span id="uri-label" class="property-label"><g:message code="userUri.uri.label" default="Uri"/></span>

                <span class="property-value" aria-labelledby="uri-label"><g:link controller="uri" action="show"
                                                                                 id="${userUriInstance?.uri?.id}">${userUriInstance?.uri?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${userUriInstance?.user}">
            <li class="fieldcontain">
                <span id="user-label" class="property-label"><g:message code="userUri.user.label"
                                                                        default="User"/></span>

                <span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show"
                                                                                  id="${userUriInstance?.user?.id}">${userUriInstance?.user?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${userUriInstance?.id}"/>
            <g:link class="edit" action="edit" id="${userUriInstance?.id}"><g:message code="default.button.edit.label"
                                                                                      default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
