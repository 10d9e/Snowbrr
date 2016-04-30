<%@ page import="snowbrr.ConsumerReview" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'consumerReview.label', default: 'ConsumerReview')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-consumerReview" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                     default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-consumerReview" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="timestamp"
                              title="${message(code: 'consumerReview.timestamp.label', default: 'Timestamp')}"/>

            <g:sortableColumn property="rating"
                              title="${message(code: 'consumerReview.rating.label', default: 'Rating')}"/>

            <th><g:message code="consumerReview.reviewer.label" default="Reviewer"/></th>

            <g:sortableColumn property="content"
                              title="${message(code: 'consumerReview.content.label', default: 'Content')}"/>

            <th><g:message code="consumerReview.consumer.label" default="Consumer"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${consumerReviewInstanceList}" status="i" var="consumerReviewInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${consumerReviewInstance.id}">${fieldValue(bean: consumerReviewInstance, field: "timestamp")}</g:link></td>

                <td>${fieldValue(bean: consumerReviewInstance, field: "rating")}</td>

                <td>${fieldValue(bean: consumerReviewInstance, field: "reviewer")}</td>

                <td>${fieldValue(bean: consumerReviewInstance, field: "content")}</td>

                <td>${fieldValue(bean: consumerReviewInstance, field: "consumer")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${consumerReviewInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
