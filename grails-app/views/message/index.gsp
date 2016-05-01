
<%@ page import="snowbrr.Message" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'message.label', default: 'Message')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-message" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-message" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>

			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="timestamp" title="${message(code: 'message.timestamp.label', default: 'Timestamp')}" />
					
						<th><g:message code="message.from.label" default="From" /></th>
						<th></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${messageInstanceList}" status="i" var="messageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<g:if test="${messageInstance.read}">
							<td><g:link action="show" id="${messageInstance.id}">${fieldValue(bean: messageInstance, field: "timestamp")}</g:link></td>
							<td> ${messageInstance.from.username} ( ${messageInstance.from.firstname} ${messageInstance.from.lastname} ) </td>
						</g:if>
						<g:else>
							<td> <strong> <g:link action="show" id="${messageInstance.id}">${fieldValue(bean: messageInstance, field: "timestamp")}</g:link> </strong></td>
							<td> <strong> ${fieldValue(bean: messageInstance, field: "from")} </strong> </td>
						</g:else>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${messageInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
