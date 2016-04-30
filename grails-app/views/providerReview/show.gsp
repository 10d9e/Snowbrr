<%@ page import="snowbrr.ProviderReview" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'providerReview.label', default: 'ProviderReview')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-providerReview" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                     default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-providerReview" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list providerReview">

        <g:if test="${providerReviewInstance?.timestamp}">
            <li class="fieldcontain">
                <span id="timestamp-label" class="property-label"><g:message code="providerReview.timestamp.label"
                                                                             default="Timestamp"/></span>

                <span class="property-value" aria-labelledby="timestamp-label"><g:formatDate
                        date="${providerReviewInstance?.timestamp}"/></span>

            </li>
        </g:if>

        <g:if test="${providerReviewInstance?.rating}">
            <li class="fieldcontain">
                <span id="rating-label" class="property-label"><g:message code="providerReview.rating.label"
                                                                          default="Rating"/></span>

                <span class="property-value" aria-labelledby="rating-label"><g:fieldValue
                        bean="${providerReviewInstance}" field="rating"/></span>

            </li>
        </g:if>

        <g:if test="${providerReviewInstance?.reviewer}">
            <li class="fieldcontain">
                <span id="reviewer-label" class="property-label"><g:message code="providerReview.reviewer.label"
                                                                            default="Reviewer"/></span>

                <span class="property-value" aria-labelledby="reviewer-label"><g:link controller="consumer"
                                                                                      action="show"
                                                                                      id="${providerReviewInstance?.reviewer?.id}">${providerReviewInstance?.reviewer?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${providerReviewInstance?.content}">
            <li class="fieldcontain">
                <span id="content-label" class="property-label"><g:message code="providerReview.content.label"
                                                                           default="Content"/></span>

                <span class="property-value" aria-labelledby="content-label"><g:fieldValue
                        bean="${providerReviewInstance}" field="content"/></span>

            </li>
        </g:if>

        <g:if test="${providerReviewInstance?.provider}">
            <li class="fieldcontain">
                <span id="provider-label" class="property-label"><g:message code="providerReview.provider.label"
                                                                            default="Provider"/></span>

                <span class="property-value" aria-labelledby="provider-label"><g:link controller="provider"
                                                                                      action="show"
                                                                                      id="${providerReviewInstance?.provider?.id}">${providerReviewInstance?.provider?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

    </ol>
    <g:form url="[resource: providerReviewInstance, action: 'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${providerReviewInstance}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
