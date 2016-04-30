<%@ page import="snowbrr.Message" %>



<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'timestamp', 'error')} required">
    <label for="timestamp">
        <g:message code="message.timestamp.label" default="Timestamp"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="timestamp" precision="day" value="${messageInstance?.timestamp}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'fromUsername', 'error')} required">
    <label for="fromUsername">
        <g:message code="message.fromUsername.label" default="From Username"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="fromUsername" required="" value="${messageInstance?.fromUsername}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'content', 'error')} required">
    <label for="content">
        <g:message code="message.content.label" default="Content"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="content" required="" value="${messageInstance?.content}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'read', 'error')} ">
    <label for="read">
        <g:message code="message.read.label" default="Read"/>

    </label>
    <g:checkBox name="read" value="${messageInstance?.read}"/>

</div>

