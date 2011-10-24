<%@ page import="folksonomy.UserUriTag" %>



<div class="fieldcontain ${hasErrors(bean: userUriTagInstance, field: 'uriTag', 'error')} required">
    <label for="uriTag">
        <g:message code="userUriTag.uriTag.label" default="Uri Tag"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="uriTag" name="uriTag.id" from="${folksonomy.UriTag.list()}" optionKey="id" required=""
              value="${userUriTagInstance?.uriTag?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userUriTagInstance, field: 'user', 'error')} required">
    <label for="user">
        <g:message code="userUriTag.user.label" default="User"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="user" name="user.id" from="${folksonomy.User.list()}" optionKey="id" required=""
              value="${userUriTagInstance?.user?.id}" class="many-to-one"/>
</div>

