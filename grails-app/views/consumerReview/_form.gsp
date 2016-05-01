<%@ page import="snowbrr.ConsumerReview" %>

<div hidden="true" class="fieldcontain ${hasErrors(bean: consumerReviewInstance, field: 'timestamp', 'error')} required">
    <label for="timestamp">
        <g:message code="consumerReview.timestamp.label" default="Timestamp"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="timestamp" precision="day" value="${consumerReviewInstance?.timestamp}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: consumerReviewInstance, field: 'rating', 'error')} required">
    <label for="rating">
        <g:message code="consumerReview.rating.label" default="Rating"/>
        <span class="required-indicator">*</span>
    </label>

    <g:render template="/shared/starRatings" model="[rating: consumerReviewInstance.rating]" />

</div>


<div hidden="true" class="fieldcontain ${hasErrors(bean: consumerReviewInstance, field: 'reviewer', 'error')} required">
    <label for="reviewer">
        <g:message code="consumerReview.reviewer.label" default="Reviewer"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="reviewer" name="reviewer.id" from="${snowbrr.Provider.list()}" optionKey="id" required=""
              value="${consumerReviewInstance?.reviewer?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: consumerReviewInstance, field: 'content', 'error')} ">
    <label for="content">
        <g:message code="consumerReview.content.label" default="Content"/>

    </label>
    <g:textField name="content" value="${consumerReviewInstance?.content}"/>

</div>

<div hidden="true" class="fieldcontain ${hasErrors(bean: consumerReviewInstance, field: 'consumer', 'error')} required">
    <label for="consumer">
        <g:message code="consumerReview.consumer.label" default="Consumer"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="consumer" name="consumer.id" from="${snowbrr.Consumer.list()}" optionKey="id" required=""
              value="${consumerReviewInstance?.consumer?.id}" class="many-to-one"/>

</div>

