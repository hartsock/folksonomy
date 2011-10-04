<%@ page import="folksonomy.Vote" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'vote.label', default: 'Vote')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${voteInstance}">
        <div class="errors">
            <g:renderErrors bean="${voteInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="username"><g:message code="vote.username.label" default="Username"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: voteInstance, field: 'username', 'errors')}">
                        <g:textField name="username" value="${voteInstance?.username}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="vote"><g:message code="vote.vote.label" default="Vote"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: voteInstance, field: 'vote', 'errors')}">
                        <g:select name="vote" from="${-1..1}" value="${fieldValue(bean: voteInstance, field: 'vote')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="uri"><g:message code="vote.uri.label" default="Uri"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: voteInstance, field: 'uri', 'errors')}">
                        <g:select name="uri.id" from="${folksonomy.Uri.list()}" optionKey="id"
                                  value="${voteInstance?.uri?.id}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save"
                                                 value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
