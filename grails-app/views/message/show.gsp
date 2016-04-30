
<%@ page import="snowbrr.Message" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="message.show" default="Show Message" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="message.list" default="Message List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="message.new" default="New Message" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="message.show" default="Show Message" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:form>
                <g:hiddenField name="id" value="${messageInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>

                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="message.timestamp" default="Timestamp" />:</td>
                                
                                <td valign="top" class="value"><g:formatDate date="${messageInstance?.timestamp}" /></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="message.from" default="From" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="user" action="show" id="${messageInstance?.from?.id}">${messageInstance?.from?.username} ( ${messageInstance?.from?.firstname} ${messageInstance?.from?.lastname} ) </g:link></td>
                                
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="message.content" default="Content" />:</td>

                                <td valign="top" class="value">${fieldValue(bean: messageInstance, field: "content")}</td>

                            </tr>
                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'edit', 'default': 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'delete', 'default': 'Delete')}" onclick="return confirm('${message(code: 'delete.confirm', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
