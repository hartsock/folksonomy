<%@ page import="folksonomy.UserUri" %>



<div class="fieldcontain ${hasErrors(bean: userUriInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="userUri.description.label" default="Description"/>

    </label>
    <g:textField name="description" value="${userUriInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userUriInstance, field: 'tags', 'error')} ">
    <label for="tags">
        <g:message code="userUri.tags.label" default="Tags"/>

    </label>
    <g:select name="tags" from="${folksonomy.Tag.list()}" multiple="multiple" optionKey="id" size="5"
              value="${userUriInstance?.tags*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userUriInstance, field: 'title', 'error')} ">
    <label for="title">
        <g:message code="userUri.title.label" default="Title"/>

    </label>
    <g:textField name="title" value="${userUriInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userUriInstance, field: 'uri', 'error')} required">
    <label for="uri">
        <g:message code="userUri.uri.label" default="Uri"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="uri" name="uri.id" from="${folksonomy.Uri.list()}" optionKey="id" required=""
              value="${userUriInstance?.uri?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userUriInstance, field: 'user', 'error')} required">
    <label for="user">
        <g:message code="userUri.user.label" default="User"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="user" name="user.id" from="${folksonomy.User.list()}" optionKey="id" required=""
              value="${userUriInstance?.user?.id}" class="many-to-one"/>
</div>

