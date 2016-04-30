<%@ page import="snowbrr.Driveway" %>



<div class="fieldcontain ${hasErrors(bean: drivewayInstance, field: 'length', 'error')} required">
    <label for="length">
        <g:message code="driveway.length.label" default="Length"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="length" type="number" value="${drivewayInstance.length}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: drivewayInstance, field: 'width', 'error')} required">
    <label for="width">
        <g:message code="driveway.width.label" default="Width"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="width" type="number" value="${drivewayInstance.width}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: drivewayInstance, field: 'photo', 'error')} ">
    <label for="photo">
        <g:message code="driveway.photo.label" default="Photo"/>

    </label>
    <input type="file" id="photo" name="photo"/>

</div>

<div class="fieldcontain ${hasErrors(bean: drivewayInstance, field: 'comment', 'error')} ">
    <label for="comment">
        <g:message code="driveway.comment.label" default="Comment"/>

    </label>
    <g:textField name="comment" value="${drivewayInstance?.comment}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: drivewayInstance, field: 'consumer', 'error')} required">
    <label for="consumer">
        <g:message code="driveway.consumer.label" default="Consumer"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="consumer" name="consumer.id" from="${snowbrr.Consumer.list()}" optionKey="id" required=""
              value="${drivewayInstance?.consumer?.id}" class="many-to-one"/>

</div>

