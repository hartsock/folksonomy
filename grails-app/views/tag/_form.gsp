<%@ page import="folksonomy.Tag" %>



<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="tag.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${tagInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'checked', 'error')} ">
    <label for="checked">
        <g:message code="tag.checked.label" default="Checked"/>

    </label>
    <g:checkBox name="checked" value="${tagInstance?.checked}"/>
</div>

