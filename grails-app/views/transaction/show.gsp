<%@ page import="snowbrr.Transaction" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>

</head>

<body>

<a href="#show-transaction" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-transaction" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>

    <ol class="property-list transaction">

        <g:if test="${transactionInstance?.status}">
            <li class="fieldcontain">
                <span id="status-label" class="property-label"><g:message code="transaction.status.label"
                                                                          default="Status"/></span>

                <span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${transactionInstance}"
                                                                                          field="status"/></span>

            </li>
        </g:if>

        <g:if test="${transactionInstance?.finishBy}">
            <li class="fieldcontain">
                <span id="finishBy-label" class="property-label"><g:message code="transaction.finishBy.label"
                                                                            default="Finish By"/></span>

                <span class="property-value" aria-labelledby="finishBy-label"><g:formatDate
                        date="${transactionInstance?.finishBy}"/></span>

            </li>
        </g:if>

        <g:if test="${transactionInstance?.photoProof}">
            <li class="fieldcontain">
                <span id="photoProof-label" class="property-label"><g:message code="transaction.photoProof.label"
                                                                              default="Photo Proof"/></span>

            </li>
        </g:if>

        <g:if test="${transactionInstance?.providerNotes}">
            <li class="fieldcontain">
                <span id="providerNotes-label" class="property-label"><g:message code="transaction.providerNotes.label"
                                                                                 default="Provider Notes"/></span>

                <span class="property-value" aria-labelledby="providerNotes-label"><g:fieldValue
                        bean="${transactionInstance}" field="providerNotes"/></span>

            </li>
        </g:if>

        <g:if test="${transactionInstance?.consumerNotes}">
            <li class="fieldcontain">
                <span id="consumerNotes-label" class="property-label"><g:message code="transaction.consumerNotes.label"
                                                                                 default="Consumer Notes"/></span>

                <span class="property-value" aria-labelledby="consumerNotes-label"><g:fieldValue
                        bean="${transactionInstance}" field="consumerNotes"/></span>

            </li>
        </g:if>

        <g:if test="${transactionInstance?.provider}">
            <li class="fieldcontain">
                <span id="provider-label" class="property-label"><g:message code="transaction.provider.label"
                                                                            default="Provider"/></span>

                <span class="property-value" aria-labelledby="provider-label"><g:link controller="provider"
                                                                                      action="show"
                                                                                      id="${transactionInstance?.provider?.id}">${transactionInstance?.provider?.companyName}</g:link></span>

            </li>
        </g:if>

        <g:if test="${transactionInstance?.consumer}">
            <li class="fieldcontain">
                <span id="consumer-label" class="property-label"><g:message code="transaction.consumer.label"
                                                                            default="Consumer"/></span>

                <span class="property-value" aria-labelledby="consumer-label"><g:link controller="consumer"
                                                                                      action="show"
                                                                                      id="${transactionInstance?.consumer?.id}">${transactionInstance?.consumer?.user?.firstname} ${transactionInstance?.consumer?.user?.lastname} </g:link></span>

            </li>
        </g:if>

        <g:if test="${transactionInstance?.price}">
            <li class="fieldcontain">
                <span id="price-label" class="property-label"><g:message code="transaction.price.label"
                                                                         default="Price"/></span>

                <span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${transactionInstance}"
                                                                                         field="price"/></span>

            </li>
        </g:if>

    </ol>
    <g:form url="[resource: transactionInstance, action: 'delete']" method="DELETE">
        <fieldset class="buttons">
            <sec:ifAllGranted roles="ROLE_PROVIDER">
                <g:link class="edit" action="providerAccept" resource="${transactionInstance}">
                    <g:message code="default.button.provider.accept.label" default="Accept Job"/></g:link>

                <g:link class="edit" action="providerComplete" resource="${transactionInstance}">
                    <g:message code="default.button.provider.complete.label" default="Complete Job"/></g:link>

                <g:link class="edit" action="providerCancel" resource="${transactionInstance}">
                    <g:message code="default.button.provider.complete.label" default="Cancel Job"/></g:link>

                <g:link class="edit" action="create" controller="consumerReview" params="[reviewer:transactionInstance.provider.id,consumer:transactionInstance.consumer.id]" >
                    <g:message code="default.button.provider.complete.label" default="Rate Client"/></g:link>

            </sec:ifAllGranted>

            <sec:ifAllGranted roles="ROLE_CONSUMER">
                <g:link class="edit" action="consumerCancel" resource="${transactionInstance}">
                    <g:message code="default.button.provider.complete.label" default="Cancel Job"/></g:link>

                <g:link class="edit" action="create" controller="providerReview" params="[reviewer:transactionInstance.consumer.id,provider:transactionInstance.provider.id]" >
                    <g:message code="default.button.provider.complete.label" default="Rate Provider"/></g:link>
            </sec:ifAllGranted>

            <g:link class="edit" action="edit" resource="${transactionInstance}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
