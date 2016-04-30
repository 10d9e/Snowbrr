<%@ page import="snowbrr.Consumer" %>



<div class="fieldcontain ${hasErrors(bean: consumerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="consumer.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${snowbrr.User.list()}" optionKey="id" required="" value="${consumerInstance?.user?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: consumerInstance, field: 'driveway', 'error')} required">
	<label for="driveway">
		<g:message code="consumer.driveway.label" default="Driveway" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="driveway" name="driveway.id" from="${snowbrr.Driveway.list()}" optionKey="id" required="" value="${consumerInstance?.driveway?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: consumerInstance, field: 'messages', 'error')} ">
	<label for="messages">
		<g:message code="consumer.messages.label" default="Messages" />
		
	</label>
	<g:select name="messages" from="${snowbrr.Message.list()}" multiple="multiple" optionKey="id" size="5" value="${consumerInstance?.messages*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: consumerInstance, field: 'reviews', 'error')} ">
	<label for="reviews">
		<g:message code="consumer.reviews.label" default="Reviews" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${consumerInstance?.reviews?}" var="r">
    <li><g:link controller="consumerReview" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="consumerReview" action="create" params="['consumer.id': consumerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'consumerReview.label', default: 'ConsumerReview')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: consumerInstance, field: 'transactions', 'error')} ">
	<label for="transactions">
		<g:message code="consumer.transactions.label" default="Transactions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${consumerInstance?.transactions?}" var="t">
    <li><g:link controller="transaction" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="transaction" action="create" params="['consumer.id': consumerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'transaction.label', default: 'Transaction')])}</g:link>
</li>
</ul>


</div>

