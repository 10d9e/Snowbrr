
<%@ page import="snowbrr.Consumer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'consumer.label', default: 'Consumer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

	</head>
	<body>

	<a href="#list-consumer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-consumer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>

			<table>
			<thead>
					<tr>
					
						<th><g:message code="consumer.user.label" default="User" /></th>
					
						<th><g:message code="consumer.driveway.label" default="Driveway" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${consumerInstanceList}" status="i" var="consumerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${consumerInstance.id}">${fieldValue(bean: consumerInstance, field: "user")}</g:link></td>
					
						<td>${fieldValue(bean: consumerInstance, field: "driveway")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${consumerInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
