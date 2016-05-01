<%@ page import="snowbrr.Driveway" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'driveway.label', default: 'Driveway')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-driveway" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-driveway" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>

    <ol class="property-list driveway">

        <g:if test="${drivewayInstance?.length}">
            <li class="fieldcontain">
                <span id="length-label" class="property-label"><g:message code="driveway.length.label"
                                                                          default="Length"/></span>

                <span class="property-value" aria-labelledby="length-label"><g:fieldValue bean="${drivewayInstance}"
                                                                                          field="length"/></span>

            </li>
        </g:if>

        <g:if test="${drivewayInstance?.width}">
            <li class="fieldcontain">
                <span id="width-label" class="property-label"><g:message code="driveway.width.label"
                                                                         default="Width"/></span>

                <span class="property-value" aria-labelledby="width-label"><g:fieldValue bean="${drivewayInstance}"
                                                                                         field="width"/></span>

            </li>
        </g:if>

        <g:if test="${drivewayInstance?.photo}">
            <li class="fieldcontain">
                <span id="photo-label" class="property-label"><g:message code="driveway.photo.label"
                                                                         default="Photo"/></span>

            </li>
        </g:if>

        <g:if test="${drivewayInstance?.comment}">
            <li class="fieldcontain">
                <span id="comment-label" class="property-label"><g:message code="driveway.comment.label"
                                                                           default="Comment"/></span>

                <span class="property-value" aria-labelledby="comment-label"><g:fieldValue bean="${drivewayInstance}"
                                                                                           field="comment"/></span>

            </li>
        </g:if>

        <g:if test="${drivewayInstance?.consumer}">
            <li class="fieldcontain">
                <span id="consumer-label" class="property-label"><g:message code="driveway.consumer.label"
                                                                            default="Consumer"/></span>

                <span class="property-value" aria-labelledby="consumer-label"><g:link controller="consumer"
                                                                                      action="show"
                                                                                      id="${drivewayInstance?.consumer?.id}">${drivewayInstance?.consumer?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

    </ol>
    <g:form url="[resource: drivewayInstance, action: 'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${drivewayInstance}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
