<%@ page import="folksonomy.Tag" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-tag" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                          default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-tag" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list tag">

        <g:if test="${tagInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="tag.name.label" default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${tagInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${tagInstance?.checked}">
            <li class="fieldcontain">
                <span id="checked-label" class="property-label"><g:message code="tag.checked.label"
                                                                           default="Checked"/></span>

                <span class="property-value" aria-labelledby="checked-label"><g:formatBoolean
                        boolean="${tagInstance?.checked}"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${tagInstance?.id}"/>
            <g:link class="edit" action="edit" id="${tagInstance?.id}"><g:message code="default.button.edit.label"
                                                                                  default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
