<%@ page import="snowbrr.Message" %>



<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'timestamp', 'error')} required">
	<label for="timestamp">
		<g:message code="message.timestamp.label" default="Timestamp" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="timestamp" value="${messageInstance?.timestamp}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'from', 'error')} required">
	<label for="from">
		<g:message code="message.from.label" default="From" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="from.id" from="${snowbrr.User.list()}" optionKey="id" value="${messageInstance?.from?.id}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="message.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="content" value="${fieldValue(bean: messageInstance, field: 'content')}" />

</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'read', 'error')} ">
	<label for="read">
		<g:message code="message.read.label" default="Read" />
		
	</label>
	<g:checkBox name="read" value="${messageInstance?.read}" />

</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="message.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="user.id" from="${snowbrr.User.list()}" optionKey="id" value="${messageInstance?.user?.id}"  />

</div>

