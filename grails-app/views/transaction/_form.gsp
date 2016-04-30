<%@ page import="snowbrr.Transaction" %>



<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'status', 'error')} required">
    <label for="status">
        <g:message code="transaction.status.label" default="Status"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select name="status" from="${transactionInstance.constraints.status.inList}" required=""
              value="${transactionInstance?.status}" valueMessagePrefix="transaction.status"/>

</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'finishBy', 'error')} required">
    <label for="finishBy">
        <g:message code="transaction.finishBy.label" default="Finish By"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="finishBy" precision="day" value="${transactionInstance?.finishBy}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'photoProof', 'error')} ">
    <label for="photoProof">
        <g:message code="transaction.photoProof.label" default="Photo Proof"/>

    </label>
    <input type="file" id="photoProof" name="photoProof"/>

</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'providerNotes', 'error')} ">
    <label for="providerNotes">
        <g:message code="transaction.providerNotes.label" default="Provider Notes"/>

    </label>
    <g:textField name="providerNotes" value="${transactionInstance?.providerNotes}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'consumerNotes', 'error')} ">
    <label for="consumerNotes">
        <g:message code="transaction.consumerNotes.label" default="Consumer Notes"/>

    </label>
    <g:textField name="consumerNotes" value="${transactionInstance?.consumerNotes}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'provider', 'error')} ">
    <label for="provider">
        <g:message code="transaction.provider.label" default="Provider"/>

    </label>
    <g:select id="provider" name="provider.id" from="${snowbrr.Provider.list()}" optionKey="id"
              value="${transactionInstance?.provider?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'consumer', 'error')} required">
    <label for="consumer">
        <g:message code="transaction.consumer.label" default="Consumer"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="consumer" name="consumer.id" from="${snowbrr.Consumer.list()}" optionKey="id" required=""
              value="${transactionInstance?.consumer?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'price', 'error')} required">
    <label for="price">
        <g:message code="transaction.price.label" default="Price"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="price" value="${fieldValue(bean: transactionInstance, field: 'price')}" required=""/>

</div>

