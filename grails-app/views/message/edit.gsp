
<%@ page import="snowbrr.Message" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="message.edit" default="Edit Message" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="message.list" default="Message List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="message.new" default="New Message" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="message.edit" default="Edit Message" /></h1>

            <g:hasErrors bean="${messageInstance}">
            <div class="errors">
                <g:renderErrors bean="${messageInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${messageInstance?.id}" />
                <g:hiddenField name="version" value="${messageInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="timestamp"><g:message code="message.timestamp" default="Timestamp" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: messageInstance, field: 'timestamp', 'errors')}">
                                    <g:datePicker name="timestamp" value="${messageInstance?.timestamp}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="from"><g:message code="message.from" default="From" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: messageInstance, field: 'from', 'errors')}">
                                    <g:select name="from.id" from="${snowbrr.User.list()}" optionKey="id" value="${messageInstance?.from?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="content"><g:message code="message.content" default="Content" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: messageInstance, field: 'content', 'errors')}">
                                    <g:textField name="content" value="${fieldValue(bean: messageInstance, field: 'content')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="read"><g:message code="message.read" default="Read" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: messageInstance, field: 'read', 'errors')}">
                                    <g:checkBox name="read" value="${messageInstance?.read}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="user"><g:message code="message.user" default="User" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: messageInstance, field: 'user', 'errors')}">
                                    <g:select name="user.id" from="${snowbrr.User.list()}" optionKey="id" value="${messageInstance?.user?.id}"  />

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'update', 'default': 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'delete', 'default': 'Delete')}" onclick="return confirm('${message(code: 'delete.confirm', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
