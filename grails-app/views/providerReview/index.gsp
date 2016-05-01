<%@ page import="snowbrr.ProviderReview" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'providerReview.label', default: 'ProviderReview')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-providerReview" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                     default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-providerReview" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>

    <table>
        <thead>
        <tr>

            <g:sortableColumn property="timestamp"
                              title="${message(code: 'providerReview.timestamp.label', default: 'Timestamp')}"/>

            <g:sortableColumn property="rating"
                              title="${message(code: 'providerReview.rating.label', default: 'Rating')}"/>

            <th><g:message code="providerReview.reviewer.label" default="Reviewer"/></th>

            <g:sortableColumn property="content"
                              title="${message(code: 'providerReview.content.label', default: 'Content')}"/>

            <th><g:message code="providerReview.provider.label" default="Provider"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${providerReviewInstanceList}" status="i" var="providerReviewInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${providerReviewInstance.id}">${fieldValue(bean: providerReviewInstance, field: "timestamp")}</g:link></td>

                <td>${fieldValue(bean: providerReviewInstance, field: "rating")}</td>

                <td>${fieldValue(bean: providerReviewInstance, field: "reviewer")}</td>

                <td>${fieldValue(bean: providerReviewInstance, field: "content")}</td>

                <td>${fieldValue(bean: providerReviewInstance, field: "provider")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${providerReviewInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
