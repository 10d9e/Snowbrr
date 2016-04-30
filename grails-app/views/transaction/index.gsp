<%@ page import="snowbrr.Transaction" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-transaction" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-transaction" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="status"
                              title="${message(code: 'transaction.status.label', default: 'Status')}"/>

            <g:sortableColumn property="finishBy"
                              title="${message(code: 'transaction.finishBy.label', default: 'Finish By')}"/>

            <th><g:message code="transaction.provider.label" default="Provider"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${transactionInstanceList}" status="i" var="transactionInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${transactionInstance.id}">${fieldValue(bean: transactionInstance, field: "status")}</g:link></td>

                <td><g:formatDate date="${transactionInstance.finishBy}"/></td>

                <td>${fieldValue(bean: transactionInstance.provider, field: "companyName")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${transactionInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
