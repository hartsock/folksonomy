<%@ page import="folksonomy.Uri" %>



<div class="fieldcontain ${hasErrors(bean: uriInstance, field: 'uri', 'error')} ">
    <label for="uri">
        <g:message code="uri.uri.label" default="Uri"/>

    </label>
    <g:field type="url" name="uri" value="${uriInstance?.uri}"/>
</div>

