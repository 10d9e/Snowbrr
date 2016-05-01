<%@ page import="snowbrr.Provider" %>

<div class="fieldcontain ${hasErrors(bean: providerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="provider.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${snowbrr.User.list()}" optionKey="id" required="" value="${providerInstance?.user?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance, field: 'companyName', 'error')} required">
	<label for="companyName">
		<g:message code="provider.companyName.label" default="Company Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="companyName" required="" value="${providerInstance?.companyName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="provider.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${providerInstance?.active}" />

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance, field: 'messages', 'error')} ">
	<label for="messages">
		<g:message code="provider.messages.label" default="Messages" />
		
	</label>
	<g:select name="messages" from="${snowbrr.Message.list()}" multiple="multiple" optionKey="id" size="5" value="${providerInstance?.messages*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance, field: 'reviews', 'error')} ">
	<label for="reviews">
		<g:message code="provider.reviews.label" default="Reviews" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${providerInstance?.reviews?}" var="r">
    <li><g:link controller="providerReview" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="providerReview" action="create" params="['provider.id': providerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'providerReview.label', default: 'ProviderReview')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance, field: 'transactions', 'error')} ">
	<label for="transactions">
		<g:message code="provider.transactions.label" default="Transactions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${providerInstance?.transactions?}" var="t">
    <li><g:link controller="transaction" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="transaction" action="create" params="['provider.id': providerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'transaction.label', default: 'Transaction')])}</g:link>
</li>
</ul>


</div>

