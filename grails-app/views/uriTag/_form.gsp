<%@ page import="folksonomy.UriTag" %>



<div class="fieldcontain ${hasErrors(bean: uriTagInstance, field: 'tag', 'error')} required">
	<label for="tag">
		<g:message code="uriTag.tag.label" default="Tag" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tag" name="tag.id" from="${folksonomy.Tag.list()}" optionKey="id" required="" value="${uriTagInstance?.tag?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: uriTagInstance, field: 'uri', 'error')} required">
	<label for="uri">
		<g:message code="uriTag.uri.label" default="Uri" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="uri" name="uri.id" from="${folksonomy.Uri.list()}" optionKey="id" required="" value="${uriTagInstance?.uri?.id}" class="many-to-one"/>
</div>

