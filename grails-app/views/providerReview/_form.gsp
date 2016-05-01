<%@ page import="snowbrr.ProviderReview" %>


<div hidden="true" class="fieldcontain ${hasErrors(bean: providerReviewInstance, field: 'timestamp', 'error')} required">
    <label for="timestamp">
        <g:message code="providerReview.timestamp.label" default="Timestamp"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="timestamp" precision="day" value="${providerReviewInstance?.timestamp}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerReviewInstance, field: 'rating', 'error')} required">
    <label for="rating">
        <g:message code="providerReview.rating.label" default="Rating"/>
        <span class="required-indicator">*</span>
    </label>

    <g:render template="/shared/starRatings" model="[rating: providerReviewInstance.rating]" />

</div>

<div hidden="true" class="fieldcontain ${hasErrors(bean: providerReviewInstance, field: 'reviewer', 'error')} required">
    <label for="reviewer">
        <g:message code="providerReview.reviewer.label" default="Reviewer"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="reviewer" name="reviewer.id" from="${snowbrr.Consumer.list()}" optionKey="id" required=""
              value="${providerReviewInstance?.reviewer?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerReviewInstance, field: 'content', 'error')} ">
    <label for="content">
        <g:message code="providerReview.content.label" default="Content"/>

    </label>
    <g:textField name="content" value="${providerReviewInstance?.content}"/>

</div>

<div hidden="true" class="fieldcontain ${hasErrors(bean: providerReviewInstance, field: 'provider', 'error')} required">
    <label for="provider">
        <g:message code="providerReview.provider.label" default="Provider"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="provider" name="provider.id" from="${snowbrr.Provider.list()}" optionKey="id" required=""
              value="${providerReviewInstance?.provider?.id}" class="many-to-one"/>

</div>

