

<%@ page import="com.dumplingjoy.pos.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'changePassword.label', default: 'User')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>Edit Password</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${changePasswordInstance}">
            <div class="errors">
                <g:renderErrors bean="${changePasswordInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" autocomplete="off">
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="oldPassword"><g:message code="changePassword.oldPassword.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: changePasswordInstance, field: 'oldPassword', 'errors')}">
                                	<g:passwordField name="oldPassword" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="newPassword"><g:message code="changePassword.newPassword.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: changePasswordInstance, field: 'newPassword', 'errors')}">
                                	<g:passwordField name="newPassword" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="retypePassword"><g:message code="changePassword.retypePassword.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: changePasswordInstance, field: 'retypePassword', 'errors')}">
                                	<g:passwordField name="retypePassword" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="updatePassword" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
		        	<span class="button"><input type="button" value="Cancel" class="cancel" onclick="window.location='${createLink(uri: "/")}'" /></span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("oldPassword")
        </g:javascript>
    </body>
</html>