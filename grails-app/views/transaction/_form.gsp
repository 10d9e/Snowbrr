<%@ page import="snowbrr.Transaction" %>



<div hidden class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'status', 'error')} required">
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

<sec:ifAllGranted roles="ROLE_PROVIDER">

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'providerNotes', 'error')} ">
    <label for="providerNotes">
        <g:message code="transaction.providerNotes.label" default="Provider Notes"/>

    </label>
    <g:textArea name="providerNotes" value="${transactionInstance?.providerNotes}"/>

</div>

</sec:ifAllGranted>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'provider', 'error')} ">
    <label for="provider">
        <g:message code="transaction.provider.label" default="Provider"/>

    </label>
    <g:select id="provider" name="provider.id" from="${snowbrr.Provider.list()}" optionKey="id" optionValue="companyName"
              value="${transactionInstance?.provider?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<sec:ifAllGranted roles="ROLE_PROVIDER">
<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'price', 'error')} required">
    <label for="price">
        <g:message code="transaction.price.label" default="Price"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="price" value="${fieldValue(bean: transactionInstance, field: 'price')}" required=""/>
</div>
</sec:ifAllGranted>

<sec:ifAllGranted roles="ROLE_CONSUMER">
    <div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'price', 'error')} required">
        <label for="price">
            <g:message code="transaction.price.label" default="Price"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field name="price" disabled="true" value="${fieldValue(bean: transactionInstance, field: 'price')}" required=""/>
    </div>
</sec:ifAllGranted>



<sec:ifAllGranted roles="ROLE_CONSUMER">
    <div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'consumerNotes', 'error')} ">
        <label for="consumerNotes">
            <g:message code="transaction.consumerNotes.label" default="Consumer Notes"/>

        </label>
        <g:textArea name="consumerNotes" value="${transactionInstance?.consumerNotes}"/>
    </div>
</sec:ifAllGranted>

<sec:ifAllGranted roles="ROLE_PROVIDER">
    <div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'consumerNotes', 'error')} ">
        <label for="consumerNotes">
            <g:message code="transaction.consumerNotes.label" default="Consumer Notes"/>

        </label>
        <g:textArea name="consumerNotes" disabled="true" value="${transactionInstance?.consumerNotes}"/>
    </div>

</sec:ifAllGranted>

