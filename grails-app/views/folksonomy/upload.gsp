<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'uriTag.label', default: 'UriTag')}" />
		<title>Upload</title>
	</head>
	<body>
		<a href="#create-uriTag" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-uriTag" class="content scaffold-create" role="main">
			<h1>Upload Exported Tags</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${uriTagInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${uriTagInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="process" method="post" enctype="multipart/form-data">
                <input type="hidden" name="profiler" value="true"/>
				<fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: uriTagInstance, field: 'tag', 'error')} required">
                        <label for="export">
                            File
                            <span class="required-indicator">*</span>
                        </label>
                        <g:select id="type" name="type" from="${['groovy','fast','faster','java']}" required="true" value="groovy" class="many-to-one"/>
                        <input type="file" name="export"/>
                    </div>

				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="upload" class="save" value="upload" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
