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

    <table>
        <thead>
        <tr>

            <th>Transaction </th>

            <sec:ifAllGranted roles="ROLE_CONSUMER">
                <th><g:message code="transaction.provider.label" default="Provider"/></th>
            </sec:ifAllGranted>

            <sec:ifAllGranted roles="ROLE_PROVIDER">
                <th><g:message code="transaction.consumer.label" default="Customer"/></th>
            </sec:ifAllGranted>

            <g:sortableColumn property="status"
                              title="${message(code: 'transaction.status.label', default: 'Status')}"/>

            <g:sortableColumn property="finishBy"
                              title="${message(code: 'transaction.finishBy.label', default: 'Finish By')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${transactionInstanceList}" status="i" var="transactionInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link class="btn btn-default" action="show"
                            id="${transactionInstance.id}"> view </g:link></td>

            <sec:ifAllGranted roles="ROLE_CONSUMER">
                <td><g:link controller="provider"
                            action="show"
                            id="${transactionInstance?.provider?.id}">${transactionInstance?.provider?.companyName}</g:link></td>
            </sec:ifAllGranted>

            <sec:ifAllGranted roles="ROLE_PROVIDER">
                <td>
                <g:link controller="consumer"
                        action="show"
                        id="${transactionInstance?.consumer?.id}">${transactionInstance?.consumer?.user?.firstname} ${transactionInstance?.consumer?.user?.lastname}
                </g:link>
                </td>
            </sec:ifAllGranted>
                <td> <trans:label transaction="${transactionInstance}"/> </td>

                <td><g:formatDate date="${transactionInstance.finishBy}"/></td>


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
