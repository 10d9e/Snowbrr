<%@ page import="snowbrr.Driveway" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'driveway.label', default: 'Driveway')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-driveway" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-driveway" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>

    <table>
        <thead>
        <tr>

            <g:sortableColumn property="length" title="${message(code: 'driveway.length.label', default: 'Length')}"/>

            <g:sortableColumn property="width" title="${message(code: 'driveway.width.label', default: 'Width')}"/>

            <g:sortableColumn property="photo" title="${message(code: 'driveway.photo.label', default: 'Photo')}"/>

            <g:sortableColumn property="comment"
                              title="${message(code: 'driveway.comment.label', default: 'Comment')}"/>

            <th><g:message code="driveway.consumer.label" default="Consumer"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${drivewayInstanceList}" status="i" var="drivewayInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${drivewayInstance.id}">${fieldValue(bean: drivewayInstance, field: "length")}</g:link></td>

                <td>${fieldValue(bean: drivewayInstance, field: "width")}</td>

                <td>${fieldValue(bean: drivewayInstance, field: "photo")}</td>

                <td>${fieldValue(bean: drivewayInstance, field: "comment")}</td>

                <td>${fieldValue(bean: drivewayInstance, field: "consumer")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${drivewayInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
